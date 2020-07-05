package com.devapp.sigsv.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	
	protected ExcelUtils() {
		super();
	}
	
	public static final Workbook getWorbookExcelDocument(String urlTemplate, AppConstantes.FileType excelType)
			throws IOException {
		File file = null;
		Workbook workbook = null;

		if (!AppUtil.isEmpty(urlTemplate)) {
			file = new File(urlTemplate);
		}

		switch (excelType) {
		case XLS:
			workbook = file != null && file.exists() && !file.isDirectory()
					? new HSSFWorkbook(new FileInputStream(file))
					: new HSSFWorkbook();
			break;
		case XLSX:
			workbook = file != null && file.exists() && !file.isDirectory()
					? new XSSFWorkbook(new FileInputStream(file))
					: new XSSFWorkbook();
			break;
		case XLSX_STREAMING:
			workbook = file != null && file.exists() && !file.isDirectory()
					? new SXSSFWorkbook(new XSSFWorkbook(new FileInputStream(file)), 1000, true)
					: new SXSSFWorkbook(new XSSFWorkbook(), 1000, true);
			break;
		default:
			workbook = file != null && file.exists() && !file.isDirectory()
					? new XSSFWorkbook(new FileInputStream(file))
					: new XSSFWorkbook();
			break;
		}

		return workbook;
	}
	
	
	//TIPO CELDA HEAD
	public static final CellStyle getCellStyle1(Workbook workbook) {
		CellStyle style = getCellStyle(workbook);
		style.setFillForegroundColor(IndexedColors.ROYAL_BLUE.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style.setFont(getFont1(workbook));
		return style;
	}
	//TIPO CELDA BODY
	public static final CellStyle getCellStyle2(Workbook workbook) {
		CellStyle style = getCellStyle(workbook);
		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBorderLeft(CellStyle.BORDER_THIN);
		style.setBorderRight(CellStyle.BORDER_THIN);
		style.setBorderTop(CellStyle.BORDER_THIN);
		style.setFont(getFont2(workbook));
		return style;
	}
	//numeros decimales
	public static final CellStyle getCellStyleDecimal2(Workbook workbook) {
		CellStyle style = getCellStyle2(workbook);
		style.setDataFormat(workbook.createDataFormat().getFormat(AppConstantes.EXCEL_FORMAT_DECIMAL));
		return style;
	}
	//date
	public static final CellStyle getCellStyleDate1(Workbook workbook) {
		CellStyle style = getCellStyle2(workbook);
		style.setDataFormat(workbook.createDataFormat().getFormat(AppConstantes.EXCEL_FORMAT_DATE));
		return style;
	}
	
	public static final CellStyle getCellStyle(Workbook workbook) {
		XSSFCellStyle style = (XSSFCellStyle) workbook.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		style.setBorderBottom(CellStyle.BORDER_MEDIUM);
		style.setBorderLeft(CellStyle.BORDER_MEDIUM);
		style.setBorderRight(CellStyle.BORDER_MEDIUM);
		style.setBorderTop(CellStyle.BORDER_MEDIUM);
		style.setShrinkToFit(false);
		style.setWrapText(true);
		return style;
	}
	
	//TIPO LETRA HEAD
	public static final Font getFont1(Workbook workbook) {
		Font font = workbook.createFont();
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		font.setFontHeightInPoints((short) 11);
		font.setColor(IndexedColors.WHITE.getIndex());
		return font;
	}
	//TIPO LETRA BODY
	public static final Font getFont2(Workbook workbook) {
		Font font = workbook.createFont();
		font.setFontHeightInPoints((short) 11);
		return font;
	}
	
	//ANCHO COLUMNA
	public static final void setColumnWidth(Sheet sheet, int indexColumn) {
		for (int i = 0; i <= indexColumn; i++) {
			int width = ((int) (25 * 1.14388)) * 256;
			sheet.setColumnWidth(i, width);
		}
	}
	
	//FILTRO
	public static final void setAutoFilter(Sheet sheet, int indexColumn, int indexRow) {
		sheet.setAutoFilter(new CellRangeAddress(0, indexRow + 1, 0, indexColumn));
	}
	
	//ENVIAR VALOR A CELDA STRING
	public static final void setValue(Cell cell, String value) {
		if (value instanceof String) {
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue((String) value);
		} else {
			cell.setCellType(Cell.CELL_TYPE_BLANK);
		}
	}
	//ENVIAR VALOR A CELDA  NUMERICO
	public static final void setValue(Cell cell, Number value) {
		if (value instanceof Number) {
			cell.setCellType(Cell.CELL_TYPE_NUMERIC);
			cell.setCellValue(Double.valueOf(String.valueOf(value)));
		} else {
			cell.setCellType(Cell.CELL_TYPE_BLANK);
		}
	}
	//ENVIAR VALOR A CELDA DATE
	public static final void setValue(Cell cell, Date value) {
		if (value instanceof Date) {
			cell.setCellType(Cell.CELL_TYPE_NUMERIC);
			cell.setCellValue((Date) value);
		} else {
			cell.setCellType(Cell.CELL_TYPE_BLANK);
		}
	}
}
