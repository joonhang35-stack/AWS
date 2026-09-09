package com.bcs.zsg.maintenance.web.bean;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.cfg.sec.vo.EmployeeViewVO;
import com.bcs.zsg.common.web.bean.AppBackingBean;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.bo.OnlineBookingConfigBO;
import com.bcs.zsg.maintenance.vo.OnlineBookingConfigVO;
import com.bcs.zsg.sales.bo.InvoiceBO;

public class OnlineBookingConfigBean extends AppBackingBean {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private transient OnlineBookingConfigBO onlineBookingConfigBO;
	@Autowired
	private transient InvoiceBO invoiceBO;
		
	private List<OnlineBookingConfigVO> onlineBookingConfigVOList;
	private List<EmployeeViewVO> employeeList;
	
	private OnlineBookingConfigVO onlineBookingConfigVO;

	@Override
	public void resetForm() {
		try {
			employeeList = invoiceBO.getEmployeeViewList(this.getSessionInfoBean().getCompanyVO().getId());
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void init() {
		resetForm();
		loadOnlineBookingConfigList();
	}
	
	public void loadOnlineBookingConfigList() {
		try {
			onlineBookingConfigVOList = onlineBookingConfigBO.getOnlineBookingConfigList(getSessionInfoBean().getCompanyVO().getId());
		} catch (BusinessException e) {
			errorResult(e);
		}
	}
	
	public void updateOnlineBookingConfig() {
		try {
			for (OnlineBookingConfigVO onlineBookingConfigVO : onlineBookingConfigVOList) {
				onlineBookingConfigVO.setIdCompany(this.getSessionInfoBean().getCompanyVO().getId());
				onlineBookingConfigBO.updateOnlineBookingConfig(onlineBookingConfigVO);
			}
			resetForm();
			loadOnlineBookingConfigList();
			successResult();
		} catch (BusinessException e) {
			errorResult(e);
		}
	}
	
	public void delete() {
		try {
			onlineBookingConfigBO.delete(onlineBookingConfigVO);
			loadOnlineBookingConfigList();
			successResult();
		} catch (BusinessException e) {
			errorResult(e);
		}
	}

	public List<OnlineBookingConfigVO> getOnlineBookingConfigVOList() {
		return onlineBookingConfigVOList;
	}

	public void setOnlineBookingConfigVOList(List<OnlineBookingConfigVO> onlineBookingConfigVOList) {
		this.onlineBookingConfigVOList = onlineBookingConfigVOList;
	}

	public OnlineBookingConfigVO getOnlineBookingConfigVO() {
		return onlineBookingConfigVO;
	}

	public void setOnlineBookingConfigVO(OnlineBookingConfigVO onlineBookingConfigVO) {
		this.onlineBookingConfigVO = onlineBookingConfigVO;
	}

	public List<EmployeeViewVO> getEmployeeList() {
		return employeeList;
	}
}
