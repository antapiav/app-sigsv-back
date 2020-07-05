package com.devapp.sigsv.view;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.context.MessageSource;

import com.devapp.sigsv.model.bean.ProveedorCategoria;
import com.devapp.sigsv.util.AppConstantes;
import com.devapp.sigsv.util.AppMessages;
import com.devapp.sigsv.util.ExcelUtils;

public class ProveedorCategoriaExcelView extends AbstractCustomExcelView {
	
	private static final long serialVersionUID = 1L;
	
	public ProveedorCategoriaExcelView(MessageSource messageSource) {
		super(messageSource);
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook) {
		List<ProveedorCategoria> list = (List<ProveedorCategoria>) model.get(AppConstantes.PARAM_LIST);
		
		Sheet sheet = workbook.createSheet(getValueMessage(AppMessages.PROVEEDOR_CATEGORIA_TITLE_SHEET));
		
		this.buildHeader(sheet, list);
		this.buildBody(sheet, list);
	}
	
	private void buildHeader(Sheet sheet, List<ProveedorCategoria> list) {
		int indexRow = 0;
		int indexColumn = 0;
		Row rowHeader = null;
		Cell cell = null;

		rowHeader = sheet.createRow(indexRow);
		CellStyle style1 = ExcelUtils.getCellStyle1(sheet.getWorkbook());
		
		cell = rowHeader.createCell(indexColumn++);
		cell.setCellStyle(style1);
		cell.setCellValue(getValueMessage(AppMessages.CAT_PROVEEDOR_HEADER_CODIGO));

		cell = rowHeader.createCell(indexColumn++);
		cell.setCellStyle(style1);
		cell.setCellValue(getValueMessage(AppMessages.CAT_PROVEEDOR_HEADER_CATEGORIA));
		
		cell = rowHeader.createCell(indexColumn++);
		cell.setCellStyle(style1);
		cell.setCellValue(getValueMessage(AppMessages.CAT_PROVEEDOR_HEADER_DESCRIPCION));
		
		cell = rowHeader.createCell(indexColumn++);
		cell.setCellStyle(style1);
		cell.setCellValue(getValueMessage(AppMessages.CAT_PROVEEDOR_HEADER_IND_ACTIVO));
	
		ExcelUtils.setColumnWidth(sheet, indexColumn);
		ExcelUtils.setAutoFilter(sheet, indexColumn, list.size());
	}
	
	private void buildBody(Sheet sheet, List<ProveedorCategoria> list) {
		int indexRow = 1;
		int indexColumn = 0;
		Cell cell = null;
		
		CellStyle style2 = ExcelUtils.getCellStyle2(sheet.getWorkbook());
		
		for (ProveedorCategoria proveedorCategoria : list) {
			indexColumn = 0;
			Row rowData = sheet.createRow(indexRow++);
			
			cell = rowData.createCell(indexColumn++);
			ExcelUtils.setValue(cell, proveedorCategoria.getIdProveedorCategoria());
			cell.setCellStyle(style2);
			
			cell = rowData.createCell(indexColumn++);
			ExcelUtils.setValue(cell, proveedorCategoria.getNombre());
			cell.setCellStyle(style2);
			
			cell = rowData.createCell(indexColumn++);
			ExcelUtils.setValue(cell, proveedorCategoria.getDetalle());
			cell.setCellStyle(style2);
			
			cell = rowData.createCell(indexColumn++);
			ExcelUtils.setValue(cell, (proveedorCategoria.getIndActivo() == true) ? AppConstantes.STRING_IND_ACTIVO_TRUE : AppConstantes.STRING_IND_ACTIVO_FALSE );
			cell.setCellStyle(style2);
		}
	}
}
