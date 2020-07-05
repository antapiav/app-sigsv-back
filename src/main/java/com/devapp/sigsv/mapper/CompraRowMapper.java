package com.devapp.sigsv.mapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.devapp.sigsv.model.bean.Compra;
import com.devapp.sigsv.model.bean.Multitabla;
import com.devapp.sigsv.model.bean.Proveedor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

public class CompraRowMapper implements RowMapper<Compra> {
	public static Logger log = LoggerFactory.getLogger(CompraRowMapper.class);
	
	private BigDecimal totalImporte = BigDecimal.ZERO;

    @Override
    public Compra mapRow(ResultSet rs, int rowNum) throws SQLException {
        Compra compra = new Compra();
        Proveedor proveedor = new Proveedor();
        Multitabla tipoComprobante = new Multitabla();
        Multitabla tipoDocumento = new Multitabla();

        compra.setIdCompra((Long) rs.getLong("id_compra"));
        compra.setNumComprobante((String) rs.getString("num_comprobante"));
        compra.setFecha((Date) rs.getDate("fecha"));
        compra.setValorCompra((Double) rs.getDouble("valor_compra"));
        //compra.setIgv((Double) rs.getDouble("igv"));
        compra.setImporteTotal((Double) rs.getDouble("importe_total"));
        compra.setIndActivo((Boolean) rs.getBoolean("ind_activo"));
        
        tipoComprobante.setDescripcion((String) rs.getString("tipo_comprobante"));
        tipoDocumento.setDescripcionValor((String) rs.getString("tipo_documento"));
        
        proveedor.setIdProveedor((Long) rs.getLong("id_proveedor"));
        proveedor.setNombre((String) rs.getString("nombre"));
        proveedor.setNumDocumento((String) rs.getString("num_documento"));
        proveedor.setTipoDocumento(tipoDocumento);

        compra.setProveedor(proveedor);
        compra.setTipoComprobante(tipoComprobante);
        
        totalImporte = totalImporte.add(BigDecimal.valueOf(rs.getDouble("importe_total")));
        return compra;
    }
    
    public BigDecimal getTotalImporte() {
		return this.totalImporte;
	}
}