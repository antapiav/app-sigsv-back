package com.devapp.sigsv.service.impl;

import com.devapp.sigsv.exception.AppInternalException;
import com.devapp.sigsv.model.bean.Usuario;
import com.devapp.sigsv.model.bean.response.GenericResponse;
import com.devapp.sigsv.model.entity.SgvUsuario;
import com.devapp.sigsv.repository.SgvUsuarioRepository;
import com.devapp.sigsv.service.UsuarioService;
import com.devapp.sigsv.util.AppConstantes;
import com.devapp.sigsv.util.AppMessages;
import com.devapp.sigsv.util.AppUtilConverter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("usuarioServiceImpl")
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
	@Qualifier("sgvUsuarioRepository")
    private SgvUsuarioRepository sgvUsuarioRepository;

    @Override//nota en que casos pgSgvUsuario puede ser null
    public Page<Usuario> lstUsuarioPaginado(Pageable pageable, String tipo, String dato) {    	
    	List<Usuario> lstUsuario = new ArrayList<>();
        Usuario usuario = new Usuario();
        Page<SgvUsuario> pgSgvUsuario = null;
        switch(tipo){
            case "1":
                pgSgvUsuario = sgvUsuarioRepository.findByDni(pageable, dato);
                break;
            case "2":
                pgSgvUsuario = sgvUsuarioRepository.findByName(pageable, dato);
                break;
            default:
                pgSgvUsuario = sgvUsuarioRepository.findAll(pageable);
        }

    	for (SgvUsuario sgvUsuario : pgSgvUsuario.getContent()) {
            usuario = AppUtilConverter.convertSgvUsuarioToUsuario(sgvUsuario);
            lstUsuario.add(usuario);
        }	
        Page<Usuario> ress = new PageImpl<>(lstUsuario, pgSgvUsuario.getPageable(), lstUsuario.size());  
        return ress;
    }

    @Override
    public GenericResponse<Usuario> detalleUsuario(Long id) throws AppInternalException {
        SgvUsuario sgvUsuario = sgvUsuarioRepository.findById(id).orElse(null);
        if(sgvUsuario == null){
            throw new AppInternalException(HttpStatus.NOT_FOUND, AppMessages.GENERIC_REGISTRO_NO_ENCONTRADO, AppConstantes.IS_MESSAGE_KEY);
        }
        GenericResponse<Usuario> ress = new GenericResponse<>();
        ress.setBody(AppUtilConverter.convertSgvUsuarioToUsuario(sgvUsuario));
        return ress;
    }

    @Override
    public GenericResponse<Usuario> saveUsuario(Usuario usuario) throws AppInternalException {
        sgvUsuarioRepository.save(AppUtilConverter.convertUsuarioToSgvUsuario(usuario));
        return new GenericResponse<>();
    }

    @Override
    public GenericResponse<Usuario> updateUsuario(Usuario usuario) throws AppInternalException {
        boolean exist = sgvUsuarioRepository.findById(usuario.getIdUsuario()).map(mapper->{
            mapper.setNombre(usuario.getNombre());
            mapper.setApPatenro(usuario.getApPaterno());
            mapper.setApMaterno(usuario.getApMaterno());
            mapper.setDni(usuario.getDni());
            mapper.setIndActivo(usuario.getIndActivo());
            mapper.setClave(usuario.getClave());
            mapper.setSgvTipoUsuario(AppUtilConverter.convertMultitablaToSgvMultitabla(usuario.getTipoUsuario()));
            return sgvUsuarioRepository.save(mapper);
        }).isPresent();
        if(!exist){
            throw new AppInternalException(HttpStatus.NOT_FOUND, AppMessages.GENERIC_REGISTRO_NO_ENCONTRADO);
        }
        return new GenericResponse<>();
    }

    @Override
    public GenericResponse<Usuario> indActivoUsuario(Long id, Boolean ind) throws AppInternalException {
        boolean exist = sgvUsuarioRepository.findById(id).map(mapper->{
            mapper.setIndActivo(ind);
            return sgvUsuarioRepository.save(mapper);
        }).isPresent();
        if(!exist){
            throw new AppInternalException(HttpStatus.NOT_FOUND, AppMessages.GENERIC_REGISTRO_NO_ENCONTRADO);
        }
        return new GenericResponse<>();
    }
    
}