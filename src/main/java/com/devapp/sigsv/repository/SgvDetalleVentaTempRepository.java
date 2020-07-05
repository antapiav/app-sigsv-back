package com.devapp.sigsv.repository;

import java.util.List;

import com.devapp.sigsv.model.entity.SgvDetalleVentaTemp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("sgvDetalleVentaTempRepository")
public interface SgvDetalleVentaTempRepository extends JpaRepository<SgvDetalleVentaTemp, Long>{
    List<SgvDetalleVentaTemp> findAllByOrderByIdDetalleVentaAsc();
}