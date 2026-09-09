package com.bcs.zsg.purchase.vo;

import com.bcs.zsg.core.vo.BaseVO;

public class ExOrderAttachmentVO extends BaseVO {

	private static final long serialVersionUID = 1L;
	
	private Long eoId;
	private String name;
	private String scannedPath;
	private String filePath;
	private String typeCd;
	
	public Long getEoId() {
		return eoId;
	}
	public void setEoId(Long eoId) {
		this.eoId = eoId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getScannedPath() {
		return scannedPath;
	}
	public void setScannedPath(String scannedPath) {
		this.scannedPath = scannedPath;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getTypeCd() {
		return typeCd;
	}
	public void setTypeCd(String typeCd) {
		this.typeCd = typeCd;
	}

	
}
