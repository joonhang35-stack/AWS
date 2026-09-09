package com.bcs.zsg.product.vo;

import com.bcs.zsg.core.vo.BaseVO;

public class TourPackageItineryVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	private Long idTourPkg;
	private Long idTourPkgDailyItinerary;
	private String name;
	private String path;
	private String langCd;
	private String typeCd;
	private boolean isSingleFile;
	
	public Long getIdTourPkg() {
		return idTourPkg;
	}
	public void setIdTourPkg(Long idTourPkg) {
		this.idTourPkg = idTourPkg;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getLangCd() {
		return langCd;
	}
	public void setLangCd(String langCd) {
		this.langCd = langCd;
	}
	public String getTypeCd() {
		return typeCd;
	}
	public void setTypeCd(String typeCd) {
		this.typeCd = typeCd;
	}
	public boolean isSingleFile() {
		return isSingleFile;
	}
	public void setSingleFile(boolean isSingleFile) {
		this.isSingleFile = isSingleFile;
	}
	public Long getIdTourPkgDailyItinerary() {
		return idTourPkgDailyItinerary;
	}
	public void setIdTourPkgDailyItinerary(Long idTourPkgDailyItinerary) {
		this.idTourPkgDailyItinerary = idTourPkgDailyItinerary;
	}
}
