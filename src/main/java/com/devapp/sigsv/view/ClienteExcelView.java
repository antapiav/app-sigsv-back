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
import com.devapp.sigsv.util.AppConstantes;
import com.devapp.sigsv.util.AppMessages;
import com.devapp.sigsv.util.ExcelUtils;

public class ClienteExcelView extends AbstractCustomExcelView {
	
	private static final long serialVersionUID = 1L;

	public ClienteExcelView(MessageSource messageSource) {
		super(messageSource);
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook) {
		List<Cliente> list = (List<Cliente>) model.get(AppConstantes.PARAM_LIST);
		
		Sheet sheet = workbook.createSheet(getValueMessage(AppMessages.CLIENTE_TITLE_SHEET));
		
		this.buildHeader(sheet, list);
		this.buildBody(sheet, list);
	}
	
	private void buildHeader(Sheet sheet, List<Cliente> list) {
		int indexRow = 0;
		int indexColumn = 0;
		Row rowHeader = null;
		Cell cell = null;

		rowHeader = sheet.createRow(indexRow);
		CellStyle style1 = ExcelUtils.getCellStyle1(sheet.getWorkbook());
		
		cell = rowHeader.createCell(indexColumn++);
		cell.setCellStyle(style1);
		cell.setCellValue(getValueMessage(AppMessages.CLIENTE_HEADER_DNI));

		cell = rowHeader.createCell(indexColumn++);
		cell.setCellStyle(style1);
		cell.setCellValue(getValueMessage(AppMessages.CLIENTE_HEADER_NOMBRE));
		
		cell = rowHeader.createCell(indexColumn++);
		cell.setCellStyle(style1);
		cell.setCellValue(getValueMessage(AppMessages.CLIENTE_HEADER_AP_PATERNO));

		cell = rowHeader.createCell(indexColumn++);
		cell.setCellStyle(style1);
		cell.setCellValue(getValueMessage(AppMessages.CLIENTE_HEADER_AP_MATERNO));
		
		cell = rowHeader.createCell(indexColumn++);
		cell.setCellStyle(style1);
		cell.setCellValue(getValueMessage(AppMessages.CLIENTE_HEADER_IND_ACTIVO));
	
		ExcelUtils.setColumnWidth(sheet, indexColumn);
		ExcelUtils.setAutoFilter(sheet, indexColumn, list.size());
	}
	
	private void buildBody(Sheet sheet, List<Cliente> list) {
		int indexRow = 1;
		int indexColumn = 0;
		Cell cell = null;
		
		CellStyle style2 = ExcelUtils.getCellStyle2(sheet.getWorkbook());
		
		for (Cliente cliente : list) {
			indexColumn = 0;
			Row rowData = sheet.createRow(indexRow++);
			
			cell = rowData.createCell(indexColumn++);
			ExcelUtils.setValue(cell, cliente.getDni());
			cell.setCellStyle(style2);
			
			cell = rowData.createCell(indexColumn++);
			ExcelUtils.setValue(cell, cliente.getNombre());
			cell.setCellStyle(style2);
			
			cell = rowData.createCell(indexColumn++);
			ExcelUtils.setValue(cell, cliente.getApPaterno());
			cell.setCellStyle(style2);
			
			cell = rowData.createCell(indexColumn++);
			ExcelUtils.setValue(cell, cliente.getApMaterno());
			cell.setCellStyle(style2);
			
			cell = rowData.createCell(indexColumn++);
			ExcelUtils.setValue(cell, (cliente.getIndActivo() == true) ? AppConstantes.STRING_IND_ACTIVO_TRUE : AppConstantes.STRING_IND_ACTIVO_FALSE );
			cell.setCellStyle(style2);
		}
	}
}
