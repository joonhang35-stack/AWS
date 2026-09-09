package com.bcs.zsg.maintenance.vo;

import java.util.List;

import com.bcs.zsg.core.vo.BaseVO;

public class ListingTableViewVO extends BaseVO {
	
	private static final long serialVersionUID = 1L;
	
	private Long idUser;
	private String listingType;
	
	private List<ListingTableViewColumnsVO> listingTableViewColumnsVOList; 
	
	public boolean verifyVisibility(Integer seqNo) {
		for (ListingTableViewColumnsVO vo : listingTableViewColumnsVOList) {
			if (vo.getSeqNo() == seqNo) {
				return vo.getVisible();
			}
		}
		
		return true;
	}
	
	public String getListingType() {
		return listingType;
	}
	
	public void setListingType(String listingType) {
		this.listingType = listingType;
	}
	
	public Long getIdUser() {
		return idUser;
	}
	
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	
	public List<ListingTableViewColumnsVO> getListingTableViewColumnsVOList() {
		return listingTableViewColumnsVOList;
	}
	
	public void setListingTableViewColumnsVOList(List<ListingTableViewColumnsVO> listingTableViewColumnsVOList) {
		this.listingTableViewColumnsVOList = listingTableViewColumnsVOList;
	}
	
}
