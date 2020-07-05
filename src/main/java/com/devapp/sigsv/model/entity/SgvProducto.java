package com.devapp.sigsv.model.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
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
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Data
@Entity
@Table(name = AppConstantesSql.TABLE_SGV_PRODUCTO, schema = AppConstantesSql.SCHEMA_SIGSV)
public class SgvProducto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_producto")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = AppConstantesSql.SEQUENCE_ID_SQ_PRODUCTO)
    @SequenceGenerator(name = AppConstantesSql.SEQUENCE_ID_SQ_PRODUCTO, sequenceName = AppConstantesSql.SCHEMA_SIGSV+"."+AppConstantesSql.SEQUENCE_SQ_PRODUCTO, allocationSize = 1)
    private Long idProducto;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "detalle")
    private String detalle;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "pre_costo")
    private Double preCosto;

    @Column(name = "pre_venta")
    private Double preVenta;

    @Column(name = "utilidad")
    private Double utilidad;

    @Column(name = "ind_activo")
    private Boolean indActivo;

    @ManyToOne
    @JoinColumn(name = "fk_id_producto_categoria")
    private SgvProductoCategoria sgvProductoCategoria;

    @ManyToOne
    @JoinColumn(name = "fk_id_unidad")
    private SgvMultitabla sgvUnidad;

    /*@OneToOne(mappedBy = "sgvProducto", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
    private SgvDetalleCompra sgvDetalleCompra;

    @OneToOne(mappedBy = "sgvProducto", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
    private SgvDetalleVenta sgvDetalleVenta;

    @OneToOne(mappedBy = "sgvProducto", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
    private SgvDetalleCompraTemp sgvDetalleCompraTemp;

    @OneToOne(mappedBy = "sgvProducto", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
    private SgvDetalleVentaTemp sgvDetalleVentaTemp;*/

    @JsonManagedReference
    @OneToOne(mappedBy = "sgvProducto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private SgvStock sgvStock;

}
