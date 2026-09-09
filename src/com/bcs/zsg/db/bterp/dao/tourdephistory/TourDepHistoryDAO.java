package com.bcs.zsg.db.bterp.dao.tourdephistory;

import java.util.List;
import java.util.Map;

import com.bcs.zsg.core.dao.BaseDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.history.vo.TourDepHistoryViewVO;

public interface TourDepHistoryDAO extends BaseDAO {

	public int getListSizeHistoryView(Map<String, Object> params) throws BusinessException;
	public List<TourDepHistoryViewVO> getListHistoryView(Map<String, Object> params) throws BusinessException;
	
}
