package com.bcs.zsg.maintenance.web.bean;

import java.math.BigDecimal;
import java.util.List;

import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.acct.bo.ChartOfAcctBO;
import com.bcs.zsg.acct.vo.AcctViewVO;
import com.bcs.zsg.common.web.bean.AppBackingBean;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.bo.AppSettingBO;
import com.bcs.zsg.maintenance.bo.IpayConfigBO;
import com.bcs.zsg.maintenance.helper.ConstantAppSetting;
import com.bcs.zsg.maintenance.vo.AppSettingVO;
import com.bcs.zsg.maintenance.vo.IpayConfigVO;

public class IpayConfigBean extends AppBackingBean {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private transient IpayConfigBO ipayConfigBO;
	
	@Autowired
	private transient ChartOfAcctBO chartOfAcctBO;
	
	@Autowired
	private transient AppSettingBO appSettingBO;
	
	private List<IpayConfigVO> ipayConfigVOList;
	private List<AcctViewVO> acctViewList;
	
	private IpayConfigVO ipayConfigVO;
	private AppSettingVO appSettingVO;
	
	private int expiryTime;

	@Override
	public void resetForm() {
		
	}
	
	public void init() {
		resetForm();
		loadIpayConfigList();
		loadAppSetting();
	}
	
	public void loadAppSetting() {
		try {
			appSettingVO = appSettingBO.getAppSettingByCode(ConstantAppSetting.MAINT_IPAY88_EXPIRY_DURATION);
		} catch (BusinessException e) {
			errorResult(e);
		}
	}
	
	public void loadIpayConfigList() {
		try {
			ipayConfigVOList = ipayConfigBO.getIpayConfigVOList();
		} catch (BusinessException e) {
			errorResult(e);
		}
	}
	
	public void navToDetail(IpayConfigVO vo) {
		if (vo == null) {
			ipayConfigVO = new IpayConfigVO();
			ipayConfigVO.setAdminChargesPercentage(BigDecimal.ZERO);
		} else {
			ipayConfigVO = vo;
		}
	}
	
	public void loadAcctList() {
		try {
			acctViewList = chartOfAcctBO.getAcctViewList(this.getSessionInfoBean().getCompanyVO().getId(), null);
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void handleAcctSelect(SelectEvent event) throws BusinessException{
		try {
			AcctViewVO vo = (AcctViewVO) event.getObject();
			ipayConfigVO.setIdAcct(vo.getId());

			if (vo.getSubCode().equals("")) {
				ipayConfigVO.setAcctCode(vo.getCode());
				ipayConfigVO.setAcctDesc(vo.getDesc());
			} else {
				ipayConfigVO.setAcctCode(vo.getCode() + "-" + vo.getSubCode());
				ipayConfigVO.setAcctDesc(vo.getDesc() + ", " + vo.getSubDesc());
			}
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void saveDuration() {
		try {
			appSettingBO.update(appSettingVO);
			successResult();
		} catch (BusinessException e) {
			errorResult(e);
		}
	}
	
	public void save() {
		try {
			ipayConfigBO.updateIpayConfig(ipayConfigVO);
			loadIpayConfigList();
			successResult();
		} catch (BusinessException e) {
			errorResult(e);
		}
	}
	
	public void delete() {
		try {
			ipayConfigBO.delete(ipayConfigVO);
			loadIpayConfigList();
			successResult();
		} catch (BusinessException e) {
			errorResult(e);
		}
	}

	public List<IpayConfigVO> getIpayConfigVOList() {
		return ipayConfigVOList;
	}

	public void setIpayConfigVOList(List<IpayConfigVO> ipayConfigVOList) {
		this.ipayConfigVOList = ipayConfigVOList;
	}

	public IpayConfigVO getIpayConfigVO() {
		return ipayConfigVO;
	}

	public void setIpayConfigVO(IpayConfigVO ipayConfigVO) {
		this.ipayConfigVO = ipayConfigVO;
	}

	public List<AcctViewVO> getAcctViewList() {
		return acctViewList;
	}

	public void setAcctViewList(List<AcctViewVO> acctViewList) {
		this.acctViewList = acctViewList;
	}

	public int getExpiryTime() {
		return expiryTime;
	}

	public void setExpiryTime(int expiryTime) {
		this.expiryTime = expiryTime;
	}

	public AppSettingVO getAppSettingVO() {
		return appSettingVO;
	}

	public void setAppSettingVO(AppSettingVO appSettingVO) {
		this.appSettingVO = appSettingVO;
	}
}
