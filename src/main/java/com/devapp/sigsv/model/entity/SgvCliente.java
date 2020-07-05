package com.devapp.sigsv.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.devapp.sigsv.util.AppConstantesSql;

import lombok.Data;

@Data
@Entity
@Table(name = AppConstantesSql.TABLE_SGV_CLIENTE, schema = AppConstantesSql.SCHEMA_SIGSV)
public class SgvCliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "id_cliente")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = AppConstantesSql.SEQUENCE_ID_SQ_CLIENTE)
    @SequenceGenerator(name = AppConstantesSql.SEQUENCE_ID_SQ_CLIENTE, sequenceName = AppConstantesSql.SCHEMA_SIGSV+"."+AppConstantesSql.SEQUENCE_SQ_CLIENTE, allocationSize = 1)
    private Long idCliente;

    @Column(name = "dni")
    private String dni;
    
    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "ap_paterno")
    private String apPaterno;
    
    @Column(name = "ap_materno")
    private String apMaterno;
    
    @Column(name = "direccion")
    private String direccion;
    
    @Column(name = "contacto")
    private String contacto;
    
    @Column(name = "telefono")
    private String telefono;
    
    @Column(name = "email")
    private String email;

    @Column(name = "ind_activo")
    private Boolean indActivo;

}
