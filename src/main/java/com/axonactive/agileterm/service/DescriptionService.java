package com.axonactive.agileterm.service;

import com.axonactive.agileterm.dao.DescriptionDAO;
import com.axonactive.agileterm.entity.DescriptionEntity;
import com.axonactive.agileterm.rest.client.model.Description;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class DescriptionService {
    @Inject
    private DescriptionDAO descriptionDAO;

    @Inject
    private UserService userService;

    public boolean descriptionListValidation(List<Description> descriptionList){
        return descriptionList != null &&
                descriptionList.isEmpty();
    }


    public DescriptionEntity convertDescriptionToDescriptionEntity(Description description) {
        DescriptionEntity descriptionEntity = new DescriptionEntity();
        descriptionEntity.setUserEntity(userService.findUserEntityByUserName(description.getUserName()));
        descriptionEntity.setContent(description.getContent());
        return descriptionEntity;
    }

    public List<DescriptionEntity> convertListOfDescriptionToDescriptionEntity(List<Description> descriptionList) {
        List<DescriptionEntity> descriptionEntityList = new ArrayList<>();
        for (Description inputDescription : descriptionList
        ) {
            descriptionEntityList.add(convertDescriptionToDescriptionEntity(inputDescription));
        }
        return descriptionEntityList;
    }

    public DescriptionEntity save(Integer termId,Description descriptionInput){
        return descriptionDAO.save(termId, descriptionInput);
    }

}

