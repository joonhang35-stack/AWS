package com.bcs.zsg.cfg.sec.bo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.cfg.sec.service.UserService;
import com.bcs.zsg.cfg.sec.vo.EmployeeVO;
import com.bcs.zsg.cfg.sec.vo.EmployeeViewVO;
import com.bcs.zsg.component.security.vo.UserVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.vo.CompanyVO;

public class UserBOImpl implements UserBO {
	
	@Autowired
	private UserService userService;
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.cfg.sec.bo.resetInvalidLogin#resetInvalidLogin(com.bcs.zsg.component.security.vo.UserVO)
	 */
	@Override
	public void resetInvalidLogin(UserVO userVO) throws BusinessException {
		userService.resetInvalidLogin(userVO);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.cfg.sec.bo.updateUser#updateUser(com.bcs.zsg.component.security.vo.UserVO, java.lang.String)
	 */
	@Override
	public void updateUser(UserVO userVO, String oldLoginId) throws BusinessException {
		userService.updateUser(userVO, oldLoginId);
	}
	
	@Override
	public void updateUserPassword(UserVO userVO) throws BusinessException {
		userService.updateUserPassword(userVO);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.cfg.sec.bo.deleteUser#deleteUser(com.bcs.zsg.component.security.vo.UserVO)
	 */
	@Override
	public void deleteUser(UserVO userVO) throws BusinessException {
		userService.deleteUser(userVO);
	}

	/* (non-Javadoc)
	 * @see com.bcs.zsg.cfg.sec.bo.UserBO#getCompanyList()
	 */
	@Override
	public List<CompanyVO> getCompanyList() throws BusinessException {
		return userService.getCompanyList();
	}

	/* (non-Javadoc)
	 * @see com.bcs.zsg.cfg.sec.bo.UserBO#addEmployee(java.util.List, java.util.List, java.util.List, java.util.String, java.util.String)
	 */
	@Override
	public void addEmployee(List<EmployeeVO> addEmployeeList, List<EmployeeVO> updEmployeeList, List<EmployeeVO> delEmployeeList, String userUUID, String department) throws BusinessException {
		userService.addEmployee(addEmployeeList, updEmployeeList, delEmployeeList, userUUID, department);
	}
	
	@Override
	public void addEmployee(List<EmployeeVO> addEmployeeList, List<EmployeeVO> updEmployeeList, List<EmployeeVO> delEmployeeList, String userUUID, String department, Long idCustomer, String customerName) throws BusinessException {
		userService.addEmployee(addEmployeeList, updEmployeeList, delEmployeeList, userUUID, department, idCustomer, customerName);
	}
	
	@Override
	public void updEmployee(List<EmployeeVO> addEmployeeList, List<EmployeeVO> updEmployeeList, List<EmployeeVO> delEmployeeList, String userUUID, EmployeeVO employeeVO) throws BusinessException {
		userService.updEmployee(addEmployeeList, updEmployeeList, delEmployeeList, userUUID, employeeVO);
	}
	
	@Override
	public void insertEmployee(EmployeeVO employeeVO) throws BusinessException {
		userService.insertEmployee(employeeVO);
	}
	
	@Override
	public void updateUserProfile(UserVO userVO, EmployeeVO employeeVO, Map<String, Object> params) throws BusinessException {
		userService.updateUserProfile(userVO, employeeVO, params);
	}

	/* (non-Javadoc)
	 * @see com.bcs.zsg.cfg.sec.bo.UserBO#getEmployeeList(com.bcs.zsg.component.security.vo.UserVO)
	 */
	@Override
	public List<EmployeeVO> getEmployeeList(UserVO userVO) throws BusinessException {
		return userService.getEmployeeList(userVO);
	}

	/* (non-Javadoc)
	 * @see com.bcs.zsg.cfg.sec.bo.UserBO#getEmployeeViewList(java.lang.Long)
	 */
	@Override
	public List<EmployeeViewVO> getEmployeeViewList(Long companyId) throws BusinessException {
		return getEmployeeViewList(companyId, null);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.cfg.sec.bo.UserBO#getEmployeeViewList(java.lang.Long, java.lang.String)
	 */
	@Override
	public List<EmployeeViewVO> getEmployeeViewList(Long companyId, String str) throws BusinessException {
		return userService.getEmployeeViewList(companyId, str);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.cfg.sec.bo.UserBO#getEmployeeView(java.lang.Long)
	 */
	@Override
	public EmployeeViewVO getEmployeeView(Long idEmployee) throws BusinessException {
		return userService.getEmployeeView(idEmployee);
	}
	
	@Override
	public EmployeeViewVO getEmployeeViewWithUser(Long idEmployee) throws BusinessException {
		return userService.getEmployeeViewWithUser(idEmployee);
	}

	@Override
	public UserVO getUserVOByLoginId(String loginId) throws BusinessException {
		return userService.getUserVOByLoginId(loginId);
	}
	
}
