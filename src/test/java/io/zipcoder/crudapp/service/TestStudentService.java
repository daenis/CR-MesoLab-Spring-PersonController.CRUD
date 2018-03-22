package io.zipcoder.crudapp.service;

import io.zipcoder.crudapp.model.Major;
import io.zipcoder.crudapp.model.Student;
import io.zipcoder.crudapp.repository.StudentRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import util.BaseTestService;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TestStudentService extends BaseTestService<Student> {

    @Mock
    private static StudentRepository studentRepository;

    @InjectMocks
    private static StudentService studentService = new StudentService(studentRepository);

    @Before
    public void init() {
        entity = new Student();
        initDependentVariables();
    }

    @Test
    public void testCreateStudent() {
        when(studentRepository.save(entity))
                .thenReturn(entity);
        returnedEntity = studentService.createStudent(entity);
        Assert.assertEquals(entityNotReturnedMessage, entity, returnedEntity);
    }

    @Test
    public void testFindAllStudents() {
        when(studentRepository.findAll())
                .thenReturn(entityCollection);
        returnedEntityCollection = studentService.findAllStudents();
        Assert.assertEquals(entityNotReturnedMessage, entityCollection, returnedEntityCollection);
    }

    @Test
    public void testFindStudentById() {
        when(studentRepository.findOne(entityId))
                .thenReturn(entity);
        returnedEntity = studentService.findStudentById(entityId);
        Assert.assertEquals(entityNotReturnedMessage, entity, returnedEntity);
    }

    @Test
    public void testUpdateStudentById() {
        when(studentRepository.save(entity))
                .thenReturn(entity);
        returnedEntity = studentService.updateStudentById(entityId, entity);
        Assert.assertEquals(entityNotReturnedMessage, entity, returnedEntity);
    }

}
