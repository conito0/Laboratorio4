package Laboratorio4com.example.demo.repository;

import Laboratorio4com.example.demo.entities.Categoria;
import Laboratorio4com.example.demo.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
