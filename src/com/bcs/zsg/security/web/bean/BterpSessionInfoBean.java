package com.bcs.zsg.security.web.bean;

import java.util.HashMap;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.bcs.zsg.cfg.sec.vo.EmployeeVO;
import com.bcs.zsg.component.security.web.BaseSessionInfoBean;
import com.bcs.zsg.maintenance.vo.CompanyVO;

public class BterpSessionInfoBean extends BaseSessionInfoBean {
	private static final long serialVersionUID = 1L;

	private CompanyVO companyVO;
	private EmployeeVO employeeVO;
	private Map<String, Object> cacheMap;
	
	/**
	 * Get session id
	 * @return
	 */
	public String getSessionID() {
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		return req.getSession().getId();
	}

	/**
	 * @return the companyVO
	 */
	public CompanyVO getCompanyVO() {
		return companyVO;
	}

	/**
	 * @param companyVO the companyVO to set
	 */
	public void setCompanyVO(CompanyVO companyVO) {
		this.companyVO = companyVO;
	}

	/**
	 * @return the employeeVO
	 */
	public EmployeeVO getEmployeeVO() {
		return employeeVO;
	}

	/**
	 * @param employeeVO the employeeVO to set
	 */
	public void setEmployeeVO(EmployeeVO employeeVO) {
		this.employeeVO = employeeVO;
	}

	/**
	 * @return the cacheMap
	 */
	public Map<String, Object> getCacheMap() {
		if (cacheMap == null) cacheMap = new HashMap<String, Object>();
		return cacheMap;
	}

	/**
	 * @param cacheMap the cacheMap to set
	 */
	public void setCacheMap(Map<String, Object> cacheMap) {
		this.cacheMap = cacheMap;
	}
}
