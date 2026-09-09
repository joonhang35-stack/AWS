package com.bcs.zsg.history.bo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.common.helper.MapUtil;
import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.component.common.helper.LookupUtils;
import com.bcs.zsg.component.common.vo.RefDataVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.vo.BaseVO;
import com.bcs.zsg.history.service.HistoryService;
import com.bcs.zsg.history.vo.InvoiceHistoryViewVO;
import com.bcs.zsg.history.vo.TourDepHistoryVO;
import com.bcs.zsg.history.vo.TourDepHistoryViewAllVO;
import com.bcs.zsg.history.vo.TourDepHistoryViewVO;
import com.bcs.zsg.history.vo.TourPkgAllHistoryViewVO;
import com.bcs.zsg.history.vo.TourPkgCurHistoryViewVO;

public class HistoryBOImpl implements HistoryBO {

	@Autowired
	private HistoryService historyService;
	
	private List<RefDataVO> tmpRefDataListInvStatus;
	private List<RefDataVO> tmpRefDataListActionCd;

	private Map<String, String> mapActionCD = new HashMap<String, String>();
	private Map<String, String> mapInvoiceStatus = new HashMap<String, String>();
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.history.bo.HistoryBO#insertVO(com.bcs.zsg.core.vo.BaseVO)
	 */
	@Override
	public void insertVO(BaseVO vo) throws BusinessException {
		historyService.insertVO(vo);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.history.bo.HistoryBO#getTourPkgHistoryViewList(com.bcs.zsg.common.vo.SearchParamVO)
	 */
	@Override
	public List<TourPkgCurHistoryViewVO> getTourPkgHistoryViewList(SearchParamVO searchParamVO) throws BusinessException {
		return historyService.getTourPkgHistoryViewList(searchParamVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.history.bo.HistoryBO#getTourPkgHistoryViewListByIdHist(java.lang.Long)
	 */
	@Override
	public List<TourPkgAllHistoryViewVO> getTourPkgHistoryViewListByIdHist(Long idHist) throws BusinessException {
		return historyService.getTourPkgHistoryViewListByIdHist(idHist);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.history.bo.HistoryBO#getTourDepHistoryViewListById(java.lang.Long)
	 */
	@Override
	public List<TourDepHistoryViewAllVO> getTourDepHistoryViewListById(Long idHist) throws BusinessException {
		return historyService.getTourDepHistoryViewListById(idHist);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.history.bo.HistoryBO#getTourDepLatestHistory(java.lang.Long)
	 */
	@Override
	public TourDepHistoryVO getTourDepLatestHistory(Long idHist) throws BusinessException {
		return historyService.getTourDepLatestHistory(idHist);
	}
	
	/* (non-Javadoc)
	 * @see com.bcs.zsg.history.bo.HistoryBO#getInvoiceHistoryViewListById(java.lang.Long)
	 */
	@Override
	public List<InvoiceHistoryViewVO> getInvoiceHistoryViewListById(Long idHist) throws BusinessException {
		return historyService.getInvoiceHistoryViewListById(idHist);
	}

	/* (non-Javadoc)
	 * @see com.bcs.zsg.history.bo.HistoryBO#getInvoiceHistoryViewDetailById(java.lang.Long)
	 */
	@Override
	public InvoiceHistoryViewVO getInvoiceHistoryViewDetailById(Long id) throws BusinessException {
		return historyService.getInvoiceHistoryViewDetailById(id);
	}

	
	private Map<String, String> updateHistoryViewfilter (String classVOType, Map<String, String> filters) 
			throws BusinessException {
		if(filters.containsKey("actionCd"))
			filters.put("actionCd", MapUtil.getKeyByValue(getRefDataListMap(CommonConstant.ACT_TYPE_CD), filters.get("actionCd")));
		
		if(classVOType.equals(InvoiceHistoryViewVO.class.toString()) && filters.containsKey("statusCd")) 
			filters.put("statusCd", MapUtil.getKeyByValue(getRefDataListMap(CommonConstant.INV_STATUS), filters.get("statusCd")));
		
		return filters;
	}
	
	@Override
	public int getHistoryListSize(Map<String, Object> params) throws BusinessException {
		return historyService.getHistoryListSize(params);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<?> getHistoryViewList(Map<String, Object> params) throws BusinessException {
		String classVOType = (String) (params.get("classVO") == null ? 
	 			InvoiceHistoryViewVO.class.toString() : params.get("classVO"));
		
		List<?> tmpResultList = historyService.getHistoryViewList(params);
		
		if(tmpResultList.size() > 0) {
			if(classVOType.equals(InvoiceHistoryViewVO.class.toString())) {
				for(InvoiceHistoryViewVO tmpResultVO : (List<InvoiceHistoryViewVO>)tmpResultList) {
					tmpResultVO.setActionCd(getRefDataListMap(CommonConstant.ACT_TYPE_CD).get(tmpResultVO.getActionCd()));
					tmpResultVO.setStatusCd(getRefDataListMap(CommonConstant.INV_STATUS).get(tmpResultVO.getStatusCd()));
				}
			} else if(classVOType.equals(TourDepHistoryViewVO.class.toString())) {
				for(TourDepHistoryViewVO tmpResultVO : (List<TourDepHistoryViewVO>)tmpResultList) {
					tmpResultVO.setActionCd(getRefDataListMap(CommonConstant.ACT_TYPE_CD).get(tmpResultVO.getActionCd()));
				}
			}
		}
		
		return tmpResultList;
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> generateHistoryViewParam(Map<String, Object> params, Class<?> objClass,
					long compID, SearchParamVO searchParamVO) throws BusinessException {
		
		params.put("idCompany", compID);
		params.put("classVO", objClass.toString());
		params.put("fromDate", searchParamVO.getFromDate());
		params.put("toDate", searchParamVO.getToDate());
		
		params.put("filters", updateHistoryViewfilter(objClass.toString(), (Map<String, String>) params.get("filters")));
		
		return params;
	}
	
	@SuppressWarnings("unchecked")
	protected Map<String, String> getRefDataListMap(String refType) throws BusinessException {
		if (refType.equals(CommonConstant.INV_STATUS)) {
			if(tmpRefDataListInvStatus == null || tmpRefDataListInvStatus.size() == 0) {
				tmpRefDataListInvStatus = LookupUtils.getReferenceDataList(CommonConstant.INV_STATUS);
				mapActionCD.clear();
			}
			
			if(tmpRefDataListInvStatus.size() > 0) {
				for(RefDataVO refDataVO : tmpRefDataListInvStatus) {
					if(!mapActionCD.containsKey(refDataVO.getCode()))
						mapActionCD.put(refDataVO.getCode(), refDataVO.getValue());
				}
			}
			return mapActionCD;
		} else if(refType.equals(CommonConstant.ACT_TYPE_CD)) {
			if(tmpRefDataListActionCd == null || tmpRefDataListActionCd.size() == 0) {
				tmpRefDataListActionCd = LookupUtils.getReferenceDataList(CommonConstant.ACT_TYPE_CD);
				mapInvoiceStatus.clear();
			}
			
			if(tmpRefDataListActionCd.size() > 0) {
				for(RefDataVO refDataVO : tmpRefDataListActionCd) {
					if(!mapInvoiceStatus.containsKey(refDataVO.getCode()))
						mapInvoiceStatus.put(refDataVO.getCode(), refDataVO.getValue());
				}
			}
			return mapInvoiceStatus;
		} else {
			return new HashMap<String, String>();
		}
	}

	@Override
	public String [] getRefDataListValue(String refType) {
		try {
			return getRefDataListMap(refType).values().toArray(new String[0]);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return new String[0];
	}
}
