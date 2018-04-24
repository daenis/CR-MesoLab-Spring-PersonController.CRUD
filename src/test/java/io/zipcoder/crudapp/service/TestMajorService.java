package io.zipcoder.crudapp.service;

import io.zipcoder.crudapp.model.Major;
import io.zipcoder.crudapp.repository.MajorRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import util.BaseTestService;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.doNothing;

@RunWith(MockitoJUnitRunner.class)
public class TestMajorService extends BaseTestService<Major> {

    @Mock
    private MajorRepository majorRepository;

    private MajorService majorService;

    @Before
    public void init() {
        entity = new Major();
        majorService = new MajorService(majorRepository);
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
    public void testFindAllMajors() {
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

    @Test
    public void testDeleteMajorById() {
        doNothing()
                .when(majorRepository)
                .delete(entityId);

        majorService.deleteMajorById(entityId);

        verify(majorRepository, times(1))
                .delete(entityId);
    }

}