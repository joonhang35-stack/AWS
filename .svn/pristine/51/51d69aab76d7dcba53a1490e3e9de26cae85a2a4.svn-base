package com.bcs.zsg.history.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.common.helper.FunctionCDConstant;
import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.component.security.helper.SecurityConstant;
import com.bcs.zsg.component.security.service.impl.SecurityServiceImpl;
import com.bcs.zsg.component.security.vo.RoleFunctionViewVO;
import com.bcs.zsg.component.security.vo.UserRoleViewVO;
import com.bcs.zsg.component.security.vo.UserVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.db.bterp.dao.invoicehistory.InvoiceHistoryDAO;
import com.bcs.zsg.db.bterp.dao.tourdephistory.TourDepHistoryDAO;
import com.bcs.zsg.history.dao.HistoryDAO;
import com.bcs.zsg.history.vo.InvoiceHistoryViewVO;
import com.bcs.zsg.history.vo.TourDepHistoryVO;
import com.bcs.zsg.history.vo.TourDepHistoryViewAllVO;
import com.bcs.zsg.history.vo.TourDepHistoryViewVO;
import com.bcs.zsg.history.vo.TourPkgAllHistoryViewVO;
import com.bcs.zsg.history.vo.TourPkgCurHistoryViewVO;

public class HistoryServiceImpl extends SecurityServiceImpl implements HistoryService {

	@Autowired
	private HistoryDAO historyDAO;

	@Autowired
	private InvoiceHistoryDAO invoiceHistDAO;

	@Autowired
	private TourDepHistoryDAO tourDepHistDAO;
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.history.service.HistoryService#getTourPkgHistoryViewList(com.bcs.zsg.common.vo.SearchParamVO)
	 */
	@Override
	public List<TourPkgCurHistoryViewVO> getTourPkgHistoryViewList(SearchParamVO searchParamVO) throws BusinessException {
		return historyDAO.getTourPkgHistoryViewList(searchParamVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.history.service.HistoryService#getTourPkgHistoryViewListByIdHist(java.lang.Long)
	 */
	@Override
	public List<TourPkgAllHistoryViewVO> getTourPkgHistoryViewListByIdHist(Long idHist) throws BusinessException {
		return historyDAO.getTourPkgHistoryViewListByIdHist(idHist);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.history.service.HistoryService#getTourDepHistoryViewListById(java.lang.Long)
	 */
	@Override
	public List<TourDepHistoryViewAllVO> getTourDepHistoryViewListById(Long idHist) throws BusinessException {
		return historyDAO.getTourDepHistoryViewListById(idHist);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.history.service.HistoryService#isNotificationAvailable(com.bcs.zsg.component.security.vo.UserVO, com.bcs.zsg.common.vo.SearchParamVO)
	 */
	@Override
	public boolean isNotificationAvailable(UserVO userVO, SearchParamVO searchParamVO) throws BusinessException {
		List<UserRoleViewVO> userRoleList = userVO.getRoleList();
		int count = 0;
		
		if (userRoleList.size() == 1) {
			if (userRoleList.get(0).getRoleCode().equals(SecurityConstant.ROLE_CD_SUPER_USER)) {
				//count = historyDAO.getCountTourPkgHistory(searchParamVO);
				count = historyDAO.getCountTourDepHistory(searchParamVO);
				if (count > 0) return true;
			}
		} else {
			for (UserRoleViewVO userRoleVO : userRoleList) {
				List<RoleFunctionViewVO> roleFunctionList = getRoleFunctionListByRole(userRoleVO.getRoleUUID(), true);
				
				for (RoleFunctionViewVO roleFuncVO : roleFunctionList) {
					if (FunctionCDConstant.PRODUCT_TOUR.equals(roleFuncVO.getFunctionCode())) {
						//count = historyDAO.getCountTourPkgHistory(searchParamVO);
						count = historyDAO.getCountTourDepHistory(searchParamVO);
						if (count > 0) return true;
						
					} else if (FunctionCDConstant.PRODUCT_FNE.equals(roleFuncVO.getFunctionCode())) {
						
					}
				}
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.history.service.HistoryService#getTourDepLatestHistory(java.lang.Long)
	 */
	@Override
	public TourDepHistoryVO getTourDepLatestHistory(Long idHist) throws BusinessException {
		return historyDAO.getTourDepLatestHistory(idHist);
	}
	
	/* (non-Javadoc)
	 * @see com.bcs.zsg.history.service.HistoryService#getInvoiceHistoryViewListById(java.lang.Long)
	 */
	@Override
	public List<InvoiceHistoryViewVO> getInvoiceHistoryViewListById(Long idHist) throws BusinessException {
		return historyDAO.getInvoiceHistoryViewListById(idHist);
	}

	/* (non-Javadoc)
	 * @see com.bcs.zsg.history.service.HistoryService#getInvoiceHistoryViewDetailById(java.lang.Long)
	 */
	@Override
	public InvoiceHistoryViewVO getInvoiceHistoryViewDetailById(Long id) throws BusinessException {
		return historyDAO.getInvoiceHistoryViewDetailById(id);
	}
	

	public int getHistoryListSize(Map<String, Object> params) throws BusinessException {
		String daoType = (String) (params.get("classVO") == null ? 
	 			InvoiceHistoryViewVO.class.toString() : params.get("classVO"));
		
		if(daoType.equals(InvoiceHistoryViewVO.class.toString()))
			return invoiceHistDAO.getListSizeHistoryView(params);
		
		else if(daoType.equals(TourDepHistoryViewVO.class.toString()))
			return tourDepHistDAO.getListSizeHistoryView(params);
		
		else {
			return 0;
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.history.service.HistoryService#getHistoryViewList(java.util.Map<String, Object>)
	 */
	@Override
	public List<?> getHistoryViewList(Map<String, Object> params) throws BusinessException {
		 String classVOType = (String) (params.get("classVO") == null ? 
				 			InvoiceHistoryViewVO.class.toString() : params.get("classVO"));
		
		if(classVOType.equals(InvoiceHistoryViewVO.class.toString()))
			return invoiceHistDAO.getListHistoryView(params);
		
		else if(classVOType.equals(TourDepHistoryViewVO.class.toString()))
			return tourDepHistDAO.getListHistoryView(params);
		
		else {
			return new ArrayList<Object>();
		}
	}
}
