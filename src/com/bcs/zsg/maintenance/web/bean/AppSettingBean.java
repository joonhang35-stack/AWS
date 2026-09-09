package com.bcs.zsg.maintenance.web.bean;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.common.web.bean.AppBackingBean;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.bo.AppSettingBO;
import com.bcs.zsg.maintenance.helper.ConstantAppSetting;
import com.bcs.zsg.maintenance.vo.AppSettingVO;

public class AppSettingBean extends AppBackingBean {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private transient AppSettingBO appSettingBO;
	
	private List<AppSettingVO> appSettingList;
	
	private AppSettingVO appSettingVO;
	
	public void init() {
		try {
			reloadAppSettingList();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}

	@Override
	public void resetForm() {
		appSettingVO = new AppSettingVO();
	}
	
	public void save() {
		try {
			appSettingBO.update(appSettingVO);
			successResult();
			reloadAppSettingList();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	private void reloadAppSettingList() throws BusinessException {
		appSettingList = appSettingBO.getAppSettingList(ConstantAppSetting.MODULE_ADMIN.getValue());
	}

	public List<AppSettingVO> getAppSettingList() {
		return appSettingList;
	}

	public AppSettingVO getAppSettingVO() {
		return appSettingVO;
	}

	public void setAppSettingVO(AppSettingVO appSettingVO) {
		this.appSettingVO = appSettingVO;
	}

}
