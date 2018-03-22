package io.zipcoder.crudapp.service;

import io.zipcoder.crudapp.model.Major;
import io.zipcoder.crudapp.repository.MajorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Service classes are where the business logic lives.

 * The controller is used to accept the HTTP request and make requests to the server

 * The repository is used to retrieve data from the data store.

 * The service handles all business logic (setting special variables, executing calculations, etc). If anything,
   it can be thought of as the middleman.

 * @Service is used to register the class as a Service bean
 * */
@Service
public class MajorService {

    private MajorRepository majorRepository;

    @Autowired
    public MajorService(MajorRepository majorRepository) {
        this.majorRepository = majorRepository;
    }

    public Major createMajor(Major major) {
        return majorRepository.save(major);
    }

    public Collection<Major> findAllMajors() {
        return majorRepository.findAll();
    }

    public Major findMajorById(int id) {
        return majorRepository.findOne(id);
    }

    /**
     * majorRepositorysave(major) will do one of two things:
        - If the major already exists, it will updated it. It knows this by checking the id property
        - If the id property is null, the major does not exist, and a new Major is created in the data store
     * */
    public Major updateMajorById(int id, Major major) {
        major.setId(id);
        return majorRepository.save(major);
    }

    public void deleteMajorById(int id) {
        majorRepository.delete(id);
    }

}
