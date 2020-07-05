package com.devapp.sigsv.util;

public interface AppUrl {

    String ALL = "/**";
	String ROOT = "/";
    String PATTERN_ACTUATOR = "/actuator/**";
    String API = "/api";
    
    //path controller
    String URL_PARAMETRO = "/parametro";
    String URL_USUARIO = "/usuario";
    String URL_CLIENTE = "/cliente";
    String URL_CONSULTA = "/consulta";
    String URL_COMPRA = "/compra";
    String URL_DETALLE_COMPRA = "/detalle_compra";
    String URL_DETALLE_COMPRA_TEMP = "/detalle_compra_temp";
    String URL_DETALLE_VENTA = "/detalle_venta";
    String URL_DETALLE_VENTA_TEMP = "/detalle_venta_temp";
    String URL_PRODUCTO = "/producto";
    String URL_PRODUCTO_CATEGORIA = "/producto_categoria";
    String URL_PROVEEDOR = "/proveedor";
    String URL_PROVEEDOR_CATEGORIA = "/proveedor_categoria";
    String URL_STOCK = "/stock";
    String URL_VENTA = "/venta";

    //path variables url tipo /{id}/
    String API_ID = "/{id}";
    String API_VALUE = "/{value}";
    String API_NUM_DOCUMENT = "/{numDocument}";
    String API_IND_ACTIVO = "/{ind}";

    //path metodos de clase
    String URL_LST_PAGINADO_FILTER = "/paginado_filter";
    String URL_LST_GET_PAGINADO = "/paginado";
    String URL_LST_GET = "/lst";
    String URL_LST_CHART_BAR = "/chart_bar";
    String URL_LST_CHART_LINE = "/chart_line";
    String URL_LST_CHART_CIRCLE = "/chart_circle";
    String URL_INSERTAR = "/insertar";
    String URL_MODIFICAR = "/modificar";
    String URL_ELIMINAR = "/eliminar" + API_ID;
    String URL_IND_ACTIVO = "/ind_activo" + API_ID;
    String URL_DETALLE = "/detalle" + API_ID;
    String URL_RENIEC = "/reniec" + API_NUM_DOCUMENT;
    String URL_SUNAT = "/sunat" + API_NUM_DOCUMENT;
    
    String URL_TIPO_DOCUMENTO = "/tipo_documento";
    String URL_TIPO_COMPROBANTE = "/tipo_comprobante";
    String URL_UNIDAD_MEDIDA = "/unidad_medida";
    String URL_TIPO_USUARIO = "/tipo_usuario";
    String URL_MONTO_BOLSA = "/monto_bolsa";
    
    //reportes
    String API_REPORTE_EXCEL = API + "/reporte-excel";
    String API_REPORTE_PDF = API + "/reporte-pdf";
    
    String API_REPORTE_EXCEL_CLIENTE = "/cliente";
    String API_REPORTE_EXCEL_PROVEEDOR_CATEGORIA = "/proveedor_categoria";
    String API_REPORTE_EXCEL_PRODUCTO_CATEGORIA = "/producto_categoria";
    String API_REPORTE_EXCEL_PROVEEDOR = "/proveedor";
    String API_REPORTE_EXCEL_PRODUCTO = "/producto";
    String API_REPORTE_EXCEL_COMPRA = "/compra";
    String API_REPORTE_EXCEL_VENTA = "/venta";
    
    String API_REPORTE_PDF_VENTA = "/venta" + API_ID;;
}