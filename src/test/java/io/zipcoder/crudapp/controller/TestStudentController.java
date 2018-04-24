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

// Extend the BaseTestController with the entity type as the generic
@RunWith(SpringRunner.class)
public class TestStudentController extends BaseTestController<Student> {

    // Declare the service as a mock bean
    @MockBean
    private StudentService studentService;

    // Inject the mocked service into the controller
    @InjectMocks
    private StudentController studentController;

    @Before
    public void init() {
        // Initialize the mocks
        MockitoAnnotations.initMocks(this);
        // Build the Mock MVC
        mvc = MockMvcBuilders.standaloneSetup(studentController).build();
        // Build the base endpoint URL
        baseEndpointUrl = "/api/students";
        // Declare the entity type
        entity = new Student();
        // Call the method from the BaseTestController for set up
        initDependentVariables();
    }

    @Test
    public void testCreateStudent() throws Exception {
        // Since we are testing the POST method, we will mock the service's create method
        when(studentService.createStudent(entity))
                .thenReturn(entity);
        // Perform a POST request with no path variables to the base endpoint url with the entity as a JSON string.
        // The assertion is made in this method, which can be found in the Base Test Controller
        mvcPerformPostWithNoPathVariables(baseEndpointUrl, entityAsJsonString);
    }

    @Test
    public void testFindAllStudents() throws Exception {
        // Since we are testing the GET ALL method, we will mock the service's find all method
        when(studentService.findAllStudents())
                .thenReturn(entityCollection);
        // Store the returned GET entity as a String; the HTTP Status Code assertion is made in this method (see
        // Base Test Controller)
        returnedEntity = mvcPerformGetWithNoPathVariables(baseEndpointUrl);
        // Assert that the entitiy collection JSON String representation was returned from the get all
        Assert.assertEquals(entityNotReturnedMessage, entityCollectionAsJsonString, returnedEntity);
    }

    @Test
    public void testFindStudentById() throws Exception {
        when(studentService.findStudentById(entityId))
                .thenReturn(entity);
        returnedEntity = mvcPerformGetWithOnePathVariable(idEndpointUrl, entityId);
        Assert.assertEquals(entityNotReturnedMessage, entityAsJsonString, returnedEntity);
    }

    @Test
    public void testUpdateStudentById() throws Exception {
        when(studentService.updateStudentById(entity, entityId))
                .thenReturn(entity);
        mvcPerformUpdateWithOnePathVariable(idEndpointUrl, entityId, entityAsJsonString);
    }

    @Test
    public void testDeleteStudentById() throws Exception {
        when(studentService.findStudentById(entityId))
                .thenReturn(entity);
        returnedEntity = mvcPerformDeleteWithOnePathVariable(idEndpointUrl, entityId);
    }

}
