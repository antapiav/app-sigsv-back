package com.devapp.sigsv.repository;

import com.devapp.sigsv.model.entity.SgvMultitabla;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("sgvMultitablaRepository")
public interface SgvMultitablaRepository extends JpaRepository<SgvMultitabla, Long> {
	
	@Query("SELECT p FROM SgvMultitabla p where p.idTabla = :idTabla and p.idItem != '00' and p.indActivo = true ORDER BY p.idCodigo ASC")
    List<SgvMultitabla> findByIdTablaAndIndActivoOrderByIdCodigoAsc(String idTabla);
}