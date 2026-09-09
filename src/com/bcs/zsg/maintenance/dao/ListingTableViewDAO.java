package com.bcs.zsg.maintenance.dao;

import com.bcs.zsg.core.dao.BaseDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.vo.ListingTableViewVO;

public interface ListingTableViewDAO extends BaseDAO {
	
	public ListingTableViewVO getListingTableViewByListingType(String listingType, Long idUser) throws BusinessException;
}
