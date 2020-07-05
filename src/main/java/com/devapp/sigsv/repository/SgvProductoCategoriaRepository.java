package com.devapp.sigsv.repository;

import java.util.List;

import com.devapp.sigsv.model.entity.SgvProductoCategoria;
import com.devapp.sigsv.util.AppConstantes;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("sgvProductoCategoriaRepository")
public interface SgvProductoCategoriaRepository extends JpaRepository<SgvProductoCategoria, Long> {
    @Query("SELECT p FROM SgvProductoCategoria p "+
	    " WHERE TRANSLATE(REPLACE(UPPER(concat(p.nombre)), ' ', ''), 'ÁÉÍÓÚ','AEIOU')"+
	    " LIKE TRANSLATE(REPLACE(UPPER(concat('%', :dato,'%')), ' ', ''), 'ÁÉÍÓÚ','AEIOU')")
    Page<SgvProductoCategoria> findByName(Pageable pageable,  @Param(AppConstantes.PARAM_PATH_dato) String dato);
    //List<SgvProductoCategoria> findAllByOrderByIdProductoCategoriaAsc();
    @Query("SELECT p FROM SgvProductoCategoria p " +
    		" WHERE TRANSLATE(REPLACE(UPPER(concat(p.nombre)), ' ', ''), 'ÁÉÍÓÚ','AEIOU') "+
    		" like TRANSLATE(REPLACE(UPPER(concat('%', :value,'%')), ' ', ''), 'ÁÉÍÓÚ','AEIOU') " + 
    		" AND p.indActivo = true")
    List<SgvProductoCategoria> findByNombreOrderByIdProductoCategoriaAsc(Pageable pageable, String value);

    @Query(value = "SELECT pc.nombre nombre_producto_categoria, pc.detalle detalle_producto_categoria, sum(s.stock_comprado) sum_compra, sum(s.stock_vendido) sum_venta FROM sigsv.sgv_stock s" + 
    		" inner join sigsv.sgv_producto p ON s.fk_id_producto = p.id_producto" + 
    		" LEFT JOIN sigsv.sgv_producto_categoria pc ON pc.id_producto_categoria = p.fk_id_producto_categoria" + 
    		" group by (pc.nombre, pc.detalle)", nativeQuery = true)
    List<List<?>> getChartCircle();
}