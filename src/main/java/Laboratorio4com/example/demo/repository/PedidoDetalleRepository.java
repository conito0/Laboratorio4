package Laboratorio4com.example.demo.repository;

import Laboratorio4com.example.demo.entities.PedidoDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoDetalleRepository extends JpaRepository<PedidoDetalle, Long> {

    @Query("SELECT pd.instrumento.nombre, COUNT(pd) FROM PedidoDetalle pd GROUP BY pd.instrumento.id, pd.instrumento.nombre")
    List<Object[]> countPedidosPorInstrumento();
}
