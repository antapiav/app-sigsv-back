package com.devapp.sigsv.controller;

import java.util.List;

import javax.validation.Valid;

import com.devapp.sigsv.exception.AppInternalException;
import com.devapp.sigsv.model.bean.ProductoCategoria;
import com.devapp.sigsv.model.bean.response.GenericResponse;
import com.devapp.sigsv.service.ProductoCategoriaService;
import com.devapp.sigsv.util.AppUrl;
import com.devapp.sigsv.util.AppUtilCodStatus;
import com.devapp.sigsv.util.AppConstantes;
import com.devapp.sigsv.util.AppMessages;

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
@RequestMapping(value = AppUrl.URL_PRODUCTO_CATEGORIA)
public class ProductoCategoriaController extends AbstractController {
    
    private ProductoCategoriaService productoCategoriaService;

    @Autowired
	public void setProductoCategoriaService(ProductoCategoriaService productoCategoriaService) {
		this.productoCategoriaService = productoCategoriaService;
    }

    @GetMapping(value = AppUrl.URL_LST_GET_PAGINADO)
    public Page<ProductoCategoria> getLstProductoCategoriaPaginado(Pageable pageable,
    		@RequestParam(AppConstantes.PARAM_PATH_NAME) String name,
            @RequestParam(AppConstantes.PARAM_PATH_OPERATOR) String operator,
            @RequestParam(AppConstantes.PARAM_PATH_VALUE) String value) {
        return productoCategoriaService.lstProductoCategoriaPaginado(pageable, name, operator, value);
    }

    @GetMapping(value = AppUrl.URL_LST_GET)
    public GenericResponse<List<ProductoCategoria>> lstProductoCategoria(@RequestParam(AppConstantes.PARAM_PATH_VALUE) String value) 
            throws AppInternalException {
        GenericResponse<List<ProductoCategoria>> response = productoCategoriaService.lstProductoCategoria(value);
        response.setCode(HttpStatus.OK.value());
        response.setMessage(getValueMessage(AppMessages.GENERIC_SUCCESS));
        return response;
    }

    @GetMapping(value = AppUrl.URL_DETALLE)
    public GenericResponse<ProductoCategoria> getProductoCategoria(@PathVariable(AppConstantes.PARAM_PATH_ID) Long id) 
            throws AppInternalException {
        GenericResponse<ProductoCategoria> response = productoCategoriaService.detalleProductoCategoria(id);
        response.setCode(HttpStatus.OK.value());
        response.setMessage(getValueMessage(AppMessages.GENERIC_SUCCESS));
        return response;
    }

    @PostMapping(value = AppUrl.URL_INSERTAR)
    public GenericResponse<ProductoCategoria> saveProductoCategoria(@Valid @RequestBody ProductoCategoria req) 
            throws AppInternalException {
                productoCategoriaService.saveProductoCategoria(req);
		GenericResponse<ProductoCategoria> response = new GenericResponse<>();
		response.setCode(HttpStatus.OK.value());
		response.setMessage(getValueMessage(AppMessages.GENERIC_SUCCESS));
		return response;
    }

    @PutMapping(value = AppUrl.URL_MODIFICAR)
    public GenericResponse<ProductoCategoria> updateProductoCategoria(@Valid @RequestBody ProductoCategoria req)
            throws AppInternalException {
                productoCategoriaService.updateProductoCategoria(req);
        GenericResponse<ProductoCategoria> response = new GenericResponse<>();
        response.setCode(HttpStatus.OK.value());
        response.setMessage(getValueMessage(AppMessages.GENERIC_SUCCESS));
        return response;
    }

    @GetMapping(value = AppUrl.URL_IND_ACTIVO)
    public GenericResponse<ProductoCategoria> indActivoCliente(@PathVariable(AppConstantes.PARAM_PATH_ID) Long id)
            throws AppInternalException {
		GenericResponse<ProductoCategoria> response = productoCategoriaService.indActivoProductoCategoria(id);
		response.setCode(HttpStatus.OK.value());
		response.setMessage(getValueMessage(AppMessages.GENERIC_SUCCESS));
		return response;
	}
    
    @GetMapping(value = AppUrl.URL_LST_CHART_CIRCLE)
    public GenericResponse<?> getChartCircle()
            throws AppInternalException {
        GenericResponse<?> response = productoCategoriaService.getChartCircle();
        response.setCode((response.getCode()==null)?HttpStatus.OK.value():response.getCode());
        response.setMessage(getValueMessage(AppUtilCodStatus.codStatus(response.getCode())));
        return response;
    }
}