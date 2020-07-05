package com.devapp.sigsv.util;

public class AppUtilCodStatus {
	
	protected AppUtilCodStatus(){
        super();
    }
	public static String codStatus(Integer codError) {
		String mensaje = AppConstantes.STRING_VACIO;
		switch(codError) {
			case 226:
				mensaje = AppMessages.GENERIC_REGISTRO_ENCONTRADO;
				break;
			case 404:
				mensaje = AppMessages.GENERIC_REGISTRO_NO_ENCONTRADO;
				break;
			case 200:
				mensaje = AppMessages.GENERIC_SUCCESS;
				break;
			default:
				mensaje = AppMessages.GENERIC_SUCCESS;
		}
		
		return mensaje;
	}

}
