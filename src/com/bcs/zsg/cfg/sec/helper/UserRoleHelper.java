package com.bcs.zsg.cfg.sec.helper;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.component.security.vo.UserRoleViewVO;

public class UserRoleHelper {

	
	public static boolean checkIsAccountManager(List<UserRoleViewVO> userRoleList) {
		
		if (CollectionUtils.isNotEmpty(userRoleList)) {
			for (UserRoleViewVO userRoleVO : userRoleList) {
				if (userRoleVO.getRoleCode().equals(CommonConstant.ACCOUNT_MGR_ROLE)) {
					return true;
				}
			}
		}
		return false;
	}
}
