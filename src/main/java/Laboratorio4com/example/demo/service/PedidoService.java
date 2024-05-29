package Laboratorio4com.example.demo.service;

import Laboratorio4com.example.demo.entities.Categoria;
import Laboratorio4com.example.demo.entities.Pedido;
import Laboratorio4com.example.demo.repository.CategoriaRepository;
import Laboratorio4com.example.demo.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PedidoService {
    private PedidoRepository pedidoRepository;
    private MercadoPagoService mercadoPagoService;

    @Autowired
    public PedidoService(MercadoPagoService mercadoPagoService, PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.mercadoPagoService = mercadoPagoService;
    }

    public Pedido findById(Long id) {
        return this.pedidoRepository.findById(id).get();
    }

    public List<Pedido> findAll() {
        return this.pedidoRepository.findAll();
    }

    public Pedido save(Pedido pedido) {

        // Guardo en Base de Datos el pedido
        Pedido pedidoGuardado = this.pedidoRepository.save(pedido);

        pedido.setPreferenceMP(mercadoPagoService.crearPreferencia(pedido));

        return pedidoGuardado;
    }

    public Pedido update(Long id, Pedido pedido){
        pedido.setId(id);

        return this.pedidoRepository.save(pedido);
    }

    public void delete(Long id) {
        this.pedidoRepository.deleteById(id);
    }

}
