package io.zipcoder.crudapp.service;

import io.zipcoder.crudapp.model.Person;
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
public class TestPersonService extends BaseTestService<Person> {

    @Mock
    private static PersonRepository personRepository;

    @InjectMocks
    private static PersonService personService = new PersonService();

    @Before
    public void init() {
        entity = new Person();
        initDependentVariables();
    }

    @Test
    public void testFindPersonById() {
        when(personRepository.findOne(entityId))
                .thenReturn(entity);
        returnedEntity = personService.findPersonById(entityId);
        Assert.assertEquals(entityNotReturnedMessage, entity, returnedEntity);
    }

    @Test
    public void testFindPeople() {
        when(personRepository.findAll())
                .thenReturn(entityCollection);
        returnedEntityCollection = personService.findPeople();
        Assert.assertEquals(entityNotReturnedMessage, entityCollection, returnedEntityCollection);
    }

}
