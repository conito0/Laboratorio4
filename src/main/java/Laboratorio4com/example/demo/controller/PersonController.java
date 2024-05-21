package Laboratorio4com.example.demo.controller;

import Laboratorio4com.example.demo.entities.Person;
import Laboratorio4com.example.demo.service.PersonService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/person")
@RestController
@Controller
public class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;

    }

    @GetMapping("/{id}")
    public Person getById(@PathVariable("id") Long id) {
        return this.personService.findById(id);
    }

    @GetMapping
    public List<Person> getAll() {
        return this.personService.findAll();
    }

    @PostMapping
    public Person post(@RequestBody Person person) {
        return this.personService.save(person);
    }

}
