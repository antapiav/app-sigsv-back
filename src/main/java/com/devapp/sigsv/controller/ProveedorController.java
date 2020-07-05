package com.devapp.sigsv.controller;

import com.devapp.sigsv.util.AppUrl;
import com.devapp.sigsv.util.AppUtilCodStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import com.devapp.sigsv.exception.AppInternalException;
import com.devapp.sigsv.model.bean.Cliente;
import com.devapp.sigsv.model.bean.Proveedor;
import com.devapp.sigsv.model.bean.response.GenericResponse;
import com.devapp.sigsv.service.ProveedorService;
import com.devapp.sigsv.util.AppConstantes;
import com.devapp.sigsv.util.AppMessages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(value = AppUrl.URL_PROVEEDOR)
public class ProveedorController extends AbstractController {

    private ProveedorService proveedorService;

    @Autowired
	public void setProveedorService(ProveedorService proveedorService) {
		this.proveedorService = proveedorService;
    }

    @GetMapping(value = AppUrl.URL_LST_GET_PAGINADO)
    public Page<Proveedor> getLstProveedor(Pageable pageable,
    		@RequestParam(AppConstantes.PARAM_PATH_NAME) String name,
            @RequestParam(AppConstantes.PARAM_PATH_OPERATOR) String operator,
            @RequestParam(AppConstantes.PARAM_PATH_VALUE) String value,
            @RequestParam(AppConstantes.ID_PROVEEDOR_CATEGORIA) String idProveedorCategoria) {
		return proveedorService.lstProveedorPaginado(pageable, name, operator, value, idProveedorCategoria);
    }
    
    @GetMapping(value = AppUrl.URL_LST_GET)
    public GenericResponse<List<Proveedor>> lstProveedor(@RequestParam(AppConstantes.PARAM_PATH_VALUE) String value){
    	GenericResponse<List<Proveedor>> response = proveedorService.lstProveedor(value);
        response.setCode(HttpStatus.OK.value());
        response.setMessage(getValueMessage(AppMessages.GENERIC_SUCCESS));
        return response;
    }

    @GetMapping(value = AppUrl.URL_DETALLE)
    public GenericResponse<Proveedor> getProveedor(@PathVariable(AppConstantes.PARAM_PATH_ID) Long id) 
            throws AppInternalException {
        GenericResponse<Proveedor> response = proveedorService.detalleProveedor(id);
        response.setCode(HttpStatus.OK.value());
        response.setMessage(getValueMessage(AppMessages.GENERIC_SUCCESS));
        return response;
    }
    
    @PostMapping(value = AppUrl.URL_INSERTAR)
    public GenericResponse<Proveedor> saveProveedor(@Valid @RequestBody Proveedor req) 
            throws AppInternalException {
		GenericResponse<Proveedor> response = proveedorService.saveProveedor(req);
		response.setCode((response.getCode()==null)?HttpStatus.OK.value():response.getCode());
		response.setMessage(getValueMessage(AppUtilCodStatus.codStatus(response.getCode())));
		return response;
    }

    @PutMapping(value = AppUrl.URL_MODIFICAR)
    public GenericResponse<Proveedor> updateProveedor(@Valid @RequestBody Proveedor req)
            throws AppInternalException {
        GenericResponse<Proveedor> response = proveedorService.updateProveedor(req);
        response.setCode((response.getCode()==null)?HttpStatus.OK.value():response.getCode());
        response.setMessage(getValueMessage(AppUtilCodStatus.codStatus(response.getCode())));
        return response;
    }

    @GetMapping(value = AppUrl.URL_IND_ACTIVO)
    public GenericResponse<Proveedor> indActivoProveedor(@PathVariable(AppConstantes.PARAM_PATH_ID) Long id)
            throws AppInternalException {
		GenericResponse<Proveedor> response = proveedorService.indActivoProveedor(id);
		response.setCode(HttpStatus.OK.value());
		response.setMessage(getValueMessage(AppMessages.GENERIC_SUCCESS));
		return response;
	}
    
}