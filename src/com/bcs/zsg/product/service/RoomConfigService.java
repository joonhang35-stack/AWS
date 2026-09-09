package com.bcs.zsg.product.service;

import java.util.List;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.product.vo.RoomConfigVO;

public interface RoomConfigService {
	
	/**
	 * 
	 * @param roomConfigVO
	 * @throws BusinessException
	 */
	public void addRoomConfig(RoomConfigVO roomConfigVO) throws BusinessException;
	
	/**
	 * 
	 * @param roomConfigVO
	 * @throws BusinessException
	 */
	public void updRoomConfig(RoomConfigVO roomConfigVO) throws BusinessException;
	
	/**
	 * 
	 * @param roomConfigVO
	 * @throws BusinessException
	 */
	public void delRoomConfig(RoomConfigVO roomConfigVO) throws BusinessException;
	
	public List<RoomConfigVO> getRoomConfigList() throws BusinessException;
	
	public List<String> getRoomConfigRegionList(Long idRoomConfig) throws BusinessException;
}
