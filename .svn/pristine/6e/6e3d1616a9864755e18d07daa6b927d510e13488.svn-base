package com.bcs.zsg.maintenance.web.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.common.helper.CommonErrConstant;
import com.bcs.zsg.common.web.bean.AppBackingBean;
import com.bcs.zsg.component.security.bo.SecurityBO;
import com.bcs.zsg.component.security.vo.RoleVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.BaseConstant;
import com.bcs.zsg.maintenance.bo.VisibleListingConfigBO;
import com.bcs.zsg.maintenance.vo.VisibleListingConfigVO;

public class VisibleListingConfigBean extends AppBackingBean {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private transient VisibleListingConfigBO visibleListingConfigBO;
	@Autowired
	private transient SecurityBO securityBO;
	
	private List<VisibleListingConfigVO> visibleListingConfigVOList;
	private List<RoleVO> roleVOList;
	
	private VisibleListingConfigVO visibleListingConfigVO;
	
	public void init() {
		loadList();
	}
	
	/**
	 * 
	 */
	public void loadList() {
		try {
			Map<String, Object> params = new HashMap<>();
			visibleListingConfigVOList = visibleListingConfigBO.getVisibleListingConfigListGroupByCatCdListingtype(params);
		} catch (BusinessException e) {
			errorResult(e);
		}
	}
	
	/**
	 * 
	 */
	public void loadRoleList() {
		roleVOList = securityBO.getRoleList(BaseConstant.STATUS_ACTIVE, true);
	}
	
	/**
	 * 
	 */
	public void onConfigSelect(VisibleListingConfigVO vo) {
		resetForm();
		visibleListingConfigVO = vo;
	}
	
	/**
	 * 
	 */
	public void save() {
		try {
			if (visibleListingConfigVO != null && visibleListingConfigVO.getIdRoleList() != null) {
				if (visibleListingConfigBO.isCatCodeAndListingTypeDuplicate(visibleListingConfigVO)) 
					throw new BusinessException(CommonErrConstant.ERR_VISIBLE_LIST_CONFIG_DUPLICATED);
				
				// To delete previous record
				if (visibleListingConfigVO.getIdList() != null) {
					List<Long> listID = new ArrayList<Long>();
					for (String id : visibleListingConfigVO.getIdList()) {
						listID.add(Long.parseLong(id));
					}
					visibleListingConfigBO.deleteByListID(listID);
				}
				
				// Insert as new
				for (String idRole : visibleListingConfigVO.getIdRoleList()) {
					VisibleListingConfigVO vo = new VisibleListingConfigVO();
					vo.setCatCd(visibleListingConfigVO.getCatCd());
					vo.setListingType(visibleListingConfigVO.getListingType());
					vo.setType(CommonConstant.VLC_TYPE_ROLE);
					vo.setIdRole(idRole);
					
					visibleListingConfigBO.save(vo);
				}
				
				for (String dept : visibleListingConfigVO.getDepartmentList()) {
					VisibleListingConfigVO vo = new VisibleListingConfigVO();
					vo.setCatCd(visibleListingConfigVO.getCatCd());
					vo.setListingType(visibleListingConfigVO.getListingType());
					vo.setType(CommonConstant.VLC_TYPE_DEPT);
					vo.setDeptCode(dept);
					
					visibleListingConfigBO.save(vo);
				}
			}
			
			visibleListingConfigVO = new VisibleListingConfigVO();
			loadList();
			successResult();
		} catch (BusinessException e) {
			errorResult(e);
		}
	}
	
	/**
	 * 
	 */
	public void delete() {
		try {
			if (visibleListingConfigVO.getIdList() != null) {
				List<Long> listID = new ArrayList<Long>();
				for (String id : visibleListingConfigVO.getIdList()) {
					listID.add(Long.parseLong(id));
				}
				visibleListingConfigBO.deleteByListID(listID);
			} else {
				visibleListingConfigBO.delete(visibleListingConfigVO);
			}
			
			loadList();
			successResult();
		} catch (BusinessException e) {
			errorResult(e);
		}
	}
	
	/***************
	 * RESET FORMS *
	 ***************/
	
	@Override
	public void resetForm() {
		visibleListingConfigVO = new VisibleListingConfigVO();
		loadRoleList();
	}
	
	/***********
	 * GETTERS *
	 ***********/

	public List<VisibleListingConfigVO> getVisibleListingConfigVOList() {
		return visibleListingConfigVOList;
	}

	public void setVisibleListingConfigVOList(List<VisibleListingConfigVO> visibleListingConfigVOList) {
		this.visibleListingConfigVOList = visibleListingConfigVOList;
	}

	public List<RoleVO> getRoleVOList() {
		return roleVOList;
	}

	public void setRoleVOList(List<RoleVO> roleVOList) {
		this.roleVOList = roleVOList;
	}

	public VisibleListingConfigVO getVisibleListingConfigVO() {
		return visibleListingConfigVO;
	}

	public void setVisibleListingConfigVO(VisibleListingConfigVO visibleListingConfigVO) {
		this.visibleListingConfigVO = visibleListingConfigVO;
	}

}
