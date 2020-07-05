package com.devapp.sigsv.service;

import java.util.List;

import com.devapp.sigsv.exception.AppInternalException;
import com.devapp.sigsv.model.bean.ProductoCategoria;
import com.devapp.sigsv.model.bean.response.GenericResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductoCategoriaService {
    Page<ProductoCategoria> lstProductoCategoriaPaginado(Pageable pageable, String name, String operator, String value);
    GenericResponse<List<ProductoCategoria>> lstProductoCategoria(String value) throws AppInternalException;
    GenericResponse<ProductoCategoria> saveProductoCategoria(ProductoCategoria productoCategoria) throws AppInternalException;
    GenericResponse<ProductoCategoria> updateProductoCategoria(ProductoCategoria productoCategoria) throws AppInternalException;
    GenericResponse<ProductoCategoria> detalleProductoCategoria(Long id) throws AppInternalException;
    GenericResponse<ProductoCategoria> indActivoProductoCategoria(Long id) throws AppInternalException;
    GenericResponse<?> getChartCircle() throws AppInternalException;
}