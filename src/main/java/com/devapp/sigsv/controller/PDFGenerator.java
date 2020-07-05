package com.devapp.sigsv.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.devapp.sigsv.model.bean.DetalleVenta;
import com.devapp.sigsv.model.bean.Venta;
import com.devapp.sigsv.util.AppUtilConverter;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

public class PDFGenerator {
	 private static Logger logger = LoggerFactory.getLogger(PDFGenerator.class);
	 
	 public static ByteArrayInputStream customerPDFReport(Venta venta) {
		 
		 /*
		  * HAY 72 UNIDADES EN 1 PULGADA
		  * Rectangle pagesize = new Rectangle(312, 461);
		  * */	 
		Integer numRow = venta.getLstDetalleVenta().size();
		Rectangle pageSize = new Rectangle(200, 400+(numRow*10));
		Document document = new Document(pageSize);
		//Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        document.setMargins(10, 10, 20, 10);
        try {
          
          PdfWriter.getInstance(document, out);
          document.open();
          
          // Add Text to PDF file ->
          Font fontBOLD = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 6, BaseColor.BLACK);
          Font font = FontFactory.getFont(FontFactory.COURIER, 6, BaseColor.BLACK);
          Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 6);
          
          Paragraph razSocial = new Paragraph( "RAZÓN SOCIAL", fontBOLD);
          Paragraph direccion = new Paragraph( "DIRECCIÓN DEL LA EMPRESA", font);
          Paragraph ubiGeo = new Paragraph( "DISTRITO - PROVINCIA - DEPARTAMENTO", font);
          Paragraph ruc = new Paragraph( "RUC XXXXXXXXXXX", fontBOLD);
          Paragraph tipoComprobante = new Paragraph( venta.getTipoComprobante().getDescripcion(), fontBOLD);
          Paragraph numComprobante = new Paragraph( "XXXX-"+venta.getNumComprobante(), fontBOLD);
          
          Paragraph stringCliente = new Paragraph( "CLIENTE:", fontBOLD);
          Paragraph numDocumento = new Paragraph( "DNI: "+venta.getCliente().getDni(), font);
          Paragraph cliente = new Paragraph( venta.getCliente().getNombre() + " "+
        		  venta.getCliente().getApPaterno() + " "+
        		  venta.getCliente().getApMaterno(), font);
                    
          Paragraph fechaVenta = new Paragraph("FECHA EMISIÓN: ", fontBOLD);
          String stringFechaVenta = AppUtilConverter.dateMilisecondsToDate(
        		  venta.getFecha());
          fechaVenta.add(new Chunk(stringFechaVenta, font)); 
          /*--------------------*/
          razSocial.setAlignment(Element.ALIGN_CENTER);
          document.add(razSocial);    
          direccion.setAlignment(Element.ALIGN_CENTER);
          document.add(direccion);
          ubiGeo.setAlignment(Element.ALIGN_CENTER);
          document.add(ubiGeo);
          ruc.setAlignment(Element.ALIGN_CENTER);
          document.add(ruc);
          document.add(Chunk.NEWLINE);
          tipoComprobante.setAlignment(Element.ALIGN_CENTER);
          document.add(tipoComprobante);
          numComprobante.setAlignment(Element.ALIGN_CENTER);
          document.add(numComprobante);
          document.add(Chunk.NEWLINE);
          
          LineSeparator ls = new LineSeparator(); 
          ls.setLineWidth(1);
          document.add(ls);
          
          stringCliente.setAlignment(Element.ALIGN_LEFT);
          document.add(stringCliente);
          numDocumento.setAlignment(Element.ALIGN_LEFT);
          document.add(numDocumento);
          cliente.setAlignment(Element.ALIGN_LEFT);
          document.add(cliente);
          document.add(Chunk.NEWLINE);
          
          fechaVenta.setAlignment(Element.ALIGN_LEFT);
          document.add(fechaVenta);
          document.add(Chunk.NEWLINE);
          document.add(ls);
                   
          PdfPTable table = new PdfPTable(4);
          Paragraph pTable = new Paragraph();
          table.setWidthPercentage(100);
          // Add PDF Table Header ->
            Stream.of("[CANT.]", "DESCRIP.", "P/U", "SUB. TOTAL")
              .forEach(headerTitle -> {
                  PdfPCell header = new PdfPCell();
                  //header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                  header.setHorizontalAlignment(Element.ALIGN_CENTER);
                  header.setBorderWidth(0);
                  header.setPhrase(new Phrase(headerTitle, headFont));
                  table.addCell(header);
              });
            
            for (DetalleVenta detalleVenta : venta.getLstDetalleVenta()) {
				PdfPCell idCell = new PdfPCell(
						new Phrase(detalleVenta.getCantidad().toString(), font));
				idCell.setPaddingLeft(1);
				idCell.setBorderWidth(0);
				idCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				idCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(idCell);		
				 
				PdfPCell descripcion = new PdfPCell(
						new Phrase(detalleVenta.getProducto().getCodigo()+
								detalleVenta.getProducto().getNombre(), font));
				descripcion.setPaddingLeft(1);
				descripcion.setBorderWidth(0);
				descripcion.setVerticalAlignment(Element.ALIGN_MIDDLE);
				descripcion.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(descripcion);
				 
				PdfPCell pUnitario = new PdfPCell(
						new Phrase(String.valueOf(
								detalleVenta.getProducto().getPreVenta()-detalleVenta.getDescuento()), font));
				pUnitario.setVerticalAlignment(Element.ALIGN_MIDDLE);
				pUnitario.setHorizontalAlignment(Element.ALIGN_CENTER);
				pUnitario.setPaddingRight(1);
				pUnitario.setBorderWidth(0);
				table.addCell(pUnitario);	
				
				PdfPCell subTotal = new PdfPCell(
						new Phrase(String.valueOf(detalleVenta.getCantidad() *
								(detalleVenta.getProducto().getPreVenta()-detalleVenta.getDescuento())), font));
				subTotal.setVerticalAlignment(Element.ALIGN_MIDDLE);
				subTotal.setHorizontalAlignment(Element.ALIGN_CENTER);
				subTotal.setPaddingRight(1);
				subTotal.setBorderWidth(0);
				table.addCell(subTotal);	
            }
            PdfPCell stringDescuento = new PdfPCell(
					new Phrase("DESCUENTO: S/. ", fontBOLD));
            stringDescuento.setVerticalAlignment(Element.ALIGN_MIDDLE);
            stringDescuento.setHorizontalAlignment(Element.ALIGN_RIGHT);
            stringDescuento.setPaddingRight(1);
            stringDescuento.setBorderWidth(0);
            stringDescuento.setColspan(3);
			table.addCell(stringDescuento);
			
            PdfPCell valDescuento = new PdfPCell(
					new Phrase(String.valueOf(
							venta.getDescuentoVenta()), fontBOLD));
            valDescuento.setVerticalAlignment(Element.ALIGN_MIDDLE);
            valDescuento.setHorizontalAlignment(Element.ALIGN_CENTER);
            valDescuento.setPaddingRight(1);
            valDescuento.setBorderWidth(0);
			table.addCell(valDescuento);
			//---
			PdfPCell stringMontoBolsa = new PdfPCell(
					new Phrase("IMP. BOLSA: S/. ", fontBOLD));
			stringMontoBolsa.setVerticalAlignment(Element.ALIGN_MIDDLE);
			stringMontoBolsa.setHorizontalAlignment(Element.ALIGN_RIGHT);
			stringMontoBolsa.setPaddingRight(1);
			stringMontoBolsa.setBorderWidth(0);
			stringMontoBolsa.setColspan(3);
			table.addCell(stringMontoBolsa);
			
            PdfPCell montoBolsa = new PdfPCell(
					new Phrase(String.valueOf(
							venta.getMontoImpuestoBolsa()), fontBOLD));
            montoBolsa.setVerticalAlignment(Element.ALIGN_MIDDLE);
            montoBolsa.setHorizontalAlignment(Element.ALIGN_CENTER);
            montoBolsa.setPaddingRight(1);
            montoBolsa.setBorderWidth(0);
			table.addCell(montoBolsa);
			//---
			PdfPCell stringMontoPagado = new PdfPCell(
					new Phrase("PAGÓ CON: S/. ", fontBOLD));
			stringMontoPagado.setVerticalAlignment(Element.ALIGN_MIDDLE);
			stringMontoPagado.setHorizontalAlignment(Element.ALIGN_RIGHT);
			stringMontoPagado.setPaddingRight(1);
			stringMontoPagado.setBorderWidth(0);
			stringMontoPagado.setColspan(3);
			table.addCell(stringMontoPagado);
			
            PdfPCell montoPagado = new PdfPCell(
					new Phrase(String.valueOf(
							venta.getMontoPagado()), fontBOLD));
            montoPagado.setVerticalAlignment(Element.ALIGN_MIDDLE);
            montoPagado.setHorizontalAlignment(Element.ALIGN_CENTER);
            montoPagado.setPaddingRight(1);
            montoPagado.setBorderWidth(0);
			table.addCell(montoPagado);
			//---
			PdfPCell stringValVenta = new PdfPCell(
					new Phrase("VAL. VENTA: S/. ", fontBOLD));
			stringValVenta.setVerticalAlignment(Element.ALIGN_MIDDLE);
			stringValVenta.setHorizontalAlignment(Element.ALIGN_RIGHT);
			stringValVenta.setPaddingRight(1);
			stringValVenta.setBorderWidth(0);
			stringValVenta.setColspan(3);
			table.addCell(stringValVenta);
			
            PdfPCell valVenta = new PdfPCell(
					new Phrase(String.valueOf(
							venta.getValorVenta()), fontBOLD));
            valVenta.setVerticalAlignment(Element.ALIGN_MIDDLE);
            valVenta.setHorizontalAlignment(Element.ALIGN_CENTER);
            valVenta.setPaddingRight(1);
            valVenta.setBorderWidth(0);
			table.addCell(valVenta);
			//---
			PdfPCell stringIGV = new PdfPCell(
					new Phrase("IGV(18%): S/. ", fontBOLD));
			stringIGV.setVerticalAlignment(Element.ALIGN_MIDDLE);
			stringIGV.setHorizontalAlignment(Element.ALIGN_RIGHT);
			stringIGV.setPaddingRight(1);
			stringIGV.setBorderWidth(0);
			stringIGV.setColspan(3);
			table.addCell(stringIGV);
			
            PdfPCell igv = new PdfPCell(
					new Phrase(String.valueOf(
							venta.getIgv()), fontBOLD));
            igv.setVerticalAlignment(Element.ALIGN_MIDDLE);
            igv.setHorizontalAlignment(Element.ALIGN_CENTER);
            igv.setPaddingRight(1);
            igv.setBorderWidth(0);
			table.addCell(igv);
			//---
			PdfPCell stringTotal = new PdfPCell(
					new Phrase("TOTAL: S/. ", fontBOLD));
            stringTotal.setVerticalAlignment(Element.ALIGN_MIDDLE);
            stringTotal.setHorizontalAlignment(Element.ALIGN_RIGHT);
            stringTotal.setPaddingRight(1);
            stringTotal.setBorderWidth(0);
            stringTotal.setColspan(3);
			table.addCell(stringTotal);
			
            PdfPCell total = new PdfPCell(
					new Phrase(String.valueOf(
							venta.getImporteTotal()), fontBOLD));
            total.setVerticalAlignment(Element.ALIGN_MIDDLE);
            total.setHorizontalAlignment(Element.ALIGN_CENTER);
            total.setPaddingRight(1);
            total.setBorderWidth(0);
			table.addCell(total);
			//---
			PdfPCell stringVuelto = new PdfPCell(
					new Phrase("VUELTO: S/. ", fontBOLD));
			stringVuelto.setVerticalAlignment(Element.ALIGN_MIDDLE);
			stringVuelto.setHorizontalAlignment(Element.ALIGN_RIGHT);
			stringVuelto.setPaddingRight(1);
			stringVuelto.setBorderWidth(0);
			stringVuelto.setColspan(3);
			table.addCell(stringVuelto);
			
            PdfPCell vuelto = new PdfPCell(
					new Phrase(String.valueOf(
							venta.getMontoPagado() - venta.getImporteTotal()), fontBOLD));
            vuelto.setVerticalAlignment(Element.ALIGN_MIDDLE);
            vuelto.setHorizontalAlignment(Element.ALIGN_CENTER);
            vuelto.setPaddingRight(1);
            vuelto.setBorderWidth(0);
			table.addCell(vuelto);
            pTable.add(table);
            //p.setIndentationLeft(5);
            document.add(pTable);
            document.add(Chunk.NEWLINE);
            document.add(ls);
            
            //OBTENER ENTERO Y DECIMAL
            BigDecimal number = new BigDecimal(venta.getImporteTotal());
            long intPart = number.longValue();
            BigDecimal fraccion = number.remainder(BigDecimal.ONE);
            
            //NUMERO EN TEXTO
            Paragraph montoText = new Paragraph("IMPORTE EN LETRAS: ", fontBOLD);
            String stringMontoText = AppUtilConverter.cantidadConLetra(String.valueOf(intPart))
            		+"CON "+fraccion+"/100 SOLES";
            montoText.setAlignment(Element.ALIGN_LEFT);
            montoText.add(new Chunk(stringMontoText, font));
			document.add(montoText);
			document.add(Chunk.NEWLINE);
            
			//QR
            Image image1;
			try {
				image1 = Image.getInstance("classpath:img/QRCode.png");
				image1.setAlignment(Element.ALIGN_CENTER);
	            image1.scaleAbsolute(60, 60);
	            //Add to document
	            document.add(image1);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			Paragraph stringFooter = new Paragraph("***IMPRESión DE PRUEBA :)***", font);
			stringFooter.setAlignment(Element.ALIGN_CENTER);
			document.add(stringFooter);

            document.close();

        }catch(DocumentException e) {
          logger.error(e.toString());
        }
        
    return new ByteArrayInputStream(out.toByteArray());
  }
}
