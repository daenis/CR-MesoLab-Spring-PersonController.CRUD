package io.zipcoder.crudapp.repository;

import io.zipcoder.crudapp.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface PersonRepository extends CrudRepository<Student, Integer> {

    Collection<Student> findAll();

}
