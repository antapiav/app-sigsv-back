package com.devapp.sigsv.dao.impl;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.List;
import java.util.Map;

import com.devapp.sigsv.dao.CompraDao;
import com.devapp.sigsv.mapper.CompraRowMapper;
import com.devapp.sigsv.model.bean.Compra;
import com.devapp.sigsv.model.bean.Pagination;
import com.devapp.sigsv.model.bean.response.GenericResponse;
import com.devapp.sigsv.model.bean.response.GenericResponseHeader;
import com.devapp.sigsv.util.AppConstantesSql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

@Repository
public class CompraDaoImpl implements CompraDao {


    private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public GenericResponse<List<Compra>> getLstCompraPaginado(Pagination pagination) {
        
        MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue(AppConstantesSql.PARAM_NUMERO_PAGINA, pagination.getPage());
		parameters.addValue(AppConstantesSql.PARAM_CANTIDAD_PAGINA, pagination.getLimit());
        parameters.addValue(AppConstantesSql.PARAM_FILTROS, pagination.getFilters());
        
        CompraRowMapper compraRowMapper = new CompraRowMapper();
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
				.withSchemaName(AppConstantesSql.SCHEMA_SIGSV)
				.withFunctionName(AppConstantesSql.FUNCTION_FILTRAR_COMPRA_PAGINADO)
				.declareParameters(
						new SqlParameter(AppConstantesSql.PARAM_NUMERO_PAGINA, Types.INTEGER),
						new SqlParameter(AppConstantesSql.PARAM_CANTIDAD_PAGINA, Types.INTEGER),
						new SqlParameter(AppConstantesSql.PARAM_FILTROS, Types.VARCHAR),
						new SqlOutParameter(AppConstantesSql.PARAM_OUT_TOTAL, Types.BIGINT),
						new SqlOutParameter(AppConstantesSql.PARAM_OUT_PAGINA, Types.INTEGER),
						new SqlOutParameter(AppConstantesSql.PARAM_OUT_TOTAL_IMPORTE, Types.NUMERIC),
						new SqlOutParameter(AppConstantesSql.PARAM_OUT_LST_COMPRA, Types.OTHER, compraRowMapper))
				.withoutProcedureColumnMetaDataAccess();

        Map<String, Object> mapOutParameters = jdbcCall.execute(parameters);

        GenericResponseHeader headerResponse = new GenericResponseHeader();
		headerResponse.setTotal((Long) mapOutParameters.get(AppConstantesSql.PARAM_OUT_TOTAL));
		headerResponse.setPagina((Integer) mapOutParameters.get(AppConstantesSql.PARAM_OUT_PAGINA));
		headerResponse.setImporteTotal((BigDecimal) mapOutParameters.get(AppConstantesSql.PARAM_OUT_TOTAL_IMPORTE));
        headerResponse.setImportePagina(compraRowMapper.getTotalImporte());
        
		List<Compra> listCompra = (List<Compra>) mapOutParameters.get(AppConstantesSql.PARAM_OUT_LST_COMPRA);

		GenericResponse<List<Compra>> genericResponse = new GenericResponse<>();
		genericResponse.setHeader(headerResponse);
        genericResponse.setBody(listCompra);
        
        return genericResponse;
    }
    
}