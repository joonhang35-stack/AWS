package com.bcs.zsg.cfg.sec.web.bean;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.common.web.bean.AppBackingBean;
import com.bcs.zsg.component.security.bo.SecurityBO;
import com.bcs.zsg.component.security.vo.RoleVO;

public class RoleBean extends AppBackingBean {
	private static final long serialVersionUID = 1L;

	@Autowired
	private transient SecurityBO securityBO;
	
	private RoleVO roleVO;
	private List<RoleVO> roleList;
	private List<RoleVO> filteredRoleList;
	
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
			roleList = securityBO.getRoleList(null, true);
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Add role
	 */
	public void addNewRole() {
		try {
			securityBO.addNewRole(roleVO);
			init();
			successResult();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Update role
	 */
	public void updateRole() {
		try {
			securityBO.updateBaseVO(roleVO);
			init();
			successResult();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Delete role
	 */
	public void deleteRole() {
		try {
			securityBO.deleteRole(roleVO.getUuid());
			init();
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
	 * @return the filteredRoleList
	 */
	public List<RoleVO> getFilteredRoleList() {
		return filteredRoleList;
	}

	/**
	 * @param filteredRoleList the filteredRoleList to set
	 */
	public void setFilteredRoleList(List<RoleVO> filteredRoleList) {
		this.filteredRoleList = filteredRoleList;
	}
}
