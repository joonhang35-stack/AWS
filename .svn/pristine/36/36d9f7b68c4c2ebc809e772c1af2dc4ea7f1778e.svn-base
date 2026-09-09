package com.bcs.zsg.maintenance.bo;

import java.util.List;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.vo.LookupCategoryVO;
import com.bcs.zsg.maintenance.vo.LookupItemVO;
import com.bcs.zsg.maintenance.vo.LookupItemViewVO;

public interface LookUpBO 
{
	public void addLookUpCategory(LookupCategoryVO lookupCategoryVO) throws BusinessException;

	public List<LookupCategoryVO> getLookUpList1() throws BusinessException;

	public List<LookupCategoryVO> getLookUpList() throws BusinessException;

	public List<LookupItemViewVO> getLookUpItemList() throws BusinessException;
	
	public List<LookupCategoryVO> getSearchLookUpCategoryList(LookupCategoryVO lookupCategoryVO) throws BusinessException;
	
	public List<LookupItemViewVO> getSearchLookUpItemViewList(LookupCategoryVO lookupCategoryVO) throws BusinessException;
	
	public void deleteLookUp(LookupCategoryVO lookupCategoryVO) throws BusinessException;
	
	public void deleteLookUpItem(LookupItemVO lookupItemVO) throws BusinessException;
	
	public void updateLookUp(LookupCategoryVO lookupCategoryVO) throws BusinessException;

	public void updateLookUpItem(LookupItemVO lookupItemVO) throws BusinessException;

	public void addLookUpItem(LookupItemVO lookupItemVO) throws BusinessException;

	public List<LookupItemVO> getLookUpCodeList(String lookupCatCd) throws BusinessException;

	public List<LookupItemVO> getLookItemList() throws BusinessException;
 
}
