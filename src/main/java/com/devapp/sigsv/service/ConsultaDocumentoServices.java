package com.devapp.sigsv.service;

import com.devapp.sigsv.exception.AppInternalException;
import com.devapp.sigsv.model.bean.Reniec;
import com.devapp.sigsv.model.bean.Sunat;
import com.devapp.sigsv.model.bean.response.GenericResponse;

public interface ConsultaDocumentoServices {
    GenericResponse<Reniec> consultaDocumentoReniec(String numDocumento) throws AppInternalException;
    GenericResponse<Sunat> consultaDocumentoSunat(String numDocumento) throws AppInternalException;
}
