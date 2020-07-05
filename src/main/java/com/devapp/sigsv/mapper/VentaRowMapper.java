package com.devapp.sigsv.mapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.devapp.sigsv.dao.impl.VentaDaoImpl;
import com.devapp.sigsv.model.bean.Cliente;
import com.devapp.sigsv.model.bean.Multitabla;
import com.devapp.sigsv.model.bean.Venta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

public class VentaRowMapper implements RowMapper<Venta> {
	public static Logger log = LoggerFactory.getLogger(VentaDaoImpl.class);
	
	private BigDecimal totalImporte = BigDecimal.ZERO;

    @Override
    public Venta mapRow(ResultSet rs, int rowNum) throws SQLException {
        Venta venta = new Venta();
        Cliente cliente = new Cliente();
        Multitabla tipoComprobante = new Multitabla(); 

        venta.setIdVenta((Long) rs.getLong("id_venta"));
        venta.setNumComprobante((String) rs.getString("num_comprobante"));
        venta.setFecha((Date) rs.getDate("fecha"));
        venta.setValorVenta((Double) rs.getDouble("valor_venta"));
        venta.setIgv((Double) rs.getDouble("igv"));
        venta.setImporteTotal((Double) rs.getDouble("importe_total"));
        venta.setIndActivo((Boolean) rs.getBoolean("ind_activo"));
        
        tipoComprobante.setDescripcion((String) rs.getString("tipo_comprobante"));
        
        cliente.setNombre((String) rs.getString("nombre"));
        cliente.setApPaterno((String) rs.getString("ap_paterno"));
        cliente.setApMaterno((String) rs.getString("ap_materno"));
        cliente.setDni((String) rs.getString("dni"));

        venta.setTipoComprobante(tipoComprobante);
        venta.setCliente(cliente);
        
        totalImporte = totalImporte.add(BigDecimal.valueOf(rs.getDouble("importe_total")));

        return venta;
    }
    
    public BigDecimal getTotalImporte() {
		return this.totalImporte;
	}
    
}