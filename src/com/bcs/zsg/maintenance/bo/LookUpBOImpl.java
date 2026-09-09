package com.bcs.zsg.maintenance.bo;

import static com.bcs.zsg.common.helper.LookupItemConstant.CACHE_KEY_PREFIX;
import static com.bcs.zsg.core.helper.BaseConstant.PAD_SLASH;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.core.cache.service.CacheService;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.service.LookUpsService;
import com.bcs.zsg.maintenance.vo.LookupCategoryVO;
import com.bcs.zsg.maintenance.vo.LookupItemVO;
import com.bcs.zsg.maintenance.vo.LookupItemViewVO;

public class LookUpBOImpl implements LookUpBO 
{
	@Autowired
	private LookUpsService lookUpsService;
	
	@Autowired
	protected CacheService cacheService;
	
	@Override
	public void addLookUpCategory(LookupCategoryVO lookupCategoryVO)
			throws BusinessException {
		
		// TODO Auto-generated method stub
		lookUpsService.addLookUpCategory(lookupCategoryVO);
	}
	
	@Override
	public List<LookupCategoryVO> getLookUpList() throws BusinessException {
		// TODO Auto-generated method stub
		return lookUpsService.getLookUpList();
	}
	
	@Override
	public void deleteLookUp(LookupCategoryVO lookupCategoryVO)
			throws BusinessException {
		// TODO Auto-generated method stub
		lookUpsService.deleteLookUp(lookupCategoryVO);
	}
	
	@Override
	public void updateLookUp(LookupCategoryVO lookupCategoryVO)
			throws BusinessException {
		// TODO Auto-generated method stub
		lookUpsService.updateLookUp(lookupCategoryVO);
	}
	
	@Override
	public void deleteLookUpItem(LookupItemVO lookupItemVO)
			throws BusinessException {
		lookUpsService.deleteLookUpItem(lookupItemVO);
		// clear cache lookup item
		cacheService.remove(CACHE_KEY_PREFIX + PAD_SLASH + lookupItemVO.getLookupCatCd());
	}
	
	@Override
	public void updateLookUpItem(LookupItemVO lookupItemVO)
			throws BusinessException {
		lookUpsService.updateLookUpItem(lookupItemVO);
		// clear cache lookup item
		cacheService.remove(CACHE_KEY_PREFIX + PAD_SLASH + lookupItemVO.getLookupCatCd());
	}
	
	@Override
	public List<LookupItemViewVO> getLookUpItemList() throws BusinessException {
		// TODO Auto-generated method stub
		return lookUpsService.getLookUpItemList();
	}
	
	@Override
	public List<LookupItemViewVO> getSearchLookUpItemViewList(LookupCategoryVO lookupCategoryVO)
			throws BusinessException {
		// TODO Auto-generated method stub
		return lookUpsService.getSearchLookUpItemViewList(lookupCategoryVO);
	}
	
	@Override
	public List<LookupCategoryVO> getSearchLookUpCategoryList(LookupCategoryVO lookupCategoryVO)
			throws BusinessException {
		// TODO Auto-generated method stub
		return lookUpsService.getSearchLookUpCategoryList(lookupCategoryVO);
	}
	
	@Override
	public List<LookupCategoryVO> getLookUpList1()
			throws BusinessException {
		// TODO Auto-generated method stub
		return lookUpsService.getLookUpList1();
	}
	
	@Override
	public void addLookUpItem(LookupItemVO lookupItemVO)
			throws BusinessException {
		lookUpsService.addLookUpItem(lookupItemVO);
		// clear cache lookup item
		cacheService.remove(CACHE_KEY_PREFIX + PAD_SLASH + lookupItemVO.getLookupCatCd());
	}

	@Override
	public List<LookupItemVO> getLookUpCodeList(String lookupCatCd)
			throws BusinessException {
		// TODO Auto-generated method stub
		return lookUpsService.getlookUpCodeList(lookupCatCd);
	}

	@Override
	public List<LookupItemVO> getLookItemList() throws BusinessException {
		// TODO Auto-generated method stub
		return lookUpsService.getLookItemList();
	}
}
