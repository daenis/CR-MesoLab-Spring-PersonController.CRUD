package io.zipcoder.crudapp.service;

import io.zipcoder.crudapp.model.Student;
import io.zipcoder.crudapp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository =studentRepository;
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Collection<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    public Student findStudentById(int id) {
        return studentRepository.findOne(id);
    }

    public Student updateStudentById(int id, Student student) {
        student.setId(id);
        return studentRepository.save(student);
    }

    public void deleteStudentById(int id) {
        studentRepository.delete(id);
    }

}
