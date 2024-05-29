package Laboratorio4com.example.demo.repository;

import Laboratorio4com.example.demo.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
