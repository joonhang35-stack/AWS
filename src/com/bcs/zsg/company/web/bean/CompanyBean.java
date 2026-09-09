package com.bcs.zsg.company.web.bean;

import java.util.List;

import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.common.bo.HomeBO;
import com.bcs.zsg.common.web.bean.AppBackingBean;
import com.bcs.zsg.company.bo.CompanyBO;
import com.bcs.zsg.maintenance.vo.CompanyVO;

public class CompanyBean extends AppBackingBean {
	private static final long serialVersionUID = 1L;

	@Autowired
	private transient CompanyBO companyBO;
	@Autowired
	private transient HomeBO homeBO;
	
	private List<CompanyVO> companyList;
	private CompanyVO companyVO;
	
	private boolean checked = true;
	private boolean uncheck = false;
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.core.web.swf.bean.BaseBackingBean#resetForm()
	 */
	@Override
	public void resetForm() {
		
	}
	
	/**
	 * Initialization
	 */
	public void init() {
		try {
			companyVO = getSessionInfoBean().getCompanyVO();
			companyList = companyBO.getCompanyList(getSessionInfoBean().getEmployeeVO().getSecUser());
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * 
	 * @param event
	 */
	public void handleCompanySelect(SelectEvent event) {
		try {
			companyVO = (CompanyVO) event.getObject();
			getSessionInfoBean().setCompanyVO(companyVO);
			getSessionInfoBean().setEmployeeVO(homeBO.getEmployeeInfo(getSessionInfoBean().getUserVO().getUuid(), companyVO.getId()));
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}

	/*******************
	 * GETTER & SETTER *
	 *******************/

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
	 * @return the companyList
	 */
	public List<CompanyVO> getCompanyList() {
		return companyList;
	}

	/**
	 * @param companyList the companyList to set
	 */
	public void setCompanyList(List<CompanyVO> companyList) {
		this.companyList = companyList;
	}

	/**
	 * @return the checked
	 */
	public boolean isChecked() {
		return checked;
	}

	/**
	 * @param checked the checked to set
	 */
	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	/**
	 * @return the uncheck
	 */
	public boolean isUncheck() {
		return uncheck;
	}

	/**
	 * @param uncheck the uncheck to set
	 */
	public void setUncheck(boolean uncheck) {
		this.uncheck = uncheck;
	}

}
