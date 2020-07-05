package com.devapp.sigsv.model.bean;

import java.io.Serializable;

import javax.persistence.Column;

import lombok.Data;

@Data
public class Stock implements Serializable{

    private static final long serialVersionUID = 1L;

    private Long idStock;

    private Integer stockComprado;

    private Integer stockVendido;
    
    private Producto producto;
    
}