package com.bcs.zsg.sales.bo;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.primefaces.model.SortOrder;

import com.bcs.zsg.cfg.sec.vo.EmployeeVO;
import com.bcs.zsg.common.vo.AddUpdDelVO;
import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.component.security.vo.UserVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.vo.BaseVO;
import com.bcs.zsg.history.vo.TourDepHistoryVO;
import com.bcs.zsg.product.vo.TourDepartureVO;
import com.bcs.zsg.sales.vo.BookingChargeItemVO;
import com.bcs.zsg.sales.vo.BookingVO;
import com.bcs.zsg.sales.vo.BookingViewVO;
import com.bcs.zsg.sales.vo.InvoiceVO;

public interface BookingBO {

	/**
	 * 
	 * @param vo
	 * @throws BusinessException
	 */
	public void insertVO(BaseVO vo) throws BusinessException;
	
	/**
	 * 
	 * @param vo
	 * @throws BusinessException
	 */
	public void updateVO(BaseVO vo) throws BusinessException;
	
	/**
	 * 
	 * @param vo
	 * @throws BusinessException
	 */
	public void deleteVO(BaseVO vo) throws BusinessException;
	
	/**
	 * 
	 * @param bookingVO
	 * @param tourDepVO 
	 * @throws BusinessException
	 */
	public void addBooking(BookingVO bookingVO, TourDepartureVO tourDepVO) throws BusinessException;

	/**
	 * 
	 * @param bookingVO
	 * @param bookItemAUDVO
	 * @param tourDepVO 
	 * @throws BusinessException
	 */
	public void updBooking(BookingVO bookingVO, AddUpdDelVO bookItemAUDVO, TourDepartureVO tourDepVO) throws BusinessException;

	/**
	 * 
	 * @param bookingVO
	 * @param tourDepVO
	 * @throws BusinessException
	 */
	public void cancelBooking(BookingVO bookingVO, TourDepartureVO tourDepVO) throws BusinessException;

	/**
	 * 
	 * @param tourDepVO
	 * @return
	 * @throws BusinessException
	 */
	public List<BookingVO> getBookingList(TourDepartureVO tourDepVO) throws BusinessException;

	/**
	 * 
	 * @param searchParamVO
	 * @param searchBookingName 
	 * @param searchBookingNumber 
	 * @param isPersonal 
	 * @param userVO 
	 * @return
	 * @throws BusinessException
	 */
	public List<BookingViewVO> getBookingViewList(SearchParamVO searchParamVO, String searchBookingName, String searchBookingNumber, Boolean isPersonal, UserVO userVO) throws BusinessException;

	/**
	 * 
	 * @param searchParamVO
	 * @param searchBookingName
	 * @param searchBookingNumber
	 * @param isPersonal
	 * @param employeeVO
	 * @return
	 * @throws BusinessException
	 */
	public List<BookingViewVO> getBookingViewList(SearchParamVO searchParamVO, Boolean isPersonal, EmployeeVO employeeVO) throws BusinessException;

	/**
	 * 
	 * @param tourDepHistoryVO
	 * @throws BusinessException
	 */
	public void updBookingTourDepHistId(TourDepHistoryVO tourDepHistoryVO) throws BusinessException;
	
	/**
	 * 
	 * @param bookingId
	 * @throws BusinessException
	 */
	public BookingViewVO getBookingView(Long bookingId) throws BusinessException;

	/**
	 * 
	 * @param bookingId
	 * @return
	 * @throws BusinessException
	 */
	public BookingVO getBooking(Long bookingId) throws BusinessException;

	/**
	 * 
	 * @param idBooking
	 * @param idTourDep 
	 * @param invDate 
	 * @param tourDepVO
	 * @param isReturnValue
	 * @return
	 * @throws BusinessException
	 */
	public InvoiceVO transferBooking(Long idBooking, Long idTourDep, Date invDate, TourDepartureVO tourDepVO, boolean isReturnValue) throws BusinessException;

	/**
	 * 
	 * @param searchParamVO
	 * @param employeeVO 
	 * @param isPersonal 
	 * @param first
	 * @param pageSize
	 * @param sortOrder 
	 * @param sortField 
	 * @param filters
	 * @return
	 * @throws BusinessException
	 */
	public List<BookingViewVO> getBookingViewList(SearchParamVO searchParamVO, Boolean isPersonal, EmployeeVO employeeVO, int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters, Map<String, Object> params) throws BusinessException;

	/**
	 * 
	 * @param searchParamVO
	 * @return
	 * @throws BusinessException
	 */
	public List<Long> getBookingIdList(SearchParamVO searchParamVO) throws BusinessException;

	/**
	 * 
	 * @param searchParamVO
	 * @param employeeVO 
	 * @param isPersonal 
	 * @param filters 
	 * @return
	 * @throws BusinessException
	 */
	public int getBookingListSize(SearchParamVO searchParamVO, Boolean isPersonal, EmployeeVO employeeVO, Map<String, String> filters, Map<String, Object> params) throws BusinessException;
	
	public void updateBookingLockStatus(Long idTourDep, boolean isLock) throws BusinessException;
	
	public void updateIndividualBookingLockStatus(Long id, boolean isLock) throws BusinessException;
	
	public List<String> getSurveyList(Long bookingId) throws BusinessException;
	
	public void insertBookingHistory(Long id, String actionCd, String reason) throws BusinessException;
	
	public void addCruiseBooking(BookingVO bookingVO, TourDepartureVO tourDepVO) throws BusinessException;
	public void addCruiseBookingForQuote(BookingVO bookingVO, TourDepartureVO tourDepVO) throws BusinessException;
	public void addNewCruiseBooking(BookingVO bookingVO, TourDepartureVO tourDepVO) throws BusinessException;
	public void updBookingForQuote(BookingVO bookingVO, AddUpdDelVO bookItemAUDVO, TourDepartureVO tourDepVO) throws BusinessException;
	public void updCruiseBooking(BookingVO bookingVO, TourDepartureVO tourDepVO) throws BusinessException;
	public void cancelCruiseBooking(BookingVO bookingVO, TourDepartureVO tourDepVO) throws BusinessException;
	
	public void reactivateBooking(BookingVO bookingVO) throws BusinessException;
	public List<BookingVO> getBookingCruiseList(TourDepartureVO tourDepVO) throws BusinessException;
	
	public List<BookingVO> getPassengerListWithNricByidTourDep(Long idTourDep) throws BusinessException;
	
	public void addBookingForQuote(BookingVO bookingVO, TourDepartureVO tourDepVO) throws BusinessException;

	public InvoiceVO transferCruiseBooking(Long idBooking, Long idTourDep, Date invDate, TourDepartureVO tourDepVO, boolean isReturnValue, boolean keepInvItem) throws BusinessException;
	
	public InvoiceVO transferCruiseInvoice(InvoiceVO invoiceVO,
			BookingVO bookingVO, TourDepartureVO tourDepVO, boolean isReturnValue, boolean keepInvItem) throws BusinessException;
	
	public List<BookingChargeItemVO> getBookingChargeItemList(Long idBooking) throws BusinessException;
	

}
