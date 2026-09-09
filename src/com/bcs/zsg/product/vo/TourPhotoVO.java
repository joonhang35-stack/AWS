package com.bcs.zsg.product.vo;

import com.bcs.zsg.core.vo.BaseVO;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TourPhotoVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	private Long idTourPkg;
	private Integer seq = 0;
	@Expose
	private String title;
	@Expose
	private String description;
	private String name;
	private String scannedPath;
	private String filePath;
	private String typeCd;
	@Expose
	@SerializedName("fileUrl")
	private String fileUrl;
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public Long getIdTourPkg() {
		return idTourPkg;
	}

	public void setIdTourPkg(Long idTourPkg) {
		this.idTourPkg = idTourPkg;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	
}
