package com.devapp.sigsv.util;

public interface AppMessages {
    
    String GENERIC_SUCCESS = "message.generic.success";
	String GENERIC_FAILED = "message.generic.failed";
	String GENERIC_ERROR = "message.generic.error";
	
	String GENERIC_REGISTRO_NO_ENCONTRADO = "message.generic.getUnique.empty";
    String GENERIC_EMPTY_DATA_DOCUMENT = "message.generic.empty.data.document";
    String GENERIC_REGISTRO_ENCONTRADO = "message.generic.registro.encontrado";
    
    //titulos hoja excel
    String CLIENTE_TITLE_SHEET = "message.archivoExcel.cliente.title.sheet";
    String PROVEEDOR_CATEGORIA_TITLE_SHEET = "message.archivoExcel.proveedorCategoria.title.sheet";
    String PRODUCTO_CATEGORIA_CLIENTE_TITLE_SHEET = "message.archivoExcel.productoCategoria.title.sheet";
    String PROVEEDOR_TITLE_SHEET = "message.archivoExcel.proveedor.title.sheet";
    String PRODUCTO_TITLE_SHEET = "message.archivoExcel.producto.title.sheet";
    String COMPRA_TITLE_SHEET = "message.archivoExcel.compra.title.sheet";
    String VENTA_TITLE_SHEET = "message.archivoExcel.venta.title.sheet";
    
    //head hoja excel cliente
    String CLIENTE_HEADER_DNI = "message.archivoExcel.cliente.header.dni";
    String CLIENTE_HEADER_NOMBRE = "message.archivoExcel.cliente.header.nombre";
    String CLIENTE_HEADER_AP_PATERNO= "message.archivoExcel.cliente.header.apPaterno";
    String CLIENTE_HEADER_AP_MATERNO = "message.archivoExcel.cliente.header.apMaterno";
    String CLIENTE_HEADER_IND_ACTIVO = "message.archivoExcel.cliente.header.indActivo";
    
    //head hoja excel cat. proveedor
    String CAT_PROVEEDOR_HEADER_CODIGO = "message.archivoExcel.catProveedor.header.codigo";
    String CAT_PROVEEDOR_HEADER_CATEGORIA = "message.archivoExcel.catProveedor.header.categoria";
    String CAT_PROVEEDOR_HEADER_DESCRIPCION = "message.archivoExcel.catProveedor.header.descripcion";
    String CAT_PROVEEDOR_HEADER_IND_ACTIVO = "message.archivoExcel.catProveedor.header.indActivo";
    
    //head hoja excel cat. producto
    String CAT_PRODUCTO_HEADER_CODIGO = "message.archivoExcel.catProducto.header.codigo";
    String CAT_PRODUCTO_HEADER_CATEGORIA = "message.archivoExcel.catProducto.header.categoria";
    String CAT_PRODUCTO_HEADER_DESCRIPCION = "message.archivoExcel.catProducto.header.descripcion";
    String CAT_PRODUCTO_HEADER_IND_ACTIVO = "message.archivoExcel.catProducto.header.indActivo";
    
    //head hoja excel proveedor
    String PROVEEDOR_HEADER_TIPO_DOCUMENTO = "message.archivoExcel.proveedor.header.tipoDocumento";
    String PROVEEDOR_HEADER_NUM_DOCUMENTO = "message.archivoExcel.proveedor.header.numDocumento";
    String PROVEEDOR_HEADER_RAZ_SOCIAL = "message.archivoExcel.proveedor.header.razSocial";
    String PROVEEDOR_HEADER_CATEGORIA = "message.archivoExcel.proveedor.header.categoria";
    String PROVEEDOR_HEADER_IND_ACTIVO = "message.archivoExcel.proveedor.header.indActivo";
    
    //head hoja excel producto
    String PRODUCTO_HEADER_CODIGO = "message.archivoExcel.producto.header.codigo";
    String PRODUCTO_HEADER_CATEGORIA = "message.archivoExcel.producto.header.categoria";
    String PRODUCTO_HEADER_NOMBRE = "message.archivoExcel.producto.header.nombre";
    String PRODUCTO_HEADER_DETALLE = "message.archivoExcel.producto.header.detalle";
    String PRODUCTO_HEADER_UNIDAD = "message.archivoExcel.producto.header.unidad";
    String PRODUCTO_HEADER_IND_ACTIVO = "message.archivoExcel.producto.header.indActivo";
    String PRODUCTO_HEADER_COMPRAS = "message.archivoExcel.producto.header.compras";
    String PRODUCTO_HEADER_VENTAS = "message.archivoExcel.producto.header.ventas";
    String PRODUCTO_HEADER_STOCK = "message.archivoExcel.producto.header.stock";
    
    //head hoja excel compra
    String COMPRA_HEADER_TIPO_COMPROBANTE = "message.archivoExcel.compra.header.tipoComprobante";
    String COMPRA_HEADER_NUM_COMPROBANTE = "message.archivoExcel.compra.header.numComprobante";
    String COMPRA_HEADER_FECHA = "message.archivoExcel.compra.header.fecha";
    String COMPRA_HEADER_PROVEEDOR_TIPO_DOCUMENTO = "message.archivoExcel.compra.header.proveedor.tipoDocumento";
    String COMPRA_HEADER_PROVEEDOR_NUM_DOCUMENTO = "message.archivoExcel.compra.header.proveedor.numDocumento";
    String COMPRA_HEADER_PROVEEDOR_RAZ_SOCIAL = "message.archivoExcel.compra.header.proveedor.razSocial";
    String COMPRA_HEADER_IMPORTE_TOTAL = "message.archivoExcel.compra.header.importeTotal";
    String COMPRA_HEADER_IND_ACTIVO = "message.archivoExcel.compra.header.indActivo";
    
    //head hoja excel venta
    String VENTA_HEADER_TIPO_COMPROBANTE = "message.archivoExcel.venta.header.tipoComprobante";
    String VENTA_HEADER_NUM_COMPROBANTE = "message.archivoExcel.venta.header.numComprobante";
    String VENTA_HEADER_FECHA = "message.archivoExcel.venta.header.fecha";
    String VENTA_HEADER_DNI = "message.archivoExcel.venta.header.dni";
    String VENTA_HEADER_CLIENTE_NOMBRE = "message.archivoExcel.venta.header.cliente.nombre";
    String VENTA_HEADER_CLIENTE_AP_PATERNO = "message.archivoExcel.venta.header.cliente.apPaterno";
    String VENTA_HEADER_CLIENTE_AP_MATERNO = "message.archivoExcel.venta.header.cliente.apMaterno";
    String VENTA_HEADER_IMPORTE_TOTAL = "message.archivoExcel.venta.header.importeTotal";
    String VENTA_HEADER_IND_ACTIVO = "message.archivoExcel.venta.header.indActivo";
}