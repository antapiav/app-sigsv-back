package com.devapp.sigsv.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.devapp.sigsv.util.AppConstantesSql;

import lombok.Data;

@Data
@Entity
@Table(name = AppConstantesSql.TABLE_SGV_DETALLE_COMPRA_TEMP, schema = AppConstantesSql.SCHEMA_SIGSV)
public class SgvDetalleCompraTemp implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_detalle_compra")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = AppConstantesSql.SEQUENCE_ID_SQ_DETALLE_COMPRA)
    @SequenceGenerator(name = AppConstantesSql.SEQUENCE_ID_SQ_DETALLE_COMPRA, sequenceName = AppConstantesSql.SCHEMA_SIGSV+"."+AppConstantesSql.SEQUENCE_SQ_DETALLE_COMPRA, allocationSize = 1)
    private Long idDetalleCompra;
    
    @Column(name = "cantidad")
    private Double cantidad;

    @Column(name = "total")
    private Double total;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_id_producto", nullable = false, updatable = true)
    private SgvProducto sgvProducto;

    @ManyToOne
    @JoinColumn(name = "fk_id_compra")
    private SgvCompra sgvCompra;

    @ManyToOne
    @JoinColumn(name = "fk_id_unidad")
    private SgvMultitabla sgvUnidad;
}
