package com.devapp.sigsv.model.bean.request;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class GenericRequest implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
    private Long page;
	
	private Object filters;
	
	private Object sort;
	
	private Object data;
    
}