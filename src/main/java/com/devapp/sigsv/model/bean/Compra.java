package com.devapp.sigsv.model.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Compra implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idCompra;
    
    private String numComprobante;
    
    private Date fecha;
    
    private Double valorCompra;
    
    private Double igv;
    
    private Double importeTotal;
    
    private Boolean indActivo;

    private Proveedor proveedor;
    
    private Usuario usuario;
    
    private Multitabla tipoComprobante;
    
    private List<DetalleCompra> lstDetalleCompra;
    
}