package com.bcs.zsg.sales.service;

import java.util.List;
import java.util.Map;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.sales.vo.CounterSalesReportVO;

public interface CounterSalesReportService {
	
	public List<CounterSalesReportVO> getCounterSalesReportList(Map<String, Object> params) throws BusinessException;
}
