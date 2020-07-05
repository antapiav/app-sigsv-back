package com.devapp.sigsv.model.bean;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class Sunat implements Serializable {

    private static final long serialVersionUID = 1L;

    /*private String createdAt;
    private String updatedAt;
    private Long id;
    private String title;
    private String description;*/

    private String ruc;
    private String razon_social;
    private String ciiu;
    private String fecha_actividad;
    private String contribuyente_condicion;
    private String contribuyente_tipo;
    private String contribuyente_estado;
    private String nombre_comercial;
    private String fecha_inscripcion;
    private String domicilio_fiscal;
    private String sistema_emision;
    private String sistema_contabilidad;
    private String actividad_exterior;
    private String emision_electronica;
    private String fecha_inscripcion_ple;
    private String fecha_baja;
}
