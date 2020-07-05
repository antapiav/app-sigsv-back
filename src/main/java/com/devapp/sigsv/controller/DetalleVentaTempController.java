package com.devapp.sigsv.controller;

import java.util.List;

import javax.validation.Valid;

import com.devapp.sigsv.exception.AppInternalException;
import com.devapp.sigsv.model.bean.DetalleVentaTemp;
import com.devapp.sigsv.model.bean.response.GenericResponse;
import com.devapp.sigsv.service.DetalleVentaTempService;
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
@RequestMapping(value = AppUrl.URL_DETALLE_VENTA_TEMP)
public class DetalleVentaTempController extends AbstractController {

    private DetalleVentaTempService detalleVentaTempService;

    @Autowired
	public void setDetalleVentaTempService(DetalleVentaTempService detalleVentaTempService) {
		this.detalleVentaTempService = detalleVentaTempService;
    }
    
    @GetMapping(value = AppUrl.URL_LST_GET)
    public GenericResponse<List<DetalleVentaTemp>> getDetalleVentaTemp(@PathVariable(AppConstantes.PARAM_PATH_ID) Long id) 
            throws AppInternalException {
        GenericResponse<List<DetalleVentaTemp>> response = detalleVentaTempService.lstDetalleVenta();
        response.setCode(HttpStatus.OK.value());
        response.setMessage(getValueMessage(AppMessages.GENERIC_SUCCESS));
        return response;
    }

    @PostMapping(value = AppUrl.URL_INSERTAR)
    public GenericResponse<DetalleVentaTemp> saveDetalleVentaTemp(@Valid @RequestBody DetalleVentaTemp req) 
            throws AppInternalException {
                detalleVentaTempService.saveDetalleVentaTemp(req);
		GenericResponse<DetalleVentaTemp> response = new GenericResponse<>();
		response.setCode(HttpStatus.OK.value());
		response.setMessage(getValueMessage(AppMessages.GENERIC_SUCCESS));
		return response;
    }

    @DeleteMapping(value = AppUrl.URL_ELIMINAR)
    public GenericResponse<DetalleVentaTemp> deleteDetalleVentaTemp(@PathVariable Long id)
            throws AppInternalException {
                detalleVentaTempService.deleteDetalleVentaTemp(id);
        GenericResponse<DetalleVentaTemp> response = new GenericResponse<>();
        response.setCode(HttpStatus.OK.value());
        response.setMessage(getValueMessage(AppMessages.GENERIC_SUCCESS));
        return response;
    }
}