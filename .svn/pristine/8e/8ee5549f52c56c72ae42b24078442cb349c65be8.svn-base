package com.bcs.zsg.db.bterp.dao.view.supplier;

import java.util.List;

import com.bcs.zsg.core.dao.BaseDAO;
import com.bcs.zsg.purchase.vo.SupplierVO;

public interface SupplierViewDAO extends BaseDAO {

	/**
	 * 
	 * @return
	 */
	public SupplierVO getSupplier(Long supplierId, Long companyId);
	
	/**
	 * 
	 * @param supplierId
	 * @param companyId
	 * @param includeInactive
	 * @return
	 */
	public SupplierVO getSupplier(Long supplierId, Long companyId, int includeInactive);

	/**
	 * 
	 * @return
	 */
	public List<SupplierVO> getSuppNameList(Long companyId);
	
}
