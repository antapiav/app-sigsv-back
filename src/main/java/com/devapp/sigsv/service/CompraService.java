package com.devapp.sigsv.service;

import java.util.List;

import com.devapp.sigsv.exception.AppInternalException;
import com.devapp.sigsv.model.bean.Compra;
import com.devapp.sigsv.model.bean.Pagination;
import com.devapp.sigsv.model.bean.response.GenericResponse;

public interface CompraService {
    GenericResponse<List<Compra>> getLstCompraPaginado(Pagination pagination);
    GenericResponse<Compra> saveCompra(Compra compra) throws AppInternalException;
    GenericResponse<Compra> detalleCompra(Long id) throws AppInternalException;
    GenericResponse<Compra> indActivoCompra(Long id) throws AppInternalException;
}