package com.bcs.zsg.sales.dao;

import java.util.List;
import java.util.Map;

import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.core.dao.BaseDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.purchase.vo.AddressVO;
import com.bcs.zsg.purchase.vo.ContactVO;
import com.bcs.zsg.purchase.vo.CountryVO;
import com.bcs.zsg.purchase.vo.IdentityVO;
import com.bcs.zsg.purchase.vo.PersonVO;
import com.bcs.zsg.sales.vo.CustDetailsVO;
import com.bcs.zsg.sales.vo.CustTourHistVO;
import com.bcs.zsg.sales.vo.CustomerVO;
import com.bcs.zsg.sales.vo.IdentityDetailVO;
import com.bcs.zsg.sales.vo.OnlineCustomerProfileUpdateVO;
import com.bcs.zsg.sales.vo.PersonContactVO;

public interface CustomerDAO extends BaseDAO{

	/**
	 * 
	 * @throws BusinessException
	 */
	public List<CustomerVO> getCustomerList(Long companyId) throws BusinessException;

	/**
	 * 
	 * @param idCust
	 * @return
	 * @throws BusinessException
	 */
	public CustomerVO getCustomer(Long idCust) throws BusinessException;
	
	/**
	 * 
	 * @throws BusinessException
	 */
	public CustomerVO getCustomerDetail(Long idCust) throws BusinessException;

	/**
	 * 
	 * @param idCust
	 * @return
	 * @throws BusinessException
	 */
	public CustomerVO getCustomerSimpleDetail(Long idCust) throws BusinessException;
	
	/**
	 * 
	 * @throws BusinessException
	 */
	public void deleteCustomer(CustomerVO customerVO) throws BusinessException;
	
	public void updateCustomerStatus(CustomerVO customerVO) throws BusinessException;
	
	/**
	 * 
	 * @return
	 */
	public List<CountryVO> getCountryList();

	/**
	 * 
	 * @param idCust
	 * @return
	 * @throws BusinessException
	 */
	public CustomerVO getCustomerInfo(Long idCust) throws BusinessException;
	
	/**
	 * 
	 * @throws BusinessException
	 */
	public boolean isIdentityExisted(IdentityVO identityVO, Long companyId) throws BusinessException;
	
	/**
	 * 
	 * @param companyId
	 * @return
	 * @throws BusinessException
	 */
	public List<CustDetailsVO> getCustomerListSearch(Long companyId) throws BusinessException ;

	/**
	 * 
	 * @param customerVO
	 * @return
	 * @throws BusinessException
	 */
	public boolean isCustNotAbleToDel(CustomerVO customerVO) throws BusinessException;

	/**
	 * 
	 * @param idCompany
	 * @param filters
	 * @return
	 * @throws BusinessException
	 */
	public int getCustomerListSize(Map<String, Object> params) throws BusinessException;

	/**
	 * 
	 * @param idCompany
	 * @param first
	 * @param pageSize
	 * @param sortField
	 * @param sortOrder
	 * @param filters
	 * @return
	 * @throws BusinessException
	 */
	public List<CustomerVO> getCustomerList(Map<String, Object> params) throws BusinessException;

	/**
	 * 
	 * @param vo
	 * @param companyId
	 * @return
	 * @throws BusinessException
	 */
	public List<CustTourHistVO> getTourHistList(CustomerVO vo, Long companyId, Map<String, Object> params) throws BusinessException;

	/**
	 * 
	 * @param table
	 * @param personId
	 * @throws BusinessException
	 */
	public void deletePersonSubInfo(String table, Long personId) throws BusinessException;
	
	/**
	 * 
	 * @param table
	 * @param companyId
	 * @throws BusinessException
	 */
	public void deleteCorporateSubInfo(String table, Long corpId) throws BusinessException;

	/**
	 * 
	 * @param table
	 * @param personId
	 * @throws BusinessException
	 */
	public void terminatePersonSubInfo(String table, Long personId) throws BusinessException;

	/**
	 * 
	 * @param table
	 * @param corId
	 * @throws BusinessException
	 */
	public void terminateCorporateSubInfo(String table, Long corId) throws BusinessException;
	
	public void terminateInvoicePaxSubInfo(String table, Long invPaxId) throws BusinessException;

	/**
	 * 
	 * @param idIdentity
	 * @return
	 * @throws BusinessException
	 */
	public IdentityDetailVO getIdentityDetail(Long idIdentity) throws BusinessException;

	/**
	 * 
	 * @param custDetailsVO
	 * @param idCompany 
	 * @param filterTypeCd
	 * @return
	 * @throws BusinessException
	 */
	public int isCustomerExisted(CustDetailsVO custDetailsVO, Long idCompany, Boolean filterTypeCd) throws BusinessException;

	/**
	 * 
	 * @param custDetailsVO
	 * @param idCompany 
	 * @param filterTypeCd
	 * @return
	 * @throws BusinessException
	 */
	public boolean isEmailExisted(CustDetailsVO custDetailsVO, Long idCompany) throws BusinessException;

	public boolean isPhoneExisted(String phoneNo, Long countryId, String typeCd, Long personId, Long idCompany) throws BusinessException;
	
	/**
	 * 
	 * @param personId
	 * @return
	 * @throws BusinessException
	 */
	public PersonVO getPersonById(Long personId) throws BusinessException;
	
	public List<CustomerVO> getCustomerList(Long idCompany, SearchParamVO searchParamVO) throws BusinessException;

	public void unlockCustomer(Long idCust, boolean isLock) throws BusinessException;
	
	public CountryVO getCountryById(Long countryId) throws BusinessException;

	public void insertCustomerHistory(Long idCust, String actionCd, String reason) throws BusinessException;
	
	public void updateCrmId(Long customerId, String crmId) throws BusinessException;
	
	public void updateCrmIdCSV(Long customerId, String crmId) throws BusinessException;

	public <T> List<T> getOnlineCustomerUpdItemList(Class<T> entityClass, Long idOnlineCustProfileUpd);

	public OnlineCustomerProfileUpdateVO getOnlineCustomerProfileUpdateVO(Long idOnlineCustProfileUpd);

	public PersonContactVO getContactVO(Long idPerson, String typeCd);

	public AddressVO getAddressVO(Long idPerson, String typeCd);

	public List<IdentityVO> getIdentityList(Long idPerson, String typeCd) throws BusinessException;
}
