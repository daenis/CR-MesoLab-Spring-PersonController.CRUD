package io.zipcoder.crudapp.service;

import io.zipcoder.crudapp.model.Person;
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

    public Person createPerson(Person person) {
        person.setMajor(majorService.findMajorById(person.getMajorId()));
        return personRepository.save(person);
    }

    public Collection<Person> findPeople() {
        return personRepository.findAll();
    }

    public Person findPersonById(int id) {
        return personRepository.findOne(id);
    }

    public Person updatePersonById(Person person, int id) {
        person.setId(id);
        return personRepository.save(person);
    }

    public void deletePersonById(int id) {
        personRepository.delete(id);
    }

}
