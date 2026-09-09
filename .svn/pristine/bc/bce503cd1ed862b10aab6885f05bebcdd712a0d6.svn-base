package com.bcs.zsg.purchase.dao;

import java.util.List;
import java.util.Map;

import com.bcs.zsg.cfg.sec.vo.EmployeeVO;
import com.bcs.zsg.component.security.vo.UserVO;
import com.bcs.zsg.core.dao.BaseDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.vo.CompanyVO;
import com.bcs.zsg.maintenance.vo.ForeignExRateVO;
import com.bcs.zsg.purchase.vo.AirLineDetailsVO;
import com.bcs.zsg.purchase.vo.CountryVO;
import com.bcs.zsg.purchase.vo.EOInvoiceVO;
import com.bcs.zsg.purchase.vo.ExOrderAttachmentVO;
import com.bcs.zsg.purchase.vo.ExOrderDetailsVO;
import com.bcs.zsg.purchase.vo.ExOrderVO;
import com.bcs.zsg.purchase.vo.HotelTourVO;
import com.bcs.zsg.purchase.vo.PaxNameVO;
import com.bcs.zsg.purchase.vo.PersonVO;
import com.bcs.zsg.purchase.vo.CorporateVO;
import com.bcs.zsg.purchase.vo.SupplierVO;
import com.bcs.zsg.purchase.vo.ExOrderBillVO;

public interface PurchaseEODAO extends BaseDAO{
	
	/**
	 * 
	 * @return
	 */
	public List<ExOrderVO> getExOrderList(Long companyId);

	/**
	 * 
	 * @return
	 */
	public List<ExOrderBillVO> getEOBillList(Long exOrderId) throws BusinessException;
	
	/**
	 * 
	 * @return
	 */
	public List<CompanyVO> getCompanyList();
	
	/**
	 * 
	 * @return
	 */
	public List<ExOrderDetailsVO> getExOrderDetailsList(Long exOrderId);
	
	/**
	 * 
	 * @return
	 */
	public List<PaxNameVO> getPaxNameList(Long exOrderId);
	
	/**
	 * 
	 * @return
	 */
	public List<AirLineDetailsVO> getAirLineList(Long exOrderId);
	
	/**
	 * 
	 * @return
	 */
	public List<HotelTourVO> getHotelTourList(Long exOrderId);
	
	/**
	 * 
	 * @return
	 */
	public HotelTourVO getHotelTourById(Long id);
	
	/**
	 * 
	 * @return
	 */
	public EmployeeVO getEmployee(Long companyId, String user);
	
	/**
	 * 
	 * @return
	 */
	public EmployeeVO getEmployee(Long ordererId);
	
	/**
	 * 
	 * @return
	 */
	public SupplierVO getSupplier(Long personId);
	
	/**
	 * 
	 * @return
	 */
	public ForeignExRateVO getExRate(Long currencyId);
	
	/**
	 * 
	 * @return
	 */
	public SupplierVO getEOSupplier(Long supplierId);
	
	/**
	 * 
	 * @return
	 */
	public CompanyVO getCompany(Long companyId);
	
	/**
	 * 
	 * @return
	 */
	public EmployeeVO getOrderer(Long ordererId);
	
	/**
	 * 
	 * @return
	 */
	public EmployeeVO getApprover(Long approverId);
	
	/**
	 * 
	 * @return
	 */
	public CountryVO getCountry(Long countryId);

	/**
	 * 
	 * @return
	 */
	public ExOrderVO getEOById(String eoSysNo);
	/**
	 * 
	 * @return
	 */
	public ExOrderVO getEOByEOId(Long idEO, Long idCompany);
	
	/**
	 * 
	 * @return
	 */
	public PersonVO getPerson(Long personId);
	
	/**
	 * 
	 * @return
	 */
	public UserVO getUser(String secUser);

	/**
	 * 
	 * @return
	 */
	public List<EOInvoiceVO> getEOInvList(Long exOrderId) ;
	
	/**
	 * 
	 * @param exOrderId
	 * @return
	 * @throws BusinessException
	 */
	public List<ExOrderAttachmentVO> getEOAttachmentList(Long exOrderId) throws BusinessException;
	
	/**
	 * 
	 * @return
	 */
	public CorporateVO getCorporate(Long corporateId) ;

	/**
	 * @param id
	 * @return
	 */
	public String getEOStatus(Long id);

	/**
	 * 
	 * @param companyId
	 * @return
	 */
	public List<ExOrderVO> getEOExOrderList(Long companyId) ;

	/**
	 * 
	 * @param exOrderId
	 * @return
	 */
	public ExOrderVO getExOrderById(Long exOrderId) ;

	/**
	 * 
	 * @param params
	 * @return
	 * @throws BusinessException
	 */
	public int getEOListSize(Map<String, Object> params) throws BusinessException;

	/**
	 * 
	 * @param params
	 * @return
	 * @throws BusinessException
	 */
	public List<ExOrderVO> getEOList(Map<String, Object> params) throws BusinessException;
	
	/**
	 * 
	 * @param idCompany
	 * @param statusCode
	 * @return
	 * @throws BusinessException
	 */
	public List<ExOrderVO> getBillEOList(Long idCompany, String statusCode) throws BusinessException;
	
	/**
	 * 
	 * @param idCompany
	 * @return
	 * @throws BusinessException
	 */
	public List<ExOrderVO> getTourDepartureEOList(Long idCompany, String tourCode) throws BusinessException;

	/**
	 * 
	 * @param table
	 * @param idEO
	 * @throws BusinessException
	 */
	public void terminateEOSubInfo(String table, Long idEO) throws BusinessException;
	
	/**
	 * 
	 * @param uuid
	 * @param code
	 * @throws BusinessException
	 */
	public void updateExOrderCode(String uuid, String code) throws BusinessException;

	public List<ExOrderVO> getExOrderHistoryList(Long id) throws BusinessException;
}
