package com.bcs.zsg.cfg.sec.dao;

import java.util.List;
import java.util.Map;

import com.bcs.zsg.cfg.sec.vo.EmployeeVO;
import com.bcs.zsg.cfg.sec.vo.EmployeeViewVO;
import com.bcs.zsg.component.security.vo.UserVO;
import com.bcs.zsg.core.dao.BaseDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.vo.CompanyVO;

public interface UserDAO extends BaseDAO {

	/**
	 * 
	 * @param userVO
	 * @throws BusinessException
	 */
	public void resetInvalidLogin(UserVO userVO) throws BusinessException;
	
	/**
	 * 
	 * @param userVO
	 * @throws BusinessException
	 */
	public void updateUser(UserVO userVO) throws BusinessException;
	
	
	public void updateUserPassword(UserVO userVO) throws BusinessException;
	
	/**
	 * 
	 * @param userVO
	 * @throws BusinessException
	 */
	public void deleteUser(UserVO userVO) throws BusinessException;
	
	/**
	 * 
	 * @throws BusinessException
	 */
	public List<CompanyVO> getCompanyList() throws BusinessException;
	
	
	/**
	 * @param userVO
	 * @throws BusinessException
	 */
	public List<EmployeeVO> getEmployeeList(UserVO userVO) throws BusinessException;
	
	/**
	 * 
	 * @param userUUID
	 * @param department
	 * @throws BusinessException
	 */
	public void updateEmployee(String userUUID, String department) throws BusinessException;
	
	public void updateEmployee(String userUUID, String department, Long idCustomer, String customerName, String roleType) throws BusinessException;
	
	public void updateEmployee(String userUUID, EmployeeVO employeeVO) throws BusinessException;
	
	public void updateUserProfile(UserVO userVO, EmployeeVO employeeVO, Map<String, Object> params) throws BusinessException;
	
	/**
	 * 
	 * @param str 
	 * @throws BusinessException
	 */
	public List<EmployeeViewVO> getEmployeeViewList(Long companyId, String str) throws BusinessException;

	/**
	 * 
	 * @param appId
	 * @param statusCode
	 * @throws BusinessException
	 */
	public List<UserVO> getUserList(String appId, String statusCode) throws BusinessException;

	/**
	 * 
	 * @param idEmployee
	 * @return
	 * @throws BusinessException
	 */
	public EmployeeViewVO getEmployeeView(Long idEmployee) throws BusinessException;
	
	/**
	 * 
	 * @param idEmployee
	 * @return
	 * @throws BusinessException
	 */
	public EmployeeViewVO getEmployeeViewWithUser(Long idEmployee) throws BusinessException;
	
	public UserVO getUserVOByLoginId(String loginId) throws BusinessException;
	
	public UserVO getUserVOByEmail(String email) throws BusinessException;
}
