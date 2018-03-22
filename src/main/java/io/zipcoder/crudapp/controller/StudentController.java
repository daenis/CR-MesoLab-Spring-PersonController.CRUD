package io.zipcoder.crudapp.controller;

import io.zipcoder.crudapp.model.Student;
import io.zipcoder.crudapp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(value = "/api/people")
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> createPerson(@RequestBody Student student) {
        Student returnStudent = studentService.createPerson(student);
        return new ResponseEntity<>(returnStudent, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Student>> findAllPeople() {
        Collection<Student> people = studentService.findPeople();
        return new ResponseEntity<>(people, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Student> findPersonById(@PathVariable("id") int id) {
        Student student = studentService.findPersonById(id);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Student> updatePersonById(@RequestBody Student student, @PathVariable("id") int id) {
        Student returnStudent = studentService.updatePersonById(student, id);
        return new ResponseEntity<>(returnStudent, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePersonById(@PathVariable("id") int id) {
        studentService.deletePersonById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
