package com.devapp.sigsv.client.impl;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import com.devapp.sigsv.client.ConsultaDocumentoWebClient;
import com.devapp.sigsv.model.bean.Reniec;
import com.devapp.sigsv.util.AppConstantes;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class ConsultaDocumentWebClient implements ConsultaDocumentoWebClient{
	  
	@Autowired
	private Environment env;
    /*
     * public ConsultaDocumentWebClient(WebClient defaultWebClient) {
        this.defaultWebClient = defaultWebClient;
    }*/
        
	public Flux<Reniec> webClientReniec(String numDocumento) {
		return null;
	}

	@Override
	public Mono<ClientResponse> webClientSunat(String numDocumento) {
		WebClient webClient = WebClient.create(env.getProperty(AppConstantes.URL_CONSULTA_SUNAT));
		Mono<ClientResponse> result = webClient.get()
		        .uri(numDocumento)
		        .exchange()
		        .timeout(Duration.ofMillis(Long.parseLong(env.getProperty(AppConstantes.REQUEST_TIME_MILIS))));
		return result;
	}	

}
