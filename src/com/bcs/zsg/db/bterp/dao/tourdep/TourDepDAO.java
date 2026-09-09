package com.bcs.zsg.db.bterp.dao.tourdep;

import java.util.List;

import com.bcs.zsg.core.dao.BaseDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.product.vo.TourDepartureVO;

public interface TourDepDAO extends BaseDAO {

	public List<TourDepartureVO> getTourDepCodeByInvoiceCategory() throws BusinessException;
	public List<TourDepartureVO> getTourDepCodeByInvoiceCategory(String category) throws BusinessException;
	
}
