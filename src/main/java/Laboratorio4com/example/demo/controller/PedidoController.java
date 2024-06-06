package Laboratorio4com.example.demo.controller;

import Laboratorio4com.example.demo.entities.Instrumento;
import Laboratorio4com.example.demo.entities.Pedido;
import Laboratorio4com.example.demo.service.PedidoService;
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
import java.time.Instant;
import java.util.List;

@RequestMapping("/pedido")
@RestController
@Controller
public class PedidoController {
    private PedidoService pedidoService;

    @Autowired
    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;

    }

    @GetMapping("/{id}")
    @CrossOrigin
    public Pedido getById(@PathVariable("id") Long id) {
        return this.pedidoService.findById(id);
    }
    @CrossOrigin
    @GetMapping
    public List<Pedido> getAll() {
        return this.pedidoService.findAll();
    }

    @PostMapping
    @CrossOrigin
    public Pedido post(@RequestBody Pedido pedido) {
        return this.pedidoService.save(pedido);
    }

    @PutMapping("/{id}")
    @CrossOrigin
    public Pedido put(@PathVariable("id") Long id, @RequestBody Pedido pedido) {
        return this.pedidoService.update(id, pedido);
    }

    @DeleteMapping("/{id}")
    @CrossOrigin
    public void delete(@PathVariable("id") Long id){
        this.pedidoService.delete(id);
    }

    @GetMapping("/stats")
    @CrossOrigin
    public List<Object[]> getStats() {
        return this.pedidoService.getStats();
    }

    @GetMapping("excel")
    public ResponseEntity<byte[]> downloadExcelPlatos(@RequestParam("desde") Instant desde, @RequestParam("hasta") Instant hasta) throws SQLException {
        try {
            SXSSFWorkbook libroExcel = this.pedidoService.generarExcel(desde, hasta);
            // Escribir el libro de trabajo en un flujo de bytes
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            libroExcel.write(outputStream);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
            headers.setContentDispositionFormData("attachment", "datos.xlsx");
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

            return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
