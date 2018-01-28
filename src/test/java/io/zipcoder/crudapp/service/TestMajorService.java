package io.zipcoder.crudapp.service;

import io.zipcoder.crudapp.model.Major;
import io.zipcoder.crudapp.repository.MajorRepository;
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
public class TestMajorService extends BaseTestService<Major> {

    @Mock
    private static MajorRepository majorRepository;

    @InjectMocks
    private static MajorService majorService = new MajorService();

    @Before
    public void init() {
        entity = new Major();
        initDependentVariables();
    }

    @Test
    public void testCreateMajor() {
        when(majorRepository.save(entity))
                .thenReturn(entity);
        returnedEntity = majorService.createMajor(entity);
        Assert.assertEquals(entityNotReturnedMessage, entity, returnedEntity);
    }

    @Test
    public void testFindPeople() {
        when(majorRepository.findAll())
                .thenReturn(entityCollection);
        returnedEntityCollection = majorService.findAllMajors();
        Assert.assertEquals(entityNotReturnedMessage, entityCollection, returnedEntityCollection);
    }

    @Test
    public void testFindMajorById() {
        when(majorRepository.findOne(entityId))
                .thenReturn(entity);
        returnedEntity = majorService.findMajorById(entityId);
        Assert.assertEquals(entityNotReturnedMessage, entity, returnedEntity);
    }

    @Test
    public void testUpdateMajor() {
        when(majorRepository.save(entity))
                .thenReturn(entity);
        returnedEntity = majorService.updateMajorById(entity, entityId);
        Assert.assertEquals(entityNotReturnedMessage, entity, returnedEntity);
    }

}

