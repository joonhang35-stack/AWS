package com.bcs.zsg.product.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.product.service.RoomConfigService;
import com.bcs.zsg.product.vo.RoomConfigVO;

public class RoomConfigBOImpl implements RoomConfigBO{
	
	@Autowired
	private RoomConfigService roomConfigService;
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.RoomConfigBO#addRoomConfig(com.bcs.zsg.product.vo.RoomConfigVO)
	 */
	@Override
	public void addRoomConfig(RoomConfigVO roomConfigVO) throws BusinessException {
		roomConfigService.addRoomConfig(roomConfigVO);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.RoomConfigBO#updRoomConfig(com.bcs.zsg.product.vo.RoomConfigVO)
	 */
	@Override
	public void updRoomConfig(RoomConfigVO roomConfigVO) throws BusinessException {
		roomConfigService.updRoomConfig(roomConfigVO);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.RoomConfigBO#delRoomConfig(com.bcs.zsg.product.vo.RoomConfigVO)
	 */
	@Override
	public void delRoomConfig(RoomConfigVO roomConfigVO) throws BusinessException {
		roomConfigService.delRoomConfig(roomConfigVO);
	}
	
	@Override
	public List<RoomConfigVO> getRoomConfigList() throws BusinessException {
		return roomConfigService.getRoomConfigList();
	}
	
	@Override
	public List<String> getRoomConfigRegionList(Long idRoomConfig) throws BusinessException {
		return roomConfigService.getRoomConfigRegionList(idRoomConfig);
	}
}
