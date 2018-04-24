package io.zipcoder.crudapp.controller;

import io.zipcoder.crudapp.model.Major;
import io.zipcoder.crudapp.service.MajorService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import util.BaseTestController;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class TestMajorController extends BaseTestController<Major> {

    @MockBean
    private MajorService majorService;

    @InjectMocks
    private MajorController majorController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(majorController).build();
        baseEndpointUrl = "/api/majors";
        entity = new Major();
        initDependentVariables();
    }

    @Test
    public void testCreateMajor() throws Exception {
        when(majorService.createMajor(entity))
                .thenReturn(entity);
        mvcPerformPostWithNoPathVariables(baseEndpointUrl, entityAsJsonString);
    }

    @Test
    public void testFindPeople() throws Exception {
        when(majorService.findAllMajors())
                .thenReturn(entityCollection);
        returnedEntity = mvcPerformGetWithNoPathVariables(baseEndpointUrl);
        Assert.assertEquals(entityNotReturnedMessage, entityCollectionAsJsonString, returnedEntity);
    }

    @Test
    public void testFindMajorById() throws Exception {
        when(majorService.findMajorById(entityId))
                .thenReturn(entity);
        returnedEntity = mvcPerformGetWithOnePathVariable(idEndpointUrl, entityId);
        Assert.assertEquals(entityNotReturnedMessage, entityAsJsonString, returnedEntity);
    }

    @Test
    public void testUpdateMajor() throws Exception {
        when(majorService.updateMajorById(entity, entityId))
                .thenReturn(entity);
        mvcPerformUpdateWithOnePathVariable(idEndpointUrl, entityId, entityAsJsonString);
    }

    @Test
    public void testDeleteMajorById() throws Exception {
        when(majorService.findMajorById(entityId))
                .thenReturn(entity);
        returnedEntity = mvcPerformDeleteWithOnePathVariable(idEndpointUrl, entityId);
    }
    
}
