package com.devapp.sigsv.service;

import com.devapp.sigsv.exception.AppInternalException;
import com.devapp.sigsv.model.bean.Cliente;
import com.devapp.sigsv.model.bean.response.GenericResponse;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClienteService {
    Page<Cliente> lstClientePaginado(Pageable pageable, String name, String operator, String value);
    GenericResponse<Cliente> saveCliente(Cliente req) throws AppInternalException;
    GenericResponse<List<Cliente>> lstCliente(String idCliente);
    GenericResponse<Cliente> updateCliente(Cliente req) throws AppInternalException;
    GenericResponse<Cliente> detalleCliente(Long id) throws AppInternalException;
    GenericResponse<Cliente> indActivoCliente(Long id) throws AppInternalException;  
}