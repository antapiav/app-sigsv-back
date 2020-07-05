package com.devapp.sigsv.repository;

import com.devapp.sigsv.model.entity.SgvCliente;
import com.devapp.sigsv.util.AppConstantes;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("sgvClienteRepository")
public interface SgvClienteRepository extends JpaRepository<SgvCliente, Long> {
    @Query("SELECT p FROM SgvCliente p WHERE p.dni LIKE concat('%', :value,'%')")
    Page<SgvCliente> findByDniPaginago(Pageable pageable, 
    		@Param(AppConstantes.PARAM_PATH_VALUE) String value);
    
    @Query("SELECT p FROM SgvCliente p "+
	    " WHERE TRANSLATE(REPLACE(UPPER(concat(p.nombre, p.apPaterno, p.apMaterno)), ' ', ''), 'ÁÉÍÓÚ','AEIOU')"+
	    " LIKE TRANSLATE(REPLACE(UPPER(concat('%', :value,'%')), ' ', ''), 'ÁÉÍÓÚ','AEIOU')")
    Page<SgvCliente> findByName(Pageable pageable, @Param(AppConstantes.PARAM_PATH_VALUE) String value);

    List<SgvCliente> findByDni(String dni);
    
    @Query("SELECT p FROM SgvCliente p " + 
    		" where (TRANSLATE(REPLACE(UPPER(concat(p.nombre, p.apPaterno, p.apMaterno)), ' ', ''), 'ÁÉÍÓÚ','AEIOU') LIKE " + 
    		" TRANSLATE(REPLACE(UPPER(concat('%', :value,'%')), ' ', ''), 'ÁÉÍÓÚ','AEIOU') " + 
    		" OR TRANSLATE(REPLACE(UPPER(concat(p.dni)), ' ', ''), 'ÁÉÍÓÚ','AEIOU') LIKE " + 
    		" TRANSLATE(REPLACE(UPPER(concat('%', :value,'%')), ' ', ''), 'ÁÉÍÓÚ','AEIOU'))" + 
    		" AND p.indActivo = true")
    List<SgvCliente> findByNombreOrCodigoOrderByIdClienteAsc(Pageable pageable, String value);
}