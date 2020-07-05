package com.devapp.sigsv.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.devapp.sigsv.dao.CompraDao;
import com.devapp.sigsv.exception.AppInternalException;
import com.devapp.sigsv.model.bean.Compra;
import com.devapp.sigsv.model.bean.DetalleCompra;
import com.devapp.sigsv.model.bean.Pagination;
import com.devapp.sigsv.model.bean.response.GenericResponse;
import com.devapp.sigsv.model.entity.SgvCompra;
import com.devapp.sigsv.model.entity.SgvDetalleCompra;
import com.devapp.sigsv.model.entity.SgvStock;
import com.devapp.sigsv.repository.SgvCompraRepository;
import com.devapp.sigsv.repository.SgvDetalleCompraRepository;
import com.devapp.sigsv.repository.SgvStockRepository;
import com.devapp.sigsv.service.CompraService;
import com.devapp.sigsv.util.AppConstantes;
import com.devapp.sigsv.util.AppMessages;
import com.devapp.sigsv.util.AppUtilConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service("compraServiceImpl")
@Transactional
public class CompraServiceImpl implements CompraService {

    @Autowired
	private CompraDao compraDao;

    @Autowired
	@Qualifier("sgvCompraRepository")
    private SgvCompraRepository sgvCompraRepository;
    
    @Autowired
	@Qualifier("sgvDetalleCompraRepository")
    private SgvDetalleCompraRepository sgvDetalleCompraRepository;
    
    @Autowired
    @Qualifier("sgvStockRepository")
    private SgvStockRepository sgvStockRepository;

    @Override
    public GenericResponse<List<Compra>> getLstCompraPaginado(Pagination pagination) {
        return compraDao.getLstCompraPaginado(pagination);
    }

    @Override
    public GenericResponse<Compra> saveCompra(Compra compra) throws AppInternalException {        
    	compra.setIndActivo(true);
    	SgvCompra sgvCompra = sgvCompraRepository.save(AppUtilConverter.convertCompraToSgvCompra(compra));
    	List<DetalleCompra> lstDetalleCompra = new ArrayList<>();
    	for(DetalleCompra detalleCompra : compra.getLstDetalleCompra()) {
    		detalleCompra.setCompra(AppUtilConverter.convertSgvCompraToCompra(sgvCompra));
    		lstDetalleCompra.add(detalleCompra);
    	}
    	for(DetalleCompra detalleCompra : lstDetalleCompra) {
    		SgvDetalleCompra sgvDetalleCompra = sgvDetalleCompraRepository.save(AppUtilConverter
            		.convertDetalleCompraToSgvDetalleCompra(detalleCompra));
    		
    		if(!sgvDetalleCompra.equals(null)) {
    			List<SgvStock> lstSgvStock = sgvStockRepository.findBySgvProductoIdProducto(sgvDetalleCompra
    					.getSgvProducto().getIdProducto());
    			
    			//if(lstSgvStock.size() > 0) {
				if(!lstSgvStock.isEmpty()) {
    				boolean exist = sgvStockRepository.findById(lstSgvStock.get(0).getIdStock()).map(mapper ->{
    					mapper.setStockComprado(Integer.sum(lstSgvStock.get(0).getStockComprado(), sgvDetalleCompra.getCantidad()));
    						return sgvStockRepository.save(mapper);
    					}).isPresent();
    			
	    			if(!exist){
	    	            throw new AppInternalException(HttpStatus.NOT_FOUND, AppMessages.GENERIC_REGISTRO_NO_ENCONTRADO);
	    	        }	
    			}    			
    		}
        }	
    	
        return new GenericResponse<>();
    }

    @Override
    public GenericResponse<Compra> detalleCompra(Long id) throws AppInternalException {
    	Compra compra = new Compra();
    	List<DetalleCompra> lstCompra = new ArrayList<>();
        SgvCompra sgvCompra = sgvCompraRepository.findById(id).orElse(null);
        if(sgvCompra == null){
            throw new AppInternalException(HttpStatus.NOT_FOUND, AppMessages.GENERIC_REGISTRO_NO_ENCONTRADO, AppConstantes.IS_MESSAGE_KEY);
        }else {
        	compra = AppUtilConverter.convertSgvCompraToCompra(sgvCompra);
            List<SgvDetalleCompra> lstSgvDetalleCompra = sgvDetalleCompraRepository.findBySgvCompraIdCompra(id);
            
            if(!lstSgvDetalleCompra.isEmpty()) {
            	for(SgvDetalleCompra sgvDetalleCompra : lstSgvDetalleCompra) {
            		lstCompra.add(AppUtilConverter.convertSgvDetalleCompraToDetalleCompra(sgvDetalleCompra));
            	}
            	compra.setLstDetalleCompra(lstCompra);
            }
        }
        GenericResponse<Compra> ress = new GenericResponse<>();
        ress.setBody(compra);
        return ress;
    }

    @Override
    public GenericResponse<Compra> indActivoCompra(Long id) throws AppInternalException {
        boolean exist = sgvCompraRepository.findById(id).map(mapper->{
            mapper.setIndActivo(false);
            return sgvCompraRepository.save(mapper);
        }).isPresent();
        if(!exist){
            throw new AppInternalException(HttpStatus.NOT_FOUND, AppMessages.GENERIC_REGISTRO_NO_ENCONTRADO);
        }
        return new GenericResponse<>();
    }
    
}