package com.bcs.zsg.db.bterp.dao.view.monthlyticketing;

import java.util.List;
import java.util.Map;

import com.bcs.zsg.core.dao.BaseDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.db.bterp.vo.report.MonthlyTicketingSalesViewVO;

public interface MonthlyTicketingDAO extends BaseDAO {

	public int getListSizeMonthlyTicket(Map<String, Object> params) throws BusinessException;
	public List<MonthlyTicketingSalesViewVO> getListMonthlyTicket(Map<String, Object> params) throws BusinessException;

	public <T> T getMonthlyTicketGrandTotal(Map<String, Object> params) throws BusinessException;
}
