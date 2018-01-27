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

    public Person createPerson(Person person) {
        return personRepository.save(person);
    }

    public Person findPersonById(int id) {
        return personRepository.findOne(id);
    }

    public Collection<Person> findPeople() {
        return personRepository.findAll();
    }

    public Person updatePersonById(Person person, int id) {
        person.setId(id);
        return personRepository.save(person);
    }

    public void deletePersonById(int id) {
        personRepository.delete(id);
    }

}
