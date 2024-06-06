package Laboratorio4com.example.demo.service;

import Laboratorio4com.example.demo.entities.Categoria;
import Laboratorio4com.example.demo.entities.Pedido;
import Laboratorio4com.example.demo.entities.PedidoDetalle;
import Laboratorio4com.example.demo.repository.CategoriaRepository;
import Laboratorio4com.example.demo.repository.PedidoRepository;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
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

    public List<Object[]> getStats() {
        return this.pedidoRepository.getStats();
    }

    public SXSSFWorkbook generarExcel(Instant desde, Instant hasta) {
        // Se crea el libro
        SXSSFWorkbook libro = new SXSSFWorkbook(50); //importante !! el 50 indica el tamaño de paginación
        // Se crea una hoja dentro del libro
        SXSSFSheet hoja = libro.createSheet();
        //estilo
        XSSFFont font = (XSSFFont) libro.createFont();
        font.setBold(true);
        XSSFCellStyle style = (XSSFCellStyle) libro.createCellStyle();
        style.setFont(font);
        int nroColumna = 0;
        // Se crea una fila dentro de la hoja
        SXSSFRow row = hoja.createRow(0);
        // Se crea una celda dentro de la fila
        SXSSFCell cell = row.createCell(nroColumna);
        cell.setCellValue("Fecha Pedido");
        cell.setCellStyle(style);
        cell = row.createCell(++nroColumna);
        cell.setCellValue("Instrumento");
        cell.setCellStyle(style);
        cell = row.createCell(++nroColumna);
        cell.setCellValue("Marca");
        cell.setCellStyle(style);
        cell = row.createCell(++nroColumna);
        cell.setCellValue("Modelo");
        cell.setCellStyle(style);
        cell = row.createCell(++nroColumna);
        cell.setCellValue("Cantiadd");
        cell.setCellStyle(style);
        cell = row.createCell(++nroColumna);
        cell.setCellValue("Precio");
        cell.setCellStyle(style);
        cell = row.createCell(++nroColumna);
        cell.setCellValue("Subtotal");
        cell.setCellStyle(style);

        int nroFila = 1;
        nroColumna = 0;
        List<Pedido> pedidos = this.pedidoRepository.getAllDesdeHasta(desde, hasta);
        if(pedidos != null){
            for (Pedido pedido : pedidos) {
                for (PedidoDetalle detalle : pedido.getDetalles()) {
                    nroColumna = 0;
                    row = hoja.createRow(nroFila);
                    ++nroFila;
                    cell = row.createCell(nroColumna);
                    cell.setCellValue(pedido.getFechaPedido().toString());
                    cell = row.createCell(++nroColumna);
                    cell.setCellValue(detalle.getInstrumento().getInstrumento());
                    cell = row.createCell(++nroColumna);
                    cell.setCellValue(detalle.getInstrumento().getMarca());
                    cell = row.createCell(++nroColumna);
                    cell.setCellValue(detalle.getInstrumento().getModelo());
                    cell = row.createCell(++nroColumna);
                    cell.setCellValue(detalle.getCantidad());
                    cell = row.createCell(++nroColumna);
                    cell.setCellValue(detalle.getInstrumento().getPrecio());
                    cell = row.createCell(++nroColumna);
                    cell.setCellValue(Double.parseDouble(detalle.getInstrumento().getPrecio()) * detalle.getCantidad());
                }
            }
        }
        return libro;
    }

}
