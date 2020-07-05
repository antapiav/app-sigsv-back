package com.devapp.sigsv.repository;

import java.util.List;

import com.devapp.sigsv.model.entity.SgvProveedorCategoria;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("sgvProveedorCategoriaRepository")
public interface SgvProveedorCategoriaRepository extends JpaRepository<SgvProveedorCategoria, Long> {
    @Query("SELECT p FROM SgvProveedorCategoria p "+
    " WHERE TRANSLATE(REPLACE(UPPER(concat(p.nombre)), ' ', ''), 'ÁÉÍÓÚ','AEIOU')"+
    " LIKE TRANSLATE(REPLACE(UPPER(concat('%', :value,'%')), ' ', ''), 'ÁÉÍÓÚ','AEIOU')")
    Page<SgvProveedorCategoria> findByName(Pageable pageable, String value);
    //List<SgvProveedorCategoria> findAllByOrderByIdProveedorCategoriaAsc();
    @Query("SELECT p FROM SgvProveedorCategoria p " +
    		" WHERE TRANSLATE(REPLACE(UPPER(concat(p.nombre)), ' ', ''), 'ÁÉÍÓÚ','AEIOU') "+
    		" like TRANSLATE(REPLACE(UPPER(concat('%', :value,'%')), ' ', ''), 'ÁÉÍÓÚ','AEIOU') " + 
    		" AND p.indActivo = true")
    List<SgvProveedorCategoria> findByNombreOrderByIdProveedorCategoriaAsc(Pageable pageable, String value);
}