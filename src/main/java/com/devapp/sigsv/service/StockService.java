package com.devapp.sigsv.service;

import com.devapp.sigsv.exception.AppInternalException;
import com.devapp.sigsv.model.bean.Stock;
import com.devapp.sigsv.model.bean.response.GenericResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StockService {
    Page<Stock> lstStockPaginado(Pageable pageable, String name, String operator, String value, String idProductoCategoria);
    GenericResponse<Stock> saveStock(Stock stock) throws AppInternalException;
    GenericResponse<Stock> updateStock(Stock stock) throws AppInternalException;
    GenericResponse<Stock> detalleStock(Long id) throws AppInternalException;
    //GenericResponse<Stock> indActivoStock(Long id, Boolean ind) throws AppInternalException;
}