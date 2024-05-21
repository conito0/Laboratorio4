package Laboratorio4com.example.demo.service;

import Laboratorio4com.example.demo.entities.Person;
import Laboratorio4com.example.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person findById(Long id) {
        return this.personRepository.findById(id).get();
    }

    public List<Person> findAll() {
        return this.personRepository.findAll();
    }

    public Person save(Person person) {
        return this.personRepository.save(person);
    }

}
