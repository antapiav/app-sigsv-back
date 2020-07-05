package com.devapp.sigsv.controller;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devapp.sigsv.exception.AppInternalException;
import com.devapp.sigsv.model.bean.response.GenericResponse;
import com.devapp.sigsv.service.MultitablaService;
import com.devapp.sigsv.util.AppMessages;
import com.devapp.sigsv.util.AppUrl;

@CrossOrigin
@RestController
@RequestMapping(value = AppUrl.URL_PARAMETRO)
public class MultitablaController extends AbstractController {

	private MultitablaService multitablaService;
    
    @Autowired
	public void setMultitablaService(MultitablaService multitablaService) {
		this.multitablaService = multitablaService;
    }
    
    @GetMapping(value = AppUrl.URL_TIPO_DOCUMENTO)
    public GenericResponse<?> getLstTipoDocumento() throws AppInternalException {
    	GenericResponse<?> response = multitablaService.getLstTipoDocumento();
        response.setCode(HttpStatus.OK.value());
        response.setMessage(getValueMessage(AppMessages.GENERIC_SUCCESS));
        return response;
    }
    
    @GetMapping(value = AppUrl.URL_TIPO_COMPROBANTE)
    public GenericResponse<?> getLstTipoComprobante() throws AppInternalException {
    	GenericResponse<?> response = multitablaService.getLstTipoComprobante();
        response.setCode(HttpStatus.OK.value());
        response.setMessage(getValueMessage(AppMessages.GENERIC_SUCCESS));
        return response;
    }
    
    @GetMapping(value = AppUrl.URL_UNIDAD_MEDIDA)
    public GenericResponse<?> getLstUnidadMedida() throws AppInternalException {
    	GenericResponse<?> response = multitablaService.getLstUnidadMedida();
        response.setCode(HttpStatus.OK.value());
        response.setMessage(getValueMessage(AppMessages.GENERIC_SUCCESS));
        return response;
    }
    
    @GetMapping(value = AppUrl.URL_TIPO_USUARIO)
    public GenericResponse<?> getLstTipoUsuario() throws AppInternalException {
    	GenericResponse<?> response = multitablaService.getLstTipoUsuario();
        response.setCode(HttpStatus.OK.value());
        response.setMessage(getValueMessage(AppMessages.GENERIC_SUCCESS));
        return response;
    }
    
    @GetMapping(value = AppUrl.URL_MONTO_BOLSA)
    public GenericResponse<?> getLstMontoBolsa() throws AppInternalException {
    	GenericResponse<?> response = multitablaService.getLstMontoBolsa();
        response.setCode(HttpStatus.OK.value());
        response.setMessage(getValueMessage(AppMessages.GENERIC_SUCCESS));
        return response;
    }
}
