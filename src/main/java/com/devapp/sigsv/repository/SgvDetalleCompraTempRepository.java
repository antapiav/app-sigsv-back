package com.devapp.sigsv.repository;

import java.util.List;

import com.devapp.sigsv.model.entity.SgvDetalleCompraTemp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("sgvDetalleCompraTempRepository")
public interface SgvDetalleCompraTempRepository extends JpaRepository<SgvDetalleCompraTemp, Long>{
    List<SgvDetalleCompraTemp> findAllByOrderByIdDetalleCompraAsc();
}