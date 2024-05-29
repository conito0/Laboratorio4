package Laboratorio4com.example.demo.controller;

import Laboratorio4com.example.demo.entities.Instrumento;
import Laboratorio4com.example.demo.entities.Pedido;
import Laboratorio4com.example.demo.service.InstrumentoService;
import Laboratorio4com.example.demo.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/pedido")
@RestController
@Controller
public class PedidoController {
    private PedidoService pedidoService;

    @Autowired
    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;

    }

    @GetMapping("/{id}")
    @CrossOrigin
    public Pedido getById(@PathVariable("id") Long id) {
        return this.pedidoService.findById(id);
    }
    @CrossOrigin
    @GetMapping
    public List<Pedido> getAll() {
        return this.pedidoService.findAll();
    }

    @PostMapping
    @CrossOrigin
    public Pedido post(@RequestBody Pedido pedido) {
        return this.pedidoService.save(pedido);
    }

    @PutMapping("/{id}")
    @CrossOrigin
    public Pedido put(@PathVariable("id") Long id, @RequestBody Pedido pedido) {
        return this.pedidoService.update(id, pedido);
    }

    @DeleteMapping("/{id}")
    @CrossOrigin
    public void delete(@PathVariable("id") Long id){
        this.pedidoService.delete(id);
    }

}
