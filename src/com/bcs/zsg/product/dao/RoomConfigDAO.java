package com.bcs.zsg.product.dao;

import java.util.List;

import com.bcs.zsg.core.dao.BaseDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.product.vo.RoomConfigVO;

public interface RoomConfigDAO extends BaseDAO{

	public void deleteRoomConfigRegion(Long idRoomConfig) throws BusinessException;
	
	public void updateRoomConfigStatus(String statusCd, Long idRoomConfig) throws BusinessException;
	
	public List<RoomConfigVO> getRoomConfigList() throws BusinessException;
	
	public List<String> getRoomConfigRegionList(Long idRoomConfig) throws BusinessException;
}
