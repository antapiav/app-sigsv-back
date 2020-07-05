package com.devapp.sigsv.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import com.devapp.sigsv.exception.AppInternalException;
import com.devapp.sigsv.model.bean.ProveedorCategoria;
import com.devapp.sigsv.model.bean.response.GenericResponse;
import com.devapp.sigsv.service.ProveedorCategoriaService;
import com.devapp.sigsv.util.AppUrl;
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
@RequestMapping(value = AppUrl.URL_PROVEEDOR_CATEGORIA)
public class ProvedorCategoriaController extends AbstractController {
	private static Logger log = LoggerFactory.getLogger(AbstractController.class);

    private ProveedorCategoriaService proveedorCategoriaService;

    @Autowired
	public void setProveedorCategoriaService(ProveedorCategoriaService proveedorCategoriaService) {
		this.proveedorCategoriaService = proveedorCategoriaService;
    }

    @GetMapping(value = AppUrl.URL_LST_GET_PAGINADO)
    public Page<ProveedorCategoria> getLstProductoCategoriaPaginado(Pageable pageable,
    		@RequestParam(AppConstantes.PARAM_PATH_NAME) String name,
            @RequestParam(AppConstantes.PARAM_PATH_OPERATOR) String operator,
            @RequestParam(AppConstantes.PARAM_PATH_VALUE) String value) {
        return proveedorCategoriaService.lstProveedorCategoriaPaginado(pageable, name, operator, value);
    }

    @GetMapping(value = AppUrl.URL_LST_GET)
    public GenericResponse<List<ProveedorCategoria>> lstProductoCategoria(@RequestParam(AppConstantes.PARAM_PATH_VALUE) String value){
        GenericResponse<List<ProveedorCategoria>> response = proveedorCategoriaService.lstProveedorCategoria(value);
        response.setCode(HttpStatus.OK.value());
        response.setMessage(getValueMessage(AppMessages.GENERIC_SUCCESS));
        return response;
    }

    @GetMapping(value = AppUrl.URL_DETALLE)
    public GenericResponse<ProveedorCategoria> getProductoCategoria(@PathVariable(AppConstantes.PARAM_PATH_ID) Long id) 
            throws AppInternalException {
        GenericResponse<ProveedorCategoria> response = proveedorCategoriaService.detalleProveedorCategoría(id);
        response.setCode(HttpStatus.OK.value());
        response.setMessage(getValueMessage(AppMessages.GENERIC_SUCCESS));
        return response;
    }

    @PostMapping(value = AppUrl.URL_INSERTAR)
    public GenericResponse<ProveedorCategoria> saveProductoCategoria(@Valid @RequestBody ProveedorCategoria req) 
            throws AppInternalException {
                proveedorCategoriaService.saveProveedorCategoría(req);
		GenericResponse<ProveedorCategoria> response = new GenericResponse<>();
		response.setCode(HttpStatus.OK.value());
		response.setMessage(getValueMessage(AppMessages.GENERIC_SUCCESS));
		return response;
    }

    @PutMapping(value = AppUrl.URL_MODIFICAR)
    public GenericResponse<ProveedorCategoria> updateProductoCategoria(@Valid @RequestBody ProveedorCategoria req)
            throws AppInternalException {
                proveedorCategoriaService.updateProveedorCategoría(req);
        GenericResponse<ProveedorCategoria> response = new GenericResponse<>();
        response.setCode(HttpStatus.OK.value());
        response.setMessage(getValueMessage(AppMessages.GENERIC_SUCCESS));
        return response;
    }

    @GetMapping(value = AppUrl.URL_IND_ACTIVO)
    public GenericResponse<ProveedorCategoria> indActivoCliente(@PathVariable(AppConstantes.PARAM_PATH_ID) Long id)
            throws AppInternalException {
		GenericResponse<ProveedorCategoria> response = proveedorCategoriaService.indActivoProveedorCategoria(id);
		response.setCode(HttpStatus.OK.value());
		response.setMessage(getValueMessage(AppMessages.GENERIC_SUCCESS));
		return response;
	}
}