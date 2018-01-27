package util;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public abstract class BaseTestController<E> {

    public MockMvc mvc;
    public String baseEndpointUrl;
    public String idEndpointUrl;
    public String entityNotReturnedMessage = "The expected entity is not being returned";
    public String entityNotDeletedMessage = "The entity is not being deleted";
    public String predictedEntity;
    public String returnedEntity;
    public int parentEntityId;
    public int childEntityId;
    public E entity;
    public Collection<E> entityCollection = Collections.singleton(entity);
    public String entityAsJsonString;
    public String entityCollectionAsJsonString;

    public void initDependentVariables() {
        parentEntityId = 1;
        childEntityId = 3;
        idEndpointUrl = baseEndpointUrl + "/{id}";
        entityAsJsonString = JsonTestUtil.writeEntityAsJsonString(entity);
        entityCollection = Collections.singleton(entity);
        entityCollectionAsJsonString = JsonTestUtil.writeEntityAsJsonString(entityCollection);
    }

    public ResultActions mvcPerformPostWithNoPathVariables
            (String endpointUrl, String entityAsJsonString) throws Exception {
        return mvc.perform(post(endpointUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(entityAsJsonString))
                .andExpect(status().isCreated());
    }

    public ResultActions mvcPerformPostWithOnePathVariable
            (String endpointUrl, Integer parentId, String entityAsJsonString) throws Exception {
        return mvc.perform(post(endpointUrl, parentId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(entityAsJsonString))
                .andExpect(status().isCreated());
    }

    public String mvcPerformGetWithNoPathVariables(String endpointUrl) throws Exception {
        return convertMvcResultToString(mvc.perform(get(endpointUrl)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()));
    }

    public String mvcPerformGetWithOnePathVariable(String endpointUrl, Integer parentId) throws Exception {
        return convertMvcResultToString(mvc.perform(get(endpointUrl, parentId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()));
    }

    public String mvcPerformGetWithOnePathVariable(String endpointUrl, String parentId) throws Exception {
        return convertMvcResultToString(mvc.perform(get(endpointUrl, parentId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()));
    }

    public String mvcPerformGetWithTwoPathVariables(String endpointUrl, Integer parentId, Integer childId)
            throws Exception {
        return convertMvcResultToString(mvc.perform(get(endpointUrl, parentId, childId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()));
    }

    public ResultActions mvcPerformUpdateWithOnePathVariable
            (String endpointUrl, Integer parentId, String entityAsJsonString) throws Exception {
        return mvc.perform(put(endpointUrl, parentId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(entityAsJsonString))
                .andExpect(status().isOk());
    }

    public ResultActions mvcPerformUpdateWithTwoPathVariables
            (String endpointUrl, Integer parentId, Integer childId, String entityAsJsonString) throws Exception {
        return mvc.perform(put(endpointUrl, parentId, childId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(entityAsJsonString))
                .andExpect(status().isOk());
    }

    public String mvcPerformDeleteWithOnePathVariable(String endpointUrl, Integer parentId)
            throws Exception {
        return convertMvcResultToString(mvc.perform(delete(endpointUrl, parentId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()));
    }

    public String mvcPerformDeleteWithTwoPathVariables(String endpointUrl, Integer parentId, Integer childId)
            throws Exception {
        return convertMvcResultToString(mvc.perform(delete(endpointUrl, parentId, childId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()));
    }

    private String convertMvcResultToString(ResultActions resultActions) throws UnsupportedEncodingException {
        return resultActions.andReturn().getResponse().getContentAsString();
    }

}
