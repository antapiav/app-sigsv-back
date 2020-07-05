package com.devapp.sigsv.controller;

import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import com.devapp.sigsv.exception.AppInternalException;
import com.devapp.sigsv.model.bean.Pagination;
import com.devapp.sigsv.model.bean.Venta;
import com.devapp.sigsv.model.bean.response.GenericResponse;
import com.devapp.sigsv.service.VentaService;
import com.devapp.sigsv.util.AppConstantes;
import com.devapp.sigsv.util.AppMessages;
import com.devapp.sigsv.util.AppUrl;

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
@RequestMapping(value = AppUrl.URL_VENTA)
public class VentaController extends AbstractController {
	
	public Logger log = LoggerFactory.getLogger(VentaController.class); 

    private VentaService ventaService;

    @Autowired
	public void setVentaService(VentaService ventaService) {
		this.ventaService = ventaService;
    }
    
    @PostMapping(value = AppUrl.URL_LST_PAGINADO_FILTER)
	public GenericResponse<?> getLsVentaPaginado(@RequestBody Pagination pagination) {
		GenericResponse<?> response = ventaService.getLsVentaPaginado(pagination);
		response.setCode(HttpStatus.OK.value());
		response.setMessage(getValueMessage(AppMessages.GENERIC_SUCCESS));
		return response;
	}
    
    @PostMapping(value = AppUrl.URL_INSERTAR)
    public GenericResponse<Venta> saveVenta(@Valid @RequestBody Venta req) 
            throws AppInternalException {
		GenericResponse<Venta> response = ventaService.saveVenta(req);
		response.setCode(HttpStatus.OK.value());
		response.setMessage(getValueMessage(AppMessages.GENERIC_SUCCESS));
		return response;
    }

    @GetMapping(value = AppUrl.URL_DETALLE)
    public GenericResponse<Venta> getVenta(@PathVariable(AppConstantes.PARAM_PATH_ID) Long id) 
            throws AppInternalException {
        GenericResponse<Venta> response = ventaService.detalleVenta(id);
        response.setCode(HttpStatus.OK.value());
        response.setMessage(getValueMessage(AppMessages.GENERIC_SUCCESS));
        return response;
    }

    @GetMapping(value = AppUrl.URL_IND_ACTIVO)
    public GenericResponse<Venta> indActivoVenta(@PathVariable(AppConstantes.PARAM_PATH_ID) Long id)
            throws AppInternalException {
		GenericResponse<Venta> response = ventaService.indActivoVenta(id);
		response.setCode(HttpStatus.OK.value());
		response.setMessage(getValueMessage(AppMessages.GENERIC_SUCCESS));
		return response;
	}    
}