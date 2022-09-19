package com.axonactive.agileterm.utility;

import com.axonactive.agileterm.exception.InputValidationException;
import org.apache.poi.util.IOUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import javax.ws.rs.core.MultivaluedMap;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Objects;



public class InputStreamUtils {

    private InputStreamUtils() {
    }

    public static InputPart getInputPart(MultipartFormDataInput formDataInput) {
        Map<String, List<InputPart>> uploadForm = formDataInput.getFormDataMap();
        List<InputPart> inputParts = uploadForm.get("file");
        if (Objects.isNull(inputParts)) {
            throw new InputValidationException(ExcelUtils.FILE_WRONG_FORMAT_EXCEPTION);
        }
        return inputParts.get(0);
    }

    public static byte[] getByteArray(InputPart inputPartUploadedFile){
        byte[] bytes;
        try (InputStream inputStream = getInputStream(inputPartUploadedFile)) {
            bytes = IOUtils.toByteArray(inputStream);
        }catch (IOException e) {
            throw new InputValidationException(ExcelUtils.FILE_WRONG_FORMAT_EXCEPTION);
        }
        return bytes;
    }

    public static String getFileName(InputPart inputPart) {
        MultivaluedMap<String, String> header = inputPart.getHeaders();
        String[] contentDisposition = header.getFirst("Content-Disposition").split(";");
        for (String filename : contentDisposition) {
            if ((filename.trim().startsWith("filename"))) {
                String[] name = filename.split("=");
                String finalFileName = name[1].trim().replace("\"", "");
                return new String(finalFileName.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            }
        }
        return "unknown";
    }

    public static InputStream getInputStream(InputPart inputPart) throws IOException {
        return inputPart.getBody(InputStream.class, null);
    }


}

