package com.devapp.sigsv.controller;

import java.util.Locale;

import com.devapp.sigsv.util.AppConstantes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

public abstract class AbstractController {
    
    protected MessageSource messageSource;
    
	@Autowired
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	protected String getValueMessage(String key) {
		return messageSource.getMessage(key, new Object[] {}, AppConstantes.NOT_FOUND_KEY, Locale.getDefault());
	}
	
	protected String getValueMessage(String key, Object[] args) {
		return messageSource.getMessage(key, args, AppConstantes.NOT_FOUND_KEY, Locale.getDefault());
	}
}