package com.bcs.zsg.maintenance.dao;

import java.util.List;

import com.bcs.zsg.core.dao.BaseDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.vo.ListingTableViewColumnsVO;

public interface ListingTableViewColumnsDAO extends BaseDAO {
	
	public List<ListingTableViewColumnsVO> getListingTableViewColumnsList(Long idListingTable) throws BusinessException;
	
	public void deleteByIdListingTable(Long idListingTable) throws BusinessException;
}
