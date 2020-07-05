package com.devapp.sigsv.dao;

import java.util.List;

import com.devapp.sigsv.model.bean.Pagination;
import com.devapp.sigsv.model.bean.Venta;
import com.devapp.sigsv.model.bean.response.GenericResponse;

public interface VentaDao {
    GenericResponse<?> getLsVentaPaginado(Pagination pagination);
}