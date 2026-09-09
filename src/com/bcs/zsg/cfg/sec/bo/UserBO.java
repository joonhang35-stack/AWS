package com.bcs.zsg.cfg.sec.bo;

import java.util.List;
import java.util.Map;

import com.bcs.zsg.cfg.sec.vo.EmployeeVO;
import com.bcs.zsg.cfg.sec.vo.EmployeeViewVO;
import com.bcs.zsg.component.security.vo.UserVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.vo.CompanyVO;

public interface UserBO {

	/**
	 * 
	 * @param userVO
	 * @throws BusinessException
	 */
	public void resetInvalidLogin(UserVO userVO) throws BusinessException;
	
	/**
	 * 
	 * @param userVO
	 * @param oldLoginId
	 * @throws BusinessException
	 */
	public void updateUser(UserVO userVO, String oldLoginId) throws BusinessException;
	
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
	 * 
	 * @param addEmployeeList
	 * @param updEmployeeList
	 * @param delEmployeeList
	 * @param userUUID
	 * @param department
	 * @throws BusinessException
	 */
	public void addEmployee(List<EmployeeVO> addEmployeeList, List<EmployeeVO> updEmployeeList, List<EmployeeVO> delEmployeeList, String userUUID, String department) throws BusinessException;

	public void addEmployee(List<EmployeeVO> addEmployeeList, List<EmployeeVO> updEmployeeList, List<EmployeeVO> delEmployeeList, String userUUID, String department, Long idCustomer, String customerName) throws BusinessException;
	
	public void updEmployee(List<EmployeeVO> addEmployeeList, List<EmployeeVO> updEmployeeList, List<EmployeeVO> delEmployeeList, String userUUID, EmployeeVO employeeVO) throws BusinessException;
	
	public void insertEmployee(EmployeeVO employeeVO) throws BusinessException;
	
	public void updateUserProfile(UserVO userVO, EmployeeVO employeeVO, Map<String, Object> params) throws BusinessException;
	
	
	/**
	 * @param userVO
	 * @throws BusinessException
	 */
	public List<EmployeeVO> getEmployeeList(UserVO userVO) throws BusinessException;
	
	/**
	 *
	 * @throws BusinessException
	 */
	public List<EmployeeViewVO> getEmployeeViewList(Long companyId) throws BusinessException;

	/**
	 * 
	 * @param companyId
	 * @param str
	 * @return
	 * @throws BusinessException
	 */
	public List<EmployeeViewVO> getEmployeeViewList(Long companyId, String str) throws BusinessException;

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
}
