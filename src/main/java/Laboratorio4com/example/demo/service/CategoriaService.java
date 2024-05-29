package Laboratorio4com.example.demo.service;

import Laboratorio4com.example.demo.entities.Categoria;
import Laboratorio4com.example.demo.entities.Instrumento;
import Laboratorio4com.example.demo.repository.CategoriaRepository;
import Laboratorio4com.example.demo.repository.InstrumentoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private CategoriaRepository categoriaRepository;

    @Autowired
    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public Categoria findById(Long id) {
        return this.categoriaRepository.findById(id).get();
    }

    public List<Categoria> findAll() {
        return this.categoriaRepository.findAll();
    }

    public Categoria save(Categoria categoria) {
        return this.categoriaRepository.save(categoria);
    }

    //metodo que se llama update devuelve un objeto de tipo instrumento, r4ecibe dos parametros,
// el id que actualzia y el instrumento con el que actualiza
    public Categoria update(Long id, Categoria categoria){
        categoria.setId(id);

        return this.categoriaRepository.save(categoria);
    }

    public void delete(Long id) {
        this.categoriaRepository.deleteById(id);
    }


}
