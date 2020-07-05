package com.devapp.sigsv.service;

import java.util.List;

import com.devapp.sigsv.exception.AppInternalException;
import com.devapp.sigsv.model.bean.DetalleVentaTemp;
import com.devapp.sigsv.model.bean.response.GenericResponse;

public interface DetalleVentaTempService {
    GenericResponse<List<DetalleVentaTemp>> lstDetalleVenta()  throws AppInternalException;
    GenericResponse<DetalleVentaTemp> saveDetalleVentaTemp(DetalleVentaTemp detalleVentaTemp) throws AppInternalException;
    GenericResponse<DetalleVentaTemp> deleteDetalleVentaTemp(Long id) throws AppInternalException;
}