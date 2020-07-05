package com.devapp.sigsv.model.bean;

import java.io.Serializable;

import lombok.Data;

@Data
public class DetalleCompraTemp implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idDetalleCompra;
    
    private Double cantidad;

    private Double total;

    private Producto producto;

    private Compra compra;

    private Multitabla unidad;
    
}