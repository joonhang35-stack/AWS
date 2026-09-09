package com.bcs.zsg.cfg.sec.vo;
import com.bcs.zsg.component.security.vo.UserVO;

public class EmployeeViewVO extends EmployeeVO {
	private static final long serialVersionUID = 1L;
	
	private UserVO userVO;

	/**
	 * @return the userVO
	 */
	public UserVO getUserVO() {
		return userVO;
	}

	/**
	 * @param userVO the userVO to set
	 */
	public void setUserVO(UserVO userVO) {
		this.userVO = userVO;
	}
}
