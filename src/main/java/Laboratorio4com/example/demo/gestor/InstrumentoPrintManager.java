//package Laboratorio4com.example.demo.gestor;
//
//import Laboratorio4com.example.demo.entities.Instrumento;
//import com.example.demo.entities.Plato;
//import com.lowagie.text.Document;
//import com.lowagie.text.DocumentException;
//import com.lowagie.text.Element;
//import com.lowagie.text.Font;
//import com.lowagie.text.Image;
//import com.lowagie.text.PageSize;
//import com.lowagie.text.Paragraph;
//import com.lowagie.text.Phrase;
//import com.lowagie.text.Rectangle;
//import com.lowagie.text.pdf.PdfPCell;
//import com.lowagie.text.pdf.PdfPTable;
//import com.lowagie.text.pdf.PdfWriter;
//
//import java.awt.*;
//import java.io.ByteArrayOutputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.net.MalformedURLException;
//import java.sql.SQLException;
//import java.util.List;
//import org.apache.poi.xssf.streaming.SXSSFCell;
//import org.apache.poi.xssf.streaming.SXSSFRow;
//import org.apache.poi.xssf.streaming.SXSSFSheet;
//import org.apache.poi.xssf.streaming.SXSSFWorkbook;
//import org.apache.poi.xssf.usermodel.XSSFCellStyle;
//import org.apache.poi.xssf.usermodel.XSSFFont;
//
//public class InstrumentoPrintManager {
//    protected static Font titulo = new Font(Font.COURIER, 14, Font.BOLD);
//    protected static Font redFont = new Font(Font.COURIER, 12, Font.NORMAL, Color.RED);
//    protected static Font textoHeader = new Font(Font.COURIER, 17, Font.BOLD);
//    protected static Font texto = new Font(Font.COURIER, 11, Font.NORMAL);
//    protected static Font textoBold = new Font(Font.COURIER, 11, Font.BOLD);
//    protected static Font textoMini = new Font(Font.COURIER, 8, Font.NORMAL);
//    protected static Font textoMiniBold = new Font(Font.COURIER, 8, Font.BOLD);
//    protected static Font textoBig = new Font(Font.COURIER, 50, Font.BOLD);
//
//    public static void addMetaData(Document document) {
//        document.addTitle("Reporte PDF");
//        document.addSubject("Ejemplo PDF");
//        document.addKeywords("PDF");
//        document.addAuthor("Constanza uno");
//        document.addCreator("Constanza uno");
//    }
//
//    public static void addEmptyLine(Document document, int number) {
//        try {
//            Paragraph espacio = new Paragraph();
//            for (int i = 0; i < number; i++) {
//                espacio.add(new Paragraph(" "));
//            }
//            document.add(espacio);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void setLineaReporte(Document document) throws DocumentException, MalformedURLException, IOException{
//        PdfPTable linea = new PdfPTable(1);
//        linea.setWidthPercentage(100.0f);
//        PdfPCell cellOne = new PdfPCell(new Paragraph(""));
//        cellOne.setBorder(Rectangle.BOTTOM);
//        cellOne.setBorder(Rectangle.TOP);
//        linea.addCell(cellOne);
//
//        document.add(linea);
//    }
//
//    public void imprimirPlatoPdf(Long idPlato, ByteArrayOutputStream outputStream) throws SQLException  {
//        try{
//            Document document = new Document(PageSize.A4, 30, 30, 0, 30);//float marginLeft, float marginRight, float marginTop, float marginBottom
//            addMetaData(document);
//
//            PlatoManager mPlato = new PlatoManager();
//            Instrumento instrumento = mPlato.(idPlato);
//
//            PdfWriter.getInstance(document, outputStream); // Code 2
//            document.open();
//            //Encabezado
//            PdfPTable tableCabecera = new PdfPTable(2);
//            tableCabecera.setWidthPercentage(100f);
//
//            Image imgCabeceraLeft = Image.getInstance("http://localhost:8080/images/images.jpg");
//            imgCabeceraLeft.scalePercent(70f);
//            imgCabeceraLeft.setBorderColorBottom(Color.BLACK);
//            Image imgCabeceraRight = Image.getInstance("http://localhost:8080/images/utn.png");
//            imgCabeceraRight.scalePercent(70f);
//            imgCabeceraRight.setBorderColorBottom(Color.BLACK);
//
//            PdfPCell logoBuenSabor = new PdfPCell(imgCabeceraLeft);
//            logoBuenSabor.setBorder(Rectangle.NO_BORDER);
//            logoBuenSabor.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
//            PdfPCell logoUTN = new PdfPCell(imgCabeceraRight);
//            logoUTN.setBorder(Rectangle.NO_BORDER);
//            logoUTN.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
//
//            tableCabecera.addCell(logoBuenSabor);
//            tableCabecera.addCell(logoUTN);
//
//            document.add(tableCabecera);
//
//            addEmptyLine(document, 1);
//            setLineaReporte(document);
//            //Fin encabezado
//
//            Paragraph paragraph = new Paragraph(plato.getNombre().toUpperCase(), titulo);
//            paragraph.setAlignment(Element.ALIGN_CENTER);
//            document.add(paragraph);
//
//            Image imgPlato = Image.getInstance("http://localhost:8080/images/"+plato.getImagenPath());
//            imgPlato.scaleAbsolute(500f, 300f);
//
//            document.add(imgPlato);
//
//            addEmptyLine(document, 2);
//
//            PdfPTable table = new PdfPTable(2);
//            table.setWidthPercentage(100);
//
//            PdfPCell celdaIzq = new PdfPCell(new Phrase("Plato:", textoBold));
//            celdaIzq.setBorder(Rectangle.NO_BORDER);
//            PdfPCell celdaDer = new PdfPCell(new Phrase(plato.getNombre(), texto));
//            celdaDer.setBorder(Rectangle.NO_BORDER);
//            table.addCell(celdaIzq);
//            table.addCell(celdaDer);
//
//            celdaIzq = new PdfPCell(new Phrase("Precio:", textoBold));
//            celdaIzq.setBorder(Rectangle.NO_BORDER);
//            celdaDer = new PdfPCell(new Phrase(plato.getStrPrecio(), texto));
//            celdaDer.setBorder(Rectangle.NO_BORDER);
//            table.addCell(celdaIzq);
//            table.addCell(celdaDer);
//
//            celdaIzq = new PdfPCell(new Phrase("Rubro:", titulo));
//            celdaIzq.setBorder(Rectangle.NO_BORDER);
//            celdaDer = new PdfPCell(new Phrase(plato.getRubro(), titulo));
//            celdaDer.setBorder(Rectangle.NO_BORDER);
//            table.addCell(celdaIzq);
//            table.addCell(celdaDer);
//
//            celdaIzq = new PdfPCell(new Phrase("Descripción:", textoBold));
//            celdaIzq.setBorder(Rectangle.NO_BORDER);
//            celdaDer = new PdfPCell(new Phrase(plato.getDescripcion(), texto));
//            celdaDer.setBorder(Rectangle.NO_BORDER);
//            table.addCell(celdaIzq);
//            table.addCell(celdaDer);
//
//
//            celdaIzq = new PdfPCell(new Phrase("Ingredientes:", textoBold));
//            celdaIzq.setBorder(Rectangle.NO_BORDER);
//            celdaDer = new PdfPCell(new Phrase(plato.getStrIngredientes(), texto));
//            celdaDer.setBorder(Rectangle.NO_BORDER);
//            table.addCell(celdaIzq);
//            table.addCell(celdaDer);
//
//            document.add(table);
//
//            document.close();
//
//        }catch(DocumentException | IOException e){
//            e.printStackTrace();
//        }
//    }
//}
