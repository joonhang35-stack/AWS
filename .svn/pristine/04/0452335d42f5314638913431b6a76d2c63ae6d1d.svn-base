package com.bcs.zsg.purchase.service;

import java.util.List;
import java.util.Map;

import com.bcs.zsg.common.vo.AddUpdDelVO;
import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.vo.LookupItemVO;
import com.bcs.zsg.maintenance.vo.SystemNumberGenerationVO;
import com.bcs.zsg.purchase.vo.AddressVO;
import com.bcs.zsg.purchase.vo.CorContactVO;
import com.bcs.zsg.purchase.vo.CorporateVO;
import com.bcs.zsg.purchase.vo.CountryVO;
import com.bcs.zsg.purchase.vo.IdentityVO;
import com.bcs.zsg.purchase.vo.PersonVO;
import com.bcs.zsg.purchase.vo.SupplierVO;

public interface PurchaseService {


	/**
	 * 
	 * @param isSimple 
	 * @return
	 * @throws BusinessException
	 */
	public List<SupplierVO> getSupplierList(Long companyId, boolean isSimple) throws BusinessException;
	
	
	/**
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public List<IdentityVO> getIdentityList() throws BusinessException;
	
	/**
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public List<LookupItemVO> getPymtTermList(String pymtTerm) throws BusinessException;
	
	/**
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public List<CountryVO> getCountryList() throws BusinessException;
	
	/**
	 * 
	 * @param purchaseVO
	 * @throws BusinessException
	 */
	public List<CorContactVO> getCorContactList(Long corporateId) throws BusinessException;
	 
	
	/**
	 * 
	 * @param purchaseVO
	 * @throws BusinessException
	 */
	public void insertPersonal(SupplierVO supplierVO, SystemNumberGenerationVO systemNumberGenerationVO) throws BusinessException;
	
	/**
	 * 
	 * @param purchaseVO
	 * @throws BusinessException
	 */
	public void insertCompany(SupplierVO supplierVO, SystemNumberGenerationVO systemNumberGenerationVO) throws BusinessException;
	
	/**
	 * 
	 * @param purchaseVO
	 * @throws BusinessException
	 */
	public void updatePersonal(SupplierVO supplierVO, AddressVO addressVO, PersonVO personVO, CorporateVO corporateVO, AddUpdDelVO contactAUDVO, AddUpdDelVO corContactAUDVO, AddUpdDelVO identityAUDVO, AddUpdDelVO remarksAUDVO) throws BusinessException;
	
	/**
	 * 
	 * @param purchaseVO
	 * @throws BusinessException
	 */
	public void delSuppl(SupplierVO supplierVO, AddressVO addressVO, PersonVO personVO, CorporateVO corporateVO) throws BusinessException;

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
	public SupplierVO getSupplierDetails(Long id);
	
	public SupplierVO getSupplierDetails(SupplierVO supplierVO) throws BusinessException;
	
	/**
	 * 
	 * @param companyId
	 * @param supplierId
	 * @return
	 * @throws BusinessException
	 */
	public void updateAcctTransDestinationSpplr(Long companyId, Long supplierId) throws BusinessException;

	/**
	 * 
	 * @param companyId
	 * @param supplierId
	 * @return
	 * @throws BusinessException
	 */
	public void updateAcctTransAcctIdSupplr(Long companyId, Long supplierId) throws BusinessException;
	
	public List<SupplierVO> getSupplierList(Long idCompany, SearchParamVO searchParamVO) throws BusinessException;
}
