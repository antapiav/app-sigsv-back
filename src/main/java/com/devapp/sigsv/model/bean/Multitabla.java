package com.devapp.sigsv.model.bean;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class Multitabla implements Serializable {

	private static final long serialVersionUID = 1L;

	private String idCodigo;

	private String idTabla;

	private String idItem;

	private String descripcion;

	private String descripcionValor;

    private Boolean indActivo;
    
}