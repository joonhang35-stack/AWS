package com.bcs.zsg.history.dao;

import java.util.List;

import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.core.dao.BaseDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.history.vo.InvoiceHistoryViewVO;
import com.bcs.zsg.history.vo.TourDepHistoryVO;
import com.bcs.zsg.history.vo.TourDepHistoryViewAllVO;
import com.bcs.zsg.history.vo.TourPkgAllHistoryViewVO;
import com.bcs.zsg.history.vo.TourPkgCurHistoryViewVO;

public interface HistoryDAO extends BaseDAO {

	/**
	 * 
	 * @param searchParamVO
	 * @return
	 * @throws BusinessException
	 */
	public List<TourPkgCurHistoryViewVO> getTourPkgHistoryViewList(SearchParamVO searchParamVO) throws BusinessException;

	/**
	 * 
	 * @param idHist
	 * @return
	 * @throws BusinessException
	 */
	public List<TourPkgAllHistoryViewVO> getTourPkgHistoryViewListByIdHist(Long idHist) throws BusinessException;
	
	/**
	 * 
	 * @param idHist
	 * @return
	 * @throws BusinessException
	 */
	public List<TourDepHistoryViewAllVO> getTourDepHistoryViewListById(Long idHist) throws BusinessException;

	/**
	 * 
	 * @param searchParamVO
	 * @return
	 * @throws BusinessException
	 */
	public int getCountTourPkgHistory(SearchParamVO searchParamVO) throws BusinessException;
	
	/**
	 * 
	 * @param idHist
	 * @return
	 * @throws BusinessException
	 */
	public TourDepHistoryVO getTourDepLatestHistory(Long idHist) throws BusinessException;
	
	/**
	 * 
	 * @param idHist
	 * @return
	 * @throws BusinessException
	 */
	public List<InvoiceHistoryViewVO> getInvoiceHistoryViewListById(Long idHist) throws BusinessException;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws BusinessException
	 */
	public InvoiceHistoryViewVO getInvoiceHistoryViewDetailById(Long id) throws BusinessException;

	public int getCountTourDepHistory(SearchParamVO searchParamVO) throws BusinessException;
}
