package com.devapp.sigsv.model.bean;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idCliente;

    @NotBlank(message = "D.N.I. OBLIGATORIO")
    @Size(min = 8, max = 8)
    private String dni;
    
    @NotBlank(message = "NOMBRE. OBLIGATORIO")
    private String nombre;

    @NotBlank(message = "AP. PATERNO OBLIGATORIO")
    private String apPaterno;
    
    @NotBlank(message = "AP. MATERNO OBLIGATORIO")
    private String apMaterno;
    
    private String direccion;
    
    private String contacto;
    
    private String telefono;
    
    private String email;

    private Boolean indActivo;
}