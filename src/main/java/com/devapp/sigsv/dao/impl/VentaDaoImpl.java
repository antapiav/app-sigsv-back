package com.devapp.sigsv.dao.impl;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.List;
import java.util.Map;

import com.devapp.sigsv.dao.VentaDao;
import com.devapp.sigsv.mapper.VentaRowMapper;
import com.devapp.sigsv.model.bean.Pagination;
import com.devapp.sigsv.model.bean.Venta;
import com.devapp.sigsv.model.bean.response.GenericResponse;
import com.devapp.sigsv.model.bean.response.GenericResponseHeader;
import com.devapp.sigsv.util.AppConstantesSql;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

@Repository
public class VentaDaoImpl implements VentaDao {
	
	public static Logger log = LoggerFactory.getLogger(VentaDaoImpl.class);

    private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
    }

    @SuppressWarnings("unchecked")
    @Override
    public GenericResponse<?> getLsVentaPaginado(Pagination pagination) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue(AppConstantesSql.PARAM_NUMERO_PAGINA, pagination.getPage());
		parameters.addValue(AppConstantesSql.PARAM_CANTIDAD_PAGINA, pagination.getLimit());
        parameters.addValue(AppConstantesSql.PARAM_FILTROS, pagination.getFilters());

        VentaRowMapper ventaRowMapper = new VentaRowMapper();
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
				.withSchemaName(AppConstantesSql.SCHEMA_SIGSV)
				.withFunctionName(AppConstantesSql.FUNCTION_FILTRAR_VENTA_PAGINADO)
				.declareParameters(
						new SqlParameter(AppConstantesSql.PARAM_NUMERO_PAGINA, Types.INTEGER),
						new SqlParameter(AppConstantesSql.PARAM_CANTIDAD_PAGINA, Types.INTEGER),
						new SqlParameter(AppConstantesSql.PARAM_FILTROS, Types.VARCHAR),
						new SqlOutParameter(AppConstantesSql.PARAM_OUT_TOTAL, Types.BIGINT),
						new SqlOutParameter(AppConstantesSql.PARAM_OUT_PAGINA, Types.INTEGER),
						new SqlOutParameter(AppConstantesSql.PARAM_OUT_TOTAL_IMPORTE, Types.NUMERIC),
						new SqlOutParameter(AppConstantesSql.PARAM_OUT_LST_VENTA, Types.OTHER, ventaRowMapper))
				.withoutProcedureColumnMetaDataAccess();
        
        Map<String, Object> mapOutParameters = jdbcCall.execute(parameters);
        
        GenericResponseHeader headerResponse = new GenericResponseHeader();
		headerResponse.setTotal((Long) mapOutParameters.get(AppConstantesSql.PARAM_OUT_TOTAL));
        headerResponse.setPagina((Integer) mapOutParameters.get(AppConstantesSql.PARAM_OUT_PAGINA));
        headerResponse.setImporteTotal((BigDecimal) mapOutParameters.get(AppConstantesSql.PARAM_OUT_TOTAL_IMPORTE));
        headerResponse.setImportePagina(ventaRowMapper.getTotalImporte());
        
        List<?> lstVenta = (List<?>) mapOutParameters.get(AppConstantesSql.PARAM_OUT_LST_VENTA);

		GenericResponse<List<?>> genericResponse = new GenericResponse<>();
		genericResponse.setHeader(headerResponse);
        genericResponse.setBody(lstVenta);

        return genericResponse;
    }
    
}