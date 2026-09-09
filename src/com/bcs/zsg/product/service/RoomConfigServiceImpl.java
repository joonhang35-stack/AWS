package com.bcs.zsg.product.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.product.dao.RoomConfigDAO;
import com.bcs.zsg.product.vo.RoomConfigRegionVO;
import com.bcs.zsg.product.vo.RoomConfigVO;


public class RoomConfigServiceImpl implements RoomConfigService{
	
	@Autowired
	private RoomConfigDAO roomConfigDAO;
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.service.RoomConfigService#addRoomConfig(com.bcs.zsg.product.vo.RoomConfigVO)
	 */
	@Override
	public void addRoomConfig(RoomConfigVO roomConfigVO) throws BusinessException {
		roomConfigDAO.insert(roomConfigVO);
		for(String code : roomConfigVO.getGroupList()) {
			RoomConfigRegionVO vo = new RoomConfigRegionVO();
			vo.setIdRoomConfig(roomConfigVO.getId());
			vo.setStatusCode(CommonConstant.STATUS_CD_ACTIVE);
			vo.setCode(code);
			roomConfigDAO.insert(vo);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.service.RoomConfigService#updRoomConfig(com.bcs.zsg.product.vo.RoomConfigVO)
	 */
	@Override
	public void updRoomConfig(RoomConfigVO roomConfigVO) throws BusinessException {
		roomConfigDAO.update(roomConfigVO);
		roomConfigDAO.deleteRoomConfigRegion(roomConfigVO.getId());
		for(String code : roomConfigVO.getGroupList()) {
			RoomConfigRegionVO vo = new RoomConfigRegionVO();
			vo.setIdRoomConfig(roomConfigVO.getId());
			vo.setStatusCode(CommonConstant.STATUS_CD_ACTIVE);
			vo.setCode(code);
			roomConfigDAO.insert(vo);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.service.RoomConfigService#delRoomConfig(com.bcs.zsg.product.vo.RoomConfigVO)
	 */
	@Override
	public void delRoomConfig(RoomConfigVO roomConfigVO) throws BusinessException {
		roomConfigVO.setStatusCode(CommonConstant.STATUS_CD_INACTIVE);
		roomConfigDAO.update(roomConfigVO);
		roomConfigDAO.updateRoomConfigStatus(roomConfigVO.getStatusCode(), roomConfigVO.getId());
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.service.RoomConfigService#delRoomConfig(com.bcs.zsg.product.vo.RoomConfigVO)
	*/ 
	@Override
	public List<RoomConfigVO> getRoomConfigList() throws BusinessException {
		List<RoomConfigVO> roomConfigList = roomConfigDAO.getRoomConfigList();
		for(RoomConfigVO vo : roomConfigList) {
			if(vo.getTwnQty() > 0)
				vo.setTourFare(StringUtils.isBlank(vo.getTourFare()) ? vo.getTwnQty() + "Adult x $TWN" : vo.getTourFare() + "," + vo.getTwnQty() + "Adult x $TWN");
			if(vo.getSglQty() > 0)
				vo.setTourFare(StringUtils.isBlank(vo.getTourFare()) ? vo.getSglQty() + "Adult x $SGL" : vo.getTourFare() + "," + vo.getSglQty() + "Adult x $SGL");
			if(vo.getCtwQty() > 0)
				vo.setTourFare(StringUtils.isBlank(vo.getTourFare()) ? vo.getCtwQty() + "Child x $CTW" : vo.getTourFare() + "," + vo.getCtwQty() + "Child x $CTW");
			if(vo.getCwbQty() > 0)
				vo.setTourFare(StringUtils.isBlank(vo.getTourFare()) ? vo.getCwbQty() + "Child x $CWB" : vo.getTourFare() + "," + vo.getCwbQty() + "Child x $CWB");
			if(vo.getCnbQty() > 0)
				vo.setTourFare(StringUtils.isBlank(vo.getTourFare()) ? vo.getCnbQty() + "Child x $CNB" : vo.getTourFare() + "," + vo.getCnbQty() + "Child x $CNB");
			if(vo.getInftQty() > 0)
				vo.setTourFare(StringUtils.isBlank(vo.getTourFare()) ? vo.getInftQty() + "Infant x $INFT" : vo.getTourFare() + "," + vo.getInftQty() + "Infant x $INFT");
		}
		return roomConfigList;
	}
	
	@Override
	public List<String> getRoomConfigRegionList(Long idRoomConfig) throws BusinessException {
		return roomConfigDAO.getRoomConfigRegionList(idRoomConfig);
	}
}
