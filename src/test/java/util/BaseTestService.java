package util;

import org.springframework.data.domain.Sort;

import java.util.Collection;
import java.util.Collections;

public abstract class BaseTestService<E> {

    public String entityNotReturnedMessage = "The expected entity is not being returned";
    public String entityNotDeletedMessage = "The entity is not being deleted";
    public E entity;
    public Integer entityId;
    public Integer parentEntityId;
    public Collection<E> entityCollection;
    public E returnedEntity;
    public Collection<E> returnedEntityCollection;
    public Sort sort;


    public void initDependentVariables() {
        entityCollection = Collections.singleton(entity);
        entityId = 10;
        parentEntityId = 20;
    }

}
