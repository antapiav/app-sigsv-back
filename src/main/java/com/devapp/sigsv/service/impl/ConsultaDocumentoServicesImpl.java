package com.devapp.sigsv.service.impl;

import java.io.IOException;

import javax.transaction.Transactional;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;

import com.devapp.sigsv.client.ConsultaDocumentoWebClient;
import com.devapp.sigsv.exception.AppInternalException;
import com.devapp.sigsv.model.bean.Reniec;
import com.devapp.sigsv.model.bean.Sunat;
import com.devapp.sigsv.model.bean.response.GenericResponse;
import com.devapp.sigsv.service.ConsultaDocumentoServices;
import com.devapp.sigsv.util.AppConstantes;
import com.devapp.sigsv.util.AppUtilConverter;

@Service("consultaServiceImpl")
@Transactional
public class ConsultaDocumentoServicesImpl implements ConsultaDocumentoServices {
    private static final Logger log = LoggerFactory.getLogger(AppUtilConverter.class);
    
    @Autowired
	private Environment env;

    @Autowired
	private ConsultaDocumentoWebClient consultaDocument;

	@Override
	public GenericResponse<Reniec> consultaDocumentoReniec(String numDocumento) throws AppInternalException {
		Connection pageHTML = null;
		Integer statusCode = null;
		String mensaje = null;
	    Reniec reniec = new Reniec();
	    StringBuilder urlReniec = new StringBuilder(); 
	    urlReniec.append(env.getProperty(AppConstantes.URL_CONSULTA_RENIEC));
	    urlReniec.append(numDocumento);
	    try {
	    	pageHTML = Jsoup.connect(urlReniec.toString())
	    			.userAgent(AppConstantes.AGENTE_WEB_MOZILLA)
	    			.timeout(Integer.parseInt(env.getProperty(AppConstantes.REQUEST_TIME_MILIS)))
	    			.ignoreHttpErrors(true);
	    	statusCode = pageHTML.execute().statusCode();

	    	if(statusCode == 200) {
	    		String tblDatosReniec = pageHTML.get().getElementsByClass("table table-striped").outerHtml();
			    Document docHTMLFinal = Jsoup.parse(tblDatosReniec);
			    Elements elements = docHTMLFinal.getElementsByClass("text-left");
		    	if(elements.size() > 0) {
		    		reniec.setDni(numDocumento);
		    		reniec.setNombre((elements.get(0).text()!=null)?elements.get(0).text():AppConstantes.STRING_VACIO);
			    	reniec.setApPaterno((elements.get(1).text()!=null)?elements.get(1).text():AppConstantes.STRING_VACIO);
			    	reniec.setApMaterno((elements.get(2).text()!=null)?elements.get(2).text():AppConstantes.STRING_VACIO);
		    	}else {
		    		statusCode = 0;
		    	}
	    	}else {
	    		log.info("Pagina no existe");
	    	}

		} catch (IOException e) {
			log.info("Error en consulta RENEIC");
		}
	    
	    GenericResponse<Reniec> ress = new GenericResponse<>();
	    ress.setMessage(mensaje);
		ress.setCode(statusCode);
        ress.setBody(reniec);
		return ress;
	}

	@Override
	public GenericResponse<Sunat> consultaDocumentoSunat(String numDocumento) throws AppInternalException {
		GenericResponse<Sunat> ress = new GenericResponse<>();
		ClientResponse clientResponse = consultaDocument.webClientSunat(numDocumento).block();
		ress.setCode(clientResponse.statusCode().value());
        ress.setBody(clientResponse
        		.bodyToMono(Sunat.class)
        		.block());
        return ress;
	}



}
