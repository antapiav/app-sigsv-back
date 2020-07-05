package com.devapp.sigsv.service;

import java.util.List;

import com.devapp.sigsv.exception.AppInternalException;
import com.devapp.sigsv.model.bean.DetalleCompraTemp;
import com.devapp.sigsv.model.bean.response.GenericResponse;

public interface DetalleCompraTempService {
    GenericResponse<List<DetalleCompraTemp>> lstDetalleCompra()  throws AppInternalException;
    GenericResponse<DetalleCompraTemp> saveDetalleCompraTemp(DetalleCompraTemp detalleCompraTemp) throws AppInternalException;
    GenericResponse<DetalleCompraTemp> deleteDetalleCompraTemp(Long id) throws AppInternalException;
}