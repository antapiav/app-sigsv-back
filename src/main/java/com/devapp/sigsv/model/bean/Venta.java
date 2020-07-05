package com.devapp.sigsv.model.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Venta implements Serializable{

    private static final long serialVersionUID = 1L;

    private Long idVenta;
    
    private Multitabla tipoComprobante;
    
    private String numComprobante;
    
    private Date fecha;

    private Cliente cliente;
    
    private Double descuentoVenta;
    
    private Double montoImpuestoBolsa;//

    private Double montoPagado;

    private Double valorVenta;

    private Double igv;

    private Double importeTotal;

    private Boolean indActivo;


    private Integer cantBolsa;

    private Usuario usuario;
    
    private List<DetalleVenta> lstDetalleVenta;

}