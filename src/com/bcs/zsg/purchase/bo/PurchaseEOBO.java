package com.bcs.zsg.purchase.bo;

import java.util.List;
import java.util.Map;

import com.bcs.zsg.cfg.sec.vo.EmployeeVO;
import com.bcs.zsg.common.vo.AddUpdDelVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.vo.CompanyVO;
import com.bcs.zsg.maintenance.vo.ForeignExRateVO;
import com.bcs.zsg.purchase.vo.AirLineDetailsVO;
import com.bcs.zsg.purchase.vo.ExOrderDetailsVO;
import com.bcs.zsg.purchase.vo.ExOrderVO;
import com.bcs.zsg.purchase.vo.HotelTourVO;
import com.bcs.zsg.purchase.vo.PaxNameVO;
import com.bcs.zsg.purchase.vo.SupplierVO;
import com.bcs.zsg.purchase.vo.EOInvoiceVO;
import com.bcs.zsg.purchase.vo.ExOrderAttachmentVO;
import com.bcs.zsg.purchase.vo.ExOrderBillVO;

public interface PurchaseEOBO {
	
	/**
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public List<ExOrderVO> getExOrderList(Long companyId) throws BusinessException;
	
	/**
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public List<ExOrderDetailsVO> getEODetailsList(Long exOrderId) throws BusinessException;
	
	/**
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public List<PaxNameVO> getPaxNameList(Long exOrderId) throws BusinessException;

	/**
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public List<AirLineDetailsVO> getAirLineList(Long exOrderId) throws BusinessException;
	
	/**
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public List<HotelTourVO> getHotelTourList(Long exOrderId) throws BusinessException;
	
	/**
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public List<EOInvoiceVO> getEOInvList(Long exOrderId) throws BusinessException ;
	
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
	 * @throws BusinessException
	 */
	public List<ExOrderBillVO> getEOBillList(Long exOrderId) throws BusinessException ;
	
	/**
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public List<CompanyVO> getCompanyList() throws BusinessException;
	
	/**
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public HotelTourVO getHotelTourById(Long id) throws BusinessException;
	
	/**
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public EmployeeVO getEmployee(Long companyId, String user) throws BusinessException;
	
	/**
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public EmployeeVO getEmployee(Long ordererId) throws BusinessException;
	
	/**
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public SupplierVO getSupplier(Long personId) throws BusinessException;
	
	/**
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public ForeignExRateVO getExRate(Long currencyId) throws BusinessException;
	
	/**
	 * 
	 * @throws BusinessException
	 */
	public void insertExOrder(SupplierVO supplierVO, ExOrderVO exOrderVO, AddUpdDelVO eoDetailsAUDVO, 
			AddUpdDelVO paxNameAUDVO, AddUpdDelVO airLineAUDVO, AddUpdDelVO hotelTourAUDVO,AddUpdDelVO eoInvAUDVO) throws BusinessException;
	
	/**
	 * 
	 * @throws BusinessException
	 */
	public void updateExOrder(SupplierVO supplierVO, ExOrderVO exOrderVO, AddUpdDelVO eoDetailsAUDVO, 
			AddUpdDelVO paxNameAUDVO, AddUpdDelVO airLineAUDVO, AddUpdDelVO hotelTourAUDVO, AddUpdDelVO eoInvAUDVO) throws BusinessException;
	
	/**
	 * 
	 * @throws BusinessException
	 */
	public void delEO(SupplierVO supplierVO, ExOrderVO exOrderVO, List<ExOrderDetailsVO> eoDetailsList, List<PaxNameVO> paxNameList, List<AirLineDetailsVO> airLineList, List<HotelTourVO> hotelTourList) throws BusinessException;
	
	/**
	 * 
	 * @throws BusinessException
	 */
	public void editEODetails(ExOrderDetailsVO exOrderDetailsVO) throws BusinessException;
	
	/**
	 * 
	 * @throws BusinessException
	 */
	public void editPaxName(PaxNameVO paxNameVO) throws BusinessException;
	
	/**
	 * 
	 * @throws BusinessException
	 */
	public void editAirLine(AirLineDetailsVO airLineVO) throws BusinessException;
	
	/**
	 * 
	 * @throws BusinessException
	 */
	public void editHotelTour(HotelTourVO hotelTourVO) throws BusinessException;
	
	/**
	 * 
	 * @throws BusinessException
	 */
	public void deleteEODetails(ExOrderDetailsVO exOrderDetailsVO) throws BusinessException;
	
	/**
	 * 
	 * @throws BusinessException
	 */
	public void deletePaxName(PaxNameVO paxNameVO) throws BusinessException;
	
	/**
	 * 
	 * @throws BusinessException
	 */
	public void deleteAirLine(AirLineDetailsVO airLineVO) throws BusinessException;
	
	/**
	 * 
	 * @throws BusinessException
	 */
	public void deleteHotelTour(HotelTourVO hotelTourVO) throws BusinessException;

	/**
	 * @param id
	 * @return
	 * @throws BusinessException
	 */
	public String getEOStatus(Long id) throws BusinessException;
	
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
	 * @param exOrderId
	 * @return
	 */
	public SupplierVO getEOSupplier(Long exOrderId) ;

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
	 * @param exOrderAttachmentVO
	 * @param isDelete
	 * @return
	 * @throws BusinessException
	 */
	public void addDeleteEOAttachment(ExOrderAttachmentVO exOrderAttachmentVO, boolean isDelete) throws BusinessException;

	public List<ExOrderVO> getExOrderHistoryList(Long id) throws BusinessException;
	

	public SupplierVO getPayeeAsSupplier(Long idPayee, String payeeType, Long idCompany) throws BusinessException;
}
