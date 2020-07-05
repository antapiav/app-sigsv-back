package com.devapp.sigsv.model.bean;

import java.io.Serializable;

import lombok.Data;

@Data
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Long idProducto;

    private String nombre;

    private String detalle;

    private String codigo;

    private Double preCosto;

    private Double preVenta;

    private Double utilidad;

    private Boolean indActivo;

    private ProductoCategoria productoCategoria;

    private Multitabla unidad;
    /*private DetalleCompra detalleCompra;
    private DetalleVenta detalleVenta;
    private Stock stock;*/
}