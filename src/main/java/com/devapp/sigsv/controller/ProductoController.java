package com.devapp.sigsv.controller;

import java.util.List;

import javax.validation.Valid;

import com.devapp.sigsv.exception.AppInternalException;
import com.devapp.sigsv.model.bean.Producto;
import com.devapp.sigsv.model.bean.ProductoCategoria;
import com.devapp.sigsv.model.bean.Stock;
import com.devapp.sigsv.model.bean.response.GenericResponse;
import com.devapp.sigsv.service.ProductoService;
import com.devapp.sigsv.util.AppUrl;
import com.devapp.sigsv.util.AppUtilCodStatus;
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
@RequestMapping(value = AppUrl.URL_PRODUCTO)
public class ProductoController extends AbstractController {
	private static Logger log = LoggerFactory.getLogger(AbstractController.class);

    private ProductoService productoService;

    @Autowired
	public void setProductoService(ProductoService productoService) {
		this.productoService = productoService;
    }

    @GetMapping(value = AppUrl.URL_LST_GET_PAGINADO)
    public Page<Stock> getLstProducto(Pageable pageable,
    		@RequestParam(AppConstantes.PARAM_PATH_NAME) String name,
            @RequestParam(AppConstantes.PARAM_PATH_OPERATOR) String operator,
            @RequestParam(AppConstantes.PARAM_PATH_VALUE) String value,
            @RequestParam(AppConstantes.ID_PRODUCTO_CATEGORIA) String idProductoCategoria) {
		return productoService.lstProductoPaginado(pageable, name, operator, value, idProductoCategoria);
    }
    
    @GetMapping(value = AppUrl.URL_LST_GET)
    public GenericResponse<List<Producto>> lstProducto(
    		@RequestParam(AppConstantes.PARAM_PATH_VALUE) String value) 
            throws AppInternalException {
        GenericResponse<List<Producto>> response = productoService.lstProducto(value);
        response.setCode(HttpStatus.OK.value());
        response.setMessage(getValueMessage(AppMessages.GENERIC_SUCCESS));
        return response;
    }

    @GetMapping(value = AppUrl.URL_DETALLE)
    public GenericResponse<Stock> getProducto(@PathVariable(AppConstantes.PARAM_PATH_ID) Long id) 
            throws AppInternalException {
        GenericResponse<Stock> response = productoService.detalleProducto(id);
        response.setCode((response.getCode()==null)?HttpStatus.OK.value():response.getCode());
        response.setMessage(getValueMessage(AppUtilCodStatus.codStatus(response.getCode())));
        return response;
    }
    
    @GetMapping(value = AppUrl.URL_LST_CHART_BAR)
    public GenericResponse<?> getChartBar()
            throws AppInternalException {
        GenericResponse<?> response = productoService.getChartBar();
        response.setCode((response.getCode()==null)?HttpStatus.OK.value():response.getCode());
        response.setMessage(getValueMessage(AppUtilCodStatus.codStatus(response.getCode())));
        return response;
    }
    
    @GetMapping(value = AppUrl.URL_LST_CHART_LINE)
    public GenericResponse<?> getChartLine()
            throws AppInternalException {
        GenericResponse<?> response = productoService.getChartLine();
        response.setCode((response.getCode()==null)?HttpStatus.OK.value():response.getCode());
        response.setMessage(getValueMessage(AppUtilCodStatus.codStatus(response.getCode())));
        return response;
    }
    
    @PostMapping(value = AppUrl.URL_INSERTAR)
    public GenericResponse<Producto> saveProducto(@Valid @RequestBody Producto req) 
            throws AppInternalException {
		GenericResponse<Producto> response = productoService.saveProducto(req);
		response.setCode((response.getCode()==null)?HttpStatus.OK.value():response.getCode());
		response.setMessage(getValueMessage(AppUtilCodStatus.codStatus(response.getCode())));
		return response;
    }

    @PutMapping(value = AppUrl.URL_MODIFICAR)
    public GenericResponse<Producto> updateProducto(@Valid @RequestBody Producto req)
            throws AppInternalException {
        productoService.updateProducto(req);
        GenericResponse<Producto> response = new GenericResponse<>();
        response.setCode((response.getCode()==null) ? HttpStatus.OK.value() : response.getCode());
		response.setMessage(getValueMessage(AppUtilCodStatus.codStatus(response.getCode())));
        return response;
    }

    @GetMapping(value = AppUrl.URL_IND_ACTIVO)
    public GenericResponse<Producto> indActivoProducto(@PathVariable(AppConstantes.PARAM_PATH_ID) Long id)
            throws AppInternalException {
		GenericResponse<Producto> response = productoService.indActivoProducto(id);
		response.setCode((response.getCode()==null) ? HttpStatus.OK.value() : response.getCode());
		response.setMessage(getValueMessage(AppUtilCodStatus.codStatus(response.getCode())));
		return response;
	}
    
}