package com.devapp.sigsv.view;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.view.AbstractView;

import com.devapp.sigsv.util.AppConstantes;
import com.devapp.sigsv.util.AppMessages;
import com.devapp.sigsv.util.ExcelUtils;

/**
 *
 * @author LENINTAPIA
 */
public abstract class AbstractCustomExcelView extends AbstractView implements Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractCustomExcelView.class);

	private static final long serialVersionUID = 1L;

	protected transient MessageSource messageSource;

	public AbstractCustomExcelView() {
		super();
	}

	public AbstractCustomExcelView(MessageSource messageSource) {
		super();
		this.messageSource = messageSource;
	}

	@Override
	protected final void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try (ByteArrayOutputStream baos = createTemporaryOutputStream();) {
			AppConstantes.FileType excelType = (AppConstantes.FileType) model.get(AppConstantes.EXCEL_TYPE);
			String excelTemplate = (String) model.get(AppConstantes.EXCEL_URL_TEMPLATE);
			String excelFilename = (String) model.get(AppConstantes.EXCEL_FILENAME);
			Boolean excelDownload = (Boolean) model.get(AppConstantes.EXCEL_DOWNLOAD);

			Workbook workbook = ExcelUtils.getWorbookExcelDocument(excelTemplate, excelType);

			buildExcelDocument(model, workbook);

			workbook.write(baos);
			workbook.close();

			Map<String, Object> mapParams = new HashMap<>();
			mapParams.put(AppConstantes.PARAM_RESPONSE_CONTENT_TYPE, excelType.getContentType());
			mapParams.put(AppConstantes.PARAM_RESPONSE_FILE_FILENAME, excelFilename);
			mapParams.put(AppConstantes.PARAM_RESPONSE_FILE_EXTENSION, excelType.getExtension());
			mapParams.put(AppConstantes.PARAM_RESPONSE_DOWNLOAD, excelDownload);

			String contentType = (String) mapParams.get(AppConstantes.PARAM_RESPONSE_CONTENT_TYPE);
			String fileName = (String) mapParams.get(AppConstantes.PARAM_RESPONSE_FILE_FILENAME);
			String extension = (String) mapParams.get(AppConstantes.PARAM_RESPONSE_FILE_EXTENSION);
			Boolean download = (Boolean) mapParams.get(AppConstantes.PARAM_RESPONSE_DOWNLOAD);

			String format = download ? AppConstantes.FORMAT_CONTENT_ATTACHMENT : AppConstantes.FORMAT_CONTENT_INLINE;

			response.setContentType(contentType);
			response.setHeader(AppConstantes.CONTENT_DISPOSITION, String.format(format, fileName, extension));
			//download attachment angular 8+
			response.setHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, AppConstantes.CONTENT_DISPOSITION);
			response.setContentLength(baos.size());

			try (ServletOutputStream out = response.getOutputStream();) {
				baos.writeTo(out);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);

			Map<String, Object> map = new HashMap<>();
			map.put(AppConstantes.PARAM_RESPONSE_CODE, HttpStatus.INTERNAL_SERVER_ERROR.value());
			map.put(AppConstantes.PARAM_RESPONSE_MESSAGE, getValueMessage(AppMessages.GENERIC_ERROR));

			response.setStatus(HttpStatus.OK.value());
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			response.setCharacterEncoding(AppConstantes.CHARACTER_ENCODING_UTF_8);
			response.getWriter().write(AppConstantes.OBJECT_MAPPER.writeValueAsString(map));
		}
	}
	
	protected abstract void buildExcelDocument(Map<String, Object> model, Workbook workbook);

	protected String getValueMessage(String key) {
		return messageSource.getMessage(key, new Object[] {}, AppConstantes.NOT_FOUND_KEY, Locale.getDefault());
	}

	protected String getValueMessage(String key, Object[] parameters) {
		return messageSource.getMessage(key, parameters, AppConstantes.NOT_FOUND_KEY, Locale.getDefault());
	}

}
