package com.devapp.sigsv.view;

import java.util.List;
import java.util.Map;

import com.devapp.sigsv.model.bean.Producto;
import com.devapp.sigsv.model.bean.Stock;
import com.devapp.sigsv.util.AppConstantes;
import com.devapp.sigsv.util.AppMessages;
import com.devapp.sigsv.util.ExcelUtils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.context.MessageSource;

public class ProductoExcelView extends AbstractCustomExcelView {

    private static final long serialVersionUID = 1L;

    public ProductoExcelView(MessageSource messageSource) {
		super(messageSource);
	}

    @SuppressWarnings({ "unchecked" })
    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook) {
        List<Stock> list = (List<Stock>) model.get(AppConstantes.PARAM_LIST);
		
		Sheet sheet = workbook.createSheet(getValueMessage(AppMessages.PRODUCTO_TITLE_SHEET));
		
		this.buildHeader(sheet, list);
		this.buildBody(sheet, list);
    }

    private void buildHeader(Sheet sheet, List<Stock> list) {
		int indexRow = 0;
		int indexColumn = 0;
		Row rowHeader = null;
		Cell cell = null;

		rowHeader = sheet.createRow(indexRow);
		CellStyle style1 = ExcelUtils.getCellStyle1(sheet.getWorkbook());
		
		cell = rowHeader.createCell(indexColumn++);
		cell.setCellStyle(style1);
		cell.setCellValue(getValueMessage(AppMessages.PRODUCTO_HEADER_CODIGO));
		
		cell = rowHeader.createCell(indexColumn++);
		cell.setCellStyle(style1);
		cell.setCellValue(getValueMessage(AppMessages.PRODUCTO_HEADER_CATEGORIA));

		cell = rowHeader.createCell(indexColumn++);
		cell.setCellStyle(style1);
        cell.setCellValue(getValueMessage(AppMessages.PRODUCTO_HEADER_NOMBRE));
        
        cell = rowHeader.createCell(indexColumn++);
		cell.setCellStyle(style1);
        cell.setCellValue(getValueMessage(AppMessages.PRODUCTO_HEADER_DETALLE));
        
        cell = rowHeader.createCell(indexColumn++);
		cell.setCellStyle(style1);
		cell.setCellValue(getValueMessage(AppMessages.PRODUCTO_HEADER_UNIDAD));
		
		cell = rowHeader.createCell(indexColumn++);
		cell.setCellStyle(style1);
		cell.setCellValue(getValueMessage(AppMessages.PRODUCTO_HEADER_IND_ACTIVO));
		
		cell = rowHeader.createCell(indexColumn++);
		cell.setCellStyle(style1);
		cell.setCellValue(getValueMessage(AppMessages.PRODUCTO_HEADER_COMPRAS));
		
		cell = rowHeader.createCell(indexColumn++);
		cell.setCellStyle(style1);
		cell.setCellValue(getValueMessage(AppMessages.PRODUCTO_HEADER_VENTAS));
		
		cell = rowHeader.createCell(indexColumn++);
		cell.setCellStyle(style1);
		cell.setCellValue(getValueMessage(AppMessages.PRODUCTO_HEADER_STOCK));
	
		ExcelUtils.setColumnWidth(sheet, indexColumn);
		ExcelUtils.setAutoFilter(sheet, indexColumn, list.size());
	}
	
	private void buildBody(Sheet sheet, List<Stock> list) {
		int indexRow = 1;
		int indexColumn = 0;
		Cell cell = null;
		
		CellStyle style2 = ExcelUtils.getCellStyle2(sheet.getWorkbook());
		
		for (Stock stock : list) {
			indexColumn = 0;
			Row rowData = sheet.createRow(indexRow++);
			
			cell = rowData.createCell(indexColumn++);
			ExcelUtils.setValue(cell, stock.getProducto().getCodigo());
            cell.setCellStyle(style2);
            
            cell = rowData.createCell(indexColumn++);
			ExcelUtils.setValue(cell, stock.getProducto().getProductoCategoria().getNombre());
			cell.setCellStyle(style2);
			
			cell = rowData.createCell(indexColumn++);
			ExcelUtils.setValue(cell, stock.getProducto().getNombre());
			cell.setCellStyle(style2);
			
			cell = rowData.createCell(indexColumn++);
			ExcelUtils.setValue(cell, stock.getProducto().getDetalle());
            cell.setCellStyle(style2);

            cell = rowData.createCell(indexColumn++);
			ExcelUtils.setValue(cell, stock.getProducto().getUnidad().getDescripcion());
            cell.setCellStyle(style2);
			
			cell = rowData.createCell(indexColumn++);
			ExcelUtils.setValue(cell, (stock.getProducto().getIndActivo() == true) ? AppConstantes.STRING_IND_ACTIVO_TRUE : AppConstantes.STRING_IND_ACTIVO_FALSE );
			cell.setCellStyle(style2);
            
            cell = rowData.createCell(indexColumn++);
			ExcelUtils.setValue(cell, stock.getStockComprado());
            cell.setCellStyle(style2);
            
            cell = rowData.createCell(indexColumn++);
			ExcelUtils.setValue(cell, stock.getStockVendido());
            cell.setCellStyle(style2);
            
            cell = rowData.createCell(indexColumn++);
			ExcelUtils.setValue(cell, (stock.getStockComprado() - stock.getStockVendido()));
            cell.setCellStyle(style2);
		}
	}
}
