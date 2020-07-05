package com.devapp.sigsv.repository;

import org.springframework.stereotype.Repository;

import com.devapp.sigsv.model.entity.SgvVenta;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository("sgvVentaRepository")
public interface SgvVentaRepository extends JpaRepository<SgvVenta, Long> {
    
}