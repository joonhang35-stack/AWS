package com.bcs.zsg.product.vo;

import com.bcs.zsg.core.vo.BaseVO;

public class RoomConfigRegionVO extends BaseVO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long idRoomConfig;
	private String code;
	
	public Long getIdRoomConfig() {
		return idRoomConfig;
	}
	public void setIdRoomConfig(Long idRoomConfig) {
		this.idRoomConfig = idRoomConfig;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
