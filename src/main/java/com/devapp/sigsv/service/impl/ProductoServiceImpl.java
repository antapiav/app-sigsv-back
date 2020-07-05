package com.devapp.sigsv.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import com.devapp.sigsv.exception.AppInternalException;
import com.devapp.sigsv.model.bean.Producto;
import com.devapp.sigsv.model.bean.ProductoCategoria;
import com.devapp.sigsv.model.bean.Stock;
import com.devapp.sigsv.model.bean.response.GenericResponse;
import com.devapp.sigsv.model.entity.SgvProducto;
import com.devapp.sigsv.model.entity.SgvProductoCategoria;
import com.devapp.sigsv.model.entity.SgvStock;
import com.devapp.sigsv.repository.SgvProductoRepository;
import com.devapp.sigsv.repository.SgvStockRepository;
import com.devapp.sigsv.service.ProductoService;
import com.devapp.sigsv.util.AppConstantes;
import com.devapp.sigsv.util.AppUtilConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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

@Service("productoServiceImpl")
@Transactional
public class ProductoServiceImpl implements ProductoService {

    private static final Logger log = LoggerFactory.getLogger(AppUtilConverter.class);

    @Autowired
    @Qualifier("sgvProductoRepository")
    private SgvProductoRepository sgvProductoRepository;
    
    @Autowired
    @Qualifier("sgvStockRepository")
    private SgvStockRepository sgvStockRepository;

    @Override
    public Page<Stock> lstProductoPaginado(Pageable pageable, String name, String operator, String value, String idProductoCategoria) {
    	List<Stock> lstStock = new ArrayList<>();
        Stock stock = new Stock();
        Page<SgvStock> pgSgvStock = null;
        if(!idProductoCategoria.equals(AppConstantes.STRING_VACIO)){
        	pgSgvStock = sgvStockRepository.findByProductoCodigo(pageable, value, Long.parseLong(idProductoCategoria));
        }else{
        	pgSgvStock = sgvStockRepository.findByProductoNombre(pageable, value);
        }
        for (SgvStock sgvStock : pgSgvStock.getContent()) {
        	stock = AppUtilConverter.convertSgvStockToStock(sgvStock);
            lstStock.add(stock);
        }
        Page<Stock> ress = new PageImpl<>(lstStock, pgSgvStock.getPageable(), pgSgvStock.getTotalElements());
        return ress;
    }
    
    @Override
	public GenericResponse<List<Producto>> lstProducto(String value) throws AppInternalException {
    	Producto producto = new Producto();
        List<Producto> lstProducto = new ArrayList<>();
        List<SgvProducto> lstSgvProducto = sgvProductoRepository
            		.findByNombreOrderByIdProductoAsc(PageRequest.of(0, 5, Sort.by(AppConstantes.ID_PRODUCTO).ascending()), value);
        for(SgvProducto sgvProducto : lstSgvProducto){
            producto = AppUtilConverter.convertSgvProductoToProducto(sgvProducto);
            lstProducto.add(producto);
        }
        GenericResponse<List<Producto>> ress = new GenericResponse<>();
        ress.setBody(lstProducto);
        return ress;
	}

    @Override
    public GenericResponse<Producto> saveProducto(Producto producto) throws AppInternalException {
    	GenericResponse<Producto> ress = new GenericResponse<>();
    	Boolean empty = sgvProductoRepository
    			.findByCodigo(producto.getCodigo()).isEmpty();
    	if(empty) {
    		SgvStock sgvStock = new SgvStock();
            SgvProducto sgvProducto = sgvProductoRepository.save(AppUtilConverter.convertProductoToSgvProducto(producto));
            sgvStock.setStockComprado(0);
            sgvStock.setStockVendido(0);
            sgvStock.setSgvProducto(sgvProducto);
            sgvStockRepository.save(sgvStock);
    	}else {
    		ress.setCode(HttpStatus.IM_USED.value());
    	}
        return ress;
    }

    @Override
    public GenericResponse<Producto> updateProducto(Producto producto) throws AppInternalException {
    	GenericResponse<Producto> ress = new GenericResponse<>();
        boolean exist = sgvProductoRepository.findById(producto.getIdProducto()).map(mapper->{
            mapper.setNombre(producto.getNombre());
            mapper.setDetalle(producto.getDetalle());
            mapper.setCodigo(producto.getCodigo());
            mapper.setPreCosto(producto.getPreCosto());
            mapper.setPreVenta(producto.getPreVenta());
            mapper.setUtilidad(producto.getUtilidad());
            mapper.setIndActivo(producto.getIndActivo());
            mapper.setSgvProductoCategoria(AppUtilConverter.convertProductoCategoriaToSgvProductoCategoria(producto.getProductoCategoria()));
            mapper.setSgvUnidad(AppUtilConverter.convertMultitablaToSgvMultitabla(producto.getUnidad()));
            return sgvProductoRepository.save(mapper);
        }).isPresent();
        if(!exist){
            //throw new AppInternalException(HttpStatus.NOT_FOUND, AppMessages.GENERIC_GET_UNIQUE_EMPTY);
        	ress.setCode(HttpStatus.NOT_FOUND.value());
        }
        return ress;
    }

    @Override
    public GenericResponse<Stock> detalleProducto(Long id) throws AppInternalException {
        //SgvProducto sgvProducto = sgvProductoRepository.findById(id).orElse(null);
        GenericResponse<Stock> ress = new GenericResponse<>();
        List<SgvStock> lstSgvProducto = sgvStockRepository.findBySgvProductoIdProducto(id);
        if(lstSgvProducto.size() == 0){
            //throw new AppInternalException(HttpStatus.NOT_FOUND, AppMessages.GENERIC_GET_UNIQUE_EMPTY, AppConstantes.IS_MESSAGE_KEY);
        	ress.setCode(HttpStatus.NOT_FOUND.value());
        }
        ress.setBody(AppUtilConverter.convertSgvStockToStock(lstSgvProducto.get(0)));
        return ress;
    }

    @Override
    public GenericResponse<Producto> indActivoProducto(Long id) throws AppInternalException {
    	GenericResponse<Producto> ress = new GenericResponse<>();
        boolean exist = sgvProductoRepository.findById(id).map(mapper->{
            mapper.setIndActivo(false);
            return sgvProductoRepository.save(mapper);
        }).isPresent();
        if(!exist){
            //throw new AppInternalException(HttpStatus.NOT_FOUND, AppMessages.GENERIC_REGISTRO_NO_ENCONTRADO);
            ress.setCode(HttpStatus.NOT_FOUND.value());
        }
        return ress;
    }

	@Override
	public GenericResponse<?> getChartBar() throws AppInternalException {
		List<Stock> lstStock = new ArrayList<>();
		for(SgvStock sgvStock : sgvStockRepository.getChartBar()) {
			lstStock.add(AppUtilConverter.convertSgvStockToStock(sgvStock));
		}

		GenericResponse<List<?>> ress = new GenericResponse<>();
		ress.setBody(lstStock);
		return ress;
	}
	
	@Override
	public GenericResponse<?> getChartLine() throws AppInternalException {
		GenericResponse<List<?>> ress = new GenericResponse<>();
		List<List<?>> lstChartLine = new ArrayList<>();
		List<String> lstFecha = new ArrayList<>();
		Integer year = Calendar.getInstance().get(Calendar.YEAR);
		for(int i = 1; i <= 12; ++i) {
			lstFecha.add("01/"+Integer.toString(i)+"/"+Integer.toString(year));
			lstFecha.add("31/"+Integer.toString(i)+"/"+Integer.toString(year));
		}
	    try {
	    	for(int i = 0; i < 24; i=i+2) {
	    		Date dateLower=new SimpleDateFormat("dd/MM/yyyy").parse(lstFecha.get(i));
				Date dateGreater=new SimpleDateFormat("dd/MM/yyyy").parse(lstFecha.get(i+1));
				List<?> lst = sgvStockRepository.getChartLine(dateLower, dateGreater);
				lstChartLine.add(lst);	
	    	}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		ress.setBody(lstChartLine);
		return ress;
	}
    
}