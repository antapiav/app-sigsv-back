package com.devapp.sigsv.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.devapp.sigsv.util.AppConstantesSql;

import lombok.Data;

@Data
@Entity
@Table(name = AppConstantesSql.TABLE_SGV_VENTA, schema = AppConstantesSql.SCHEMA_SIGSV)
public class SgvVenta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_venta")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = AppConstantesSql.SEQUENCE_ID_SQ_VENTA)
    @SequenceGenerator(name = AppConstantesSql.SEQUENCE_ID_SQ_VENTA, sequenceName = AppConstantesSql.SCHEMA_SIGSV+"."+AppConstantesSql.SEQUENCE_SQ_VENTA, allocationSize = 1)
    private Long idVenta;

    @Column(name = "num_comprobante")
    private String numComprobante;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha", columnDefinition = "date")
    private Date fecha;

    @Column(name = "valor_venta")
    private Double valorVenta;

    @Column(name = "igv")
    private Double igv;

    @Column(name = "importe_total")
    private Double importeTotal;
    
    @Column(name = "monto_impuesto_bolsa")
    private Double montoImpuestoBolsa;

    @Column(name = "descuento_venta")
    private Double descuentoVenta;

    @Column(name = "monto_pagado")
    private Double montoPagado;

    /*@Column(name = "vuelto")
    private Double vuelto;*/

    @Column(name = "cant_bolsa")//eliminar
    private Integer cantBolsa;

    @Column(name = "ind_activo")
    private Boolean indActivo;

    @ManyToOne
    @JoinColumn(name = "fk_id_cliente")
    private SgvCliente sgvCliente;

    /*@ManyToOne
    @JoinColumn(name = "fk_id_usuario")//eliminar
    private SgvUsuario sgvUsuario;*/

    @ManyToOne
    @JoinColumn(name = "fk_id_tipo_comprobante")
    private SgvMultitabla sgvTipoComprobante;

    /*@ManyToOne
    @JoinColumn(name = "fk_id_monto_bolsa")
    private SgvMultitabla sgvMontoBolsa;*/

}
