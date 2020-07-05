package com.devapp.sigsv.repository;

import com.devapp.sigsv.model.entity.SgvCompra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("sgvCompraRepository")
public interface SgvCompraRepository extends JpaRepository<SgvCompra, Long> {
    
}