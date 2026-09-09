package com.bcs.zsg.cfg.sec.web.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.component.picklist.PickList;
import org.primefaces.model.DualListModel;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.common.web.bean.AppBackingBean;
import com.bcs.zsg.component.security.bo.SecurityBO;
import com.bcs.zsg.component.security.vo.FunctionVO;
import com.bcs.zsg.component.security.vo.RoleFunctionViewVO;
import com.bcs.zsg.component.security.vo.RoleVO;
import com.bcs.zsg.core.helper.BaseConstant;

public class RoleFunctionBean extends AppBackingBean {
	private static final long serialVersionUID = 1L;

	@Autowired
	private transient SecurityBO securityBO;
	
	private RoleVO roleVO;
	private List<RoleVO> roleList;
	
	private DualListModel<FunctionVO> functionListModel;
	
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
			functionListModel = new DualListModel<FunctionVO>(new ArrayList<FunctionVO>(), new ArrayList<FunctionVO>());
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Handle role change
	 */
	public void handleRoleChange() {
		try {
			// reset function list model
			functionListModel = new DualListModel<FunctionVO>(new ArrayList<FunctionVO>(), new ArrayList<FunctionVO>());
			
			if (StringUtils.isNotEmpty(roleVO.getUuid())){
				List<RoleFunctionViewVO> roleFunctionList = securityBO.getRoleFunctionList(roleVO.getUuid());
				List<FunctionVO> usedFunctionList = new ArrayList<FunctionVO>();
				if (CollectionUtils.isNotEmpty(roleFunctionList)) {
					for (RoleFunctionViewVO vo : roleFunctionList) {
						FunctionVO functionVO = new FunctionVO();
						functionVO.setUuid(vo.getFunctionUUID());
						functionVO.setFunctionCode(vo.getFunctionCode());
						functionVO.setFunctionName(vo.getFunctionName());
						usedFunctionList.add(functionVO);
					}
				}
				List<FunctionVO> availableFunctionList = securityBO.getAvailableFunctionList(usedFunctionList, BaseConstant.STATUS_ACTIVE);
				functionListModel = new DualListModel<FunctionVO>(availableFunctionList, usedFunctionList);
			}
		} catch (Throwable t) {
			errorResult(t);
		}
	}

	/**
	 * Update role function
	 */
	public void updateRoleFunction() {
		try {
			String[] selectedFunctions = new String[functionListModel.getTarget().size()];
			
			for (int i = 0 ; i < functionListModel.getTarget().size() ; i++) {
				FunctionVO vo = functionListModel.getTarget().get(i);
				selectedFunctions[i] = vo.getUuid();
			}
			securityBO.updateRoleFunctions(roleVO.getUuid(), selectedFunctions);
			successResult();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Function converter
	 * @return
	 */
	public Converter getFunctionConverter() {
		return new Converter() {

			/*
			 * (non-Javadoc)
			 * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.String)
			 */
			@SuppressWarnings("unchecked")
			@Override
			public Object getAsObject(FacesContext context, UIComponent component, String str) {
				DualListModel<FunctionVO> listModel = (DualListModel<FunctionVO>) ((PickList) component).getValue();
				if (CollectionUtils.isNotEmpty(listModel.getSource())) {
					for (FunctionVO vo : listModel.getSource()) {
						if (StringUtils.equals(vo.getUuid(), str)) return vo;
					}
				}
				
				if (CollectionUtils.isNotEmpty(listModel.getTarget())) {
					for (FunctionVO vo : listModel.getTarget()) {
						if (StringUtils.equals(vo.getUuid(), str)) return vo;
					}
				}
				return null;
			}

			/*
			 * (non-Javadoc)
			 * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.Object)
			 */
			@Override
			public String getAsString(FacesContext context, UIComponent component, Object obj) {
				return ((FunctionVO) obj).getUuid();
			}
			
		};
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
	 * @return the functionListModel
	 */
	public DualListModel<FunctionVO> getFunctionListModel() {
		return functionListModel;
	}

	/**
	 * @param functionListModel the functionListModel to set
	 */
	public void setFunctionListModel(DualListModel<FunctionVO> functionListModel) {
		this.functionListModel = functionListModel;
	}
}
