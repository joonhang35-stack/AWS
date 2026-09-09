package com.bcs.zsg.maintenance.bo;

import java.util.List;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.helper.ConstantAppSetting;
import com.bcs.zsg.maintenance.vo.AppSettingVO;

public interface AppSettingBO {
	
	public List<AppSettingVO> getAppSettingList(String module) throws BusinessException;
	
	public AppSettingVO getAppSettingByCode(ConstantAppSetting constantAppSetting) throws BusinessException;
	
	public void update(AppSettingVO appSettingVO) throws BusinessException;
	public void update(List<AppSettingVO> appSettingVOList) throws BusinessException;
}
