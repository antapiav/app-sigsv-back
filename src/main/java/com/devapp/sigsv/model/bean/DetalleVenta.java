package com.devapp.sigsv.model.bean;

import java.io.Serializable;

import lombok.Data;

@Data
public class DetalleVenta implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Long idDetalleVenta;
    
    private Venta venta;

    private Producto producto;
    
    private Integer cantidad;

    private Double descuento;
}