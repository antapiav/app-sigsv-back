package com.devapp.sigsv.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.devapp.sigsv.exception.AppInternalException;
import com.devapp.sigsv.model.bean.Cliente;
import com.devapp.sigsv.model.bean.Producto;
import com.devapp.sigsv.model.bean.response.GenericResponse;
import com.devapp.sigsv.model.entity.SgvCliente;
import com.devapp.sigsv.repository.SgvClienteRepository;
import com.devapp.sigsv.service.ClienteService;
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

@Service("clienteServiceImpl")
@Transactional
public class ClienteServiceImpl implements ClienteService {
    private static final Logger log = LoggerFactory.getLogger(AppUtilConverter.class);

    @Autowired
	@Qualifier("sgvClienteRepository")
    private SgvClienteRepository sgvClienteRepository;

    @Override
    public Page<Cliente> lstClientePaginado(Pageable pageable, String name, String operator, String value) {
        List<Cliente> lstCliente = new ArrayList<>();
        Page<SgvCliente> pgSgvCliente = null;
        switch(name){
            case AppConstantes.STRING_DNI:
            	pgSgvCliente = sgvClienteRepository.findByDniPaginago(pageable, value);
                break;
            case AppConstantes.STRING_NOMBRE:
                pgSgvCliente = sgvClienteRepository.findByName(pageable, value);
                break;
            default:
                pgSgvCliente = sgvClienteRepository.findAll(pageable);
        
        }
    	for (SgvCliente sgvCliente : pgSgvCliente.getContent()) {
            lstCliente.add(AppUtilConverter.convertSgvClienteToCliente(sgvCliente));
        }	
        Page<Cliente> ress = new PageImpl<>(lstCliente, pgSgvCliente.getPageable(), pgSgvCliente.getTotalElements());  
        return ress;
    }
    
    @Override
	public GenericResponse<List<Cliente>> lstCliente(String value) {
    	List<Cliente> lstCliente = new ArrayList<>();
        List<SgvCliente> lstSgvCliente = sgvClienteRepository
        		.findByNombreOrCodigoOrderByIdClienteAsc(PageRequest.of(0, 5, Sort.by(AppConstantes.ID_CLIENTE).ascending()), value);
        for(SgvCliente sgvCliente : lstSgvCliente){
            lstCliente.add(AppUtilConverter.convertSgvClienteToCliente(sgvCliente));
        }
        GenericResponse<List<Cliente>> ress = new GenericResponse<>();
        ress.setBody(lstCliente);
        return ress;
	}

    @Override
    public GenericResponse<Cliente> saveCliente(Cliente cliente) throws AppInternalException {
    	 GenericResponse<Cliente> ress = new GenericResponse<>();
    	if(sgvClienteRepository.findByDni(cliente.getDni()).size() == 0 ) {
    		sgvClienteRepository.save(AppUtilConverter.convertClienteToSgvCliente(cliente));
    	}else {
    		ress.setCode(HttpStatus.IM_USED.value());
    	}
    	return ress;
    }

    @Override
    public GenericResponse<Cliente> updateCliente(Cliente cliente) throws AppInternalException {
    	GenericResponse<Cliente> ress = new GenericResponse<>();
        boolean exist = sgvClienteRepository.findById(cliente.getIdCliente()).map(mapper->{
            mapper.setNombre(cliente.getNombre());
            mapper.setApPaterno(cliente.getApPaterno());
            mapper.setApMaterno(cliente.getApMaterno());
            mapper.setDni(cliente.getDni());
            mapper.setIndActivo(cliente.getIndActivo());
            mapper.setDireccion(cliente.getDireccion());
            mapper.setContacto(cliente.getContacto());
            mapper.setTelefono(cliente.getTelefono());
            mapper.setEmail(cliente.getEmail());
            return sgvClienteRepository.save(mapper);
        }).isPresent();
        if(!exist){
        	ress.setCode(HttpStatus.NOT_FOUND.value());
        }
        return ress;
    }

    @Override
    public GenericResponse<Cliente> detalleCliente(Long id) throws AppInternalException {
    	GenericResponse<Cliente> ress = new GenericResponse<>();
        SgvCliente sgvCliente = sgvClienteRepository.findById(id).orElse(null);
        if(sgvCliente == null){
        	ress.setCode(HttpStatus.NOT_FOUND.value());
        }
        ress.setBody(AppUtilConverter.convertSgvClienteToCliente(sgvCliente));
        return ress;
    }

    @Override
    public GenericResponse<Cliente> indActivoCliente(Long id) throws AppInternalException {
    	GenericResponse<Cliente> ress = new GenericResponse<>();
        boolean exist = sgvClienteRepository.findById(id).map(mapper->{
            mapper.setIndActivo(false);
            return sgvClienteRepository.save(mapper);
        }).isPresent();
        if(!exist){
        	ress.setCode(HttpStatus.NOT_FOUND.value());
        }
        return ress;
    }
    
}