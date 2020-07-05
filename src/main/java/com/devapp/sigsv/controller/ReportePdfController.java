package com.devapp.sigsv.controller;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devapp.sigsv.exception.AppInternalException;
import com.devapp.sigsv.model.bean.Venta;
import com.devapp.sigsv.model.entity.SgvVenta;
import com.devapp.sigsv.repository.SgvVentaRepository;
import com.devapp.sigsv.service.VentaService;
import com.devapp.sigsv.util.AppConstantes;
import com.devapp.sigsv.util.AppUrl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin
@RestController
@RequestMapping(value = AppUrl.API_REPORTE_PDF)
public class ReportePdfController {
	 private static Logger logger = LoggerFactory.getLogger(PDFGenerator.class);

	@Autowired
	private VentaService ventaService;
	
	@Autowired
	SgvVentaRepository ventaRepository;
	
	@GetMapping(value = AppUrl.API_REPORTE_PDF_VENTA)
	public ResponseEntity<InputStreamResource> exportPDF(@PathVariable(AppConstantes.PARAM_PATH_ID) Long id) throws AppInternalException{
		
		Venta venta = ventaService.detalleVenta(id).getBody();
		
		//List<SgvVenta> customers = (List<SgvVenta>) ventaRepository.findById(id).get();
		 
        ByteArrayInputStream bis = PDFGenerator.customerPDFReport(venta);
 
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION,
        		String.format(AppConstantes.FORMAT_CONTENT_INLINE, venta.getNumComprobante() + AppConstantes.STRING_SUB_GUION +
        				venta.getCliente().getNombre() + AppConstantes.STRING_SUB_GUION +
        				venta.getCliente().getApPaterno() + AppConstantes.STRING_SUB_GUION +
        				venta.getCliente().getApMaterno(), AppConstantes.PARAM_PDF_EXTENCION));//"inline; filename=customers.pdf");
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, AppConstantes.CONTENT_DISPOSITION);		
        headers.add(HttpHeaders.CONTENT_TYPE, AppConstantes.PARAM_PDF_FILE);//"application/pdf"	
		return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
	}

}
