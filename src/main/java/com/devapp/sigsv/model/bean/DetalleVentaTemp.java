package com.devapp.sigsv.model.bean;

import java.io.Serializable;

import lombok.Data;

@Data
public class DetalleVentaTemp implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Long idDetalleVenta;
    
    private Double cantidad;

    private Double total;

    private Producto producto;

    private Venta venta;

    private Multitabla unidad;
}