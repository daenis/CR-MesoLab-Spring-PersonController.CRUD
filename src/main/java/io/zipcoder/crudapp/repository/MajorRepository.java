package io.zipcoder.crudapp.repository;

import io.zipcoder.crudapp.model.Major;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface MajorRepository extends CrudRepository<Major, Integer> {

    Collection<Major> findAll();

}
