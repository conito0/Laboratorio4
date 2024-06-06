package Laboratorio4com.example.demo.controller;

import Laboratorio4com.example.demo.entities.Instrumento;
import Laboratorio4com.example.demo.entities.Person;
import Laboratorio4com.example.demo.service.InstrumentoService;
import Laboratorio4com.example.demo.service.PersonService;
import jakarta.websocket.server.PathParam;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@RequestMapping("/instrumento")
@RestController
@Controller
public class InstrumentoController {
    private InstrumentoService instrumentoService;

    @Autowired
    public InstrumentoController(InstrumentoService instrumentoService) {
        this.instrumentoService = instrumentoService;

    }

    @GetMapping("/{id}")
    @CrossOrigin
    public Instrumento getById(@PathVariable("id") Long id) {
        return this.instrumentoService.findById(id);
    }
    @CrossOrigin
    @GetMapping
    public List<Instrumento> getAll() {
        return this.instrumentoService.findAll();
    }

    @PostMapping
    @CrossOrigin
    public Instrumento post(@RequestBody Instrumento instrumento) {
        return this.instrumentoService.save(instrumento);
    }

    @PutMapping("/{id}")
    @CrossOrigin
    public Instrumento put(@PathVariable("id") Long id, @RequestBody Instrumento instrumento) {
        return this.instrumentoService.update(id, instrumento);
    }

    @DeleteMapping("/{id}")
    @CrossOrigin
    public void delete(@PathVariable("id") Long id){
         this.instrumentoService.delete(id);
    }


    @GetMapping("pdf/{id}")
    public ResponseEntity<byte[]> downloadPdf(@PathVariable String id) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            // Crear un nuevo documento
            this.instrumentoService.generarPdf(Long.parseLong(id), outputStream);

            // Establecer las cabeceras de la respuesta
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/pdf"));
            headers.setContentDispositionFormData("attachment", "documento.pdf");//nombre con fecha de hoy
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

            // Devolver el archivo PDF como parte de la respuesta HTTP
            return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
