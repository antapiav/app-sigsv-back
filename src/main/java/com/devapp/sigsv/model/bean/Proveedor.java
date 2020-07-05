package com.devapp.sigsv.model.bean;

import java.io.Serializable;

import lombok.Data;

@Data
public class Proveedor implements Serializable{

    private static final long serialVersionUID = 1L;

    private Long idProveedor;

    private String numDocumento;

    private String nombre;

    private String direccion;

    private String contacto;

    private String telefono;

    private String email;

    private Boolean indActivo;

    private Multitabla tipoDocumento;

    private ProveedorCategoria proveedorCategoria;
    
}