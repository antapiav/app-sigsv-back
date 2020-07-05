package com.devapp.sigsv.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.devapp.sigsv.util.AppConstantesSql;

import lombok.Data;

@Data
@Entity
@Table(name=AppConstantesSql.TABLE_SGV_MULTITABLA, schema=AppConstantesSql.SCHEMA_SIGSV)
public class SgvMultitabla implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_codigo")
	private String idCodigo;

	@Column(name = "id_tabla")
	private String idTabla;

	@Column(name = "id_item")
	private String idItem;

	@Column(name = "descripcion")
	private String descripcion;

	@Column(name = "desc_valor")
	private String descripcionValor;

	@Column(name = "ind_activo")
    private Boolean indActivo;

}
