package com.bcs.zsg.cfg.sec.web.bean;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.common.web.bean.AppBackingBean;
import com.bcs.zsg.component.security.bo.SecurityBO;
import com.bcs.zsg.component.security.vo.FunctionVO;
import com.bcs.zsg.core.helper.BaseConstant;

public class FunctionBean extends AppBackingBean {
	private static final long serialVersionUID = 1L;

	@Autowired
	private transient SecurityBO securityBO;
	
	private FunctionVO functionVO;
	private List<FunctionVO> functionList;
	private List<FunctionVO> filteredFunctionList;
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.core.web.swf.bean.BaseBackingBean#resetForm()
	 */
	@Override
	public void resetForm() {
		functionVO = new FunctionVO();
		functionVO.setAppId(BaseConstant.ALL);
	}
	
	/**
	 * Initialization
	 */
	public void init() {
		try {
			resetForm();
			functionList = securityBO.getFunctionList(null, false, false);
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Add new function
	 */
	public void addNewFunction() {
		try {
			securityBO.addNewFunction(functionVO);
			init();
			successResult();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Update function
	 */
	public void updateFunction() {
		try {
			securityBO.updateBaseVO(functionVO);
			init();
			successResult();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Delete function
	 */
	public void deleteFunction() {
		try {
			securityBO.deleteFunction(functionVO.getUuid());
			init();
			successResult();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}

	/*******************
	 * GETTER & SETTER *
	 *******************/
	
	/**
	 * @return the functionVO
	 */
	public FunctionVO getFunctionVO() {
		return functionVO;
	}

	/**
	 * @param functionVO the functionVO to set
	 */
	public void setFunctionVO(FunctionVO functionVO) {
		this.functionVO = functionVO;
	}

	/**
	 * @return the functionList
	 */
	public List<FunctionVO> getFunctionList() {
		return functionList;
	}

	/**
	 * @param functionList the functionList to set
	 */
	public void setFunctionList(List<FunctionVO> functionList) {
		this.functionList = functionList;
	}

	/**
	 * @return the filteredFunctionList
	 */
	public List<FunctionVO> getFilteredFunctionList() {
		return filteredFunctionList;
	}

	/**
	 * @param filteredFunctionList the filteredFunctionList to set
	 */
	public void setFilteredFunctionList(List<FunctionVO> filteredFunctionList) {
		this.filteredFunctionList = filteredFunctionList;
	}

}
