package Laboratorio4com.example.demo.controller;

import Laboratorio4com.example.demo.entities.Categoria;
import Laboratorio4com.example.demo.entities.Instrumento;
import Laboratorio4com.example.demo.service.CategoriaService;
import Laboratorio4com.example.demo.service.InstrumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/categoria")
@RestController
@Controller
public class CategoriaController {
    private CategoriaService categoriaservice;

    @Autowired
    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaservice = categoriaService;
    }
    @GetMapping("/{id}")
    @CrossOrigin
    public Categoria getById(@PathVariable("id") Long id) {
        return this.categoriaservice.findById(id);
    }
    @CrossOrigin
    @GetMapping
    public List<Categoria> getAll() {
        return this.categoriaservice.findAll();
    }

    @PostMapping
    public Categoria post(@RequestBody Categoria categoria) {
        return this.categoriaservice.save(categoria);
    }

    @PutMapping("/{id}")
    public Categoria put(@PathVariable("id") Long id, @RequestBody Categoria categoria) {
        return this.categoriaservice.update(id, categoria);
    }

    @DeleteMapping
    public void delete(@PathVariable("id") Long id){
        this.categoriaservice.delete(id);
    }



}
