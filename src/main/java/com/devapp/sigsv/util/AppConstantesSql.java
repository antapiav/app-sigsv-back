package com.devapp.sigsv.util;

public interface AppConstantesSql {
	
	String SCHEMA_SIGSV = "sigsv";
	
	//constantes nombre tabla
	String TABLE_SGV_MULTITABLA = "sgv_multitabla";
	String TABLE_SGV_CLIENTE = "sgv_cliente";
	String TABLE_SGV_COMPRA = "sgv_compra";
	String TABLE_SGV_DETALLE_COMPRA = "sgv_detalle_compra";
	String TABLE_SGV_DETALLE_VENTA = "sgv_detalle_venta";
	String TABLE_SGV_DETALLE_COMPRA_TEMP = "sgv_detalle_compra_temp";
	String TABLE_SGV_DETALLE_VENTA_TEMP = "sgv_detalle_venta_temp";
	String TABLE_SGV_PRODUCTO = "sgv_producto";
	String TABLE_SGV_PRODUCTO_CATEGORIA = "sgv_producto_categoria";
	String TABLE_SGV_PROVEEDOR = "sgv_proveedor";
	String TABLE_SGV_PROVEEDOR_CATEGORIA = "sgv_proveedor_categoria";
	String TABLE_SGV_STOCK = "sgv_stock";
	String TABLE_SGV_USUARIO = "sgv_usuario";
	String TABLE_SGV_VENTA = "sgv_venta";

	//secuencias nombre
	String SEQUENCE_SQ_CLIENTE = "sq_cliente";
	String SEQUENCE_SQ_COMPRA = "sq_compra";
	String SEQUENCE_SQ_DETALLE_COMPRA = "sq_detalle_compra";
	String SEQUENCE_SQ_DETALLE_VENTA = "sq_detalle_venta";
	String SEQUENCE_SQ_PRODUCTO = "sq_producto";
	String SEQUENCE_SQ_PRODUCTO_CATEGORIA = "sq_producto_categoria";
	String SEQUENCE_SQ_PROVEEDOR = "sq_proveedor";
	String SEQUENCE_SQ_PROVEEDOR_CATEGORIA = "sq_proveedor_categoria";
	String SEQUENCE_SQ_STOCK = "sq_stock";
	String SEQUENCE_SQ_USUARIO = "sq_usuario";
	String SEQUENCE_SQ_VENTA = "sq_venta";

	//sequence id
	String SEQUENCE_ID_SQ_CLIENTE = "id_sq_cliente";
	String SEQUENCE_ID_SQ_COMPRA = "id_sq_compra";
	String SEQUENCE_ID_SQ_DETALLE_COMPRA = "id_sq_detalle_compra";
	String SEQUENCE_ID_SQ_DETALLE_VENTA = "id_sq_detalle_venta";
	String SEQUENCE_ID_SQ_PRODUCTO = "id_sq_producto";
	String SEQUENCE_ID_SQ_PRODUCTO_CATEGORIA = "id_sq_producto_categoria";
	String SEQUENCE_ID_SQ_PROVEEDOR = "id_sq_proveedor";
	String SEQUENCE_ID_SQ_PROVEEDOR_CATEGORIA = "id_sq_proveedor_categoria";
	String SEQUENCE_ID_SQ_STOCK = "id_sq_stock";
	String SEQUENCE_ID_SQ_USUARIO = "id_sq_usuario";
	String SEQUENCE_ID_SQ_VENTA = "id_sq_venta";

	//Pagination function input
	String PARAM_NUMERO_PAGINA = "p_pagina";
	String PARAM_CANTIDAD_PAGINA = "p_cantidad_por_pagina";
	String PARAM_FILTROS = "p_filtros";
	//pagination function output
	String PARAM_OUT_TOTAL = "p_out_total";
	String PARAM_OUT_PAGINA = "p_out_pagina";
	String PARAM_OUT_LST_COMPRA = "p_out_cursor_compra";
	String PARAM_OUT_TOTAL_IMPORTE = "p_out_total_importe";


	String PARAM_OUT_LST_VENTA = "p_out_cursor_venta";

	/*String PARAM_PAGE = "p_page";
	String PARAM_LIMIT = "p_limit";
	String PARAM_FILTERS = "p_filters";*/

	//funciones nama
	String FUNCTION_FILTRAR_COMPRA_PAGINADO = "f_filtrar_compra_paginado";
	String FUNCTION_FILTRAR_VENTA_PAGINADO = "f_filtrar_venta_paginado";

}
