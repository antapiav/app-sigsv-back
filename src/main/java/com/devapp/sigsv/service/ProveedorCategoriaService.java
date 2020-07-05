package com.devapp.sigsv.service;

import java.util.List;

import com.devapp.sigsv.exception.AppInternalException;
import com.devapp.sigsv.model.bean.ProveedorCategoria;
import com.devapp.sigsv.model.bean.response.GenericResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProveedorCategoriaService {
    Page<ProveedorCategoria> lstProveedorCategoriaPaginado(Pageable pageable, String name, String operator, String value);
    GenericResponse<List<ProveedorCategoria>> lstProveedorCategoria(String value) ;
    GenericResponse<ProveedorCategoria> saveProveedorCategoría(ProveedorCategoria proveedorCategoria) throws AppInternalException;
    GenericResponse<ProveedorCategoria> updateProveedorCategoría(ProveedorCategoria proveedorCategoria) throws AppInternalException;
    GenericResponse<ProveedorCategoria> detalleProveedorCategoría(Long id) throws AppInternalException;
    GenericResponse<ProveedorCategoria> indActivoProveedorCategoria(Long id) throws AppInternalException;
}