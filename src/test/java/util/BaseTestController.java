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

    // Spring's Mock MVC, used for testing a Controller
    protected MockMvc mvc;

    // The base endpoint URL; this will typically be the URL that a POST is send to (eg. 'api/students' or
    // 'api/students/{studentId}/majors'.
    protected String baseEndpointUrl;

    // The ID endpoint URL; this will typically be an individual entity (eg. 'api/students/{studentId}' or
    // 'api/students/{studentId}/majors/{majorId}'
    protected String idEndpointUrl;

    // The error message to print when an entity is not returned in the expected state
    protected final String entityNotReturnedMessage = "The expected entity is not being returned";

    // A variable to hold a representation of the returned entity as a JSON string (since the MockMVC returns a string)
    protected String returnedEntity;

    // An example parent ID. The parent is typically the first entity in the URL
    protected int parentEntityId;

    // An example child ID. The child is typically the second entity in the URL
    protected int childEntityId;

    // The entity
    protected E entity;

    // A collection of the entity
    protected Collection<E> entityCollection;

    // A representation of the entity as a JSON string
    protected String entityAsJsonString;

    // A representation of the entity collection as a JSON string
    protected String entityCollectionAsJsonString;

    protected void initDependentVariables() {
        // Random parent entity ID assignment
        parentEntityId = 1;

        // Random child entity ID assignment
        childEntityId = 3;

        // ID endpoint is essentially the base endpoint with an ID place holder. Note that both the ID endpoint URL
        // and the base endpoint URL can be overridden in the child class as needed
        idEndpointUrl = baseEndpointUrl + "/{id}";

        // Write the entity as a JSON string
        entityAsJsonString = JsonTestUtil.writeEntityAsJsonString(entity);

        // Instantiate the entity collection as a singleton
        entityCollection = Collections.singleton(entity);

        // Write the entity collection as a JSON string
        entityCollectionAsJsonString = JsonTestUtil.writeEntityAsJsonString(entityCollection);
    }

    protected ResultActions mvcPerformPostWithNoPathVariables
            (String endpointUrl, String entityAsJsonString) throws Exception {
        return mvc.perform(post(endpointUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(entityAsJsonString))
                .andExpect(status().isCreated());
    }

    protected String mvcPerformGetWithNoPathVariables(String endpointUrl) throws Exception {
        return convertMvcResultToString(mvc.perform(get(endpointUrl)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()));
    }

    protected String mvcPerformGetWithOnePathVariable(String endpointUrl, Integer parentId) throws Exception {
        return convertMvcResultToString(mvc.perform(get(endpointUrl, parentId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()));
    }

    protected ResultActions mvcPerformUpdateWithOnePathVariable
            (String endpointUrl, Integer parentId, String entityAsJsonString) throws Exception {
        return mvc.perform(put(endpointUrl, parentId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(entityAsJsonString))
                .andExpect(status().isOk());
    }

    protected String mvcPerformDeleteWithOnePathVariable(String endpointUrl, Integer parentId)
            throws Exception {
        return convertMvcResultToString(mvc.perform(delete(endpointUrl, parentId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()));
    }

    private String convertMvcResultToString(ResultActions resultActions) throws UnsupportedEncodingException {
        return resultActions.andReturn().getResponse().getContentAsString();
    }

}
