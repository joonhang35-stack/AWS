package com.bcs.zsg.sales.bo;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.cfg.sec.vo.EmployeeVO;
import com.bcs.zsg.common.helper.CommonErrConstant;
import com.bcs.zsg.common.vo.AddUpdDelVO;
import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.component.security.vo.UserVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.vo.BaseVO;
import com.bcs.zsg.history.vo.TourDepHistoryVO;
import com.bcs.zsg.product.vo.TourDepartureVO;
import com.bcs.zsg.sales.service.BookingService;
import com.bcs.zsg.sales.vo.BookingChargeItemVO;
import com.bcs.zsg.sales.vo.BookingVO;
import com.bcs.zsg.sales.vo.BookingViewVO;
import com.bcs.zsg.sales.vo.InvoiceVO;

public class BookingBOImpl implements BookingBO {

	@Autowired
	private BookingService bookingService;
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.sales.bo.BookingBO#insertVO(com.bcs.zsg.core.vo.BaseVO)
	 */
	@Override
	public void insertVO(BaseVO vo) throws BusinessException {
		bookingService.insertVO(vo);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.sales.bo.BookingBO#updateVO(com.bcs.zsg.core.vo.BaseVO)
	 */
	@Override
	public void updateVO(BaseVO vo) throws BusinessException {
		bookingService.updateVO(vo);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.sales.bo.BookingBO#deleteVO(com.bcs.zsg.core.vo.BaseVO)
	 */
	@Override
	public void deleteVO(BaseVO vo) throws BusinessException {
		bookingService.deleteVO(vo);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.sales.bo.BookingBO#addBooking(com.bcs.zsg.sales.vo.BookingVO, com.bcs.zsg.product.vo.TourDepartureVO)
	 */
	@Override
	public void addBooking(BookingVO bookingVO, TourDepartureVO tourDepVO) throws BusinessException {
		if (bookingVO.getIdCust() == null) throw new BusinessException(CommonErrConstant.ERR_BOOKING_CUST_EMPTY);
		bookingService.addBooking(bookingVO, tourDepVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.sales.bo.BookingBO#updBooking(com.bcs.zsg.sales.vo.BookingVO, com.bcs.zsg.common.vo.AddUpdDelVO)
	 */
	@Override
	public void updBooking(BookingVO bookingVO, AddUpdDelVO bookItemAUDVO, TourDepartureVO tourDepVO) throws BusinessException {
		bookingService.updBooking(bookingVO, bookItemAUDVO, tourDepVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.sales.bo.BookingBO#cancelBooking(com.bcs.zsg.sales.vo.BookingVO, com.bcs.zsg.product.vo.TourDepartureVO)
	 */
	@Override
	public void cancelBooking(BookingVO bookingVO, TourDepartureVO tourDepVO) throws BusinessException {
		bookingService.cancelBooking(bookingVO, tourDepVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.sales.bo.BookingBO#getBookingList(java.lang.Long)
	 */
	@Override
	public List<BookingVO> getBookingList(TourDepartureVO tourDepVO) throws BusinessException {
		return bookingService.getBookingList(tourDepVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.sales.bo.BookingBO#getBookingViewList(com.bcs.zsg.common.vo.SearchParamVO, java.lang.String, java.lang.Long, java.lang.Boolean)
	 */
	@Override
	public List<BookingViewVO> getBookingViewList(SearchParamVO searchParamVO, String searchBookingName, String searchBookingNumber, Boolean isPersonal, UserVO userVO) throws BusinessException {
		if (StringUtils.isNotEmpty(searchBookingNumber) && !NumberUtils.isNumber(searchBookingNumber)) {
			throw new BusinessException(CommonErrConstant.ERR_BOOKING_NOT_NUMBER);
		}
		return bookingService.getBookingViewList(searchParamVO, searchBookingName, searchBookingNumber, isPersonal, userVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.sales.bo.BookingBO#getBookingViewList(com.bcs.zsg.common.vo.SearchParamVO, java.lang.String, java.lang.String, java.lang.Boolean, com.bcs.zsg.cfg.sec.vo.EmployeeVO)
	 */
	@Override
	public List<BookingViewVO> getBookingViewList(SearchParamVO searchParamVO, Boolean isPersonal, EmployeeVO employeeVO) throws BusinessException {
		return bookingService.getBookingViewList(searchParamVO, isPersonal, employeeVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.sales.bo.BookingBO#updBookingTourDepHistId(com.bcs.zsg.history.vo.TourDepHistoryVO)
	 */
	@Override
	public void updBookingTourDepHistId(TourDepHistoryVO tourDepHistoryVO) throws BusinessException {
		bookingService.updBookingTourDepHistId(tourDepHistoryVO);
	}

	/* (non-Javadoc)
	 * @see com.bcs.zsg.sales.bo.BookingBO#getBooking(java.lang.Long)
	 */
	@Override
	public BookingViewVO getBookingView(Long bookingId) throws BusinessException {
		return bookingService.getBookingView(bookingId);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.sales.bo.BookingBO#getBooking(java.lang.Long)
	 */
	@Override
	public BookingVO getBooking(Long bookingId) throws BusinessException {
		return bookingService.getBooking(bookingId);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.sales.bo.BookingBO#transferBooking(java.lang.Long, java.lang.Long, boolean)
	 */
	@Override
	public InvoiceVO transferBooking(Long idBooking, Long idTourDep, Date invDate, TourDepartureVO tourDepVO, boolean isReturnValue) throws BusinessException {
		return bookingService.transferBooking(idBooking, idTourDep, invDate, tourDepVO, isReturnValue);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.sales.bo.BookingBO#getBookingViewList(com.bcs.zsg.common.vo.SearchParamVO, int, int, java.util.Map)
	 */
	@Override
	public List<BookingViewVO> getBookingViewList(SearchParamVO searchParamVO, Boolean isPersonal, EmployeeVO employeeVO, int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters, Map<String, Object> params) throws BusinessException {
		return bookingService.getBookingViewList(searchParamVO, isPersonal, employeeVO, first, pageSize, sortField, sortOrder, filters, params);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.sales.bo.BookingBO#getBookingIdList(com.bcs.zsg.common.vo.SearchParamVO)
	 */
	@Override
	public List<Long> getBookingIdList(SearchParamVO searchParamVO) throws BusinessException {
		return bookingService.getBookingIdList(searchParamVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.sales.bo.BookingBO#getBookingSize(com.bcs.zsg.common.vo.SearchParamVO)
	 */
	@Override
	public int getBookingListSize(SearchParamVO searchParamVO, Boolean isPersonal, EmployeeVO employeeVO, Map<String, String> filters, Map<String, Object> params) throws BusinessException {
		return bookingService.getBookingListSize(searchParamVO, isPersonal, employeeVO, filters, params);
	}
	
	@Override
	public void updateBookingLockStatus(Long idTourDep, boolean isLock) throws BusinessException {
		bookingService.updateBookingLockStatus(idTourDep, isLock);
	}
	
	@Override
	public void updateIndividualBookingLockStatus(Long id, boolean isLock) throws BusinessException {
		bookingService.updateIndividualBookingLockStatus(id, isLock);
	}
	
	@Override
	public List<String> getSurveyList(Long bookingId) throws BusinessException{
		return bookingService.getSurveyList(bookingId);
	}
	
	@Override
	public void insertBookingHistory(Long id, String actionCd, String reason) throws BusinessException {
		bookingService.insertBookingHistory(id, actionCd, reason);
	}

	@Override
	public void addCruiseBooking(BookingVO bookingVO, TourDepartureVO tourDepVO) throws BusinessException {
		if (bookingVO.getIdCust() == null) throw new BusinessException(CommonErrConstant.ERR_BOOKING_CUST_EMPTY);
		bookingService.addCruiseBooking(bookingVO, tourDepVO);
	}
	
	@Override
	public void addCruiseBookingForQuote(BookingVO bookingVO, TourDepartureVO tourDepVO) throws BusinessException {
		if (bookingVO.getIdCust() == null) throw new BusinessException(CommonErrConstant.ERR_BOOKING_CUST_EMPTY);
		bookingService.addCruiseBookingForQuote(bookingVO, tourDepVO);
	}

	@Override
	public void addNewCruiseBooking(BookingVO bookingVO, TourDepartureVO tourDepVO) throws BusinessException {
		if (bookingVO.getIdCust() == null) throw new BusinessException(CommonErrConstant.ERR_BOOKING_CUST_EMPTY);
		bookingService.addNewCruiseBooking(bookingVO, tourDepVO);
	}

	@Override
	public void updBookingForQuote(BookingVO bookingVO, AddUpdDelVO bookItemAUDVO, TourDepartureVO tourDepVO) throws BusinessException {
		bookingService.updBookingForQuote(bookingVO, bookItemAUDVO, tourDepVO);
	}
	
	@Override
	public void updCruiseBooking(BookingVO bookingVO, TourDepartureVO tourDepVO) throws BusinessException {
		bookingService.updCruiseBooking(bookingVO, tourDepVO);
	}

	@Override
	public void cancelCruiseBooking(BookingVO bookingVO, TourDepartureVO tourDepVO) throws BusinessException {
		bookingService.cancelCruiseBooking(bookingVO, tourDepVO);
	}

	@Override
	public void reactivateBooking(BookingVO bookingVO) throws BusinessException {
		bookingService.reactivateBooking(bookingVO);
	}
	
	@Override
	public List<BookingVO> getBookingCruiseList(TourDepartureVO tourDepVO) throws BusinessException {
		return bookingService.getBookingCuiseList(tourDepVO);
	}

	@Override
	public List<BookingVO> getPassengerListWithNricByidTourDep(Long idTourDep) throws BusinessException {
		return bookingService.getPassengerListWithNricByidTourDep(idTourDep);
	}

	@Override
	public void addBookingForQuote(BookingVO bookingVO, TourDepartureVO tourDepVO) throws BusinessException {
		if (bookingVO.getIdCust() == null) throw new BusinessException(CommonErrConstant.ERR_BOOKING_CUST_EMPTY);
		bookingService.addBookingForQuote(bookingVO, tourDepVO);
	}
	
	public InvoiceVO transferCruiseBooking(Long idBooking, Long idTourDep, Date invDate, TourDepartureVO tourDepVO, boolean isReturnValue, boolean keepInvItem) throws BusinessException {
		return bookingService.transferCruiseBooking(idBooking, idTourDep, invDate, tourDepVO, isReturnValue, keepInvItem);
	}

	public InvoiceVO transferCruiseInvoice(InvoiceVO invoiceVO,
			BookingVO bookingVO, TourDepartureVO tourDepVO, boolean isReturnValue, boolean keepInvItem) throws BusinessException {
		return bookingService.transferCruiseInvoice(invoiceVO, bookingVO, tourDepVO, isReturnValue, keepInvItem);
	}
	
	public List<BookingChargeItemVO> getBookingChargeItemList(Long idBooking) throws BusinessException {
		return bookingService.getBookingChargeItemList(idBooking);
	}
	

}
