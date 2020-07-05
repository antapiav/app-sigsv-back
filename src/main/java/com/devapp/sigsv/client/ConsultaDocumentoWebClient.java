package com.devapp.sigsv.client;

import org.springframework.web.reactive.function.client.ClientResponse;

import com.devapp.sigsv.model.bean.Reniec;
import com.devapp.sigsv.model.bean.Sunat;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ConsultaDocumentoWebClient {
    Flux<Reniec> webClientReniec(String numDocumento);
    Mono<ClientResponse> webClientSunat(String numDocumento);
}
