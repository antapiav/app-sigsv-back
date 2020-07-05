package com.devapp.sigsv.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.devapp.sigsv.util.AppConstantesSql;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Data
@Entity
@Table(name = AppConstantesSql.TABLE_SGV_STOCK, schema = AppConstantesSql.SCHEMA_SIGSV)
public class SgvStock implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_stock")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = AppConstantesSql.SEQUENCE_ID_SQ_STOCK)
    @SequenceGenerator(name = AppConstantesSql.SEQUENCE_ID_SQ_STOCK, sequenceName = AppConstantesSql.SCHEMA_SIGSV+"."+AppConstantesSql.SEQUENCE_SQ_STOCK, allocationSize = 1)
    private Long idStock;

    @Column(name = "stock_comprado")
    private Integer stockComprado;

    @Column(name = "stock_vendido")
    private Integer stockVendido;

    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_id_producto", nullable = false, updatable = true)
    private SgvProducto sgvProducto;

}
