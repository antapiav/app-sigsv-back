package com.devapp.sigsv.view;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.context.MessageSource;

import com.devapp.sigsv.model.bean.Compra;
import com.devapp.sigsv.model.bean.response.GenericResponse;
import com.devapp.sigsv.model.bean.response.GenericResponseHeader;
import com.devapp.sigsv.util.AppConstantes;
import com.devapp.sigsv.util.AppMessages;
import com.devapp.sigsv.util.ExcelUtils;

public class CompraExcelView extends AbstractCustomExcelView {
	
	private static final long serialVersionUID = 1L;

	public CompraExcelView(MessageSource messageSource) {
		super(messageSource);
	}
	
	@SuppressWarnings({ "unchecked" })
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook) {
		//List<Cliente> list = (List<Cliente>) model.get(AppConstantes.PARAM_LIST);
		GenericResponse<List<Compra>> response = (GenericResponse<List<Compra>>) model.get(AppConstantes.PARAM_LIST);

		Sheet sheet = workbook.createSheet(getValueMessage(AppMessages.CLIENTE_TITLE_SHEET));
		
		this.buildHeader(sheet, response.getBody());
		this.buildBody(sheet, response.getBody(), response.getHeader());
	}
	
	private void buildHeader(Sheet sheet, List<Compra> list) {
		int indexRow = 0;
		int indexColumn = 0;
		Row rowHeader = null;
		Cell cell = null;

		rowHeader = sheet.createRow(indexRow);
		CellStyle style1 = ExcelUtils.getCellStyle1(sheet.getWorkbook());
		
		cell = rowHeader.createCell(indexColumn++);
		cell.setCellStyle(style1);
		cell.setCellValue(getValueMessage(AppMessages.COMPRA_HEADER_TIPO_COMPROBANTE));

		cell = rowHeader.createCell(indexColumn++);
		cell.setCellStyle(style1);
		cell.setCellValue(getValueMessage(AppMessages.COMPRA_HEADER_NUM_COMPROBANTE));
		
		cell = rowHeader.createCell(indexColumn++);
		cell.setCellStyle(style1);
		cell.setCellValue(getValueMessage(AppMessages.COMPRA_HEADER_FECHA));
		
		cell = rowHeader.createCell(indexColumn++);
		cell.setCellStyle(style1);
		cell.setCellValue(getValueMessage(AppMessages.COMPRA_HEADER_PROVEEDOR_TIPO_DOCUMENTO));

		cell = rowHeader.createCell(indexColumn++);
		cell.setCellStyle(style1);
		cell.setCellValue(getValueMessage(AppMessages.COMPRA_HEADER_PROVEEDOR_NUM_DOCUMENTO));
		
		cell = rowHeader.createCell(indexColumn++);
		cell.setCellStyle(style1);
		cell.setCellValue(getValueMessage(AppMessages.COMPRA_HEADER_PROVEEDOR_RAZ_SOCIAL));
		
		cell = rowHeader.createCell(indexColumn++);
		cell.setCellStyle(style1);
		cell.setCellValue(getValueMessage(AppMessages.COMPRA_HEADER_IMPORTE_TOTAL));
		
		cell = rowHeader.createCell(indexColumn++);
		cell.setCellStyle(style1);
		cell.setCellValue(getValueMessage(AppMessages.COMPRA_HEADER_IND_ACTIVO));
	
		ExcelUtils.setColumnWidth(sheet, indexColumn);
		ExcelUtils.setAutoFilter(sheet, indexColumn, list.size());
	}
	
	private void buildBody(Sheet sheet, List<Compra> list, GenericResponseHeader genericResponseHeader) {
		int indexRow = 1;
		int indexColumn = 0;
		Cell cell = null;
		
		CellStyle style2 = ExcelUtils.getCellStyle2(sheet.getWorkbook());
		CellStyle style3 = ExcelUtils.getCellStyleDecimal2(sheet.getWorkbook());
		CellStyle style4 = ExcelUtils.getCellStyleDate1(sheet.getWorkbook());
		
		for (Compra compra : list) {
			indexColumn = 0;
			Row rowData = sheet.createRow(indexRow++);
			
			cell = rowData.createCell(indexColumn++);
			ExcelUtils.setValue(cell, compra.getTipoComprobante().getDescripcion());
			cell.setCellStyle(style2);
			
			cell = rowData.createCell(indexColumn++);
			ExcelUtils.setValue(cell, compra.getNumComprobante());
			cell.setCellStyle(style2);
			
			cell = rowData.createCell(indexColumn++);
			ExcelUtils.setValue(cell, compra.getFecha());
			cell.setCellStyle(style4);
			
			cell = rowData.createCell(indexColumn++);
			ExcelUtils.setValue(cell, compra.getProveedor().getTipoDocumento().getDescripcionValor());
			cell.setCellStyle(style2);
			
			cell = rowData.createCell(indexColumn++);
			ExcelUtils.setValue(cell, compra.getProveedor().getNumDocumento());
			cell.setCellStyle(style2);
			
			cell = rowData.createCell(indexColumn++);
			ExcelUtils.setValue(cell, compra.getProveedor().getNombre());
			cell.setCellStyle(style2);
			
			cell = rowData.createCell(indexColumn++);
			ExcelUtils.setValue(cell, compra.getImporteTotal());
			cell.setCellStyle(style3);
			
			cell = rowData.createCell(indexColumn++);
			ExcelUtils.setValue(cell, (compra.getIndActivo() == true) ? AppConstantes.STRING_IND_ACTIVO_TRUE : AppConstantes.STRING_IND_ACTIVO_FALSE );
			cell.setCellStyle(style2);
		}
		Row rowDataTotal = sheet.createRow(indexRow++);
		cell = rowDataTotal.createCell(5);
		ExcelUtils.setValue(cell, AppConstantes.IMPORTE_TOTAL);
		cell.setCellStyle(style2);
		
		cell = rowDataTotal.createCell(6);
		ExcelUtils.setValue(cell, genericResponseHeader.getImporteTotal());
		cell.setCellStyle(style3);
	}
}
