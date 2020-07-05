package com.devapp.sigsv.model.bean;

import java.io.Serializable;

import lombok.Data;

@Data
public class ProveedorCategoria implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idProveedorCategoria;

    private String nombre;

    private String detalle;

    private Boolean indActivo;
    
}