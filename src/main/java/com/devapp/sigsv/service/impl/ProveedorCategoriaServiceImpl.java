package com.devapp.sigsv.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.devapp.sigsv.exception.AppInternalException;
import com.devapp.sigsv.model.bean.ProveedorCategoria;
import com.devapp.sigsv.model.bean.response.GenericResponse;
import com.devapp.sigsv.model.entity.SgvProveedorCategoria;
import com.devapp.sigsv.repository.SgvProveedorCategoriaRepository;
import com.devapp.sigsv.service.ProveedorCategoriaService;
import com.devapp.sigsv.util.AppConstantes;
import com.devapp.sigsv.util.AppMessages;
import com.devapp.sigsv.util.AppUtilConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service("proveedorCategriaServiceImpl")
@Transactional
public class ProveedorCategoriaServiceImpl implements ProveedorCategoriaService {

    @Autowired
	@Qualifier("sgvProveedorCategoriaRepository")
    private SgvProveedorCategoriaRepository sgvProveedorCategoriaRepository;

    @Override
    public Page<ProveedorCategoria> lstProveedorCategoriaPaginado(Pageable pageable, String name, String operator, String value) {
        List<ProveedorCategoria> lstProveedorCategoria = new ArrayList<>();
        ProveedorCategoria proveedorCategoria = new ProveedorCategoria();
        Page<SgvProveedorCategoria> pgSgvProveedorCategoria = null;
        switch(name){
            case AppConstantes.STRING_NOMBRE:
            pgSgvProveedorCategoria = sgvProveedorCategoriaRepository.findByName(pageable, value);
                break;
            default:
            pgSgvProveedorCategoria = sgvProveedorCategoriaRepository.findAll(pageable);
        }

    	for (SgvProveedorCategoria sgvProveedorCategoria : pgSgvProveedorCategoria.getContent()) {
            proveedorCategoria = AppUtilConverter.convertSgvProveedorCategoriaToProveedorCategoria(sgvProveedorCategoria);
            lstProveedorCategoria.add(proveedorCategoria);
        }	
        Page<ProveedorCategoria> ress = new PageImpl<>(lstProveedorCategoria, pgSgvProveedorCategoria.getPageable(), pgSgvProveedorCategoria.getTotalElements());  
        return ress;
    }

    @Override
    public GenericResponse<List<ProveedorCategoria>> lstProveedorCategoria(String value) {
        List<ProveedorCategoria> lstProveedorCategoria = new ArrayList<>();
        List<SgvProveedorCategoria> lstSgvProveedorCategoria = sgvProveedorCategoriaRepository
        		.findByNombreOrderByIdProveedorCategoriaAsc(PageRequest.of(0, 5, Sort.by(AppConstantes.ID_PROVEEDOR_CATEGORIA).ascending()), value);
        for(SgvProveedorCategoria sgvProveedorCategoria : lstSgvProveedorCategoria){
            lstProveedorCategoria.add(AppUtilConverter.convertSgvProveedorCategoriaToProveedorCategoria(sgvProveedorCategoria));
        }
        GenericResponse<List<ProveedorCategoria>> ress = new GenericResponse<>();
        ress.setBody(lstProveedorCategoria);
        return ress;
    }

    @Override
    public GenericResponse<ProveedorCategoria> saveProveedorCategoría(ProveedorCategoria proveedorCategoria)
            throws AppInternalException {
                sgvProveedorCategoriaRepository.save(AppUtilConverter.convertProveedorCategoriaToSgvProveedorCategoria(proveedorCategoria));
                return new GenericResponse<>();
    }

    @Override
    public GenericResponse<ProveedorCategoria> updateProveedorCategoría(ProveedorCategoria proveedorCategoria)
            throws AppInternalException {
                boolean exist = sgvProveedorCategoriaRepository.findById(proveedorCategoria.getIdProveedorCategoria()).map(mapper->{
                    mapper.setNombre(proveedorCategoria.getNombre());
                    mapper.setDetalle(proveedorCategoria.getDetalle());
                    mapper.setIndActivo(proveedorCategoria.getIndActivo());
                    return sgvProveedorCategoriaRepository.save(mapper);
                }).isPresent();
                if(!exist){
                    throw new AppInternalException(HttpStatus.NOT_FOUND, AppMessages.GENERIC_REGISTRO_NO_ENCONTRADO);
                }
                return new GenericResponse<>();
    }

    @Override
    public GenericResponse<ProveedorCategoria> detalleProveedorCategoría(Long id) throws AppInternalException {
        SgvProveedorCategoria sgvProveedorCategoria = sgvProveedorCategoriaRepository.findById(id).orElse(null);
        if(sgvProveedorCategoria == null){
            throw new AppInternalException(HttpStatus.NOT_FOUND, AppMessages.GENERIC_REGISTRO_NO_ENCONTRADO, AppConstantes.IS_MESSAGE_KEY);
        }
        GenericResponse<ProveedorCategoria> ress = new GenericResponse<>();
        ress.setBody(AppUtilConverter.convertSgvProveedorCategoriaToProveedorCategoria(sgvProveedorCategoria));
        return ress;
    }

    @Override
    public GenericResponse<ProveedorCategoria> indActivoProveedorCategoria(Long id)
            throws AppInternalException {
                boolean exist = sgvProveedorCategoriaRepository.findById(id).map(mapper->{
                    mapper.setIndActivo(false);
                    return sgvProveedorCategoriaRepository.save(mapper);
                }).isPresent();
                if(!exist){
                    throw new AppInternalException(HttpStatus.NOT_FOUND, AppMessages.GENERIC_REGISTRO_NO_ENCONTRADO);
                }
        return new GenericResponse<>();
    }
    
}