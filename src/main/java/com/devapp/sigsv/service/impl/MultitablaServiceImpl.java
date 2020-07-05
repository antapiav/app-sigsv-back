package com.devapp.sigsv.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.devapp.sigsv.exception.AppInternalException;
import com.devapp.sigsv.model.bean.Multitabla;
import com.devapp.sigsv.model.bean.response.GenericResponse;
import com.devapp.sigsv.model.entity.SgvMultitabla;
import com.devapp.sigsv.repository.SgvMultitablaRepository;
import com.devapp.sigsv.service.MultitablaService;
import com.devapp.sigsv.util.AppConstantes;
import com.devapp.sigsv.util.AppUtilConverter;

@Service("multitablaServiceImpl")
@Transactional
public class MultitablaServiceImpl implements MultitablaService {
	
	@Autowired
    @Qualifier("sgvMultitablaRepository")
	public SgvMultitablaRepository sgvMultitablaRepository;

	@Override
	public GenericResponse<?> getLstTipoDocumento() throws AppInternalException {
		List<Multitabla> lstMultitabla = new ArrayList<>();
		for(SgvMultitabla sgvMultitabla : sgvMultitablaRepository
				.findByIdTablaAndIndActivoOrderByIdCodigoAsc(AppConstantes.TBL_TIPO_DOCUMENTO)) {
			lstMultitabla.add(AppUtilConverter.convertSgvMultitablaToMultitabla(sgvMultitabla));
		}
		GenericResponse<List<Multitabla>> ress = new GenericResponse<>();
		ress.setBody(lstMultitabla);
		return ress;
	}

	@Override
	public GenericResponse<?> getLstTipoComprobante() throws AppInternalException {
		List<Multitabla> lstMultitabla = new ArrayList<>();
		for(SgvMultitabla sgvMultitabla : sgvMultitablaRepository
				.findByIdTablaAndIndActivoOrderByIdCodigoAsc(AppConstantes.TBL_TIPO_COMPROBANTE)) {
			lstMultitabla.add(AppUtilConverter.convertSgvMultitablaToMultitabla(sgvMultitabla));
		}
		GenericResponse<List<Multitabla>> ress = new GenericResponse<>();
		ress.setBody(lstMultitabla);
		return ress;
	}

	@Override
	public GenericResponse<?> getLstUnidadMedida() throws AppInternalException {
		List<Multitabla> lstMultitabla = new ArrayList<>();
		for(SgvMultitabla sgvMultitabla : sgvMultitablaRepository
				.findByIdTablaAndIndActivoOrderByIdCodigoAsc(AppConstantes.TBL_UNIDAD_MEDIDA)) {
			lstMultitabla.add(AppUtilConverter.convertSgvMultitablaToMultitabla(sgvMultitabla));
		}
		GenericResponse<List<Multitabla>> ress = new GenericResponse<>();
		ress.setBody(lstMultitabla);
		return ress;
	}

	@Override
	public GenericResponse<?> getLstTipoUsuario() throws AppInternalException {
		List<Multitabla> lstMultitabla = new ArrayList<>();
		for(SgvMultitabla sgvMultitabla : sgvMultitablaRepository
				.findByIdTablaAndIndActivoOrderByIdCodigoAsc(AppConstantes.TBL_TIPO_USUARIO)) {
			lstMultitabla.add(AppUtilConverter.convertSgvMultitablaToMultitabla(sgvMultitabla));
		}
		GenericResponse<List<Multitabla>> ress = new GenericResponse<>();
		ress.setBody(lstMultitabla);
		return ress;
	}

	@Override
	public GenericResponse<?> getLstMontoBolsa() throws AppInternalException {
		List<Multitabla> lstMultitabla = new ArrayList<>();
		for(SgvMultitabla sgvMultitabla : sgvMultitablaRepository
				.findByIdTablaAndIndActivoOrderByIdCodigoAsc(AppConstantes.TBL_MONTO_BOLSA)) {
			lstMultitabla.add(AppUtilConverter.convertSgvMultitablaToMultitabla(sgvMultitabla));
		}
		GenericResponse<List<Multitabla>> ress = new GenericResponse<>();
		ress.setBody(lstMultitabla);
		return ress;
	}

}
