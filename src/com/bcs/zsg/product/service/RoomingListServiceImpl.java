
package com.bcs.zsg.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.vo.BaseVO;
import com.bcs.zsg.product.dao.RoomingListDAO;
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

public class RoomingListServiceImpl implements RoomingListService {
	@Autowired
	private RoomingListDAO roomingListDAO;

	public RoomingListVO getRoomingListByCategory(Long idTourDep, String strCategory) throws BusinessException {
		return roomingListDAO.getRoomingListByCategory(idTourDep,strCategory);
	}

	public void insertVO(BaseVO baseVO) throws BusinessException {
		roomingListDAO.insert(baseVO);
	}


	public void updateVO(BaseVO vo) throws BusinessException { 
		roomingListDAO.update(vo);
	}
	 
	public void deleteVO(BaseVO vo) throws BusinessException {
		roomingListDAO.delete(vo);
	}

	public List<RoomingListVO> getPICList(Long idTourDep) throws BusinessException {
		return roomingListDAO.getPICList(idTourDep);
	}
	
	@Override
	public List<RoomingListVO> getTourLeaderList(Long idTourDep) throws BusinessException {
		return roomingListDAO.getTourLeaderList(idTourDep);
	}
	
	@Override
	public List<RoomingListVO> getIdHotelList(Long idTourDep) throws BusinessException {
		return roomingListDAO.getIdHotelList(idTourDep);
	}
	
	@Override
	public List<RoomingListVO> getTourManagerList(Long idTourDep) throws BusinessException {
		return roomingListDAO.getTourManagerList(idTourDep);
	}

	@Override
	public AddressVO getAddressVO(Long personId) throws BusinessException {
		// TODO Auto-generated method stub
		return roomingListDAO.getAddressVO(personId);
	}

	@Override
	public List<PersonContactVO> getPersonContList(Long personId)
			throws BusinessException {
		// TODO Auto-generated method stub
		return roomingListDAO.getPersonContList(personId);
	}

	@Override
	public List<InvoiceVO> getInvoiceList(Long tourDepId)
			throws BusinessException {
		// TODO Auto-generated method stub
		return roomingListDAO.getInvoiceList(tourDepId);
	}

	@Override
	public PersonVO getPerson(Long persCorpId) throws BusinessException {
		// TODO Auto-generated method stub
		return roomingListDAO.getPerson(persCorpId);
	}

	@Override
	public List<InvoicePaxVO> getInvoicePax(Long invId) throws BusinessException {
		// TODO Auto-generated method stub
		return roomingListDAO.getInvoicePax(invId);
	}

	@Override
	public IdentityVO getPersonIdentity(Long id,String typeCd) throws BusinessException {
		// TODO Auto-generated method stub
		return roomingListDAO.getPersonIdentity(id,typeCd);
	}

	@Override
	public IdentityDetailVO getPersonIdentityDetails(Long id)
			throws BusinessException {
		// TODO Auto-generated method stub
		return roomingListDAO.getPersonIdentityDetails(id);
	}

	@Override
	public List<PersonLangVO> getpersonLang(Long id) throws BusinessException {
		// TODO Auto-generated method stub
		return roomingListDAO.getpersonLang(id);
	}

	@Override
	public List<PersonMealVO> getpersonMeal(Long id) throws BusinessException {
		// TODO Auto-generated method stub
		return roomingListDAO.getpersonMeal(id);
	}

	@Override
	public CorporateVO getCorporateVO(Long personId) throws BusinessException {
		// TODO Auto-generated method stub
		return roomingListDAO.getCorporateVO(personId);
	}

	@Override
	public CorAddressVO getCorpAddrVO(Long id) throws BusinessException {
		// TODO Auto-generated method stub
		return roomingListDAO.getCorpAddrVO(id);
	}

	

	@Override
	public List<Object> getuniqueList() throws BusinessException {
		// TODO Auto-generated method stub
		return roomingListDAO.getuniqueList();
	}
	
}
