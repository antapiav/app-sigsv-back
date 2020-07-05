package com.devapp.sigsv.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public interface AppConstantes {
	
	String STRING_VACIO = "";

    String NOT_FOUND_KEY = "notFoundKey";
    
    String PARAM_PATH_ID = "id";
    String PARAM_PATH_NUM_DOCUMENT = "numDocument";
    String PARAM_PATH_IND_ACTIVO = "ind";

    String PARAM_PATH_tipo = "tipo";
    String PARAM_PATH_dato = "dato";
    
    String PARAM_PATH_NAME = "name";
    String PARAM_PATH_OPERATOR = "operator";
    String PARAM_PATH_VALUE = "value";
    
    String URL_CONSULTA_SUNAT = "sunat.consulta.url";
	String URL_CONSULTA_RENIEC = "reniec.consulta.url";
	
	String REQUEST_TIME_MILIS = "time.request.milis";
	
	String AGENTE_WEB_MOZILLA = "Mozilla/5.0";
	
	String CHARACTER_ENCODING_UTF_8 = "UTF-8";

    Boolean IS_MESSAGE_KEY = true;
	Boolean IS_NOT_MESSAGE_KEY = false;
	
	//constantes string estado IND ACTIVO
	String STRING_IND_ACTIVO_TRUE = "ACTIVO";
	String STRING_IND_ACTIVO_FALSE = "BAJA";
	
	//constantes nombre filtros busqueda
	String STRING_NOMBRE = "nombre";
	String STRING_DNI = "dni";
	String STRING_CODIGO = "codigo";
	String STRING_CATEGORIA = "categoria";
	
	//atrribute Sort
	String ID_PROVEEDOR_CATEGORIA = "idProveedorCategoria";
	String ID_CLIENTE = "idCliente";
	String ID_PROVEEDOR = "idProveedor";
	String ID_PRODUCTO_CATEGORIA = "idProductoCategoria";
	String ID_PRODUCTO = "idProducto";
	
	//constantes nombres reportes
	String NAME_EXCEL_FILE_CLIENTE = "Reporte de clientes";
	String NAME_EXCEL_FILE_CATEGORIA_DE_PROVEEDOR = "Reporte de categoria de proveedores";
	String NAME_EXCEL_FILE_CATEGORIA_PRODUCTO = "Reporte de categoria de productos";
	String NAME_EXCEL_FILE_PROVEEDOR = "Reporte de proveedores";
	String NAME_EXCEL_FILE_PRODUCTO = "Reporte de productos";
	String NAME_EXCEL_FILE_COMPRA = "Reporte de compras";
	String NAME_EXCEL_FILE_VENTA = "Reporte de ventas";
	
	//constantes excel
	String EXCEL_FORMAT_DECIMAL = "#,##0.00";
	String EXCEL_FORMAT_DATE = "dd/mm/yyyy";
	String EXCEL_FORMAT_DATETIME = "dd/mm/yyyy HH:mm:ss";
	
	String IMPORTE_TOTAL_PAGINA = "Total Página (PEN)";
	String IMPORTE_TOTAL = "Total (PEN)";
	
	String EXCEL_URL_TEMPLATE = "EXCEL_URL_TEMPLATE";
	String EXCEL_TYPE = "EXCEL_TYPE";
	String EXCEL_FILENAME = "EXCEL_FILENAME";
	String EXCEL_DOWNLOAD = "EXCEL_DOWNLOAD";
	
	String PARAM_RESPONSE_CODE = "code";
	String PARAM_RESPONSE_MESSAGE = "message";
	String PARAM_RESPONSE_DOWNLOAD = "download";
	String PARAM_RESPONSE_CONTENT_TYPE = "contentType";
	String PARAM_RESPONSE_FILE = "file";
	String PARAM_RESPONSE_FILE_EXTENSION = "fileExtension";
	String PARAM_RESPONSE_FILE_FILENAME = "fileFilename";
	
	String FORMAT_CONTENT_ATTACHMENT_BASE = "attachment; filename=\"%s\"";
	String FORMAT_CONTENT_ATTACHMENT = "attachment; filename=\"%s%s\"";
	String FORMAT_CONTENT_INLINE = "inline; filename=\"%s%s\"";
	
	String CONTENT_DISPOSITION = "Content-Disposition";
	
	String PARAM_PAGINATION = "pagination";
	String PARAM_LIST = "list";
	
	String PARAM_PDF_EXTENCION = ".pdf";
	String PARAM_PDF_FILE = "application/pdf";
	String STRING_SUB_GUION = "_";
	String STRING_SLASH = "/";
	
	ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	
	String TBL_TIPO_DOCUMENTO = "004";
	String TBL_TIPO_COMPROBANTE = "002";
	String TBL_UNIDAD_MEDIDA = "003";
	String TBL_TIPO_USUARIO = "001";
	String TBL_MONTO_BOLSA = "005";
	
	enum FileType {
		XLS("application/vnd.ms-excel", ".xls"), 
		XLSX("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", ".xlsx"), 
		XLSM("application/vnd.ms-excel.sheet.macroEnabled.12", ".xlsm"), 
		XLSX_STREAMING("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", ".xlsx"), 
		PDF("application/pdf", ".pdf"), 
		IMG_JPG("image/jpg", ".jpg"), 
		ZIP("application/zip", ".zip"),
		DOCX("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", ".docx");

		private String contentType;
		private String extension;

		FileType(String contentType, String extension) {
			this.contentType = contentType;
			this.extension = extension;
		}

		public String getContentType() {
			return contentType;
		}

		public String getExtension() {
			return extension;
		}
	}

}
