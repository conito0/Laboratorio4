package Laboratorio4com.example.demo.service;

import Laboratorio4com.example.demo.entities.Instrumento;
import Laboratorio4com.example.demo.entities.Person;
import Laboratorio4com.example.demo.repository.InstrumentoRepository;
import Laboratorio4com.example.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstrumentoService {
    private InstrumentoRepository instrumentoRepository;

    @Autowired
    public InstrumentoService(InstrumentoRepository instrumentoRepository) {
        this.instrumentoRepository = instrumentoRepository;
    }

    public Instrumento findById(Long id) {
        return this.instrumentoRepository.findById(id).get();
    }

    public List<Instrumento> findAll() {
        return this.instrumentoRepository.findAll();
    }

    public Instrumento save(Instrumento instrumento) {
        return this.instrumentoRepository.save(instrumento);
    }
//metodo que se llama update devuelve un objeto de tipo instrumento, r4ecibe dos parametros,
// el id que actualzia y el instrumento con el que actualiza
    public Instrumento update(Long id, Instrumento instrumento){
        instrumento.setId(id);

        return this.instrumentoRepository.save(instrumento);
    }

    public void delete(Long id) {
        this.instrumentoRepository.deleteById(id);
    }

}
