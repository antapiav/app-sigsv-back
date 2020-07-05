package com.devapp.sigsv.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import com.devapp.sigsv.exception.AppInternalException;
import com.devapp.sigsv.model.bean.Cliente;
import com.devapp.sigsv.model.bean.ProveedorCategoria;
import com.devapp.sigsv.model.bean.response.GenericResponse;
import com.devapp.sigsv.service.ClienteService;
import com.devapp.sigsv.util.AppUrl;
import com.devapp.sigsv.util.AppUtilCodStatus;
import com.devapp.sigsv.util.AppUtilConverter;
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
@RequestMapping(value = AppUrl.URL_CLIENTE)
public class ClienteController extends AbstractController {
    private static final Logger log = LoggerFactory.getLogger(AppUtilConverter.class);

    private ClienteService clienteService;

    @Autowired
	public void setClienteService(ClienteService clienteService) {
		this.clienteService = clienteService;
    }

    @GetMapping(value = AppUrl.URL_LST_GET_PAGINADO)
    public Page<Cliente> getLstCliente(Pageable pageable,
            @RequestParam(AppConstantes.PARAM_PATH_NAME) String name,
            @RequestParam(AppConstantes.PARAM_PATH_OPERATOR) String operator,
            @RequestParam(AppConstantes.PARAM_PATH_VALUE) String value) {
        return clienteService.lstClientePaginado(pageable, name, operator, value);
    }
    
    @GetMapping(value = AppUrl.URL_LST_GET)
    public GenericResponse<List<Cliente>> lstCliente(@RequestParam(AppConstantes.PARAM_PATH_VALUE) String value){
    	GenericResponse<List<Cliente>> response = clienteService.lstCliente(value);
        response.setCode(HttpStatus.OK.value());
        response.setMessage(getValueMessage(AppMessages.GENERIC_SUCCESS));
        return response;
    }

    @GetMapping(value = AppUrl.URL_DETALLE)
    public GenericResponse<Cliente> getCliente(@PathVariable(AppConstantes.PARAM_PATH_ID) Long id) 
            throws AppInternalException {
    	
        GenericResponse<Cliente> response = clienteService.detalleCliente(id);
        response.setCode((response.getCode()==null)?HttpStatus.OK.value():response.getCode());
        response.setMessage(getValueMessage(AppUtilCodStatus.codStatus(response.getCode())));
        return response;
    }

    @PostMapping(value = AppUrl.URL_INSERTAR)
    public GenericResponse<Cliente> saveCliente(@Valid @RequestBody Cliente req) 
            throws AppInternalException {
        GenericResponse<Cliente> response = clienteService.saveCliente(req);
        response.setCode((response.getCode()==null)?HttpStatus.OK.value():response.getCode());
        response.setMessage(getValueMessage(AppUtilCodStatus.codStatus(response.getCode())));
		return response;
    }

    @PutMapping(value = AppUrl.URL_MODIFICAR)
    public GenericResponse<Cliente> updateCliente(@Valid @RequestBody Cliente req)
            throws AppInternalException {
        GenericResponse<Cliente> response = clienteService.updateCliente(req);
        response.setCode((response.getCode()==null)?HttpStatus.OK.value():response.getCode());
        response.setMessage(getValueMessage(AppUtilCodStatus.codStatus(response.getCode())));
        return response;
    }

    @GetMapping(value = AppUrl.URL_IND_ACTIVO)
    public GenericResponse<Cliente> indActivoCliente(@PathVariable(AppConstantes.PARAM_PATH_ID) Long id)
            throws AppInternalException {
		GenericResponse<Cliente> response = clienteService.indActivoCliente(id);
		response.setCode(HttpStatus.OK.value());
		response.setMessage(getValueMessage(AppMessages.GENERIC_SUCCESS));
		return response;
	}
}