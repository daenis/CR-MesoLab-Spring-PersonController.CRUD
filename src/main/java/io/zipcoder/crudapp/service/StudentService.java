package io.zipcoder.crudapp.service;

import io.zipcoder.crudapp.model.Student;
import io.zipcoder.crudapp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private MajorService majorService;

    public Student createPerson(Student student) {
        student.setMajor(majorService.findMajorById(student.getMajorId()));
        return studentRepository.save(student);
    }

    public Collection<Student> findPeople() {
        return studentRepository.findAll();
    }

    public Student findPersonById(int id) {
        return studentRepository.findOne(id);
    }

    public Student updatePersonById(Student student, int id) {
        student.setId(id);
        return studentRepository.save(student);
    }

    public void deletePersonById(int id) {
        studentRepository.delete(id);
    }

}
