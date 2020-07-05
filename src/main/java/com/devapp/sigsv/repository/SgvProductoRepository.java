package com.devapp.sigsv.repository;

import com.devapp.sigsv.model.entity.SgvProducto;
import com.devapp.sigsv.util.AppConstantes;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("sgvProductoRepository")
public interface SgvProductoRepository extends JpaRepository<SgvProducto, Long> {
    @Query("SELECT p FROM SgvProducto p " + 
    		" where (TRANSLATE(REPLACE(UPPER(concat(p.nombre)), ' ', ''), 'ÁÉÍÓÚ','AEIOU') LIKE "+
    		" TRANSLATE(REPLACE(UPPER(concat('%', :value,'%')), ' ', ''), 'ÁÉÍÓÚ','AEIOU') " + 
    		" OR TRANSLATE(REPLACE(UPPER(concat(p.codigo)), ' ', ''), 'ÁÉÍÓÚ','AEIOU') LIKE "+
    		" TRANSLATE(REPLACE(UPPER(concat('%', :value,'%')), ' ', ''), 'ÁÉÍÓÚ','AEIOU'))"+
    		" AND p.sgvProductoCategoria.idProductoCategoria = :id ")
    Page<SgvProducto> findByCodigo(Pageable pageable, 
    		@Param(AppConstantes.PARAM_PATH_VALUE) String value,
    		@Param(AppConstantes.PARAM_PATH_ID) Long idCategoria);
    
    @Query("SELECT p FROM SgvProducto p " + 
    		" where TRANSLATE(REPLACE(UPPER(concat(p.nombre)), ' ', ''), 'ÁÉÍÓÚ','AEIOU') LIKE "+
    		" TRANSLATE(REPLACE(UPPER(concat('%', :value,'%')), ' ', ''), 'ÁÉÍÓÚ','AEIOU') " + 
    		" OR TRANSLATE(REPLACE(UPPER(concat(p.codigo)), ' ', ''), 'ÁÉÍÓÚ','AEIOU') LIKE "+
    		" TRANSLATE(REPLACE(UPPER(concat('%', :value,'%')), ' ', ''), 'ÁÉÍÓÚ','AEIOU') ")
    Page<SgvProducto> findAllByNombre(Pageable pageable, 
    		@Param(AppConstantes.PARAM_PATH_VALUE) String value);
    
    List<SgvProducto> findByCodigo(String value);
    
    /*@Query("SELECT p FROM SgvProducto p " +
    		" WHERE (TRANSLATE(REPLACE(UPPER(concat(p.nombre)), ' ', ''), 'ÁÉÍÓÚ','AEIOU') "+
    		" like TRANSLATE(REPLACE(UPPER(concat('%', :value,'%')), ' ', ''), 'ÁÉÍÓÚ','AEIOU') " + 
    		" OR TRANSLATE(REPLACE(UPPER(concat(p.codigo)), ' ', ''), 'ÁÉÍÓÚ','AEIOU') LIKE "+
    		" TRANSLATE(REPLACE(UPPER(concat('%', :value,'%')), ' ', ''), 'ÁÉÍÓÚ','AEIOU')) "+
    		" AND p.sgvProductoCategoria.idProductoCategoria = :id "+
    		" AND p.indActivo = true")
    List<SgvProducto>findByNombreAndCategoriaOrderByIdProductoAsc(Pageable pageable, String value, Long id);*/
    
    @Query("SELECT p FROM SgvProducto p " +
    		" WHERE (TRANSLATE(REPLACE(UPPER(concat(p.nombre)), ' ', ''), 'ÁÉÍÓÚ','AEIOU') "+
    		" like TRANSLATE(REPLACE(UPPER(concat('%', :value,'%')), ' ', ''), 'ÁÉÍÓÚ','AEIOU') " + 
    		" OR TRANSLATE(REPLACE(UPPER(concat(p.codigo)), ' ', ''), 'ÁÉÍÓÚ','AEIOU') LIKE "+
    		" TRANSLATE(REPLACE(UPPER(concat('%', :value,'%')), ' ', ''), 'ÁÉÍÓÚ','AEIOU')) "+
    		" AND p.indActivo = true")
    List<SgvProducto>findByNombreOrderByIdProductoAsc(Pageable pageable, String value);
}