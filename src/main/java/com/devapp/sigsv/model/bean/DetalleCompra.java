package com.devapp.sigsv.model.bean;

import java.io.Serializable;

import lombok.Data;

@Data
public class DetalleCompra implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idDetalleCompra;
    
    private Integer cantidad;

    private Double descuento;

    private Producto producto;

    private Compra compra;

    //private Multitabla unidad;
    
}