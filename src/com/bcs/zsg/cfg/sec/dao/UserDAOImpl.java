package com.bcs.zsg.cfg.sec.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;

import com.bcs.zsg.cfg.sec.vo.EmployeeVO;
import com.bcs.zsg.cfg.sec.vo.EmployeeViewVO;
import com.bcs.zsg.component.security.vo.UserVO;
import com.bcs.zsg.core.dao.BaseHibernateDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.BaseContext;
import com.bcs.zsg.maintenance.vo.CompanyVO;

public class UserDAOImpl extends BaseHibernateDAO implements UserDAO {

	@Autowired
	protected PasswordEncoder passwordEncoder;
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.cfg.sec.dao.resetInvalidLogin#resetInvalidLogin(com.bcs.zsg.component.security.vo.UserVO)
	 */
	@Override
	public void resetInvalidLogin(UserVO userVO) throws BusinessException {
		Query query = createQuery("update UserVO" 
				+ " set loginInvalidCount = :loginInvalidCount, updatedBy = :updatedBy, updatedDate = :updatedDate"
				+ " where uuid = :uuid");
		
		query.setParameter("uuid", userVO.getUuid());
		query.setParameter("loginInvalidCount", 0);
		query.setParameter("updatedBy", getUserInfo());
		query.setParameter("updatedDate", new Date());
		
		query.executeUpdate();
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.cfg.sec.dao.updateUser#updateUser(com.bcs.zsg.component.security.vo.UserVO)
	 */
	@Override
	public void updateUser(UserVO userVO) throws BusinessException {
		if (!StringUtils.isEmpty(userVO.getLoginPassword())) {
			String hashedPassword = passwordEncoder.encodePassword(userVO.getLoginPassword(), userVO.getLoginId());
			userVO.setLoginPassword(hashedPassword);
			userVO.setLoginInvalidCount(0);
		}
		
		StringBuilder sql = new StringBuilder();
		sql.append("update UserVO");
		sql.append(" set ");
		sql.append("name = :name, ");
		sql.append("loginId = :loginId, ");
		sql.append("statusCode = :statusCd, ");
		sql.append("emailAddress = :emailAddress, ");
		sql.append("mobileNumber = :mobileNumber, ");
		if (!StringUtils.isEmpty(userVO.getLoginPassword())) {
			sql.append("loginPassword = :loginPassword, ");
			sql.append("lastPasswordChangeDate = :lastPasswordChangeDate, ");
			sql.append("loginInvalidCount = :loginInvalidCount, ");
		}
		sql.append("updatedBy = :updatedBy, ");
		sql.append("updatedDate = :updatedDate");
		sql.append(" where uuid = :uuid");
		
		Query query = createQuery(sql.toString());
		query.setParameter("uuid", userVO.getUuid());
		query.setParameter("name", userVO.getName());
		query.setParameter("loginId", userVO.getLoginId());
		query.setParameter("statusCd", userVO.getStatusCode());
		query.setParameter("emailAddress", userVO.getEmailAddress());
		query.setParameter("mobileNumber", userVO.getMobileNumber());
		if (!StringUtils.isEmpty(userVO.getLoginPassword())) {
			query.setParameter("loginPassword", userVO.getLoginPassword());
			query.setParameter("lastPasswordChangeDate", new Date());
			query.setParameter("loginInvalidCount", 0);
		}
		query.setParameter("updatedBy", getUserInfo());
		query.setParameter("updatedDate", new Date());
		
		query.executeUpdate();
	}
	
	@Override
	public void updateUserPassword(UserVO userVO) throws BusinessException {
		if (!StringUtils.isEmpty(userVO.getLoginPassword())) {
			String hashedPassword = passwordEncoder.encodePassword(userVO.getLoginPassword(), userVO.getLoginId());
			userVO.setLoginPassword(hashedPassword);
			userVO.setLoginInvalidCount(0);
		}
		
		StringBuilder sql = new StringBuilder();
		sql.append("update UserVO");
		sql.append(" set ");
		sql.append("statusCode = :statusCd, ");
		if (!StringUtils.isEmpty(userVO.getLoginPassword())) {
			sql.append("loginPassword = :loginPassword, ");
			sql.append("lastPasswordChangeDate = :lastPasswordChangeDate, ");
			sql.append("loginInvalidCount = :loginInvalidCount, ");
		}
		sql.append("updatedBy = :updatedBy, ");
		sql.append("updatedDate = :updatedDate");
		sql.append(" where uuid = :uuid");
		
		Query query = createQuery(sql.toString());
		query.setParameter("uuid", userVO.getUuid());
		query.setParameter("statusCd", userVO.getStatusCode());
		if (!StringUtils.isEmpty(userVO.getLoginPassword())) {
			query.setParameter("loginPassword", userVO.getLoginPassword());
			query.setParameter("lastPasswordChangeDate", new Date());
			query.setParameter("loginInvalidCount", 0);
		}
		query.setParameter("updatedBy", "SYSTEM");
		query.setParameter("updatedDate", new Date());
		
		query.executeUpdate();
	}
	
	private String getUserInfo() {
		return StringUtils.isBlank(BaseContext.getUserFullName()) ? BaseContext.getLoginId() : BaseContext.getUserFullName();
	}

	/* (non-Javadoc)
	 * @see com.bcs.zsg.cfg.sec.dao.UserDAO#getCompanyList()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CompanyVO> getCompanyList() throws BusinessException {
		Criteria criteria = createCriteria(CompanyVO.class);
		return criteria.list();
	}

	/* (non-Javadoc)
	 * @see com.bcs.zsg.cfg.sec.dao.UserDAO#getEmployeeList(com.bcs.zsg.component.security.vo.UserVO)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeeVO> getEmployeeList(UserVO userVO) throws BusinessException {
		Criteria criteria = createCriteria(EmployeeVO.class);
		criteria.add(Restrictions.eq("secUser", userVO.getUuid()));
		return criteria.list();
	}

	/* (non-Javadoc)
	 * @see com.bcs.zsg.cfg.sec.dao.UserDAO#updateEmployee(java.lang.String, java.lang.String)
	 */
	@Override
	public void updateEmployee(String userUUID, String department) throws BusinessException {
		StringBuilder sql = new StringBuilder();
		sql.append("update EmployeeVO");
		sql.append(" set ");
		sql.append("department = :department ");
		sql.append(" where u_sec_user = :uuid");
		
		Query query = createQuery(sql.toString());
		query.setParameter("uuid", userUUID);
		query.setParameter("department", department);
		
		query.executeUpdate();
	}
	
	@Override
	public void updateEmployee(String userUUID, String department, Long idCustomer, String customerName, String roleType) throws BusinessException {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE EmployeeVO");
		sql.append(" SET ");
		sql.append("department = :department ");
		if (idCustomer != null) sql.append(", id_customer = :idCustomer "); else sql.append(", id_customer = NULL ");
		if (customerName != null) sql.append(", customer_name = :customerName "); else sql.append(", customer_name = NULL ");
		if (roleType != null) sql.append(", role_type = :roleType ");
		sql.append(" WHERE u_sec_user = :uuid");
		
		Query query = createQuery(sql.toString());
		query.setParameter("uuid", userUUID);
		query.setParameter("department", department);
		if (idCustomer != null) query.setParameter("idCustomer", idCustomer);
		if (customerName != null) query.setParameter("customerName", customerName);
		if (roleType != null) query.setParameter("roleType", roleType);
		
		query.executeUpdate();
	}
	
	@Override
	public void updateEmployee(String userUUID, EmployeeVO employeeVO) throws BusinessException {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE EmployeeVO");
		sql.append(" SET ");
		sql.append("department = :department ");
		if (employeeVO.getCustomerId() != null) sql.append(", id_customer = :idCustomer "); else sql.append(", id_customer = NULL ");
		if (employeeVO.getCustomerName() != null) sql.append(", customer_name = :customerName "); else sql.append(", customer_name = NULL ");
		if (employeeVO.getRoleType() != null) sql.append(", role_type = :roleType ");
		
		if (employeeVO.getCategory() != null) sql.append(", category = :category ");
		if (employeeVO.getFullName() != null) sql.append(", fullName = :fullName ");
		if (employeeVO.getIdNo() != null) sql.append(", id_no = :idNo ");
		if (employeeVO.getTaxIdNo() != null) sql.append(", tax_id_no = :taxIdNo ");
		if (employeeVO.getNickName() != null) sql.append(", nick_name = :nickName ");
		if (employeeVO.getMsicCode() != null) sql.append(", msic_code = :msicCode ");
		if (employeeVO.getCountryId() != null) sql.append(", id_country = :idCountry ");
		if (employeeVO.getAddr1() != null) sql.append(", addr_1 = :addr1 ");
		if (employeeVO.getAddr2() != null) sql.append(", addr_2 = :addr2 ");
		if (employeeVO.getAddr3() != null) sql.append(", addr_3 = :addr3 ");
		if (employeeVO.getCity() != null) sql.append(", city = :city ");
		if (employeeVO.getState() != null) sql.append(", state = :state ");
		if (employeeVO.getPostcode() != null) sql.append(", postcode = :postcode ");
		if (employeeVO.getAcctNo() != null) sql.append(", acct_no = :acctNo ");
		if (employeeVO.getBankName() != null) sql.append(", bank_name = :bankName ");
		if (employeeVO.getIsAccessSystem() != null) sql.append(", is_access_sys = :isAccessSystem ");
		if (employeeVO.getIsEditable() != null) sql.append(", is_editable = :isEditable ");
		
		sql.append(" WHERE u_sec_user = :uuid");
		
		Query query = createQuery(sql.toString());
		query.setParameter("uuid", userUUID);
		query.setParameter("department", employeeVO.getDepartment());
		if (employeeVO.getCustomerId() != null) query.setParameter("idCustomer", employeeVO.getCustomerId());
		if (employeeVO.getCustomerName() != null) query.setParameter("customerName", employeeVO.getCustomerName());
		if (employeeVO.getRoleType() != null) query.setParameter("roleType", employeeVO.getRoleType());
		
		if (employeeVO.getCategory() != null) query.setParameter("category", employeeVO.getCategory());
		if (employeeVO.getFullName() != null) query.setParameter("fullName", employeeVO.getFullName());
		if (employeeVO.getIdNo() != null) query.setParameter("idNo", employeeVO.getIdNo());
		if (employeeVO.getTaxIdNo() != null) query.setParameter("taxIdNo", employeeVO.getTaxIdNo());
		if (employeeVO.getNickName() != null) query.setParameter("nickName", employeeVO.getNickName());
		if (employeeVO.getMsicCode() != null) query.setParameter("msicCode", employeeVO.getMsicCode());
		if (employeeVO.getCountryId() != null) query.setParameter("idCountry", employeeVO.getCountryId());
		if (employeeVO.getAddr1() != null) query.setParameter("addr1", employeeVO.getAddr1());
		if (employeeVO.getAddr2() != null) query.setParameter("addr2", employeeVO.getAddr2());
		if (employeeVO.getAddr3() != null) query.setParameter("addr3", employeeVO.getAddr3());
		if (employeeVO.getCity() != null) query.setParameter("city", employeeVO.getCity());
		if (employeeVO.getState() != null) query.setParameter("state", employeeVO.getState());
		if (employeeVO.getPostcode() != null) query.setParameter("postcode", employeeVO.getPostcode());
		if (employeeVO.getAcctNo() != null) query.setParameter("acctNo", employeeVO.getAcctNo());
		if (employeeVO.getBankName() != null) query.setParameter("bankName", employeeVO.getBankName());
		if (employeeVO.getIsAccessSystem() != null) query.setParameter("isAccessSystem", employeeVO.getIsAccessSystem());
		if (employeeVO.getIsEditable() != null) query.setParameter("isEditable", employeeVO.getIsEditable());
		
		query.executeUpdate();
	}
	
	@Override
	public void updateUserProfile(UserVO userVO, EmployeeVO employeeVO, Map<String, Object> params) throws BusinessException {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE UserVO");
		sql.append(" SET ");
		sql.append("updatedBy = :updatedBy, ");
		sql.append("updatedDate = :updatedDate");
		
		if (userVO.getMobileNumber() != null) sql.append(", mobile_no = :mobileNumber ");
		sql.append(" WHERE uuid = :uuid");
		
		Query query = createQuery(sql.toString());
		query.setParameter("uuid", userVO.getUuid());
		query.setParameter("updatedBy", userVO.getName());
		query.setParameter("updatedDate", new Date());
		if (userVO.getMobileNumber() != null) query.setParameter("mobileNumber", userVO.getMobileNumber());
		
		query.executeUpdate();
		
		sql = new StringBuilder();
		sql.append("UPDATE EmployeeVO");
		sql.append(" SET ");
		sql.append("updatedBy = :updatedBy, ");
		sql.append("updatedDate = :updatedDate");
		
		if (employeeVO.getFullName() != null) sql.append(", fullName = :fullName ");
		if (employeeVO.getIdNo() != null && params.get("idNo") != null) sql.append(", id_no = :idNo ");
		if (employeeVO.getTaxIdNo() != null && params.get("taxIdNo") != null) sql.append(", tax_id_no = :taxIdNo ");
		if (employeeVO.getNickName() != null) sql.append(", nick_name = :nickName ");
		if (employeeVO.getMsicCode() != null) sql.append(", msic_code = :msicCode ");
		if (employeeVO.getCountryId() != null) sql.append(", id_country = :idCountry ");
		if (employeeVO.getAddr1() != null) sql.append(", addr_1 = :addr1 ");
		if (employeeVO.getAddr2() != null) sql.append(", addr_2 = :addr2 ");
		if (employeeVO.getAddr3() != null) sql.append(", addr_3 = :addr3 ");
		if (employeeVO.getCity() != null) sql.append(", city = :city ");
		if (employeeVO.getState() != null) sql.append(", state = :state ");
		if (employeeVO.getPostcode() != null) sql.append(", postcode = :postcode ");
		if (employeeVO.getAcctNo() != null) sql.append(", acct_no = :acctNo ");
		if (employeeVO.getBankName() != null) sql.append(", bank_name = :bankName ");
		if (employeeVO.getIsEditable() != null) sql.append(", is_editable = :isEditable ");
		
		sql.append(" WHERE u_sec_user = :uuid");
		
		query = createQuery(sql.toString());
		query.setParameter("uuid", userVO.getUuid());
		query.setParameter("updatedBy", userVO.getName());
		query.setParameter("updatedDate", new Date());
		if (employeeVO.getFullName() != null) query.setParameter("fullName", employeeVO.getFullName());
		if (employeeVO.getIdNo() != null && params.get("idNo") != null) query.setParameter("idNo", employeeVO.getIdNo());
		if (employeeVO.getTaxIdNo() != null && params.get("taxIdNo") != null) query.setParameter("taxIdNo", employeeVO.getTaxIdNo());
		if (employeeVO.getNickName() != null) query.setParameter("nickName", employeeVO.getNickName());
		if (employeeVO.getMsicCode() != null) query.setParameter("msicCode", employeeVO.getMsicCode());
		if (employeeVO.getCountryId() != null) query.setParameter("idCountry", employeeVO.getCountryId());
		if (employeeVO.getAddr1() != null) query.setParameter("addr1", employeeVO.getAddr1());
		if (employeeVO.getAddr2() != null) query.setParameter("addr2", employeeVO.getAddr2());
		if (employeeVO.getAddr3() != null) query.setParameter("addr3", employeeVO.getAddr3());
		if (employeeVO.getCity() != null) query.setParameter("city", employeeVO.getCity());
		if (employeeVO.getState() != null) query.setParameter("state", employeeVO.getState());
		if (employeeVO.getPostcode() != null) query.setParameter("postcode", employeeVO.getPostcode());
		if (employeeVO.getAcctNo() != null) query.setParameter("acctNo", employeeVO.getAcctNo());
		if (employeeVO.getBankName() != null) query.setParameter("bankName", employeeVO.getBankName());
		if (employeeVO.getIsEditable() != null) query.setParameter("isEditable", employeeVO.getIsEditable());
		
		query.executeUpdate();
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.cfg.sec.bo.deleteUser#deleteUser(com.bcs.zsg.component.security.vo.UserVO)
	 */
	@Override
	public void deleteUser(UserVO userVO) throws BusinessException {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from EmployeeVO ");
		sql.append("where u_sec_user = :uuid");
		
		Query query = createQuery(sql.toString());
		query.setParameter("uuid", userVO.getUuid());
		
		query.executeUpdate();
	}

	/* (non-Javadoc)
	 * @see com.bcs.zsg.cfg.sec.dao.UserDAO#getEmployeeViewList(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeeViewVO> getEmployeeViewList(Long companyId, String str) throws BusinessException {
		StringBuilder sb = new StringBuilder();
		sb.append("FROM EmployeeViewVO WHERE ");
		sb.append("companyId = " + companyId + " ");
		sb.append("AND userVO.uuid != 'superman' AND userVO.statusCode = 'A' ");
		if (str != null) sb.append("AND department like '%" + str + "%' ");
		sb.append("ORDER BY userVO.name");
		Query query = createQuery(sb.toString());
		return query.list();
	}
	
	/* (non-Javadoc)
	 * @see com.bcs.zsg.cfg.sec.dao.UserDAO#getUserList(java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<UserVO> getUserList(String appId, String statusCode) {
		Criteria criteria = createCriteria(UserVO.class);
		
		if (StringUtils.isNotBlank(statusCode))
			criteria.add(Restrictions.eq("statusCode", statusCode));
		
		if (StringUtils.isNotBlank(appId))
			criteria.add(Restrictions.eq("appId", appId));
		
		return criteria.list();
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.cfg.sec.dao.UserDAO#getEmployeeView(java.lang.Long)
	 */
	@Override
	public EmployeeViewVO getEmployeeView(Long idEmployee) throws BusinessException {
		Criteria criteria = createCriteria(EmployeeViewVO.class);
		criteria.add(Restrictions.eq("id", idEmployee));
		return (EmployeeViewVO) criteria.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public EmployeeViewVO getEmployeeViewWithUser(Long idEmployee) throws BusinessException {
		StringBuilder sb = new StringBuilder();
		sb.append("select e.id as idEmployee, e.u_sec_user, e.department, e.is_default_comp, e.role_type, ");
		sb.append("u.id as idUser, u.user_name, u.mobile_no ");
		sb.append("FROM employee e LEFT JOIN sec_user u ON e.u_sec_user = u.uuid ");
		sb.append("WHERE e.u_sec_user != 'superman' AND e.id = :idEmployee ");
		
		Query query = createSQLQuery(sb.toString());
		query.setParameter("idEmployee", idEmployee);
		
		List<Object> results = query.list();
		List<EmployeeViewVO> employeeList = new ArrayList<EmployeeViewVO>();

		for (Iterator<Object> it = results.iterator(); it.hasNext();) {
			Object[] row = (Object[]) it.next();
			EmployeeViewVO vo = new EmployeeViewVO();
			vo.setId(((BigInteger) row[0]).longValue());
			vo.setSecUser((String) row[1]);
			vo.setDepartment((String) row[2]);
			vo.setIsDefaultComp((Boolean) row[3]);
			vo.setRoleType((String) row[4]);
			
			vo.setUserVO(new UserVO());
			vo.getUserVO().setId(((BigInteger) row[5]).longValue());
			vo.getUserVO().setName((String) row[6]);
			vo.getUserVO().setMobileNumber((String) row[7]);
			employeeList.add(vo);
		}
		return employeeList.size() > 0 ? employeeList.get(0) : new EmployeeViewVO();
	}
	
	@Override
	public UserVO getUserVOByLoginId(String loginId) throws BusinessException {
		Criteria criteria = createCriteria(UserVO.class);
		criteria.add(Restrictions.eq("loginId", loginId));
		return (UserVO) criteria.uniqueResult();
	}
	
	@Override
	public UserVO getUserVOByEmail(String email) throws BusinessException {
		Criteria criteria = createCriteria(UserVO.class);
		criteria.add(Restrictions.eq("emailAddress", email));
		return (UserVO) criteria.uniqueResult();
	}
}
