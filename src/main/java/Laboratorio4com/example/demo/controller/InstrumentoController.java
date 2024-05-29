package Laboratorio4com.example.demo.controller;

import Laboratorio4com.example.demo.entities.Instrumento;
import Laboratorio4com.example.demo.entities.Person;
import Laboratorio4com.example.demo.service.InstrumentoService;
import Laboratorio4com.example.demo.service.PersonService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/instrumento")
@RestController
@Controller
public class InstrumentoController {
    private InstrumentoService instrumentoService;

    @Autowired
    public InstrumentoController(InstrumentoService instrumentoService) {
        this.instrumentoService = instrumentoService;

    }

    @GetMapping("/{id}")
    @CrossOrigin
    public Instrumento getById(@PathVariable("id") Long id) {
        return this.instrumentoService.findById(id);
    }
    @CrossOrigin
    @GetMapping
    public List<Instrumento> getAll() {
        return this.instrumentoService.findAll();
    }

    @PostMapping
    @CrossOrigin
    public Instrumento post(@RequestBody Instrumento instrumento) {
        return this.instrumentoService.save(instrumento);
    }

    @PutMapping("/{id}")
    @CrossOrigin
    public Instrumento put(@PathVariable("id") Long id, @RequestBody Instrumento instrumento) {
        return this.instrumentoService.update(id, instrumento);
    }

    @DeleteMapping("/{id}")
    @CrossOrigin
    public void delete(@PathVariable("id") Long id){
         this.instrumentoService.delete(id);
    }


}
