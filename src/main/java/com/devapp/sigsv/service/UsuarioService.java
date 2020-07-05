package com.devapp.sigsv.service;

import com.devapp.sigsv.exception.AppInternalException;
import com.devapp.sigsv.model.bean.Usuario;
import com.devapp.sigsv.model.bean.response.GenericResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UsuarioService {
    Page<Usuario> lstUsuarioPaginado(Pageable pageable, String tipo, String dato);
    GenericResponse<Usuario> saveUsuario(Usuario req) throws AppInternalException;
    GenericResponse<Usuario> updateUsuario(Usuario req) throws AppInternalException;
    GenericResponse<Usuario> detalleUsuario(Long id) throws AppInternalException;
    GenericResponse<Usuario> indActivoUsuario(Long id, Boolean ind) throws AppInternalException;    
}