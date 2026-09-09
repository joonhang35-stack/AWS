package com.bcs.zsg.history.bo;

import java.util.List;
import java.util.Map;


import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.vo.BaseVO;
import com.bcs.zsg.history.vo.InvoiceHistoryViewVO;
import com.bcs.zsg.history.vo.TourDepHistoryVO;
import com.bcs.zsg.history.vo.TourDepHistoryViewAllVO;
import com.bcs.zsg.history.vo.TourPkgAllHistoryViewVO;
import com.bcs.zsg.history.vo.TourPkgCurHistoryViewVO;

public interface HistoryBO {

	/**
	 * 
	 * @param vo
	 * @throws BusinessException
	 */
	public void insertVO(BaseVO vo) throws BusinessException;

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
	

	public Map<String, Object> generateHistoryViewParam(Map<String, Object> params, Class<?> objClass,
					long compID, SearchParamVO searchParamVO) throws BusinessException;
	
	public int getHistoryListSize(Map<String, Object> params) throws BusinessException;
	public List<?> getHistoryViewList(Map<String, Object> params) throws BusinessException;

	public String [] getRefDataListValue(String refType);
}
