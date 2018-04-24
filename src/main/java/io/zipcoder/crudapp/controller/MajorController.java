package io.zipcoder.crudapp.controller;

import io.zipcoder.crudapp.model.Major;
import io.zipcoder.crudapp.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * The Controller is responsible for handling HTTP calls.

 * A Controller holds endpoints, which are associated with methods. When a client (Angular app, Postman, CURL, etc)
   submits an HTTP request to the server url (ex: http://localhost:8080), the application is able to register specific
   endpoints. For example, by submitting a GET request to (http://localhost:8080/api/majors), one will execute the
   findAllMajors() method.

 * In addition to accepting the HTTP request, a controller will typically return some type of data. However, it is not
   the controller's job to retrieve said data; the controller simply requests the data via a service method. The
   responsibilities are decoupled; the controller listens for a request, calls to the service and returns the result
   as a response entity. It's up to the service to execute any business logic. The controller isn't concerned with
   how the service retrieves the information; only that the data is retrieved.

 * All rest controllers must be annotated with @RestController

 * The @RequestMapping(value = "/api/majors") defines a base URL at the class level; every method's associated endpoint
   URL will have this prefixed. (ex: "/{id}" endpoint -> "/api/majors/{id}"

 * */

@RestController
@RequestMapping(value = "/api/majors")
public class MajorController {

    /**
     * Service is declared as a private, final field
     * */
    private final MajorService majorService;

    /**
     * @Autowired is how the service bean is wired into this class. This will give the Spring Container control of
     * bean instantiation. We are INJECTING the DEPENDENCY via the constructor (research construction injection and
     * field injection, this is an example of constructor injection)
     * */
    @Autowired
    public MajorController(MajorService majorService) {
        this.majorService = majorService;
    }

    /**
     * POST - Accepts a JSON object and creates a major in our system.

     * @RequestBody is used to tell Spring that we will be accepting a JSON representation of the following class,
       which is Major in this class (note that the ID, or Primary Key, is not included in POST requests. We rely
       on Spring to generate this for us. It preserves data integrity. See the model in order to know what fields
       are included in the JSON representation.

       Ex JSON:
       {
        "majorName": "Computer Science"
        }

     * The createMajor(major) is what saves the entity to the repository. This will return the entity holding
       the provided data, along with an automatically generated ID.

     * ResponseEntity is a wrapper class that will send an HTTP code along with the JSON representation of the
       response. HttpStatus.CREATED is a constant, and will return the code 201.

     * */
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Major> createMajor(@RequestBody Major major) {
        Major returnMajor = majorService.createMajor(major);
        return new ResponseEntity<>(returnMajor, HttpStatus.CREATED);
    }

    /**
     * GET Request to retrieve all majors from the data store.
     * */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Major>> findAllMajors() {
        Collection<Major> majors = majorService.findAllMajors();
        return new ResponseEntity<>(majors, HttpStatus.OK);
    }

    /**
     * GET Request to retrieve a specific major from the data store. This will grab the majorId from the URL endpoint
       and return the major in the data store with said ID.
     * */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Major> findMajorById(@PathVariable("id") int id) {
        Major major = majorService.findMajorById(id);
        return new ResponseEntity<>(major, HttpStatus.OK);
    }

    /**
     * PUT Request to update a specific major. We know what major to update based on the ID in the URL Endpoint, and we
       update the data with the information provided in the JSON representation. Again, the ID is NOT included in the
       JSON parameter. Instead, it is retrieved from the URL.
     * */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Major> updateMajorById(@PathVariable("id") int id, @RequestBody Major major) {
        Major returnMajor = majorService.updateMajorById(major, id);
        return new ResponseEntity<>(returnMajor, HttpStatus.OK);
    }

    /**
     * DELETE Request for the major with the ID provided in the endpoint URL. Note that this will only return a response
       code.
     * */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteMajorById(@PathVariable("id") int id) {
        majorService.deleteMajorById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
