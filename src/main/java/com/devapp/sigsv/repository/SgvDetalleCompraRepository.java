package com.devapp.sigsv.repository;

import com.devapp.sigsv.model.entity.SgvDetalleCompra;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("sgvDetalleCompraRepository")
public interface SgvDetalleCompraRepository extends JpaRepository<SgvDetalleCompra, Long> {
	List<SgvDetalleCompra> findBySgvCompraIdCompra(Long idCompra);
}