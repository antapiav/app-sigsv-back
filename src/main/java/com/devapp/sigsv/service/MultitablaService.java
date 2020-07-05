package com.devapp.sigsv.service;

import com.devapp.sigsv.exception.AppInternalException;
import com.devapp.sigsv.model.bean.response.GenericResponse;

public interface MultitablaService {
	GenericResponse<?> getLstTipoDocumento() throws AppInternalException;
	GenericResponse<?> getLstTipoComprobante() throws AppInternalException;
	GenericResponse<?> getLstUnidadMedida() throws AppInternalException;
	GenericResponse<?> getLstTipoUsuario() throws AppInternalException;
	GenericResponse<?> getLstMontoBolsa() throws AppInternalException;
}
