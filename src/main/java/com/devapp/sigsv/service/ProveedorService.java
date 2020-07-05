package com.devapp.sigsv.service;

import com.devapp.sigsv.exception.AppInternalException;
import com.devapp.sigsv.model.bean.Proveedor;
import com.devapp.sigsv.model.bean.response.GenericResponse;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProveedorService {
    Page<Proveedor> lstProveedorPaginado(Pageable pageable, String name, String operator, String value, String idProveedorCategoria);
    GenericResponse<List<Proveedor>> lstProveedor(String idProveedor);
    GenericResponse<Proveedor> saveProveedor(Proveedor proveedor) throws AppInternalException;
    GenericResponse<Proveedor> updateProveedor(Proveedor proveedor) throws AppInternalException;
    GenericResponse<Proveedor> detalleProveedor(Long id) throws AppInternalException;
    GenericResponse<Proveedor> indActivoProveedor(Long id) throws AppInternalException;
}