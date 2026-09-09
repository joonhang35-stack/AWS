package com.bcs.zsg.product.service;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.common.vo.GenAddUpdDelVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.vo.BaseVO;
import com.bcs.zsg.product.dao.RoomingListLandOperatorDAO;
import com.bcs.zsg.product.vo.RoomingListLandOperatorContVO;
import com.bcs.zsg.product.vo.RoomingListLandOperatorVO;

public class RoomingListLandOperatorServiceImpl implements RoomingListLandOperatorService{
	
	@Autowired
	private RoomingListLandOperatorDAO roomingListLandOperatorDAO;
	
	public void insertVO(BaseVO vo) throws BusinessException {
		roomingListLandOperatorDAO.insert(vo);
	}
	
	public void updateVO(BaseVO vo) throws BusinessException { 
		roomingListLandOperatorDAO.update(vo);
	}
	 
	public void deleteVO(BaseVO vo) throws BusinessException {
		roomingListLandOperatorDAO.delete(vo);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.service.RoomingListLandOperatorService#addLandOperatorSupplier(com.bcs.zsg.product.vo.RoomingListLandOperatorVO)
	 */
	@Override
	public void addLandOperatorSupplier(List<RoomingListLandOperatorVO> roomingListLandOprVOList, GenAddUpdDelVO<RoomingListLandOperatorVO> landOprAUDList) throws BusinessException {
		
		for (RoomingListLandOperatorVO landOprVO : roomingListLandOprVOList) {
			if (landOprVO.getId() == null) {
				insertVO(landOprVO);
				for (RoomingListLandOperatorContVO contactVO : landOprVO.getRoomingListLandOprContList()) {
					contactVO.setIdLandOperator(landOprVO.getId());
					insertVO(contactVO);
				}
			} else {
				updateVO(landOprVO);
				for (RoomingListLandOperatorContVO contactVO : landOprVO.getRoomingListLandOprContList()) {
					if (contactVO.getId() == null) {
						contactVO.setIdLandOperator(landOprVO.getId());
						insertVO(contactVO);
					} else {
						updateVO(contactVO);
					}
				}
				for (RoomingListLandOperatorContVO contactVO : landOprVO.getLandOperatorContAUDList().getDelList()) {
					deleteVO(contactVO);
				}
			}
		}

		if (CollectionUtils.isNotEmpty(landOprAUDList.getDelList())) {
			for (RoomingListLandOperatorVO vo : landOprAUDList.getDelList()) {
				deleteVO(vo);
				for (RoomingListLandOperatorContVO contactVO : vo.getRoomingListLandOprContList()) {
					deleteVO(contactVO);
				}
			}
		}
	}
	
	@Override
	public List<RoomingListLandOperatorVO> getSupplierList(Long idTourDep, boolean isExcel) throws BusinessException {
		List<RoomingListLandOperatorVO> roomingListLandOprVOList = roomingListLandOperatorDAO.getSupplierList(idTourDep);
		for (RoomingListLandOperatorVO landOprVO : roomingListLandOprVOList) {
			landOprVO.setLandOperatorContAUDList(new GenAddUpdDelVO<RoomingListLandOperatorContVO>());
			landOprVO.setRoomingListLandOprContList(roomingListLandOperatorDAO.getContactList(landOprVO.getId()));
			
			for (RoomingListLandOperatorContVO contactVO : landOprVO.getRoomingListLandOprContList()) {
				String tempCode = "";
				if (StringUtils.equals(contactVO.getContactType(), "mobile")) {
					tempCode = "(M):";
				} else if (StringUtils.equals(contactVO.getContactType(), "fax")) {
					tempCode = "(F):";
				} else if (StringUtils.equals(contactVO.getContactType(), "home")) {
					tempCode = "(H):";
				} else if (StringUtils.equals(contactVO.getContactType(), "office")) {
					tempCode = "(O):";
				}
				
				if(!isExcel)
					landOprVO.setContactNo(StringUtils.defaultIfBlank(landOprVO.getContactNo(), "")
							+ tempCode + contactVO.getContactNo() + "<br/>");
				else
					landOprVO.setContactNo(StringUtils.defaultIfBlank(landOprVO.getContactNo(), "")
							+ tempCode + contactVO.getContactNo() + "\n");
			}
		}
		return roomingListLandOprVOList;
	}
	
	@Override
	public List<RoomingListLandOperatorContVO> getContactList(Long idLandOperator) throws BusinessException {
		return roomingListLandOperatorDAO.getContactList(idLandOperator);
	}
}
