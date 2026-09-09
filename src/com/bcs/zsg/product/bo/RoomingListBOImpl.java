package com.bcs.zsg.product.bo;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.common.vo.AddUpdDelVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.product.service.RoomingListService;
import com.bcs.zsg.product.vo.RoomingListVO;
import com.bcs.zsg.purchase.vo.AddressVO;
import com.bcs.zsg.purchase.vo.CorAddressVO;
import com.bcs.zsg.purchase.vo.CorporateVO;
import com.bcs.zsg.purchase.vo.IdentityVO;
import com.bcs.zsg.purchase.vo.PersonVO;
import com.bcs.zsg.sales.vo.IdentityDetailVO;
import com.bcs.zsg.sales.vo.InvoicePaxVO;
import com.bcs.zsg.sales.vo.InvoiceVO;
import com.bcs.zsg.sales.vo.PersonContactVO;
import com.bcs.zsg.sales.vo.PersonLangVO;
import com.bcs.zsg.sales.vo.PersonMealVO;

import com.bcs.zsg.core.vo.BaseVO;

public class RoomingListBOImpl implements RoomingListBO{

	@Autowired
	private RoomingListService roomingListService;

	@Override
	public RoomingListVO getRoomingListByCategory(Long idTourDep, String strCategory) throws BusinessException {
		return roomingListService.getRoomingListByCategory(  idTourDep,strCategory);
	}

	@Override
	public void insertVO(BaseVO baseVO) throws BusinessException { 
		roomingListService.insertVO(baseVO);
	}
	
	@Override
	public void updRoomingList(AddUpdDelVO roomingListAUDVO) throws BusinessException { 
		// update tour dep item
		if (CollectionUtils.isNotEmpty(roomingListAUDVO.getUpdList())) { 
			for (Object obj : roomingListAUDVO.getUpdList()) roomingListService.updateVO((RoomingListVO) obj);
		} 
		if (CollectionUtils.isNotEmpty(roomingListAUDVO.getAddList())) { 
			for (Object obj : roomingListAUDVO.getAddList()) roomingListService.insertVO((RoomingListVO) obj);
		} 

		if (CollectionUtils.isNotEmpty(roomingListAUDVO.getDelList())) { 
			for (Object obj : roomingListAUDVO.getDelList()) roomingListService.deleteVO((RoomingListVO) obj);
		} 
		  
	} 
	
	@Override
	public List<RoomingListVO> getPICList(Long idTourDep) throws BusinessException {
		return roomingListService.getPICList(  idTourDep);
	}
	
	@Override
	public List<RoomingListVO> getIdHotelList(Long idTourDep) throws BusinessException {
		return roomingListService.getIdHotelList(  idTourDep);
	}
	
	@Override
	public List<RoomingListVO> getTourLeaderList(Long idTourDep) throws BusinessException {
		return roomingListService.getTourLeaderList(idTourDep);
	}
	
	@Override
	public List<RoomingListVO> getTourManagerList(Long idTourDep) throws BusinessException {
		return roomingListService.getTourManagerList(idTourDep);
	}

	@Override
	public AddressVO getAddressVO(Long personId) throws BusinessException {
		// TODO Auto-generated method stub
		return roomingListService.getAddressVO(personId);
	}

	@Override
	public List<PersonContactVO> getPersonContList(Long personId)
			throws BusinessException {
		// TODO Auto-generated method stub
		return roomingListService.getPersonContList(personId);
	}

	@Override
	public List<InvoiceVO> getInvoiceList(Long tourDepId)
			throws BusinessException {
		// TODO Auto-generated method stub
		return roomingListService.getInvoiceList(tourDepId);
	}

	@Override
	public PersonVO getPerson(Long persCorpId) throws BusinessException {
		// TODO Auto-generated method stub
		return roomingListService.getPerson(persCorpId);
	}

	@Override
	public List<InvoicePaxVO> getInvoicePax(Long invId) throws BusinessException {
		// TODO Auto-generated method stub
		return roomingListService.getInvoicePax(invId);
	}

	@Override
	public IdentityVO getPersonIdentity(Long id,String typeCd) throws BusinessException {
		// TODO Auto-generated method stub
		return roomingListService.getPersonIdentity(id,typeCd);
	}

	@Override
	public IdentityDetailVO getPersonIdentityDetails(Long id)
			throws BusinessException {
		// TODO Auto-generated method stub
		return roomingListService.getPersonIdentityDetails(id);
	}

	@Override
	public List<PersonLangVO> getpersonLang(Long id) throws BusinessException {
		// TODO Auto-generated method stub
		return roomingListService.getpersonLang(id);
	}

	@Override
	public List<PersonMealVO> getpersonMeal(Long id) throws BusinessException {
		// TODO Auto-generated method stub
		return roomingListService.getpersonMeal(id);
	}

	@Override
	public CorporateVO getCorporateVO(Long personId) throws BusinessException {
		// TODO Auto-generated method stub
		return roomingListService.getCorporateVO(personId);
	}

	@Override
	public CorAddressVO getCorpAddrVO(Long id) throws BusinessException {
		// TODO Auto-generated method stub
		return roomingListService.getCorpAddrVO(id);
	}


	@Override
	public List<Object> getuniqueList() throws BusinessException {
		// TODO Auto-generated method stub
		return roomingListService.getuniqueList();
	}
	
}
