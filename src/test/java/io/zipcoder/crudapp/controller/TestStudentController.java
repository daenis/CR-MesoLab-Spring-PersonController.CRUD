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
        baseEndpointUrl = "/api/students";
        entity = new Student();
        initDependentVariables();
    }

    @Test
    public void testCreateStudent() throws Exception {
        when(studentService.createStudent(entity))
                .thenReturn(entity);
        mvcPerformPostWithNoPathVariables(baseEndpointUrl, entityAsJsonString);
    }

    @Test
    public void testFindAllStudents() throws Exception {
        when(studentService.findAllStudents())
                .thenReturn(entityCollection);
        returnedEntity = mvcPerformGetWithNoPathVariables(baseEndpointUrl);
        Assert.assertEquals(entityNotReturnedMessage, entityCollectionAsJsonString, returnedEntity);
    }

    @Test
    public void testFindStudentById() throws Exception {
        when(studentService.findStudentById(childEntityId))
                .thenReturn(entity);
        returnedEntity = mvcPerformGetWithOnePathVariable(idEndpointUrl, childEntityId);
        Assert.assertEquals(entityNotReturnedMessage, entityAsJsonString, returnedEntity);
    }

    @Test
    public void testUpdateStudentById() throws Exception {
        when(studentService.updateStudentById(childEntityId, entity))
                .thenReturn(entity);
        mvcPerformUpdateWithOnePathVariable(idEndpointUrl, childEntityId, entityAsJsonString);
    }

    @Test
    public void testDeleteStudentById() throws Exception {
        when(studentService.findStudentById(childEntityId))
                .thenReturn(entity);
        returnedEntity = mvcPerformDeleteWithOnePathVariable(idEndpointUrl, childEntityId);
    }

}
