package com.axonactive.agileterm.service;

import com.axonactive.agileterm.dao.TermDAO;
import com.axonactive.agileterm.entity.DescriptionEntity;
import com.axonactive.agileterm.entity.TermEntity;
import com.axonactive.agileterm.entity.UserEntity;
import com.axonactive.agileterm.exception.ErrorMessage;
import com.axonactive.agileterm.exception.InputValidationException;
import com.axonactive.agileterm.exception.ResourceNotFoundException;
import com.axonactive.agileterm.rest.client.model.Term;
import com.axonactive.agileterm.rest.model.ResponseForUploadFile;
import com.axonactive.agileterm.service.dto.RowResultDto;
import com.axonactive.agileterm.service.dto.TermImportDto;
import com.axonactive.agileterm.utility.ExcelUtils;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

@Stateless
public class TermService {
    @Inject
    private TermDAO termDAO;

    @Inject
    private DescriptionService descriptionService;

    @Inject
    private UserService userService;

    private Integer getDecodedId(String encodedId) {
        if (encodedId.length() < 2) {
            throw new InputValidationException(ErrorMessage.INVALID_ID);
        }
        String decodedString = new String(Base64.getDecoder().decode(encodedId));
        System.out.println(decodedString);
        return Integer.parseInt(decodedString.substring(decodedString.lastIndexOf('_') + 1));
    }

    public List<TermEntity> getAll() {
        return termDAO.getAll();
    }

    public TermEntity findTermByTermId(Integer id) {
        TermEntity termEntity = termDAO.findTermById(id);
        if (termEntity == null) {
            throw new ResourceNotFoundException(ErrorMessage.TERM_NOT_FOUND);
        }
        return termEntity;
    }

    public TermEntity save(Term term) {
        TermEntity termEntity = new TermEntity();
        termEntity.setName(term.getName());
        if (descriptionService.descriptionListValidation(term.getDescriptionList()))
            termEntity.setDescriptionEntityList(
                    descriptionService
                            .convertListOfDescriptionToDescriptionEntity(
                                    term.getDescriptionList()));

        return termDAO.save(termEntity);
    }


    public TermEntity update(String encodeId, Term term) {
        Integer id = getDecodedId(encodeId);

        TermEntity termEntity = termDAO.findTermById(id);
        termEntity.setName(termEntity.getName());
        if (descriptionService.descriptionListValidation(term.getDescriptionList()))
            termEntity.setDescriptionEntityList(
                    descriptionService
                            .convertListOfDescriptionToDescriptionEntity(
                                    term.getDescriptionList()));
        return termDAO.save(termEntity);
    }


    public ResponseForUploadFile uploadTermAndDescriptionExcelFile(MultipartFormDataInput excelFile) throws IOException {


        List<TermImportDto> uploadedRawDataTermAndDescriptionList = ExcelUtils.getListOfTermAndDescriptionImportDto(excelFile);

        RowResultDto rowResult = getRowResultFromRawDataList(uploadedRawDataTermAndDescriptionList);

        Map<String, Integer> successfullySavedMap = saveAllListOfValidRows(rowResult);

        return new ResponseForUploadFile(rowResult.getInvalidRows(), successfullySavedMap, getStopCountingRow(uploadedRawDataTermAndDescriptionList));
    }

    public RowResultDto getRowResultFromRawDataList(List<TermImportDto> rawDataList) {
        RowResultDto rowResultDto = new RowResultDto();
        List<TermImportDto> syntaxValidImportTermList = new ArrayList<>();
        for (TermImportDto importingTerm : rawDataList
        ) {
            String importingTermString = importingTerm.getTermString();
            int importingTermRowIndex = importingTerm.getRowIndex();
            if (isStringEmpty(importingTermString)) {
                handleNullTermString(rowResultDto, importingTermRowIndex);
            } else if (isTermStringLengthInvalid(importingTermString)) {
                handleTermLengthInvalid(rowResultDto, importingTermRowIndex);
            } else if (isDescriptionLengthInvalid(importingTerm.getDescriptionString())) {
                handleDescriptionLengthInvalid(rowResultDto, importingTermRowIndex);
            } else if (isRowDuplicated(rowResultDto, importingTerm, syntaxValidImportTermList)) {
                handleDuplicatedRow(rowResultDto, importingTerm);
            } else if (isTermNotExistedInDatabase(importingTermString)) {
                rowResultDto.getValidRowNotExistedTermInDataBase().add(importingTerm);

            } else if (isDescriptionExistedInDataBase(importingTermString, importingTerm.getDescriptionString())) {
                handleRowExistedInDatabase(rowResultDto, importingTerm, syntaxValidImportTermList);

            } else {
                rowResultDto.getValidRowExistedTermInDataBase().add(importingTerm);
                syntaxValidImportTermList.add(importingTerm);

            }
        }
        return rowResultDto;
    }

    public Map<String, Integer> saveAllListOfValidRows(RowResultDto rowResult) {
        Map<String, List<String>> existedTermWithNewDescriptionToBeSave = ExcelUtils.getTermAndDefinitionMap(rowResult.getValidRowExistedTermInDataBase());
        Map<String, List<String>> nonExistedTermToBeSave = ExcelUtils.getTermAndDefinitionMap(rowResult.getValidRowNotExistedTermInDataBase());

        Map<String, Integer> successfullySavedMap = new HashMap<>();


        for (Map.Entry<String, List<String>> entry : existedTermWithNewDescriptionToBeSave.entrySet()) {
            List<DescriptionEntity> descriptionList = saveNewDescriptionWithExistedTerm(entry.getKey(), entry.getValue());
            successfullySavedMap.put(entry.getKey(), descriptionList.size());
        }

        for (Map.Entry<String, List<String>> entry : nonExistedTermToBeSave.entrySet()) {
            TermEntity savedTerm = saveNewTermNewDescription(entry.getKey(), entry.getValue());
            successfullySavedMap.put(savedTerm.getName(), savedTerm.getDescriptionEntityList().size());
        }

        return successfullySavedMap;
    }

    public TermEntity saveNewTermNewDescription(String termName, List<String> descriptionContentList) {
        UserEntity admin = userService.findUserEntityByUserName("admin");

        List<DescriptionEntity> descriptionList = new ArrayList<>();

        TermEntity newTerm = new TermEntity();


        for (String descriptionContent : descriptionContentList
        ) {
            if (!isStringEmpty(descriptionContent)) {
                descriptionList.add(new DescriptionEntity(null, descriptionContent, LocalDate.now(), newTerm, admin, 0));
            }
        }

        newTerm.setName(termName);

        newTerm.setDescriptionEntityList(descriptionList);

        return termDAO.save(newTerm);
    }

    public List<DescriptionEntity> saveNewDescriptionWithExistedTerm(String termName, List<String> descriptionContentList) {
        UserEntity admin = userService.findUserEntityByUserName("admin");
        List<DescriptionEntity> descriptionList = new ArrayList<>();
        TermEntity term = findTermByTermName(termName);

        for (String descriptionContent : descriptionContentList
        ) {
            if (!isStringEmpty(descriptionContent))
                descriptionList.add(new DescriptionEntity(null, descriptionContent, LocalDate.now(), term, admin, 0));
        }
        return descriptionService.saveAll(descriptionList);
    }

    public TermEntity findTermByTermName(String termName) {
        return termDAO.findTermByTermName(termName);
    }


    public void handleRowExistedInDatabase(RowResultDto rowResultDto, TermImportDto termImportDto, List<TermImportDto> validList) {
        rowResultDto.getInvalidRows().getExistedInTheDatabase().add(termImportDto.getRowIndex());
        validList.add(termImportDto);
    }

    public boolean isTermNotExistedInDatabase(String termName) {
        TermEntity term = findTermByTermName(termName);
        return term == null;

    }

    public boolean isDescriptionExistedInDataBase(String termName, String descriptionString) {
        if (isStringEmpty(descriptionString)) {
            return true;
        }
        List<DescriptionEntity> descriptionList = descriptionService.findDescriptionByTermNameAndDescriptionString(termName, descriptionString);

        return !descriptionList.isEmpty();
    }

    public void handleDuplicatedRow(RowResultDto rowResultDto, TermImportDto termImport) {
        rowResultDto.getInvalidRows().getDuplicatedDescriptionInFile().add(termImport.getRowIndex());
    }

    public boolean isRowDuplicated(RowResultDto rowResultDto, TermImportDto termImport, List<TermImportDto> syntaxValidList) {
        return rowResultDto.getValidRowExistedTermInDataBase().contains(termImport) || syntaxValidList.contains(termImport) || rowResultDto.getValidRowNotExistedTermInDataBase().contains(termImport);
    }

    public void handleDescriptionLengthInvalid(RowResultDto rowResultDto, int rowIndex) {
        rowResultDto.getInvalidRows().getInvalidDescriptionLength().add(rowIndex);
    }

    public boolean isDescriptionLengthInvalid(String descriptionString) {
        return descriptionString.length() > ExcelUtils.DESCRIPTION_MAXIMUM_LENGTH;
    }


    public void handleNullTermString(RowResultDto rowResultDto, int rowIndex) {
        rowResultDto.getInvalidRows().getTermIsNull().add(rowIndex);
    }

    public boolean isStringEmpty(String string) {
        return "".equalsIgnoreCase(string) || string.isBlank() || string.isEmpty();
    }

    public void handleTermLengthInvalid(RowResultDto rowResultDto, int rowIndex) {
        rowResultDto.getInvalidRows().getInvalidTermLength().add(rowIndex);
    }

    public boolean isTermStringLengthInvalid(String termImport) {
        return !(termImport.length() >= ExcelUtils.TERM_MINIMUM_LENGTH && termImport.length() <= ExcelUtils.TERM_TOPIC_MAXIMUM_LENGTH);
    }

    public int getStopCountingRow(List<TermImportDto> rawDataList) {
        return rawDataList != null && !rawDataList.isEmpty() ?
                rawDataList.get(rawDataList.size() - 1).getRowIndex() : 1;
    }

    public TermEntity findTermDetailById(String encodedId) {
        return findTermByTermId(getDecodedId(encodedId));
    }


}
