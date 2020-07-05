package com.devapp.sigsv.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.devapp.sigsv.controller.AbstractController;
import com.devapp.sigsv.exception.AppInternalException;
import com.devapp.sigsv.model.bean.Cliente;
import com.devapp.sigsv.model.bean.Proveedor;
import com.devapp.sigsv.model.bean.response.GenericResponse;
import com.devapp.sigsv.model.entity.SgvProveedor;
import com.devapp.sigsv.repository.SgvProveedorRepository;
import com.devapp.sigsv.service.ProveedorService;
import com.devapp.sigsv.util.AppConstantes;
import com.devapp.sigsv.util.AppMessages;
import com.devapp.sigsv.util.AppUtilConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service("proveedorServiceImpl")
@Transactional
public class ProveedorServiceImpl implements ProveedorService {
	private static Logger log = LoggerFactory.getLogger(AbstractController.class);

    @Autowired
	@Qualifier("sgvProveedorRepository")
    private SgvProveedorRepository sgvProveedorRepository;

    @Override
    public Page<Proveedor> lstProveedorPaginado(Pageable pageable, String categoria, String operator, String value, String idProveedorCategoria) {
        List<Proveedor> lstProveedor = new ArrayList<>();
        Proveedor proveedor = new Proveedor();
        Page<SgvProveedor> pgSgvProveedor = null;        
        if(!idProveedorCategoria.equals(AppConstantes.STRING_VACIO)){
    		pgSgvProveedor = sgvProveedorRepository.findByNumDocOrName(pageable, value, Long.parseLong(idProveedorCategoria));
        }else {
            pgSgvProveedor = sgvProveedorRepository.findAllByNombre(pageable, value);
        }
    	for (SgvProveedor sgvProveedor : pgSgvProveedor.getContent()) {
            proveedor = AppUtilConverter.convertSgvProveedorToProveedor(sgvProveedor);
            lstProveedor.add(proveedor);
        }	
        Page<Proveedor> ress = new PageImpl<>(lstProveedor, pgSgvProveedor.getPageable(), pgSgvProveedor.getTotalElements());  
        return ress;
    }
    
    @Override
	public GenericResponse<List<Proveedor>> lstProveedor(String value) {
    	List<Proveedor> lstProveedor = new ArrayList<>();
        List<SgvProveedor> lstSgvProveedor = sgvProveedorRepository
        		.findByNombreOrCodigoOrderByIdProveedorAsc(PageRequest.of(0, 5, Sort.by(AppConstantes.ID_PROVEEDOR).ascending()), value);
        for(SgvProveedor sgvProveedor : lstSgvProveedor){
            lstProveedor.add(AppUtilConverter.convertSgvProveedorToProveedor(sgvProveedor));
        }
        GenericResponse<List<Proveedor>> ress = new GenericResponse<>();
        ress.setBody(lstProveedor);
        return ress;
	}

    @Override
    public GenericResponse<Proveedor> saveProveedor(Proveedor proveedor) throws AppInternalException {
    	GenericResponse<Proveedor> ress = new GenericResponse<>();
    	if(sgvProveedorRepository.findByNumDocumento(proveedor.getNumDocumento()).isEmpty()) {
        	sgvProveedorRepository.save(AppUtilConverter.convertProveedorToSgvProveedor(proveedor));
    	}else {
    		ress.setCode(HttpStatus.IM_USED.value());
    	}
        return ress;
    }

    @Override
    public GenericResponse<Proveedor> updateProveedor(Proveedor proveedor) throws AppInternalException {
    	GenericResponse<Proveedor> ress = new GenericResponse<>();
    	boolean exist = sgvProveedorRepository.findById(proveedor.getIdProveedor()).map(mapper->{
            mapper.setNombre(proveedor.getNombre());
            mapper.setNumDocumento(proveedor.getNumDocumento());
            mapper.setDireccion(proveedor.getDireccion());
            mapper.setContacto(proveedor.getContacto());
            mapper.setIndActivo(proveedor.getIndActivo());
            mapper.setTelefono(proveedor.getTelefono());
            mapper.setEmail(proveedor.getEmail());
            mapper.setSgvProveedorcategoria(AppUtilConverter.convertProveedorCategoriaToSgvProveedorCategoria(proveedor.getProveedorCategoria()));
            mapper.setSgvTipoDocumento(AppUtilConverter.convertMultitablaToSgvMultitabla(proveedor.getTipoDocumento()));
            return sgvProveedorRepository.save(mapper);
        }).isPresent();
        if(!exist){
        	ress.setCode(HttpStatus.NOT_FOUND.value());
            //throw new AppInternalException(HttpStatus.NOT_FOUND, AppMessages.GENERIC_REGISTRO_NO_ENCONTRADO);
        }
        return ress;
    }

    @Override
    public GenericResponse<Proveedor> detalleProveedor(Long id) throws AppInternalException {
        SgvProveedor sgvProveedor = sgvProveedorRepository.findById(id).orElse(null);
        if(sgvProveedor == null){
            throw new AppInternalException(HttpStatus.NOT_FOUND, AppMessages.GENERIC_REGISTRO_NO_ENCONTRADO, AppConstantes.IS_MESSAGE_KEY);
        }
        GenericResponse<Proveedor> ress = new GenericResponse<>();
        ress.setBody(AppUtilConverter.convertSgvProveedorToProveedor(sgvProveedor));
        return ress;
    }

    @Override
    public GenericResponse<Proveedor> indActivoProveedor(Long id) throws AppInternalException {
        boolean exist = sgvProveedorRepository.findById(id).map(mapper->{
            mapper.setIndActivo(false);
            return sgvProveedorRepository.save(mapper);
        }).isPresent();
        if(!exist){
            throw new AppInternalException(HttpStatus.NOT_FOUND, AppMessages.GENERIC_REGISTRO_NO_ENCONTRADO);
        }
        return new GenericResponse<>();
    }
    
}