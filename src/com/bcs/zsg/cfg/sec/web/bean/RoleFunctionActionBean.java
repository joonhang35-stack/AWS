package com.bcs.zsg.cfg.sec.web.bean;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.common.helper.FunctionCDConstant;
import com.bcs.zsg.common.web.bean.AppBackingBean;
import com.bcs.zsg.component.security.bo.SecurityBO;
import com.bcs.zsg.component.security.vo.RoleFunctionActionVO;
import com.bcs.zsg.component.security.vo.RoleFunctionViewVO;
import com.bcs.zsg.component.security.vo.RoleVO;
import com.bcs.zsg.core.helper.BaseConstant;

public class RoleFunctionActionBean extends AppBackingBean {
	private static final long serialVersionUID = 1L;

	@Autowired
	private transient SecurityBO securityBO;
	
	private RoleVO roleVO;
	
	private List<RoleVO> roleList;
	private List<RoleFunctionViewVO> roleFunctionList;
	private List<RoleFunctionActionVO> roleFunctionActionList;
	
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.core.web.swf.bean.BaseBackingBean#resetForm()
	 */
	@Override
	public void resetForm() {
		roleVO = new RoleVO();
	}
	
	/**
	 * Initialization
	 */
	public void init() {
		try {
			resetForm();
			roleList = securityBO.getRoleList(BaseConstant.STATUS_ACTIVE, true);
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Handle role change
	 */
	public void handleRoleChange() {
		try {
			if (StringUtils.isNotEmpty(roleVO.getUuid())) {
				roleFunctionList = securityBO.getRoleFunctionList(roleVO.getUuid());
				if (CollectionUtils.isNotEmpty(roleFunctionList)) {
					for (int i = roleFunctionList.size() - 1 ; i >= 0 ; i--) {
						RoleFunctionViewVO vo = roleFunctionList.get(i);
						if (StringUtils.equals(vo.getFunctionEntryURI(), BaseConstant.PAD_HASH) ||
								StringUtils.equals(vo.getFunctionCode(), FunctionCDConstant.CHANGE_PSWD) ||
								StringUtils.equals(vo.getFunctionCode(), FunctionCDConstant.LOGOUT)) {
							roleFunctionList.remove(i);
							continue;
						}
						vo.setActionRefCodeList(securityBO.getActionRefCodeList(vo.getRoleUUID(), vo.getFunctionUUID()));
					}
				}
			}
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Update role function action
	 * @param event
	 */
	public void onEdit(RowEditEvent event) {
		try {
			securityBO.addRoleFunctionAction((RoleFunctionViewVO) event.getObject());
			handleRoleChange();
			successResult();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}

	/*******************
	 * GETTER & SETTER *
	 *******************/

	/**
	 * @return the roleVO
	 */
	public RoleVO getRoleVO() {
		return roleVO;
	}

	/**
	 * @param roleVO the roleVO to set
	 */
	public void setRoleVO(RoleVO roleVO) {
		this.roleVO = roleVO;
	}

	/**
	 * @return the roleList
	 */
	public List<RoleVO> getRoleList() {
		return roleList;
	}

	/**
	 * @param roleList the roleList to set
	 */
	public void setRoleList(List<RoleVO> roleList) {
		this.roleList = roleList;
	}

	/**
	 * @return the roleFunctionList
	 */
	public List<RoleFunctionViewVO> getRoleFunctionList() {
		return roleFunctionList;
	}

	/**
	 * @param roleFunctionList the roleFunctionList to set
	 */
	public void setRoleFunctionList(List<RoleFunctionViewVO> roleFunctionList) {
		this.roleFunctionList = roleFunctionList;
	}

	/**
	 * @return the roleFunctionActionList
	 */
	public List<RoleFunctionActionVO> getRoleFunctionActionList() {
		return roleFunctionActionList;
	}

	/**
	 * @param roleFunctionActionList the roleFunctionActionList to set
	 */
	public void setRoleFunctionActionList(
			List<RoleFunctionActionVO> roleFunctionActionList) {
		this.roleFunctionActionList = roleFunctionActionList;
	}

}
