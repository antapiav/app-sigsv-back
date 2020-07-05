package com.devapp.sigsv.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppUtil {
	
private static final Logger LOGGER = LoggerFactory.getLogger(AppUtil.class);
	
	protected AppUtil() {
		super();
	}
	
	public static final boolean isEmpty(String object) {
		if (isNull(object)) {
			return true;
		}

		return StringUtils.trim(object).isEmpty();
	}
	
	public static final boolean isNull(Object object) {
		return object == null;
	}

}
