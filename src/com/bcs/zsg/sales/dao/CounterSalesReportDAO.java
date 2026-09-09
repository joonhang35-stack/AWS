package com.bcs.zsg.sales.dao;

import java.util.List;
import java.util.Map;

import com.bcs.zsg.core.dao.BaseDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.sales.vo.CounterSalesReportVO;

public interface CounterSalesReportDAO extends BaseDAO {
	
	public List<CounterSalesReportVO> getCounterSalesReportList(Map<String, Object> params) throws BusinessException;
}
