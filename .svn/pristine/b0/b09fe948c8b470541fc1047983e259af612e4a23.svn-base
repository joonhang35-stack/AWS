package com.bcs.zsg.maintenance.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.helper.ConstantAppSetting;
import com.bcs.zsg.maintenance.service.AppSettingService;
import com.bcs.zsg.maintenance.vo.AppSettingVO;

public class AppSettingBOImpl implements AppSettingBO {
	
	@Autowired
	private AppSettingService appSettingService;

	@Override
	public List<AppSettingVO> getAppSettingList(String module) throws BusinessException {
		return appSettingService.getAppSettingList(module);
	}

	@Override
	public AppSettingVO getAppSettingByCode(ConstantAppSetting constantAppSetting) throws BusinessException {
		return appSettingService.getAppSettingByCode(constantAppSetting);
	}
	
	@Override
	public void update(AppSettingVO appSettingVO) throws BusinessException {
		appSettingService.update(appSettingVO);
	}
	
	@Override
	public void update(List<AppSettingVO> appSettingVOList) throws BusinessException {
		appSettingService.update(appSettingVOList);
	}

}
