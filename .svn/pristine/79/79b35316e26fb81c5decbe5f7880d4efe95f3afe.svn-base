package com.bcs.zsg.cfg.sec.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;

import com.bcs.zsg.cfg.sec.dao.PasswordResetDAO;
import com.bcs.zsg.cfg.sec.dao.UserDAO;
import com.bcs.zsg.cfg.sec.helper.ConstantUser;
import com.bcs.zsg.cfg.sec.vo.EmployeeVO;
import com.bcs.zsg.cfg.sec.vo.EmployeeViewVO;
import com.bcs.zsg.cfg.sec.vo.PasswordResetVO;
import com.bcs.zsg.component.security.bo.SecurityBO;
import com.bcs.zsg.component.security.service.SecurityService;
import com.bcs.zsg.component.security.vo.UserVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.BaseConstant;
import com.bcs.zsg.mail.bo.EmailingBO;
import com.bcs.zsg.maintenance.vo.CompanyVO;

public class UserServiceImpl implements UserService {
	private String salt = "fi$hM0nt@r";
	
	@Autowired
	private SecurityBO securityBO;
	
	@Autowired
	protected PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	protected SecurityService securityService;
	
	@Autowired
	private PasswordResetDAO passwordResetDAO;
	
	@Autowired
	private EmailingBO emailingBO;
	
	protected String defaultLoginPassword;
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.cfg.sec.service.resetInvalidLogin#resetInvalidLogin(com.bcs.zsg.component.security.vo.UserVO)
	 */
	@Override
	public void resetInvalidLogin(UserVO userVO) throws BusinessException {
		userDAO.resetInvalidLogin(userVO);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.cfg.sec.service.updateUser#updateUser(com.bcs.zsg.component.security.vo.UserVO, java.lang.String)
	 */
	@Override
	public void updateUser(UserVO userVO, String oldLoginId) throws BusinessException {
		if (!StringUtils.equals(oldLoginId, userVO.getLoginId())) {
			securityBO.validateLoginIdAvailability(userVO.getLoginId());
		}
		userDAO.updateUser(userVO);
	}
	
	@Override
	public void updateUserPassword(UserVO userVO) throws BusinessException {
		userDAO.updateUserPassword(userVO);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.cfg.sec.bo.deleteUser#deleteUser(com.bcs.zsg.component.security.vo.UserVO)
	 */
	@Override
	public void deleteUser(UserVO userVO) throws BusinessException {
		userDAO.deleteUser(userVO);
	}

	/* (non-Javadoc)
	 * @see com.bcs.zsg.cfg.sec.service.UserService#getCompanyList()
	 */
	@Override
	public List<CompanyVO> getCompanyList() throws BusinessException {
		return userDAO.getCompanyList();
	}

	/* (non-Javadoc)
	 * @see com.bcs.zsg.cfg.sec.service.UserService#addEmployee(java.util.ArrayList, java.util.ArrayList, java.util.ArrayList, java.util.String, java.util.String)
	 */
	@Override
	public void addEmployee(List<EmployeeVO> addEmployeeList, List<EmployeeVO> updEmployeeList, List<EmployeeVO> delEmployeeList, String userUUID, String department) throws BusinessException {
		addEmployee(addEmployeeList, updEmployeeList, delEmployeeList, userUUID, department, null, null);
	}
	
	@Override
	public void addEmployee(List<EmployeeVO> addEmployeeList, List<EmployeeVO> updEmployeeList, List<EmployeeVO> delEmployeeList, String userUUID, String department, Long idCustomer, String customerName) throws BusinessException {
		if (CollectionUtils.isNotEmpty(addEmployeeList)) {
			for (EmployeeVO addVO : addEmployeeList) {
				userDAO.insert(addVO);
			}
		}
		
		if (CollectionUtils.isNotEmpty(updEmployeeList)) {
			for (EmployeeVO updVO : updEmployeeList) {
				userDAO.update(updVO);
			}
		}
		
		if (CollectionUtils.isNotEmpty(delEmployeeList)) {
			for (EmployeeVO delVO : delEmployeeList) {
				//userDAO.delete(delVO);
				userDAO.update(delVO);
			}
		}
		
		String roleType = "";
		
		if (idCustomer == null) roleType = ConstantUser.ROLE_TYPE_STAFF;
		else roleType = ConstantUser.ROLE_TYPE_AGENT;
		
		userDAO.updateEmployee(userUUID, department, idCustomer, customerName, roleType);
	}
	
	@Override
	public void updEmployee(List<EmployeeVO> addEmployeeList, List<EmployeeVO> updEmployeeList, List<EmployeeVO> delEmployeeList, String userUUID, EmployeeVO employeeVO) throws BusinessException {
		if (CollectionUtils.isNotEmpty(addEmployeeList)) {
			for (EmployeeVO addVO : addEmployeeList) {
				userDAO.insert(addVO);
			}
		}
		
		if (CollectionUtils.isNotEmpty(updEmployeeList)) {
			for (EmployeeVO updVO : updEmployeeList) {
				userDAO.update(updVO);
			}
		}
		
		if (CollectionUtils.isNotEmpty(delEmployeeList)) {
			for (EmployeeVO delVO : delEmployeeList) {
				//userDAO.delete(delVO);
				userDAO.update(delVO);
			}
		}
		
		if (employeeVO.getCustomerId() == null) employeeVO.setRoleType(ConstantUser.ROLE_TYPE_STAFF);
		else employeeVO.setRoleType(ConstantUser.ROLE_TYPE_AGENT);
		
		if (employeeVO != null) userDAO.updateEmployee(userUUID, employeeVO);
	}
	
	@Override
	public void insertEmployee(EmployeeVO employeeVO) throws BusinessException {
		employeeVO.setStatusCode(BaseConstant.STATUS_ACTIVE);
		userDAO.insert(employeeVO);
	}
	
	@Override
	public void updateUserProfile(UserVO userVO, EmployeeVO employeeVO, Map<String, Object> params) throws BusinessException {
		userDAO.updateUserProfile(userVO, employeeVO, params);
	}
	

	/* (non-Javadoc)
	 * @see com.bcs.zsg.cfg.sec.service.UserService#getEmployeeList(com.bcs.zsg.component.security.vo.UserVO)
	 */
	@Override
	public List<EmployeeVO> getEmployeeList(UserVO userVO) throws BusinessException {
		return userDAO.getEmployeeList(userVO);
	}

	/* (non-Javadoc)
	 * @see com.bcs.zsg.cfg.sec.service.UserService#getEmployeeViewList(java.lang.Long)
	 */
	@Override
	public List<EmployeeViewVO> getEmployeeViewList(Long companyId, String str) throws BusinessException {
		return userDAO.getEmployeeViewList(companyId, str);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.cfg.sec.service.UserService#getEmployeeView(java.lang.Long)
	 */
	@Override
	public EmployeeViewVO getEmployeeView(Long idEmployee) throws BusinessException {
		return userDAO.getEmployeeView(idEmployee);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.cfg.sec.service.UserService#getEmployeeViewWithUser(java.lang.Long)
	 */
	@Override
	public EmployeeViewVO getEmployeeViewWithUser(Long idEmployee) throws BusinessException {
		return userDAO.getEmployeeViewWithUser(idEmployee);
	}
	
	@Override
	public UserVO getUserVOByLoginId(String loginId) throws BusinessException {
		return userDAO.getUserVOByLoginId(loginId);
	}
	
	@Override
	public void processReset(PasswordResetVO vo) throws BusinessException {
		try {
			//UserVO userVO = userDAO.getUserVOByEmail(vo.getEmailAddress());
			UserVO userVO = userDAO.getUserVOByLoginId(vo.getLoginId());
			
			if (userVO != null) {	
				// Find out any record request for forgot email
				PasswordResetVO passwordResetVO = passwordResetDAO.getPasswordResetVOByLoginId(vo.getLoginId());
				
				if (passwordResetVO != null) {
					//Delete old entries if exists
					passwordResetDAO.delete(passwordResetVO);
				}
				
				vo.setLoginId(userVO.getLoginId());
				vo.setEmailAddress(userVO.getEmailAddress());
				
				String randomPassword = salt + RandomStringUtils.randomAscii(70 - salt.length());
				vo.setEncodedPassword(passwordEncoder.encodePassword(userVO.getLoginId(), randomPassword));
				vo.setStatusCode(BaseConstant.STATUS_ACTIVE);
				passwordResetDAO.insert(vo);
				
				emailingBO.sendForgotPasswordEmail(vo, userVO);
			}
		} catch ( Exception e) {
			throw new BusinessException(e);
		}
	}
}
