package com.devapp.sigsv.service;

import com.devapp.sigsv.exception.AppInternalException;
import com.devapp.sigsv.model.bean.Producto;
import com.devapp.sigsv.model.bean.Stock;
import com.devapp.sigsv.model.bean.response.GenericResponse;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductoService {
    Page<Stock> lstProductoPaginado(Pageable pageable, String name, String operator, String value, String idProductoCategoria);
    GenericResponse<List<Producto>> lstProducto(String value) throws AppInternalException;
    GenericResponse<Producto> saveProducto(Producto producto) throws AppInternalException;
    GenericResponse<Producto> updateProducto(Producto producto) throws AppInternalException;
    GenericResponse<Stock> detalleProducto(Long id) throws AppInternalException;
    GenericResponse<?> getChartBar() throws AppInternalException;
    GenericResponse<?> getChartLine() throws AppInternalException;
    GenericResponse<Producto> indActivoProducto(Long id) throws AppInternalException;
}