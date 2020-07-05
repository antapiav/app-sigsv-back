package com.devapp.sigsv.repository;

import com.devapp.sigsv.model.entity.SgvDetalleVenta;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("sgvDetalleVentaRepository")
public interface SgvDetalleVentaRepository extends JpaRepository<SgvDetalleVenta, Long> {
    List<SgvDetalleVenta> findBySgvVentaIdVenta(Long idVenta);
}