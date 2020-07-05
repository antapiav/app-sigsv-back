package com.devapp.sigsv.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.devapp.sigsv.exception.AppInternalException;
import com.devapp.sigsv.model.bean.DetalleVentaTemp;
import com.devapp.sigsv.model.bean.response.GenericResponse;
import com.devapp.sigsv.model.entity.SgvDetalleVentaTemp;
import com.devapp.sigsv.repository.SgvDetalleVentaTempRepository;
import com.devapp.sigsv.service.DetalleVentaTempService;
import com.devapp.sigsv.util.AppMessages;
import com.devapp.sigsv.util.AppUtilConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service("detalleVentaTempServiceImpl")
@Transactional
public class DetalleVentaTempServiceImpl implements DetalleVentaTempService {

    @Autowired
	@Qualifier("sgvDetalleVentaTempRepository")
    private SgvDetalleVentaTempRepository sgvDetalleVentaTempRepository;

    @Override
    public GenericResponse<List<DetalleVentaTemp>> lstDetalleVenta() throws AppInternalException {
        List<DetalleVentaTemp> lstDetalleVentaTemp = new ArrayList<>();
        List<SgvDetalleVentaTemp> lstSgvDetalleVentaTemp = new ArrayList<>();
        lstSgvDetalleVentaTemp = sgvDetalleVentaTempRepository.findAllByOrderByIdDetalleVentaAsc();
        for(SgvDetalleVentaTemp sgvDetalleVentaTemp : lstSgvDetalleVentaTemp){
            lstDetalleVentaTemp.add(AppUtilConverter.convertSgvDetalleVentaTempToDetalleVentaTemp(sgvDetalleVentaTemp));
        }
        GenericResponse<List<DetalleVentaTemp>> ress = new GenericResponse<>();
        ress.setBody(lstDetalleVentaTemp);
        return ress;
    }

    @Override
    public GenericResponse<DetalleVentaTemp> saveDetalleVentaTemp(DetalleVentaTemp detalleVentaTemp)
            throws AppInternalException {
                sgvDetalleVentaTempRepository.save(AppUtilConverter.convertDetalleVentaTempToSgvDetalleVentaTemp(detalleVentaTemp));
            return new GenericResponse<>();
    }

    @Override
    public GenericResponse<DetalleVentaTemp> deleteDetalleVentaTemp(Long id) throws AppInternalException {
        boolean exist =  sgvDetalleVentaTempRepository.findById(id)
                .map(mapper -> {
                    sgvDetalleVentaTempRepository.delete(mapper);
                    return ResponseEntity.ok().build();
        }).isPresent();
        if(!exist){
            throw new AppInternalException(HttpStatus.NOT_FOUND, AppMessages.GENERIC_REGISTRO_NO_ENCONTRADO);
        }
        return new GenericResponse<>();
    }    
}