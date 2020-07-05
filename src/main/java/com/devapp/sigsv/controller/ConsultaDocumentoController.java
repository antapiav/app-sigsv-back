package com.devapp.sigsv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devapp.sigsv.exception.AppInternalException;
import com.devapp.sigsv.model.bean.Reniec;
import com.devapp.sigsv.model.bean.Sunat;
import com.devapp.sigsv.model.bean.response.GenericResponse;
import com.devapp.sigsv.service.ConsultaDocumentoServices;
import com.devapp.sigsv.util.AppConstantes;
import com.devapp.sigsv.util.AppUrl;
import com.devapp.sigsv.util.AppUtilCodStatus;

@CrossOrigin
@RestController
@RequestMapping(value = AppUrl.URL_CONSULTA)
public class ConsultaDocumentoController extends AbstractController {

	private ConsultaDocumentoServices consultaDocumentService;

    @Autowired
	public void setConsultaService(ConsultaDocumentoServices consultaDocumentService) {
		this.consultaDocumentService = consultaDocumentService;
    }
    
    @GetMapping(value = AppUrl.URL_RENIEC)
    public GenericResponse<Reniec> getDocumnetReniec(@PathVariable(AppConstantes.PARAM_PATH_NUM_DOCUMENT) String numDocument) 
            throws AppInternalException {
    	GenericResponse<Reniec> response = consultaDocumentService.consultaDocumentoReniec(numDocument);
        response.setMessage(getValueMessage(AppUtilCodStatus.codStatus(response.getCode())));
        return response;
    }
    
    @GetMapping(value = AppUrl.URL_SUNAT)
    public GenericResponse<Sunat> getDocumnetSunat(@PathVariable(AppConstantes.PARAM_PATH_NUM_DOCUMENT) String numDocument) 
            throws AppInternalException {
        GenericResponse<Sunat> response = consultaDocumentService.consultaDocumentoSunat(numDocument);
        response.setMessage(getValueMessage(AppUtilCodStatus.codStatus(response.getCode())));
        return response;
    }

}
