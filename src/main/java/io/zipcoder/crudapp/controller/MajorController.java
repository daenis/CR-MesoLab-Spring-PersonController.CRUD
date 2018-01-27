package io.zipcoder.crudapp.controller;

import io.zipcoder.crudapp.model.Major;
import io.zipcoder.crudapp.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(value = "/api/people")
public class MajorController {

    @Autowired
    private MajorService majorService;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Major> createMajor(@RequestBody Major Major) {
        Major returnMajor = majorService.createMajor(Major);
        return new ResponseEntity<>(returnMajor, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Major>> findAllPeople() {
        Collection<Major> people = majorService.findAllMajors();
        return new ResponseEntity<>(people, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Major> findMajorById(@PathVariable("id") int id) {
        Major Major = majorService.findMajorById(id);
        return new ResponseEntity<>(Major, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Major> updateMajorById(@RequestBody Major Major, @PathVariable("id") int id) {
        Major returnMajor = majorService.updateMajorById(Major, id);
        return new ResponseEntity<>(returnMajor, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteMajorById(@PathVariable("id") int id) {
        majorService.deleteMajorById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
