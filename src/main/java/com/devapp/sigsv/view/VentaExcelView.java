package com.devapp.sigsv.view;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.context.MessageSource;

import com.devapp.sigsv.model.bean.Cliente;
import com.devapp.sigsv.model.bean.Pagination;
import com.devapp.sigsv.model.bean.Venta;
import com.devapp.sigsv.model.bean.response.GenericResponse;
import com.devapp.sigsv.model.bean.response.GenericResponseHeader;
import com.devapp.sigsv.util.AppConstantes;
import com.devapp.sigsv.util.AppMessages;
import com.devapp.sigsv.util.ExcelUtils;

public class VentaExcelView extends AbstractCustomExcelView {
	
	private static final long serialVersionUID = 1L;

	public VentaExcelView(MessageSource messageSource) {
		super(messageSource);
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook) {
		//List<Venta> list = (List<Venta>) model.get(AppConstantes.PARAM_LIST);
		GenericResponse<List<Venta>> response = (GenericResponse<List<Venta>>) model.get(AppConstantes.PARAM_LIST);
		
		Sheet sheet = workbook.createSheet(getValueMessage(AppMessages.VENTA_TITLE_SHEET));
		
		this.buildHeader(sheet, response.getBody());
		this.buildBody(sheet, response.getBody(), response.getHeader());
	}
	
	private void buildHeader(Sheet sheet, List<Venta> list) {
		int indexRow = 0;
		int indexColumn = 0;
		Row rowHeader = null;
		Cell cell = null;

		rowHeader = sheet.createRow(indexRow);
		CellStyle style1 = ExcelUtils.getCellStyle1(sheet.getWorkbook());
		
		cell = rowHeader.createCell(indexColumn++);
		cell.setCellStyle(style1);
		cell.setCellValue(getValueMessage(AppMessages.VENTA_HEADER_TIPO_COMPROBANTE));

		cell = rowHeader.createCell(indexColumn++);
		cell.setCellStyle(style1);
		cell.setCellValue(getValueMessage(AppMessages.VENTA_HEADER_NUM_COMPROBANTE));
		
		cell = rowHeader.createCell(indexColumn++);
		cell.setCellStyle(style1);
		cell.setCellValue(getValueMessage(AppMessages.VENTA_HEADER_FECHA));

		cell = rowHeader.createCell(indexColumn++);
		cell.setCellStyle(style1);
		cell.setCellValue(getValueMessage(AppMessages.VENTA_HEADER_DNI));
		
		cell = rowHeader.createCell(indexColumn++);
		cell.setCellStyle(style1);
		cell.setCellValue(getValueMessage(AppMessages.VENTA_HEADER_CLIENTE_NOMBRE));
		
		cell = rowHeader.createCell(indexColumn++);
		cell.setCellStyle(style1);
		cell.setCellValue(getValueMessage(AppMessages.VENTA_HEADER_CLIENTE_AP_PATERNO));
		
		cell = rowHeader.createCell(indexColumn++);
		cell.setCellStyle(style1);
		cell.setCellValue(getValueMessage(AppMessages.VENTA_HEADER_CLIENTE_AP_MATERNO));
		
		cell = rowHeader.createCell(indexColumn++);
		cell.setCellStyle(style1);
		cell.setCellValue(getValueMessage(AppMessages.VENTA_HEADER_IMPORTE_TOTAL));
		
		cell = rowHeader.createCell(indexColumn++);
		cell.setCellStyle(style1);
		cell.setCellValue(getValueMessage(AppMessages.VENTA_HEADER_IND_ACTIVO));
	
		ExcelUtils.setColumnWidth(sheet, indexColumn);
		ExcelUtils.setAutoFilter(sheet, indexColumn, list.size());
	}
	
	private void buildBody(Sheet sheet, List<Venta> list, GenericResponseHeader genericResponseHeader) {
		int indexRow = 1;
		int indexColumn = 0;
		Cell cell = null;
		
		CellStyle style2 = ExcelUtils.getCellStyle2(sheet.getWorkbook());
		CellStyle style3 = ExcelUtils.getCellStyleDecimal2(sheet.getWorkbook());
		CellStyle style4 = ExcelUtils.getCellStyleDate1(sheet.getWorkbook());
		
		for (Venta venta : list) {
			indexColumn = 0;
			Row rowData = sheet.createRow(indexRow++);
			
			cell = rowData.createCell(indexColumn++);
			ExcelUtils.setValue(cell, venta.getTipoComprobante().getDescripcion());
			cell.setCellStyle(style2);
			
			cell = rowData.createCell(indexColumn++);
			ExcelUtils.setValue(cell, venta.getNumComprobante());
			cell.setCellStyle(style2);
			
			cell = rowData.createCell(indexColumn++);
			ExcelUtils.setValue(cell, venta.getFecha());
			cell.setCellStyle(style4);
			
			cell = rowData.createCell(indexColumn++);
			ExcelUtils.setValue(cell, venta.getCliente().getDni());
			cell.setCellStyle(style2);
			
			cell = rowData.createCell(indexColumn++);
			ExcelUtils.setValue(cell, venta.getCliente().getNombre());
			cell.setCellStyle(style2);
			
			cell = rowData.createCell(indexColumn++);
			ExcelUtils.setValue(cell, venta.getCliente().getApPaterno());
			cell.setCellStyle(style2);
			
			cell = rowData.createCell(indexColumn++);
			ExcelUtils.setValue(cell, venta.getCliente().getApPaterno());
			cell.setCellStyle(style2);
			
			cell = rowData.createCell(indexColumn++);
			ExcelUtils.setValue(cell, venta.getImporteTotal());
			cell.setCellStyle(style3);
			
			cell = rowData.createCell(indexColumn++);
			ExcelUtils.setValue(cell, (venta.getIndActivo() == true) ? AppConstantes.STRING_IND_ACTIVO_TRUE : AppConstantes.STRING_IND_ACTIVO_FALSE );
			cell.setCellStyle(style2);
		}
		Row rowDataTotal = sheet.createRow(indexRow++);
		cell = rowDataTotal.createCell(6);
		ExcelUtils.setValue(cell, AppConstantes.IMPORTE_TOTAL);
		cell.setCellStyle(style2);
		
		cell = rowDataTotal.createCell(7);
		ExcelUtils.setValue(cell, genericResponseHeader.getImporteTotal());
		cell.setCellStyle(style3);
	}

}
