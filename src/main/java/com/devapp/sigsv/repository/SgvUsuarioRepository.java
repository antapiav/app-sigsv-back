package com.devapp.sigsv.repository;

import com.devapp.sigsv.model.entity.SgvUsuario;
import com.devapp.sigsv.util.AppConstantes;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("sgvUsuarioRepository")
public interface SgvUsuarioRepository extends JpaRepository<SgvUsuario, Long> {
    @Query("SELECT p FROM SgvUsuario p WHERE p.dni LIKE concat('%', :dato,'%')")
    Page<SgvUsuario> findByDni(Pageable pageable, @Param(AppConstantes.PARAM_PATH_dato) String dato);
    
    @Query("SELECT p FROM SgvUsuario p "+
	    " WHERE TRANSLATE(REPLACE(UPPER(concat(p.nombre, p.apPatenro, p.apMaterno)), ' ', ''), 'ÁÉÍÓÚ','AEIOU')"+
	    " LIKE TRANSLATE(REPLACE(UPPER(concat('%', :dato,'%')), ' ', ''), 'ÁÉÍÓÚ','AEIOU')")
    Page<SgvUsuario> findByName(Pageable pageable, @Param(AppConstantes.PARAM_PATH_dato) String dato);
}