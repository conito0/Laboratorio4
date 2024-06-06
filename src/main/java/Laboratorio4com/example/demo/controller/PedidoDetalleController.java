package Laboratorio4com.example.demo.controller;

import Laboratorio4com.example.demo.entities.Instrumento;
import Laboratorio4com.example.demo.entities.PedidoDetalle;
import Laboratorio4com.example.demo.service.InstrumentoService;
import Laboratorio4com.example.demo.service.PedidoDetalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/pedidoDetalle")
@RestController
@Controller
public class PedidoDetalleController {
    private PedidoDetalleService pedidoDetalleService;

    @Autowired
    public PedidoDetalleController(PedidoDetalleService pedidoDetalleService) {
        this.pedidoDetalleService = pedidoDetalleService;

    }

    @GetMapping("/{id}")
    @CrossOrigin
    public PedidoDetalle getById(@PathVariable("id") Long id) {
        return this.pedidoDetalleService.findById(id);
    }
    @CrossOrigin
    @GetMapping
    public List<PedidoDetalle> getAll() {
        return this.pedidoDetalleService.findAll();
    }

    @PostMapping
    @CrossOrigin
    public PedidoDetalle post(@RequestBody PedidoDetalle pedidoDetalle) {
        return this.pedidoDetalleService.save(pedidoDetalle);
    }

    @PutMapping("/{id}")
    @CrossOrigin
    public PedidoDetalle put(@PathVariable("id") Long id, @RequestBody PedidoDetalle pedidoDetalle) {
        return this.pedidoDetalleService.update(id, pedidoDetalle);
    }

    @DeleteMapping("/{id}")
    @CrossOrigin
    public void delete(@PathVariable("id") Long id){
        this.pedidoDetalleService.delete(id);
    }

    @GetMapping("/stats")
    @CrossOrigin
    public List<Object[]> getStats() {
        return this.pedidoDetalleService.getStats();
    }

}

