package io.zipcoder.crudapp.controller;

import io.zipcoder.crudapp.model.Student;
import io.zipcoder.crudapp.service.StudentService;
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
public class TestStudentController extends BaseTestController<Student> {

    @MockBean
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(studentController).build();
        baseEndpointUrl = "/api/people";
        entity = new Student();
        initDependentVariables();
    }

    @Test
    public void testCreatePerson() throws Exception {
        when(studentService.createPerson(entity))
                .thenReturn(entity);
        mvcPerformPostWithNoPathVariables(baseEndpointUrl, entityAsJsonString);
    }

    @Test
    public void testFindPeople() throws Exception {
        when(studentService.findPeople())
                .thenReturn(entityCollection);
        returnedEntity = mvcPerformGetWithNoPathVariables(baseEndpointUrl);
        Assert.assertEquals(entityNotReturnedMessage, entityCollectionAsJsonString, returnedEntity);
    }

    @Test
    public void testFindPersonById() throws Exception {
        when(studentService.findPersonById(childEntityId))
                .thenReturn(entity);
        returnedEntity = mvcPerformGetWithOnePathVariable(idEndpointUrl, childEntityId);
        Assert.assertEquals(entityNotReturnedMessage, entityAsJsonString, returnedEntity);
    }

    @Test
    public void testUpdatePerson() throws Exception {
        when(studentService.updatePersonById(entity, childEntityId))
                .thenReturn(entity);
        mvcPerformUpdateWithOnePathVariable(idEndpointUrl, childEntityId, entityAsJsonString);
    }

    @Test
    public void testDeletePersonById() throws Exception {
        when(studentService.findPersonById(childEntityId))
                .thenReturn(entity);
        returnedEntity = mvcPerformDeleteWithOnePathVariable(idEndpointUrl, childEntityId);
    }

}
