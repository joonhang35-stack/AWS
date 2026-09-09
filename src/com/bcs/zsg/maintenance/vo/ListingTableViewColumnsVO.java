package com.bcs.zsg.maintenance.vo;

import com.bcs.zsg.core.vo.BaseVO;

public class ListingTableViewColumnsVO extends BaseVO {
	
	private static final long serialVersionUID = 1L;
	
	private Long idListingTable;
	private String columnsName;
	private int seqNo;
	private boolean visible;
	
	public Long getIdListingTable() {
		return idListingTable;
	}

	public void setIdListingTable(Long idListingTable) {
		this.idListingTable = idListingTable;
	}
	
	public String getColumnsName() {
		return columnsName;
	}
	
	public void setColumnsName(String columnsName) {
		this.columnsName = columnsName;
	}
	
	public boolean getVisible() {
		return visible;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public int getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(int seqNo) {
		this.seqNo = seqNo;
	}
}
