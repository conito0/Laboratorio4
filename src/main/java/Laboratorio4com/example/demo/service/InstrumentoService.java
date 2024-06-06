package Laboratorio4com.example.demo.service;

import Laboratorio4com.example.demo.entities.Instrumento;
import Laboratorio4com.example.demo.entities.Person;
import Laboratorio4com.example.demo.repository.InstrumentoRepository;
import Laboratorio4com.example.demo.repository.PersonRepository;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
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

    public static void addMetaData(Document document) {
        document.addTitle("Reporte PDF");
        document.addSubject("Ejemplo PDF");
        document.addKeywords("PDF");
        document.addAuthor("Constanza uno");
        document.addCreator("Constanza uno");
    }

    public static void addEmptyLine(Document document, int number) {
        try {
            Paragraph espacio = new Paragraph();
            for (int i = 0; i < number; i++) {
                espacio.add(new Paragraph(" "));
            }
            document.add(espacio);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setLineaReporte(Document document) throws DocumentException, MalformedURLException, IOException {
        PdfPTable linea = new PdfPTable(1);
        linea.setWidthPercentage(100.0f);
        PdfPCell cellOne = new PdfPCell(new Paragraph(""));
        cellOne.setBorder(Rectangle.BOTTOM);
        cellOne.setBorder(Rectangle.TOP);
        linea.addCell(cellOne);

        document.add(linea);
    }

    protected static Font titulo = new Font(Font.COURIER, 14, Font.BOLD);
    protected static Font redFont = new Font(Font.COURIER, 12, Font.NORMAL, Color.RED);
    protected static Font textoHeader = new Font(Font.COURIER, 17, Font.BOLD);
    protected static Font texto = new Font(Font.COURIER, 11, Font.NORMAL);
    protected static Font textoBold = new Font(Font.COURIER, 11, Font.BOLD);
    protected static Font textoMini = new Font(Font.COURIER, 8, Font.NORMAL);
    protected static Font textoMiniBold = new Font(Font.COURIER, 8, Font.BOLD);
    protected static Font textoBig = new Font(Font.COURIER, 50, Font.BOLD);

    public void generarPdf(Long id, ByteArrayOutputStream outputStream) throws SQLException {
        try {
            Document document = new Document(PageSize.A4, 30, 30, 0, 30); // float marginLeft, float marginRight, float marginTop, float marginBottom
            addMetaData(document);

            Instrumento instrumento = this.findById(id);

            PdfWriter.getInstance(document, outputStream); // Code 2
            document.open();

            // Encabezado
            PdfPTable tableCabecera = new PdfPTable(2);
            tableCabecera.setWidthPercentage(100f);

            document.add(tableCabecera);

            addEmptyLine(document, 1);
            setLineaReporte(document);
            // Fin encabezado

            // Sección con dos columnas
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            float[] columnWidths = {1f, 2f};
            table.setWidths(columnWidths);

            // Columna izquierda con imagen
            Image imgInstrumento = Image.getInstance(instrumento.getImagen());
            imgInstrumento.scaleAbsolute(150f, 150f); // Ajustar tamaño según sea necesario

            PdfPCell imgCell = new PdfPCell(imgInstrumento);
            imgCell.setBorder(Rectangle.NO_BORDER);
            table.addCell(imgCell);

            // Columna derecha con información del instrumento
            PdfPTable infoTable = new PdfPTable(2);
            infoTable.setWidthPercentage(100);

            PdfPCell celdaIzq = new PdfPCell(new Phrase("Cantidad Vendida:", textoBold));
            celdaIzq.setBorder(Rectangle.NO_BORDER);
            PdfPCell celdaDer = new PdfPCell(new Phrase(String.valueOf(instrumento.getCantidadVendida()), texto));
            celdaDer.setBorder(Rectangle.NO_BORDER);
            infoTable.addCell(celdaIzq);
            infoTable.addCell(celdaDer);

            celdaIzq = new PdfPCell(new Phrase("Nombre:", textoBold));
            celdaIzq.setBorder(Rectangle.NO_BORDER);
            celdaDer = new PdfPCell(new Phrase(instrumento.getNombre(), texto));
            celdaDer.setBorder(Rectangle.NO_BORDER);
            infoTable.addCell(celdaIzq);
            infoTable.addCell(celdaDer);

            celdaIzq = new PdfPCell(new Phrase("Precio:", textoBold));
            celdaIzq.setBorder(Rectangle.NO_BORDER);
            celdaDer = new PdfPCell(new Phrase(String.valueOf(instrumento.getPrecio()), texto));
            celdaDer.setBorder(Rectangle.NO_BORDER);
            infoTable.addCell(celdaIzq);
            infoTable.addCell(celdaDer);

            celdaIzq = new PdfPCell(new Phrase("Marca:", textoBold));
            celdaIzq.setBorder(Rectangle.NO_BORDER);
            celdaDer = new PdfPCell(new Phrase(instrumento.getMarca(), texto));
            celdaDer.setBorder(Rectangle.NO_BORDER);
            infoTable.addCell(celdaIzq);
            infoTable.addCell(celdaDer);

            celdaIzq = new PdfPCell(new Phrase("Modelo:", textoBold));
            celdaIzq.setBorder(Rectangle.NO_BORDER);
            celdaDer = new PdfPCell(new Phrase(instrumento.getModelo(), texto));
            celdaDer.setBorder(Rectangle.NO_BORDER);
            infoTable.addCell(celdaIzq);
            infoTable.addCell(celdaDer);

            celdaIzq = new PdfPCell(new Phrase("Costo del Envío:", textoBold));
            celdaIzq.setBorder(Rectangle.NO_BORDER);
            celdaDer = new PdfPCell(new Phrase(String.valueOf(instrumento.getCostoEnvio()), texto));
            celdaDer.setBorder(Rectangle.NO_BORDER);
            infoTable.addCell(celdaIzq);
            infoTable.addCell(celdaDer);

            PdfPCell infoCell = new PdfPCell(infoTable);
            infoCell.setBorder(Rectangle.NO_BORDER);
            table.addCell(infoCell);

            document.add(table);

            // Descripción
            PdfPTable descTable = new PdfPTable(1);
            descTable.setWidthPercentage(100);

            PdfPCell descCell = new PdfPCell(new Phrase(instrumento.getDescripcion(), texto));
            descCell.setBorder(Rectangle.NO_BORDER);
            descCell.setPaddingTop(10f); // Ajustar según sea necesario
            descTable.addCell(descCell);

            document.add(descTable);

            document.close();

        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }

}
