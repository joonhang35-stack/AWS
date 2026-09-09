package com.bcs.zsg.common.bo;

import java.util.ArrayList;
import java.util.List;

public class CommonObject {
	private boolean boolResult = false;
	private String ObjectName = "";
	private String errorMsg = "";
	private List<Object> objListResult;
	private List<Object> objListSource;
	
	public CommonObject() {
		objListResult = new ArrayList<Object>();
	}

	public CommonObject(String _ObjectName) {
		this.ObjectName = _ObjectName;
		this.errorMsg = "";
		objListResult = new ArrayList<Object>();
	}

	public String getObjectName() {
		return ObjectName;
	}

	public void setObjectName(String objectName) {
		ObjectName = objectName;
	}

	public void setErrorMessage(String _strErrorMsg) {
		this.boolResult = true;
		this.errorMsg = _strErrorMsg;
	}

	public boolean getBoolResult() {
		return boolResult;
	}

	public String getErrorMessage() {
		return this.errorMsg;
	}

	public List<Object> getListResult() {
		return this.objListResult;
	}
	
	public Object getListResult(int _intLoc) {
		if(_intLoc >= this.objListResult.size())	_intLoc = this.objListResult.size() - 1;
		if(_intLoc < 0)	_intLoc = 0;
		return this.objListResult.get(_intLoc);
	}

	public void setListResult(Object _objResult) {
		this.objListResult.add(_objResult);
	}

	public void setListSource(List<Object> _objListSource) {
		this.objListSource = _objListSource;
	}
	
	public List<Object> getListSource() {
		return this.objListSource;
	}

	public Object getListSource(int _intLoc) {
		if(_intLoc >= this.objListSource.size())	_intLoc = this.objListSource.size() - 1;
		if(_intLoc < 0)	_intLoc = 0;
		return this.objListSource.get(_intLoc);
	}
}

