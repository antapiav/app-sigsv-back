package com.devapp.sigsv.dao;

import java.util.List;

import com.devapp.sigsv.model.bean.Compra;
import com.devapp.sigsv.model.bean.Pagination;
import com.devapp.sigsv.model.bean.response.GenericResponse;

public interface CompraDao {
    GenericResponse<List<Compra>> getLstCompraPaginado(Pagination pagination);
}