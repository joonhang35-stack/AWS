package com.bcs.zsg.maintenance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.dao.LookUpDAO;
import com.bcs.zsg.maintenance.vo.LookupCategoryVO;
import com.bcs.zsg.maintenance.vo.LookupItemVO;
import com.bcs.zsg.maintenance.vo.LookupItemViewVO;

public class LookUpsServiceImpl implements LookUpsService {
	@Autowired
	private LookUpDAO lookUpDAO;

	@Override
	public void addLookUpCategory(LookupCategoryVO lookupCategoryVO)
			throws BusinessException {
		// TODO Auto-generated method stub
		lookUpDAO.insert(lookupCategoryVO);
	}

	@Override
	public List<LookupCategoryVO> getLookUpList() throws BusinessException {
		// TODO Auto-generated method stub
		return lookUpDAO.getLookUpList();
	}

	

	@Override
	public void deleteLookUp(LookupCategoryVO lookupCategoryVO)
			throws BusinessException {
		// TODO Auto-generated method stub
		lookUpDAO.delete(lookupCategoryVO);
	}

	@Override
	public void updateLookUp(LookupCategoryVO lookupCategoryVO)
			throws BusinessException {
		// TODO Auto-generated method stub
		lookUpDAO.update(lookupCategoryVO);
	}

	@Override
	public void deleteLookUpItem(LookupItemVO lookupItemVO)
			throws BusinessException {
		// TODO Auto-generated method stub
		lookUpDAO.delete(lookupItemVO);
	}

	

	@Override
	public void updateLookUpItem(LookupItemVO lookupItemVO)
			throws BusinessException {
		// TODO Auto-generated method stub
		lookUpDAO.update(lookupItemVO);
	}

	@Override
	public List<LookupItemViewVO> getLookUpItemList() throws BusinessException {
		// TODO Auto-generated method stub
		return lookUpDAO.getLookUpItemList();
	}

	

	@Override
	public List<LookupItemViewVO> getSearchLookUpItemViewList(LookupCategoryVO lookupCategoryVO)
			throws BusinessException {
		// TODO Auto-generated method stub
		return lookUpDAO.getSearchLookUpItemViewList(lookupCategoryVO);
	}

	@Override
	public List<LookupCategoryVO> getSearchLookUpCategoryList(LookupCategoryVO lookupCategoryVO)
			throws BusinessException {
		// TODO Auto-generated method stub
		return lookUpDAO.getSearchLookUpCategoryList(lookupCategoryVO);
	}

	@Override
	public List<LookupCategoryVO> getLookUpList1()
			throws BusinessException {
		// TODO Auto-generated method stub
		return lookUpDAO.getLookUpList1();
	}

	@Override
	public void addLookUpItem(LookupItemVO lookupItemVO)
			throws BusinessException {
		// TODO Auto-generated method stub
		lookUpDAO.insert(lookupItemVO);
	}

	@Override
	public List<LookupItemVO> getlookUpCodeList(String lookupCatCd)
			throws BusinessException {
		// TODO Auto-generated method stub
		return lookUpDAO.getLookUpCodeList(lookupCatCd);
	}

	@Override
	public List<LookupItemVO> getLookItemList() throws BusinessException {
		// TODO Auto-generated method stub
		return lookUpDAO.getLookItemList();
	}

}
