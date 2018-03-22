package io.zipcoder.crudapp.repository;

import io.zipcoder.crudapp.model.Major;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Spring repository, used to access data from a data store (in this case an H2 memory store, most of the time
   a Database such as MySQL

 * Repositories are interfaces, and Spring is actually able to build queries from the method names.

 * Research Spring repositories to understand how these guys work
 * */

@Repository
public interface MajorRepository extends CrudRepository<Major, Integer> {

    Collection<Major> findAll();

}
