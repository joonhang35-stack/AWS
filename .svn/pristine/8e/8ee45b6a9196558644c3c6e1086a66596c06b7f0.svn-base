package com.bcs.zsg.maintenance.bo;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.service.ListingTableViewService;
import com.bcs.zsg.maintenance.vo.ListingTableViewVO;

public class ListingTableViewBOImpl implements ListingTableViewBO {
	
	@Autowired
	private ListingTableViewService listingTableViewService;

	@Override
	public ListingTableViewVO getListingTableView(String listingType, Long idUser) throws BusinessException {
		return listingTableViewService.getListingTableView(listingType, idUser);
	}
	
	@Override
	public void updateListingTableView(ListingTableViewVO vo) throws BusinessException {
		listingTableViewService.updateListingTableView(vo);
	}
}
