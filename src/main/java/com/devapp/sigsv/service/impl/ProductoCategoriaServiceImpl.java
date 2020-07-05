package com.devapp.sigsv.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.devapp.sigsv.exception.AppInternalException;
import com.devapp.sigsv.model.bean.Producto;
import com.devapp.sigsv.model.bean.ProductoCategoria;
import com.devapp.sigsv.model.bean.Stock;
import com.devapp.sigsv.model.bean.response.GenericResponse;
import com.devapp.sigsv.model.entity.SgvProductoCategoria;
import com.devapp.sigsv.repository.SgvProductoCategoriaRepository;
import com.devapp.sigsv.service.ProductoCategoriaService;
import com.devapp.sigsv.util.AppConstantes;
import com.devapp.sigsv.util.AppMessages;
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

@Service("productoCategoriaServiceImpl")
@Transactional
public class ProductoCategoriaServiceImpl implements ProductoCategoriaService {
    private static final Logger log = LoggerFactory.getLogger(ProductoCategoriaServiceImpl.class);

    @Autowired
    @Qualifier("sgvProductoCategoriaRepository")
    private SgvProductoCategoriaRepository sgvProductoCategoriaRepository;

    @Override
    public Page<ProductoCategoria> lstProductoCategoriaPaginado(Pageable pageable, String name, String operator, String value) {
        List<ProductoCategoria> lstProductoCategoria = new ArrayList<>();
        ProductoCategoria productoCategoria = new ProductoCategoria();
        Page<SgvProductoCategoria> pgSgvProductoCategoria = null;
        switch(name){
            case AppConstantes.STRING_NOMBRE:
            pgSgvProductoCategoria = sgvProductoCategoriaRepository.findByName(pageable, value);
                break;
            default:
            pgSgvProductoCategoria = sgvProductoCategoriaRepository.findAll(pageable);
        }

    	for (SgvProductoCategoria sgvProductoCategoria : pgSgvProductoCategoria.getContent()) {
            productoCategoria = AppUtilConverter.convertSgvProductoCategoriaToProductoCategoria(sgvProductoCategoria);
            lstProductoCategoria.add(productoCategoria);
        }	
        Page<ProductoCategoria> ress = new PageImpl<>(lstProductoCategoria, pgSgvProductoCategoria.getPageable(), pgSgvProductoCategoria.getTotalElements());  
        return ress;
    }

    @Override
    public GenericResponse<List<ProductoCategoria>> lstProductoCategoria(String value) {
        ProductoCategoria productoCategoria = new ProductoCategoria();
        List<ProductoCategoria> lstProductoCategoria = new ArrayList<>();
        List<SgvProductoCategoria> lstSgvProductoCategoria = sgvProductoCategoriaRepository
        		.findByNombreOrderByIdProductoCategoriaAsc(PageRequest.of(0, 5, Sort.by(AppConstantes.ID_PRODUCTO_CATEGORIA).ascending()), value);
        for(SgvProductoCategoria sgvProductoCategoria : lstSgvProductoCategoria){
            productoCategoria = AppUtilConverter.convertSgvProductoCategoriaToProductoCategoria(sgvProductoCategoria);
            lstProductoCategoria.add(productoCategoria);
        }
        GenericResponse<List<ProductoCategoria>> ress = new GenericResponse<>();
        ress.setBody(lstProductoCategoria);
        return ress;
    }

    @Override
    public GenericResponse<ProductoCategoria> saveProductoCategoria(ProductoCategoria productoCategoria)
            throws AppInternalException {
        sgvProductoCategoriaRepository.save(AppUtilConverter.convertProductoCategoriaToSgvProductoCategoria(productoCategoria));
        return new GenericResponse<>();
    }

    @Override
    public GenericResponse<ProductoCategoria> updateProductoCategoria(ProductoCategoria productoCategoria)
            throws AppInternalException {
        boolean exist = sgvProductoCategoriaRepository.findById(productoCategoria.getIdProductoCategoria())
                .map(mapper -> {
                    mapper.setNombre(productoCategoria.getNombre());
                    mapper.setDetalle(productoCategoria.getDetalle());
                    mapper.setIndActivo(productoCategoria.getIndActivo());
                    return sgvProductoCategoriaRepository.save(mapper);
                }).isPresent();
        if (!exist) {
            throw new AppInternalException(HttpStatus.NOT_FOUND, AppMessages.GENERIC_REGISTRO_NO_ENCONTRADO);
        }
        return new GenericResponse<>();
    }

    @Override
    public GenericResponse<ProductoCategoria> detalleProductoCategoria(Long id) throws AppInternalException {
        SgvProductoCategoria sgvProductoCategoria = sgvProductoCategoriaRepository.findById(id).orElse(null);
        if (sgvProductoCategoria == null) {
            throw new AppInternalException(HttpStatus.NOT_FOUND, AppMessages.GENERIC_REGISTRO_NO_ENCONTRADO,
                    AppConstantes.IS_MESSAGE_KEY);
        }
        GenericResponse<ProductoCategoria> ress = new GenericResponse<>();
        ress.setBody(AppUtilConverter.convertSgvProductoCategoriaToProductoCategoria(sgvProductoCategoria));
        return ress;
    }

    @Override
    public GenericResponse<ProductoCategoria> indActivoProductoCategoria(Long id)
            throws AppInternalException {
                boolean exist = sgvProductoCategoriaRepository.findById(id).map(mapper->{
                    mapper.setIndActivo(false);
                    return sgvProductoCategoriaRepository.save(mapper);
                }).isPresent();
                if(!exist){
                    throw new AppInternalException(HttpStatus.NOT_FOUND, AppMessages.GENERIC_REGISTRO_NO_ENCONTRADO);
                }
        return new GenericResponse<>();
    }

	@Override
	public GenericResponse<?> getChartCircle() throws AppInternalException {
		GenericResponse<List<?>> ress = new GenericResponse<>();
		ress.setBody(sgvProductoCategoriaRepository.getChartCircle());
		return ress;
	}
    
}