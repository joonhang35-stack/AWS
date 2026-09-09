package com.bcs.zsg.maintenance.service;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.BaseConstant;
import com.bcs.zsg.core.helper.BaseContext;
import com.bcs.zsg.core.helper.CollectionUtils;
import com.bcs.zsg.maintenance.dao.ListingTableViewColumnsDAO;
import com.bcs.zsg.maintenance.dao.ListingTableViewDAO;
import com.bcs.zsg.maintenance.vo.ListingTableViewColumnsVO;
import com.bcs.zsg.maintenance.vo.ListingTableViewVO;

public class ListingTableViewServiceImpl implements ListingTableViewService {
	
	@Autowired
	private ListingTableViewDAO listingTableViewDAO;
	
	@Autowired
	private ListingTableViewColumnsDAO listingTableViewColumnsDAO;
	
	@Override
	public ListingTableViewVO getListingTableView(String listingType, Long idUser) throws BusinessException {
		ListingTableViewVO vo = listingTableViewDAO.getListingTableViewByListingType(listingType, idUser);
		
		if (vo != null) {
			vo.setListingTableViewColumnsVOList(listingTableViewColumnsDAO.getListingTableViewColumnsList(vo.getId()));
		}
		
		return vo;
	}
	
	@Override
	public void updateListingTableView(ListingTableViewVO vo) throws BusinessException {
		vo.setUpdatedBy(getUserInfo());
		vo.setUpdatedDate(new Date());
		
		if (vo.getId() == null) {
			vo.setStatusCode(BaseConstant.STATUS_ACTIVE);
			listingTableViewDAO.insert(vo);
		} else {
			listingTableViewDAO.update(vo);
		}
		
		// Delete all previous records
		listingTableViewColumnsDAO.deleteByIdListingTable(vo.getId());
		
		if (CollectionUtils.isNotEmpty(vo.getListingTableViewColumnsVOList())) {
			for (ListingTableViewColumnsVO columnsVO : vo.getListingTableViewColumnsVOList()) {
				columnsVO.setIdListingTable(vo.getId());
				columnsVO.setStatusCode(BaseConstant.STATUS_ACTIVE);
				listingTableViewColumnsDAO.insert(columnsVO);
			}
		}
	}
	
	private String getUserInfo() {
		return StringUtils.isBlank(BaseContext.getUserFullName()) ? BaseContext.getLoginId() : BaseContext.getUserFullName();
	}
}
