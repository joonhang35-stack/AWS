package com.bcs.zsg.purchase.dao;

import java.util.List;
import java.util.Map;

import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.core.dao.BaseDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.vo.LookupItemVO;
import com.bcs.zsg.purchase.vo.AddressVO;
import com.bcs.zsg.purchase.vo.ContactVO;
import com.bcs.zsg.purchase.vo.CorAddressVO;
import com.bcs.zsg.purchase.vo.CorContactVO;
import com.bcs.zsg.purchase.vo.CorporateVO;
import com.bcs.zsg.purchase.vo.CountryVO;
import com.bcs.zsg.purchase.vo.IdentityVO;
import com.bcs.zsg.purchase.vo.PersonVO;
import com.bcs.zsg.purchase.vo.RemarksVO;
import com.bcs.zsg.purchase.vo.SuppPersonInChargeVO;
import com.bcs.zsg.purchase.vo.SupplierVO;


public interface PurchaseDAO extends BaseDAO{

	/**
	 * 
	 * @return
	 */
	public List<SupplierVO> getSupplierList();
	
	/**
	 * 
	 * @return
	 */
	public List<IdentityVO> getIdentityList();
	
	/**
	 * 
	 * @return
	 */
	public List<LookupItemVO> getPymtTermList(String pymtTerm);
	
	/**
	 * 
	 * @return
	 */
	public List<CountryVO> getCountryList();
	
	/**
	 * 
	 * @return
	 */
	public PersonVO getPerson(Long personId);
	
	/**
	 * 
	 * @return
	 */
	public List<ContactVO> getContactList(Long personId);
	
	/**
	 * 
	 * @return
	 */
	public List<CorContactVO> getCorContactList(Long corporateId);
	
	/**
	 * 
	 * @return
	 */
	public AddressVO getAddress(Long personId);
	
	/**
	 * 
	 * @return
	 */
	public CorAddressVO getCorAddress(Long corporateId);
	
	/**
	 * 
	 * @return
	 */
	public CorporateVO getCompany(Long personId);
	
	/**
	 * 
	 * @return
	 */
	public List<IdentityVO> getIdentityList(Long personId);
	
	/**
	 * 
	 * @param personId
	 * @return
	 */
	public List<RemarksVO> getRemarksList(Long personId);

	/**
	 * 
	 * @param params
	 * @return
	 * @throws BusinessException
	 */
	public int getSupplierListSize(Map<String, Object> params) throws BusinessException;

	/**
	 * 
	 * @param params
	 * @return
	 * @throws BusinessException
	 */
	public List<SupplierVO> getSupplierList(Map<String, Object> params) throws BusinessException;

	/**
	 * 
	 * @param id
	 * @return
	 */
	public SupplierVO getSupplierById(Long id);

	public void terminateSupplierCorContact(Long corporateId) throws BusinessException;

	public void terminateSupplierPIC(Long supplierId) throws BusinessException;

	public List<SuppPersonInChargeVO> getSuppPicList(Long supplierId);

	public List<SupplierVO> getSupplierList(Long idCompany, SearchParamVO searchParamVO) throws BusinessException;
}
