package com.devapp.sigsv.model.bean;

import java.io.Serializable;

import lombok.Data;

@Data
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Long idUsuario;

    private String nombre;

    private String apPaterno;

    private String apMaterno;

    private String dni;

    private Boolean indActivo;

    private String clave;

    private Multitabla tipoUsuario;
    
}