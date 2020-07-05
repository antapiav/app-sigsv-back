package com.devapp.sigsv.controller;

import javax.validation.Valid;

import com.devapp.sigsv.exception.AppInternalException;
import com.devapp.sigsv.model.bean.Usuario;
import com.devapp.sigsv.model.bean.response.GenericResponse;
import com.devapp.sigsv.service.UsuarioService;
import com.devapp.sigsv.util.AppMessages;
import com.devapp.sigsv.util.AppConstantes;
import com.devapp.sigsv.util.AppUrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = AppUrl.URL_USUARIO)
public class UsuarioController extends AbstractController {

    private UsuarioService usuarioService;

    @Autowired
	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
    }
    
    @GetMapping(value = AppUrl.URL_LST_GET_PAGINADO)
    public Page<Usuario> getLstUsuario(Pageable pageable,
            @RequestParam(AppConstantes.PARAM_PATH_tipo) String tipo,
            @RequestParam(AppConstantes.PARAM_PATH_dato) String dato) {
		return usuarioService.lstUsuarioPaginado(pageable, tipo, dato);
    }

    @GetMapping(value = AppUrl.URL_DETALLE)
    public GenericResponse<Usuario> getUsuario(@PathVariable(AppConstantes.PARAM_PATH_ID) Long id) 
            throws AppInternalException {
        GenericResponse<Usuario> response = usuarioService.detalleUsuario(id);
        response.setCode(HttpStatus.OK.value());
        response.setMessage(getValueMessage(AppMessages.GENERIC_SUCCESS));
        return response;
    }
    
    @PostMapping(value = AppUrl.URL_INSERTAR)
    public GenericResponse<Usuario> saveUsuario(@Valid @RequestBody Usuario req) 
            throws AppInternalException {
        usuarioService.saveUsuario(req);
		GenericResponse<Usuario> response = new GenericResponse<>();
		response.setCode(HttpStatus.OK.value());
		response.setMessage(getValueMessage(AppMessages.GENERIC_SUCCESS));
		return response;
    }

    @PutMapping(value = AppUrl.URL_MODIFICAR)
    public GenericResponse<Usuario> updateUsuario(@Valid @RequestBody Usuario req)
            throws AppInternalException {
        usuarioService.updateUsuario(req);
        GenericResponse<Usuario> response = new GenericResponse<>();
        response.setCode(HttpStatus.OK.value());
        response.setMessage(getValueMessage(AppMessages.GENERIC_SUCCESS));
        return response;
    }

    @GetMapping(value = AppUrl.URL_IND_ACTIVO)
    public GenericResponse<Usuario> indActivoUsuario(@PathVariable(AppConstantes.PARAM_PATH_ID) Long id, @PathVariable (AppConstantes.PARAM_PATH_IND_ACTIVO) Boolean ind)
            throws AppInternalException {
		GenericResponse<Usuario> response = usuarioService.indActivoUsuario(id, ind);
		response.setCode(HttpStatus.OK.value());
		response.setMessage(getValueMessage(AppMessages.GENERIC_SUCCESS));
		return response;
	}    
}