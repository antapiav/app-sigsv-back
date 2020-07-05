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
@Table(name = AppConstantesSql.TABLE_SGV_DETALLE_VENTA_TEMP, schema = AppConstantesSql.SCHEMA_SIGSV)
public class SgvDetalleVentaTemp implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_detalle_venta")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = AppConstantesSql.SEQUENCE_ID_SQ_DETALLE_VENTA)
    @SequenceGenerator(name = AppConstantesSql.SEQUENCE_ID_SQ_DETALLE_VENTA, sequenceName = AppConstantesSql.SCHEMA_SIGSV+"."+AppConstantesSql.SEQUENCE_SQ_DETALLE_VENTA, allocationSize = 1)
    private Long idDetalleVenta;
    
    @Column(name = "cantidad")
    private Double cantidad;

    @Column(name = "total")
    private Double total;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_id_producto", nullable = false, updatable = true)
    private SgvProducto sgvProducto;

    @ManyToOne
    @JoinColumn(name = "fk_id_venta")
    private SgvVenta sgvVenta;

    @ManyToOne
    @JoinColumn(name = "fk_id_unidad")
    private SgvMultitabla sgvUnidad;

}
