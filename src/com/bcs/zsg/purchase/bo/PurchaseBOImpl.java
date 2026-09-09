package com.bcs.zsg.purchase.bo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.maintenance.vo.LookupItemVO;
import com.bcs.zsg.maintenance.vo.SystemNumberGenerationVO;
import com.bcs.zsg.purchase.vo.AddressVO;
import com.bcs.zsg.purchase.vo.CorContactVO;
import com.bcs.zsg.purchase.vo.CorporateVO;
import com.bcs.zsg.purchase.vo.CountryVO;
import com.bcs.zsg.purchase.vo.IdentityVO;
import com.bcs.zsg.purchase.vo.PersonVO;
import com.bcs.zsg.purchase.vo.SupplierVO;
import com.bcs.zsg.purchase.service.PurchaseService;
import com.bcs.zsg.common.vo.AddUpdDelVO;
import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.core.exception.BusinessException;

public class PurchaseBOImpl implements PurchaseBO {

	@Autowired
	private PurchaseService purchaseService;
	
	/*
	 * (non-Javadoc)
	 */
	@Override
	public List<SupplierVO> getSupplierList(Long companyId, boolean isSimple) throws BusinessException {
		return purchaseService.getSupplierList(companyId, isSimple);
	}
	
	/*
	 * (non-Javadoc)
	 */
	@Override
	public List<IdentityVO> getIdentityList() throws BusinessException {
		return purchaseService.getIdentityList();
	}
	
	/*
	 * (non-Javadoc)
	 */
	@Override
	public List<LookupItemVO> getPymtTermList(String pymtTerm) throws BusinessException {
		return purchaseService.getPymtTermList(pymtTerm);
	}
	
	/*
	 * (non-Javadoc)
	 */
	@Override
	public List<CountryVO> getCountryList() throws BusinessException {
		return purchaseService.getCountryList();
	}

	 
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.bo.PurchaseBO#insert(com.bcs.zsg.purchase.vo.PurchaseVO)
	 */
	@Override
	public void insertPersonal(SupplierVO supplierVO, SystemNumberGenerationVO systemNumberGenerationVO) throws BusinessException {
		purchaseService.insertPersonal(supplierVO, systemNumberGenerationVO);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.bo.PurchaseBO#insert(com.bcs.zsg.purchase.vo.PurchaseVO)
	 */
	@Override
	public void insertCompany(SupplierVO supplierVO, SystemNumberGenerationVO systemNumberGenerationVO) throws BusinessException {
		purchaseService.insertCompany(supplierVO, systemNumberGenerationVO);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.bo.PurchaseBO#update(com.bcs.zsg.purchase.vo.PurchaseVO)
	 */
	@Override
	public void updatePersonal(SupplierVO supplierVO, AddressVO addressVO, PersonVO personVO, CorporateVO corporateVO, AddUpdDelVO contactAUDVO, AddUpdDelVO corContactAUDVO, AddUpdDelVO identityAUDVO, AddUpdDelVO remarksAUDVO) throws BusinessException {
		purchaseService.updatePersonal(supplierVO, addressVO, personVO, corporateVO, contactAUDVO, corContactAUDVO, identityAUDVO, remarksAUDVO);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.bo.PurchaseBO#delete(com.bcs.zsg.purchase.vo.PurchaseVO)
	 */
	@Override
	public void delSuppl(SupplierVO supplierVO, AddressVO addressVO, PersonVO personVO, CorporateVO corporateVO) throws BusinessException {
		purchaseService.delSuppl(supplierVO, addressVO, personVO, corporateVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.bo.PurchaseBO#getSupplierListSize(java.util.Map)
	 */
	@Override
	public int getSupplierListSize(Map<String, Object> params) throws BusinessException {
		return purchaseService.getSupplierListSize(params);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.bo.PurchaseBO#getSupplierList(java.util.Map)
	 */
	@Override
	public List<SupplierVO> getSupplierList(Map<String, Object> params) throws BusinessException {
		return purchaseService.getSupplierList(params);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.bo.PurchaseBO#getSupplierList(java.lang.Long)
	 */
	@Override
	public List<SupplierVO> getSupplierList(Long idCompany) throws BusinessException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idCompany", idCompany);
		return getSupplierList(map);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.bo.PurchaseBO#getSupplierById(java.lang.Long)
	 */
	@Override
	public SupplierVO getSupplierDetails(Long id) throws BusinessException {
		return purchaseService.getSupplierDetails(id);
	}
	
	@Override
	public SupplierVO getSupplierDetails(SupplierVO supplierVO) throws BusinessException {
		return purchaseService.getSupplierDetails(supplierVO);
	}
	
	@Override
	public List<CorContactVO> getCorContactList(Long corporateId) throws BusinessException{
		return purchaseService.getCorContactList(corporateId);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.bo.PurchaseBO#updateAcctTransDestinationSpplr(java.lang.Long, java.lang.Long)
	 */
	@Override
	public void updateAcctTransDestinationSpplr(Long companyId, Long supplierId) throws BusinessException {
		purchaseService.updateAcctTransDestinationSpplr(companyId, supplierId);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.bo.PurchaseBO#updateAcctTransAcctIdSupplr(java.lang.Long, java.lang.Long)
	 */
	@Override
	public void updateAcctTransAcctIdSupplr(Long companyId, Long supplierId) throws BusinessException {
		purchaseService.updateAcctTransAcctIdSupplr(companyId, supplierId);
	}

	@Override
	public List<SupplierVO> getSupplierList(Long idCompany, SearchParamVO searchParamVO) throws BusinessException {
		return purchaseService.getSupplierList(idCompany, searchParamVO);
	}
	
	
}
