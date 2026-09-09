package com.bcs.zsg.maintenance.service;

import static com.bcs.zsg.core.helper.BaseConstant.PAD_SLASH;
import static com.bcs.zsg.core.helper.BaseConstant.PAD_UNDERSCORE;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.common.helper.SchedulerUtils;
import com.bcs.zsg.core.cache.service.CacheService;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.dao.AppSettingDAO;
import com.bcs.zsg.maintenance.helper.ConstantAppSetting;
import com.bcs.zsg.maintenance.vo.AppSettingVO;
import com.bcs.zsg.scheduler.helper.EnumJobKey;

public class AppSettingServiceImpl implements AppSettingService {
	
	@Autowired
	private AppSettingDAO appSettingDAO;
	@Autowired
	protected CacheService cacheService;
	
	private static final String CACHE_KEY_PREFIX = "APP_SETTING";

	@Override
	public List<AppSettingVO> getAppSettingList(String module) throws BusinessException {
		return appSettingDAO.getAppSettingList(module);
	}
	
	@Override
	public AppSettingVO getAppSettingByCode(ConstantAppSetting constantAppSetting) throws BusinessException {
		String cacheKey = CACHE_KEY_PREFIX + PAD_SLASH + constantAppSetting.getValue();
		AppSettingVO appSettingVO = cacheService.get(cacheKey, AppSettingVO.class);
		
		if (appSettingVO == null) {
			String code = constantAppSetting.getValue().split(PAD_UNDERSCORE, 2)[1];
			appSettingVO = appSettingDAO.getAppSettingByCode(code);
			
			// add app setting to cache
			cacheService.put(cacheKey, appSettingVO);
		}
		return appSettingVO;
	}
	
	@Override
	public void update(AppSettingVO vo) throws BusinessException {
		appSettingDAO.update(vo);
		
		// clear cache
		cacheService.remove(CACHE_KEY_PREFIX + PAD_SLASH + vo.getModule() + PAD_UNDERSCORE + vo.getCode());
				
		// Scheduler - POS Upload
		if (StringUtils.equals(vo.getCode(), ConstantAppSetting.MAINT_POS_CONFIG_SCHEDULER_TIME_UPLOAD.getValue().split(PAD_UNDERSCORE, 2)[1])) {
//			String time = vo.getValue();
//			String timePattern = "";
//			if (StringUtils.isNotBlank(time)) {
//				String[] timeSplitList = time.split(":");
//				timePattern = "0 " + (StringUtils.equals(timeSplitList[1], "00") ? "0" : timeSplitList[1]) + " " + timeSplitList[0] + " 1/1 * ? *";
//			}
//			
//			try {
//				SchedulerApp.getInstance().removeJob(EnumJobKey.ScheduleManager, TriggerType.Schedule.toString(), "POSUploadDailySchedule");
//				SchedulerApp.getInstance().scheduledJob(EnumJobKey.ScheduleManager, 
//						TriggerType.Schedule, POSUploadSalesServiceImpl.class, "POSUploadDailySchedule", timePattern);
//				
//			} catch (Exception e) { }
			try {
				SchedulerUtils.rescheduleJob(vo.getValue(), EnumJobKey.ScheduleManager, POSUploadSalesServiceImpl.class, "POSUploadDailySchedule", true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void update(List<AppSettingVO> appSettingVOList) throws BusinessException {
		for (AppSettingVO vo : appSettingVOList) {
			update(vo);
			// clear cache
			cacheService.remove(CACHE_KEY_PREFIX + PAD_SLASH + vo.getModule() + PAD_UNDERSCORE + vo.getCode());
		}
	}
}
