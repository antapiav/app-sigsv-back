package com.devapp.sigsv.repository;

import com.devapp.sigsv.model.entity.SgvProveedor;
import com.devapp.sigsv.util.AppConstantes;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("sgvProveedorRepository")
public interface SgvProveedorRepository extends JpaRepository<SgvProveedor, Long> {
    /*@Query("SELECT p FROM SgvProveedor p "+
    " WHERE TRANSLATE(REPLACE(UPPER(concat(p.numDocumento)), ' ', ''), 'ÁÉÍÓÚ','AEIOU')"+
    " LIKE TRANSLATE(REPLACE(UPPER(concat('%', :value,'%')), ' ', ''), 'ÁÉÍÓÚ','AEIOU')")
    Page<SgvProveedor> findByCodigo(Pageable pageable, @Param(AppConstantes.PARAM_PATH_VALUE) String value);
    @Query("SELECT p FROM SgvProveedor p "+
    " WHERE TRANSLATE(REPLACE(UPPER(concat(p.nombre)), ' ', ''), 'ÁÉÍÓÚ','AEIOU')"+
    " LIKE TRANSLATE(REPLACE(UPPER(concat('%', :value,'%')), ' ', ''), 'ÁÉÍÓÚ','AEIOU')")
    Page<SgvProveedor> findByName(Pageable pageable, @Param(AppConstantes.PARAM_PATH_VALUE) String value);*/
    @Query("SELECT p FROM SgvProveedor p " + 
    		" where (TRANSLATE(REPLACE(UPPER(concat(p.nombre)), ' ', ''), 'ÁÉÍÓÚ','AEIOU') LIKE "+
    		" TRANSLATE(REPLACE(UPPER(concat('%', :value,'%')), ' ', ''), 'ÁÉÍÓÚ','AEIOU') " + 
    		" OR TRANSLATE(REPLACE(UPPER(concat(p.numDocumento)), ' ', ''), 'ÁÉÍÓÚ','AEIOU') LIKE "+
    		" TRANSLATE(REPLACE(UPPER(concat('%', :value,'%')), ' ', ''), 'ÁÉÍÓÚ','AEIOU'))"+
    		" AND p.sgvProveedorcategoria.idProveedorCategoria = :id ")
    Page<SgvProveedor> findByNumDocOrName(Pageable pageable, 
    		@Param(AppConstantes.PARAM_PATH_VALUE) String value,
    		@Param(AppConstantes.PARAM_PATH_ID) Long idCategoria);
    
    @Query("SELECT p FROM SgvProveedor p " + 
    		" where TRANSLATE(REPLACE(UPPER(concat(p.nombre)), ' ', ''), 'ÁÉÍÓÚ','AEIOU') LIKE "+
    		" TRANSLATE(REPLACE(UPPER(concat('%', :value,'%')), ' ', ''), 'ÁÉÍÓÚ','AEIOU') " + 
    		" OR TRANSLATE(REPLACE(UPPER(concat(p.numDocumento)), ' ', ''), 'ÁÉÍÓÚ','AEIOU') LIKE "+
    		" TRANSLATE(REPLACE(UPPER(concat('%', :value,'%')), ' ', ''), 'ÁÉÍÓÚ','AEIOU') ")
    Page<SgvProveedor> findAllByNombre(Pageable pageable, 
    		@Param(AppConstantes.PARAM_PATH_VALUE) String value);
    
    @Query("SELECT p FROM SgvProveedor p " + 
    		" where (TRANSLATE(REPLACE(UPPER(concat(p.nombre)), ' ', ''), 'ÁÉÍÓÚ','AEIOU') LIKE " + 
    		" TRANSLATE(REPLACE(UPPER(concat('%', :value,'%')), ' ', ''), 'ÁÉÍÓÚ','AEIOU') " + 
    		" OR TRANSLATE(REPLACE(UPPER(concat(p.numDocumento)), ' ', ''), 'ÁÉÍÓÚ','AEIOU') LIKE " + 
    		" TRANSLATE(REPLACE(UPPER(concat('%', :value,'%')), ' ', ''), 'ÁÉÍÓÚ','AEIOU'))" + 
    		" AND p.indActivo = true")
    List<SgvProveedor> findByNombreOrCodigoOrderByIdProveedorAsc(Pageable pageable, String value);
    
    List<SgvProveedor> findByNumDocumento(String value);
}