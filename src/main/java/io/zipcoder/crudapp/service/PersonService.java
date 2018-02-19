package io.zipcoder.crudapp.service;

import io.zipcoder.crudapp.model.Student;
import io.zipcoder.crudapp.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private MajorService majorService;

    public Student createPerson(Student student) {
        student.setMajor(majorService.findMajorById(student.getMajorId()));
        return personRepository.save(student);
    }

    public Collection<Student> findPeople() {
        return personRepository.findAll();
    }

    public Student findPersonById(int id) {
        return personRepository.findOne(id);
    }

    public Student updatePersonById(Student student, int id) {
        student.setId(id);
        return personRepository.save(student);
    }

    public void deletePersonById(int id) {
        personRepository.delete(id);
    }

}
