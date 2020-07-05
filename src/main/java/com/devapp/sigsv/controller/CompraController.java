package com.devapp.sigsv.controller;

import java.util.List;

import javax.validation.Valid;

import com.devapp.sigsv.exception.AppInternalException;
import com.devapp.sigsv.model.bean.Compra;
import com.devapp.sigsv.model.bean.Pagination;
import com.devapp.sigsv.model.bean.response.GenericResponse;
import com.devapp.sigsv.service.CompraService;
import com.devapp.sigsv.util.AppMessages;
import com.devapp.sigsv.util.AppConstantes;
import com.devapp.sigsv.util.AppUrl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(value = AppUrl.URL_COMPRA)
public class CompraController extends AbstractController {
	
	public Logger log = LoggerFactory.getLogger(this.getClass());

    private CompraService compraService;

    @Autowired
	public void setCompraService(CompraService compraService) {
		this.compraService = compraService;
    }

    @PostMapping(value = AppUrl.URL_LST_PAGINADO_FILTER)
    public GenericResponse<?> getLstCompraPaginado(@RequestBody Pagination pagination) {
		GenericResponse<?> response = compraService.getLstCompraPaginado(pagination);
		response.setCode(HttpStatus.OK.value());
		response.setMessage(getValueMessage(AppMessages.GENERIC_SUCCESS));
		return response;
    }

    @GetMapping(value = AppUrl.URL_DETALLE)
    public GenericResponse<Compra> getCompra(@PathVariable(AppConstantes.PARAM_PATH_ID) Long id) 
            throws AppInternalException {
        GenericResponse<Compra> response = compraService.detalleCompra(id);
        response.setCode(HttpStatus.OK.value());
        response.setMessage(getValueMessage(AppMessages.GENERIC_SUCCESS));
        return response;
    }
    
    @PostMapping(value = AppUrl.URL_INSERTAR)
    public GenericResponse<Compra> saveCompra(@Valid @RequestBody Compra req) 
            throws AppInternalException {
        compraService.saveCompra(req);
		GenericResponse<Compra> response = new GenericResponse<>();
		response.setCode(HttpStatus.OK.value());
		response.setMessage(getValueMessage(AppMessages.GENERIC_SUCCESS));
		return response;
    }

    @GetMapping(value = AppUrl.URL_IND_ACTIVO)
    public GenericResponse<Compra> indActivoCompra(@PathVariable(AppConstantes.PARAM_PATH_ID) Long id)
            throws AppInternalException {
		GenericResponse<Compra> response = compraService.indActivoCompra(id);
		response.setCode(HttpStatus.OK.value());
		response.setMessage(getValueMessage(AppMessages.GENERIC_SUCCESS));
		return response;
	}
    
}