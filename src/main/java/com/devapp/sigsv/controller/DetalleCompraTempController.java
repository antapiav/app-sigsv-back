package com.devapp.sigsv.controller;

import java.util.List;

import javax.validation.Valid;

import com.devapp.sigsv.exception.AppInternalException;
import com.devapp.sigsv.model.bean.DetalleCompraTemp;
import com.devapp.sigsv.model.bean.response.GenericResponse;
import com.devapp.sigsv.service.DetalleCompraTempService;
import com.devapp.sigsv.util.AppUrl;
import com.devapp.sigsv.util.AppConstantes;
import com.devapp.sigsv.util.AppMessages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = AppUrl.URL_DETALLE_COMPRA_TEMP)
public class DetalleCompraTempController extends AbstractController {

    private DetalleCompraTempService detalleCompraTempService;

    @Autowired
	public void setDetalleCompraTempService(DetalleCompraTempService detalleCompraTempService) {
		this.detalleCompraTempService = detalleCompraTempService;
    }

    @GetMapping(value = AppUrl.URL_LST_GET)
    public GenericResponse<List<DetalleCompraTemp>> getDetalleCompraTemp(@PathVariable(AppConstantes.PARAM_PATH_ID) Long id) 
            throws AppInternalException {
        GenericResponse<List<DetalleCompraTemp>> response = detalleCompraTempService.lstDetalleCompra();
        response.setCode(HttpStatus.OK.value());
        response.setMessage(getValueMessage(AppMessages.GENERIC_SUCCESS));
        return response;
    }

    @PostMapping(value = AppUrl.URL_INSERTAR)
    public GenericResponse<DetalleCompraTemp> saveDetalleCompraTemp(@Valid @RequestBody DetalleCompraTemp req) 
            throws AppInternalException {
        detalleCompraTempService.saveDetalleCompraTemp(req);
		GenericResponse<DetalleCompraTemp> response = new GenericResponse<>();
		response.setCode(HttpStatus.OK.value());
		response.setMessage(getValueMessage(AppMessages.GENERIC_SUCCESS));
		return response;
    }

    @DeleteMapping(value = AppUrl.URL_ELIMINAR)
    public GenericResponse<DetalleCompraTemp> deleteDetalleCompraTemp(@PathVariable Long id)
            throws AppInternalException {
        detalleCompraTempService.deleteDetalleCompraTemp(id);
        GenericResponse<DetalleCompraTemp> response = new GenericResponse<>();
        response.setCode(HttpStatus.OK.value());
        response.setMessage(getValueMessage(AppMessages.GENERIC_SUCCESS));
        return response;
    }
    
}