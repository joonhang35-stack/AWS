package com.bcs.zsg.common.helper;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("trimConverter")
public class TrimConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		return (value != null) ? value.trim() : null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		return (value != null) ? value.toString() : "";
	}

}
