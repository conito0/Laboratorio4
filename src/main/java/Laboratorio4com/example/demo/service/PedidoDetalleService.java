package Laboratorio4com.example.demo.service;

import Laboratorio4com.example.demo.entities.Categoria;
import Laboratorio4com.example.demo.entities.PedidoDetalle;
import Laboratorio4com.example.demo.repository.CategoriaRepository;
import Laboratorio4com.example.demo.repository.PedidoDetalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoDetalleService {
    private PedidoDetalleRepository pedidoDetalleRepository;

    @Autowired
    public PedidoDetalleService(PedidoDetalleRepository pedidoDetalleRepository) {
        this.pedidoDetalleRepository = pedidoDetalleRepository;
    }

    public PedidoDetalle findById(Long id) {
        return this.pedidoDetalleRepository.findById(id).get();
    }

    public List<PedidoDetalle> findAll() {
        return this.pedidoDetalleRepository.findAll();
    }

    public PedidoDetalle save(PedidoDetalle pedidoDetalle) {
        return this.pedidoDetalleRepository.save(pedidoDetalle);
    }

    public PedidoDetalle update(Long id, PedidoDetalle pedidoDetalle){
        pedidoDetalle.setId(id);

        return this.pedidoDetalleRepository.save(pedidoDetalle);
    }

    public void delete(Long id) {
        this.pedidoDetalleRepository.deleteById(id);
    }

}
