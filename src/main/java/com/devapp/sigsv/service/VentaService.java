package com.devapp.sigsv.service;

import java.util.List;
import java.util.Map;

import com.devapp.sigsv.exception.AppInternalException;
import com.devapp.sigsv.model.bean.DetalleVenta;
import com.devapp.sigsv.model.bean.Pagination;
import com.devapp.sigsv.model.bean.Venta;
import com.devapp.sigsv.model.bean.response.GenericResponse;

public interface VentaService {
	GenericResponse<?> getLsVentaPaginado(Pagination pagination);
    GenericResponse<Venta> saveVenta(Venta req) throws AppInternalException;
    GenericResponse<Venta> detalleVenta(Long id) throws AppInternalException;
    GenericResponse<Venta> indActivoVenta(Long id) throws AppInternalException;
}