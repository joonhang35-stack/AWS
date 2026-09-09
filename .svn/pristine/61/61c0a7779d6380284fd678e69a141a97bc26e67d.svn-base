package com.bcs.zsg.product.web.bean;

import java.util.ArrayList;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.common.helper.LookupItemUtils;
import com.bcs.zsg.common.web.bean.AppBackingBean;
import com.bcs.zsg.product.bo.RoomConfigBO;
import com.bcs.zsg.product.vo.RoomConfigVO;
import com.mchange.v2.ser.SerializableUtils;

public class RoomConfigBean extends AppBackingBean{
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private transient RoomConfigBO roomConfigBO;
	
	private RoomConfigVO roomConfigVO;
	private List<RoomConfigVO> tourRoomConfigViewList;
	
	private String groupDesc;
	
	@Override
	public void resetForm() {
		roomConfigVO = new RoomConfigVO();
		groupDesc = "";
	}
	
	public void init() {
		try {
			resetForm();
			tourRoomConfigViewList = new ArrayList<RoomConfigVO>();
			tourRoomConfigViewList = roomConfigBO.getRoomConfigList();
		} catch(Throwable t) {
			errorResult(t);
		}
	}
	
	public void addNewRoomConfig() {
		try {
			resetForm();
			roomConfigVO.setTwnQty(0);
			roomConfigVO.setSglQty(0);
			roomConfigVO.setCwbQty(0);
			roomConfigVO.setCnbQty(0);
			roomConfigVO.setCtwQty(0);
			roomConfigVO.setInftQty(0);
		}catch(Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Add Room Config
	 */
	public void addRoomConfig() {
		try {
			roomConfigVO.setStatusCode(CommonConstant.STATUS_CD_ACTIVE);
			roomConfigBO.addRoomConfig(roomConfigVO);
			tourRoomConfigViewList = roomConfigBO.getRoomConfigList();
			resetForm();
			successResult();
		}catch(Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Update tour category
	 */
	public void updRoomConfig() {
		try {
			roomConfigBO.updRoomConfig(roomConfigVO);
			tourRoomConfigViewList = roomConfigBO.getRoomConfigList();
			resetForm();
			successResult();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Delete Room Config
	 */
	public void delRoomConfig() {
		try {
			roomConfigBO.delRoomConfig(roomConfigVO);
			tourRoomConfigViewList = roomConfigBO.getRoomConfigList();
			resetForm();
			successResult();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void handleRoomConfigSelect(RoomConfigVO vo) {
		try {
			roomConfigVO = (RoomConfigVO) SerializableUtils.deepCopy(vo);
			roomConfigVO.setCreatedDate(vo.getCreatedDate());
			roomConfigVO.setCreatedBy(vo.getCreatedBy());
			roomConfigVO.setStatusCode(vo.getStatusCode());
			roomConfigVO.setGroupList(roomConfigBO.getRoomConfigRegionList(vo.getId()));
			
			handleGroupChange();
		}catch(Throwable t) {
			errorResult(t);
		}
	}
	
	public void handleGroupChange() {
		groupDesc = "";
		
		for (String group : roomConfigVO.getGroupList()) {
			String tempDesc = LookupItemUtils.getLookupItemDesc(CommonConstant.LOOKUP_CAT_CD_ROOM_CONFIG_REGION, group);
			
			if (StringUtils.isBlank(groupDesc))
				groupDesc = tempDesc;
			else
				groupDesc = groupDesc + ", " + tempDesc;
		}
	}

	public RoomConfigVO getRoomConfigVO() {
		return roomConfigVO;
	}

	public void setRoomConfigVO(RoomConfigVO roomConfigVO) {
		this.roomConfigVO = roomConfigVO;
	}

	public List<RoomConfigVO> getTourRoomConfigViewList() {
		return tourRoomConfigViewList;
	}

	public void setTourRoomConfigViewList(List<RoomConfigVO> tourRoomConfigViewList) {
		this.tourRoomConfigViewList = tourRoomConfigViewList;
	}

	public String getGroupDesc() {
		return groupDesc;
	}

	public void setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc;
	}
	
}
