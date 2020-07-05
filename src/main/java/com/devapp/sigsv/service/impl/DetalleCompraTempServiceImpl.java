package com.devapp.sigsv.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.devapp.sigsv.exception.AppInternalException;
import com.devapp.sigsv.model.bean.DetalleCompraTemp;
import com.devapp.sigsv.model.bean.response.GenericResponse;
import com.devapp.sigsv.model.entity.SgvDetalleCompraTemp;
import com.devapp.sigsv.repository.SgvDetalleCompraTempRepository;
import com.devapp.sigsv.service.DetalleCompraTempService;
import com.devapp.sigsv.util.AppMessages;
import com.devapp.sigsv.util.AppUtilConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service("detalleCompraTempServiceImpl")
@Transactional
public class DetalleCompraTempServiceImpl implements DetalleCompraTempService {

    @Autowired
	@Qualifier("sgvDetalleCompraTempRepository")
    private SgvDetalleCompraTempRepository sgvDetalleCompraTempRepository;

    @Override
    public GenericResponse<List<DetalleCompraTemp>> lstDetalleCompra() throws AppInternalException {
        List<DetalleCompraTemp> lstDetalleCompraTemp = new ArrayList<>();
        List<SgvDetalleCompraTemp> lstSgvDetalleCompraTemp = new ArrayList<>();
        lstSgvDetalleCompraTemp = sgvDetalleCompraTempRepository.findAllByOrderByIdDetalleCompraAsc();
        for(SgvDetalleCompraTemp sgvDetalleCompraTemp : lstSgvDetalleCompraTemp){
            lstDetalleCompraTemp.add(AppUtilConverter.convertSgvDetalleCompraTempToDetalleCompraTemp(sgvDetalleCompraTemp));
        }
        GenericResponse<List<DetalleCompraTemp>> ress = new GenericResponse<>();
        ress.setBody(lstDetalleCompraTemp);
        return ress;
    }

    @Override
    public GenericResponse<DetalleCompraTemp> saveDetalleCompraTemp(DetalleCompraTemp detalleCompraTemp)
            throws AppInternalException {
                sgvDetalleCompraTempRepository.save(AppUtilConverter.convertDetalleCompraTempToSgvDetalleCompraTemp(detalleCompraTemp));
            return new GenericResponse<>();
    }

    @Override
    public GenericResponse<DetalleCompraTemp> deleteDetalleCompraTemp(Long id) throws AppInternalException {
        boolean exist =  sgvDetalleCompraTempRepository.findById(id)
                .map(mapper -> {
                    sgvDetalleCompraTempRepository.delete(mapper);
                    return ResponseEntity.ok().build();
        }).isPresent();
        if(!exist){
            throw new AppInternalException(HttpStatus.NOT_FOUND, AppMessages.GENERIC_REGISTRO_NO_ENCONTRADO);
        }
        return new GenericResponse<>();
    }
    
}