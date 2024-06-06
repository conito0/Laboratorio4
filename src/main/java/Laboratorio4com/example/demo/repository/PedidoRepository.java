package Laboratorio4com.example.demo.repository;

import Laboratorio4com.example.demo.entities.Categoria;
import Laboratorio4com.example.demo.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query("SELECT YEAR(p.fechaPedido) AS anio, MONTH(p.fechaPedido) AS mes, COUNT(p) AS cantidadPedidos " +
            "FROM Pedido p " +
            "GROUP BY YEAR(p.fechaPedido), MONTH(p.fechaPedido) " +
            "ORDER BY anio, mes")
    List<Object[]> getStats();

    @Query("SELECT p FROM Pedido p WHERE p.fechaPedido BETWEEN ?1 and ?2")
    List<Pedido> getAllDesdeHasta(Instant desde, Instant hasta);

}
