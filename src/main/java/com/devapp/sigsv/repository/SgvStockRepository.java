package com.devapp.sigsv.repository;

import com.devapp.sigsv.model.entity.SgvStock;
import com.devapp.sigsv.util.AppConstantes;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("sgvStockRepository")
public interface SgvStockRepository extends JpaRepository<SgvStock, Long> {
	
    @Query("SELECT s FROM SgvStock s " + 
    		" INNER JOIN SgvProducto p " + 
    		" ON p.idProducto = s.sgvProducto.idProducto " + 
    		" where (TRANSLATE(REPLACE(UPPER(concat(p.nombre)), ' ', ''), 'ÁÉÍÓÚ','AEIOU') LIKE " + 
    		" TRANSLATE(REPLACE(UPPER(concat('%', :value,'%')), ' ', ''), 'ÁÉÍÓÚ','AEIOU') " + 
    		" OR TRANSLATE(REPLACE(UPPER(concat(p.codigo)), ' ', ''), 'ÁÉÍÓÚ','AEIOU') LIKE " + 
    		" TRANSLATE(REPLACE(UPPER(concat('%', :value,'%')), ' ', ''), 'ÁÉÍÓÚ','AEIOU')) " + 
    		" AND p.sgvProductoCategoria.idProductoCategoria = :id ")
    Page<SgvStock> findByProductoCodigo(Pageable pageable, 
    		@Param(AppConstantes.PARAM_PATH_VALUE) String value,
    		@Param(AppConstantes.PARAM_PATH_ID) Long idCategoria);
    
    @Query("SELECT s FROM SgvStock s " + 
    		" INNER JOIN SgvProducto p " + 
    		" ON p.idProducto = s.sgvProducto.idProducto " + 
    		" where TRANSLATE(REPLACE(UPPER(concat(p.nombre)), ' ', ''), 'ÁÉÍÓÚ','AEIOU') LIKE " + 
    		" TRANSLATE(REPLACE(UPPER(concat('%', :value,'%')), ' ', ''), 'ÁÉÍÓÚ','AEIOU') " + 
    		" OR TRANSLATE(REPLACE(UPPER(concat(p.codigo)), ' ', ''), 'ÁÉÍÓÚ','AEIOU') LIKE " + 
    		" TRANSLATE(REPLACE(UPPER(concat('%', :value,'%')), ' ', ''), 'ÁÉÍÓÚ','AEIOU') ")
    Page<SgvStock> findByProductoNombre(Pageable pageable, 
    		@Param(AppConstantes.PARAM_PATH_VALUE) String value);
    
    List<SgvStock> findBySgvProductoIdProducto(Long value);
    
    @Query("SELECT s FROM SgvStock s" + 
    		"	INNER JOIN SgvProducto p ON s.sgvProducto.idProducto = p.idProducto")
    List<SgvStock> getChartBar();
    
    @Query(value="SELECT (s.stock_comprado*p.pre_costo) monto_compra, (s.stock_vendido*p.pre_venta) monto_venta," + 
    		" ((s.stock_vendido*p.pre_venta)-(s.stock_comprado*p.pre_costo)) diferencia"+
    		" FROM sigsv.sgv_stock s" + 
    		" left JOIN sigsv.sgv_producto p ON s.fk_id_producto = p.id_producto" + 
    		" left JOIN sigsv.sgv_detalle_venta dv ON p.id_producto = dv.fk_id_producto" + 
    		" left join sigsv.sgv_venta v on dv.fk_id_venta = v.id_venta" + 
    		" where v.fecha BETWEEN ?1" + 
    		" AND ?2" + 
    		" group by ((s.stock_comprado*p.pre_costo), (s.stock_vendido*p.pre_venta))", nativeQuery = true)
    List<List<?>> getChartLine(Date dateLower, Date dateGreater);

}