package io.zipcoder.crudapp.core.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.zipcoder.crudapp.core.exception.FormatterUtilException;

public class FormatterUtil {

    public static <E> String writeEntityAsJsonString(E entity) {
        try {
            return new ObjectMapper().writeValueAsString(entity);
        } catch (JsonProcessingException e) {
            throw new FormatterUtilException("Error when writing entity as JSON String: " + e.getMessage());
        }
    }

}
