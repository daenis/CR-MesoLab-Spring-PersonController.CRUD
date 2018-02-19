package io.zipcoder.crudapp.service;

import io.zipcoder.crudapp.model.Student;
import io.zipcoder.crudapp.repository.PersonRepository;
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
    private static PersonRepository personRepository;

    @InjectMocks
    private static PersonService personService = new PersonService();

    @Before
    public void init() {
        entity = new Student();
        initDependentVariables();
    }

    @Test
    public void testCreatePerson() {
        when(personRepository.save(entity))
                .thenReturn(entity);
        returnedEntity = personService.createPerson(entity);
        Assert.assertEquals(entityNotReturnedMessage, entity, returnedEntity);
    }

    @Test
    public void testFindPeople() {
        when(personRepository.findAll())
                .thenReturn(entityCollection);
        returnedEntityCollection = personService.findPeople();
        Assert.assertEquals(entityNotReturnedMessage, entityCollection, returnedEntityCollection);
    }

    @Test
    public void testFindPersonById() {
        when(personRepository.findOne(entityId))
                .thenReturn(entity);
        returnedEntity = personService.findPersonById(entityId);
        Assert.assertEquals(entityNotReturnedMessage, entity, returnedEntity);
    }

    @Test
    public void testUpdatePerson() {
        when(personRepository.save(entity))
                .thenReturn(entity);
        returnedEntity = personService.updatePersonById(entity, entityId);
        Assert.assertEquals(entityNotReturnedMessage, entity, returnedEntity);
    }

}
