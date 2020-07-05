package com.devapp.sigsv.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.devapp.sigsv.dao.VentaDao;
import com.devapp.sigsv.exception.AppInternalException;
import com.devapp.sigsv.model.bean.DetalleVenta;
import com.devapp.sigsv.model.bean.Pagination;
import com.devapp.sigsv.model.bean.Venta;
import com.devapp.sigsv.model.bean.response.GenericResponse;
import com.devapp.sigsv.model.entity.SgvDetalleVenta;
import com.devapp.sigsv.model.entity.SgvStock;
import com.devapp.sigsv.model.entity.SgvVenta;
import com.devapp.sigsv.repository.SgvDetalleVentaRepository;
import com.devapp.sigsv.repository.SgvStockRepository;
import com.devapp.sigsv.repository.SgvVentaRepository;
import com.devapp.sigsv.service.VentaService;
import com.devapp.sigsv.util.AppConstantes;
import com.devapp.sigsv.util.AppMessages;
import com.devapp.sigsv.util.AppUtilConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service("ventaServiceImpl")
@Transactional
public class VentaServiceImpl implements VentaService {
	public static Logger log = LoggerFactory.getLogger(VentaServiceImpl.class);

    @Autowired
	private VentaDao ventaDao;

    @Autowired
	@Qualifier("sgvVentaRepository")
    private SgvVentaRepository sgvVentaRepository;
    
    @Autowired
	@Qualifier("sgvDetalleVentaRepository")
    private SgvDetalleVentaRepository sgvDetalleVentaRepository;
    
    @Autowired
    @Qualifier("sgvStockRepository")
    private SgvStockRepository sgvStockRepository;
    
    @Override
	public GenericResponse<?> getLsVentaPaginado(Pagination pagination) {
    	return ventaDao.getLsVentaPaginado(pagination);
	}

    @Override
    public GenericResponse<Venta> saveVenta(Venta venta) throws AppInternalException {
    	venta.setIndActivo(true);
    	SgvVenta sgvVenta = sgvVentaRepository.save(AppUtilConverter.convertVentaToSgvVenta(venta));
    	List<DetalleVenta> lstDetalleVenta = new ArrayList<>();
    	for(DetalleVenta detalleVenta : venta.getLstDetalleVenta()) {
    		detalleVenta.setVenta(AppUtilConverter.convertSgvVentaToVenta(sgvVenta));
    		lstDetalleVenta.add(detalleVenta);
    	}
    	for(DetalleVenta detalleVenta : lstDetalleVenta) {
    		SgvDetalleVenta sgvDetalleVenta = sgvDetalleVentaRepository.save(AppUtilConverter
            		.convertDetalleVentaToSgvDetalleVenta(detalleVenta));
    		if(!sgvDetalleVenta.equals(null)) {
    			List<SgvStock> lstSgvStock = sgvStockRepository.findBySgvProductoIdProducto(sgvDetalleVenta
    					.getSgvProducto().getIdProducto());
    			
    			if(!lstSgvStock.isEmpty()) {
    				boolean exist = sgvStockRepository.findById(lstSgvStock.get(0).getIdStock()).map(mapper ->{
    					mapper.setStockVendido(Integer.sum(lstSgvStock.get(0).getStockVendido(), sgvDetalleVenta.getCantidad()));
    						return sgvStockRepository.save(mapper);
    					}).isPresent();
    				if(!exist){
	    	            throw new AppInternalException(HttpStatus.NOT_FOUND, AppMessages.GENERIC_REGISTRO_NO_ENCONTRADO);
	    	        }
    			}
    		}
        }
    	GenericResponse<Venta> ress = new GenericResponse<>();
    	ress.setBody(AppUtilConverter.convertSgvVentaToVenta(sgvVenta));
        return ress;
    }

    @Override
    public GenericResponse<Venta> detalleVenta(Long id) throws AppInternalException {
    	Venta venta = new Venta();
    	List<DetalleVenta> lstVenta = new ArrayList<>();
        SgvVenta sgvVenta = sgvVentaRepository.findById(id).orElse(null);
        if(sgvVenta == null){
            throw new AppInternalException(HttpStatus.NOT_FOUND, AppMessages.GENERIC_REGISTRO_NO_ENCONTRADO, AppConstantes.IS_MESSAGE_KEY);
        }else {
        	venta = AppUtilConverter.convertSgvVentaToVenta(sgvVenta);
            List<SgvDetalleVenta> lstSgvDetalleVenta = sgvDetalleVentaRepository.findBySgvVentaIdVenta(id);
            
            if(!lstSgvDetalleVenta.isEmpty()) {
            	for(SgvDetalleVenta sgvDetalleVenta : lstSgvDetalleVenta) {
            		lstVenta.add(AppUtilConverter.convertSgvDetalleVentaToDetalleVenta(sgvDetalleVenta));
            	}
            	venta.setLstDetalleVenta(lstVenta);
            }
        }
        GenericResponse<Venta> ress = new GenericResponse<>();
        ress.setBody(venta);
        return ress;
    }

    @Override
    public GenericResponse<Venta> indActivoVenta(Long id) throws AppInternalException {
        boolean exist = sgvVentaRepository.findById(id).map(mapper->{
            mapper.setIndActivo(false);
            return sgvVentaRepository.save(mapper);
        }).isPresent();
        if(!exist){
            throw new AppInternalException(HttpStatus.NOT_FOUND, AppMessages.GENERIC_REGISTRO_NO_ENCONTRADO);
        }
        return new GenericResponse<>();
    }
    
}