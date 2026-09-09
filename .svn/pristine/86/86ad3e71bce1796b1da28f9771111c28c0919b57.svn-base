package com.bcs.zsg.maintenance.web.bean;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.common.web.bean.AppBackingBean;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.bo.GlobalConfigBO;
import com.bcs.zsg.maintenance.vo.GlobalConfigVO;



public class GlobalConfigBean extends AppBackingBean 
{
	private static final long serialVersionUID = 1L;

	@Autowired
	private transient GlobalConfigBO globalConfigBO;

	private GlobalConfigVO globalConfigVO;

	private List<GlobalConfigVO> globalConfigList;
	private List<GlobalConfigVO> globalConfigDescList;
	private List<GlobalConfigVO> globalConfigValueList;

	@Override
	public void resetForm() {
		setGlobalConfigVO(new GlobalConfigVO());
		}


	public void init() throws BusinessException {
		try {
			loadGlobalConfig();

		}catch (Throwable t) {
			errorResult(t);
		}
	}

	private void loadGlobalConfig() throws BusinessException {
		try {
			globalConfigList=globalConfigBO.getGlobalConfigList();
			globalConfigDescList=globalConfigBO.getGlobalConfigDescList();
			resetForm();

		}catch (Throwable t) {
			errorResult(t);
		}
	}

	public void updateGlobalConfig() throws BusinessException{
		try {
			globalConfigBO.updateGlobalConfig(globalConfigList);
			resetForm();
			init();
			loadGlobalConfig();	
			successResult();

		} catch (Throwable t) {
			errorResult(t);
		}
	}

	public List<GlobalConfigVO> getGlobalConfigList() {
		return globalConfigList;
	}

	public void setGlobalConfigList(List<GlobalConfigVO> globalConfigList) {
		this.globalConfigList = globalConfigList;
	}


	public GlobalConfigVO getGlobalConfigVO() {
		return globalConfigVO;
	}


	public void setGlobalConfigVO(GlobalConfigVO globalConfigVO) {
		this.globalConfigVO = globalConfigVO;
	}


	public List<GlobalConfigVO> getGlobalConfigDescList() {
		return globalConfigDescList;
	}


	public void setGlobalConfigDescList(List<GlobalConfigVO> globalConfigDescList) {
		this.globalConfigDescList = globalConfigDescList;
	}


	public List<GlobalConfigVO> getGlobalConfigValueList() {
		return globalConfigValueList;
	}


	public void setGlobalConfigValueList(List<GlobalConfigVO> globalConfigValueList) {
		this.globalConfigValueList = globalConfigValueList;
	}
}
