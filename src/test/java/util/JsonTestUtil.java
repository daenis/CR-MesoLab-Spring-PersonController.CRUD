package util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonTestUtil {

    public static <E> String writeEntityAsJsonString(E entity) {
        try {
            return new ObjectMapper().writeValueAsString(entity);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
            return "JsonProcessingException";
        }
    }

}
