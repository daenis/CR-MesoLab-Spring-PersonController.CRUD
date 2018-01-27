package io.zipcoder.crudapp.service;

import io.zipcoder.crudapp.model.Major;
import io.zipcoder.crudapp.repository.MajorRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

public class MajorService {

    @Autowired
    private MajorRepository majorRepository;

    public Major createMajor(Major major) {
        return majorRepository.save(major);
    }

    public Collection<Major> findAllMajors() {
        return majorRepository.findAll();
    }

    public Major findMajorById(int id) {
        return majorRepository.findOne(id);
    }

    public Major updateMajorById(Major major, int id) {
        major.setId(id);
        return majorRepository.save(major);
    }

    public void deleteMajorById(int id) {
        majorRepository.delete(id);
    }

}
