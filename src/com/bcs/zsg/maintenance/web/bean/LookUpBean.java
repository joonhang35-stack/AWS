package com.bcs.zsg.maintenance.web.bean;


import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.common.helper.CommonErrConstant;
import com.bcs.zsg.common.web.bean.AppBackingBean;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.bo.LookUpBO;
import com.bcs.zsg.maintenance.vo.LookupCategoryVO;
import com.bcs.zsg.maintenance.vo.LookupItemVO;
import com.bcs.zsg.maintenance.vo.LookupItemViewVO;




public class LookUpBean extends AppBackingBean 
{
	private static final long serialVersionUID = 1L;
	@Autowired
	private transient LookUpBO lookUpBO;

	private LookupCategoryVO lookupCategoryVO;
	private LookupItemVO lookupItemVO;
	private LookupItemViewVO lookupItemViewVO;

	private List<LookupCategoryVO> lookupCategoryList;
	private List<LookupCategoryVO> lookupCategoryAllList;
	private List<LookupItemVO> lookupItemList;
	private List<LookupItemViewVO> searchlookupList;
	private List<LookupItemVO> lookUpCodeList;
	private List<LookupItemVO> lookUpItemSearchList;
	
	String temp;

	@Override
	public void resetForm() 
	{
		lookupCategoryVO = new LookupCategoryVO();
		lookupItemVO =new LookupItemVO();
	}

	public void resetLookupItemViewForm()
	{
		lookupItemViewVO=new LookupItemViewVO();
	}

	public void init() throws BusinessException{
		try {
			resetForm();
			resetLookupItemViewForm();
			loadLookup();
			initSearchParam();
			searchParamVO.setObj1(null);

		}catch (Throwable t) {
			errorResult(t);
		}
	}

	public void addLookUp() throws BusinessException 
	{
		lookUpBO.addLookUpCategory(lookupCategoryVO);
		resetForm();
		loadLookup();	
		successResult();
	}

	public void addLookUpItem() throws BusinessException 
	{
		try{
			lookUpCodeList=lookUpBO.getLookUpCodeList(lookupItemVO.getLookupCatCd());
			for(LookupItemVO vo : lookUpCodeList)
			{
				if(vo.getCode().toString().equalsIgnoreCase(lookupItemVO.getCode().toString()))
				{
					throw new BusinessException(CommonErrConstant.ERR_LOOKUP_LOOKUPITEM_CODE_DUPLICATE);
				}
			}
		
			lookUpBO.addLookUpItem(lookupItemVO);
			
			
			//resetForm();
			//loadLookup();
			search();
			lookupItemVO =new LookupItemVO();
			if(lookupItemViewVO.getLookupCategoryVO()!=null)
			lookupItemVO.setLookupCatCd(lookupItemViewVO.getLookupCategoryVO().getCode());
			successResult();
		}catch(Throwable t){
			errorResult(t);
		}
	}

	private void loadLookup() throws BusinessException {
		try {
			lookupCategoryAllList=lookUpBO.getLookUpList1();
			search();
			resetForm();

		}catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
	}

	public void search() throws BusinessException 
	{
		if (lookupItemViewVO.getLookupCategoryVO()==null) 
		{
			resetFilteredObjList();
			lookupCategoryList=lookUpBO.getLookUpList();
			lookUpItemSearchList=lookUpBO.getLookItemList();
			searchlookupList=lookUpBO.getLookUpItemList();
			for(int j=0;j<lookUpItemSearchList.size();j++)
			{
				lookUpItemSearchList.get(j).setCatDesc(searchlookupList.get(j).getLookupCategoryVO().getDescription());
				
			}	
		}
		else 
		{
			resetFilteredObjList();
			lookupCategoryList=lookUpBO.getSearchLookUpCategoryList(lookupItemViewVO.getLookupCategoryVO());
			lookUpItemSearchList=lookUpBO.getLookUpCodeList(lookupItemViewVO.getLookupCategoryVO().getCode());
			searchlookupList=lookUpBO.getSearchLookUpItemViewList(lookupItemViewVO.getLookupCategoryVO());
			for(int j=0;j<lookUpItemSearchList.size();j++)
			{
				lookUpItemSearchList.get(j).setCatDesc(searchlookupList.get(j).getLookupCategoryVO().getDescription());
				
			}	
		}
	}

	public Converter getLookupCategoryConverter() {
		return new Converter() {

			@Override
			public Object getAsObject(FacesContext context, UIComponent component, String s) {
				if (s != null) {
					for (LookupCategoryVO vo : lookupCategoryAllList) {
						if (String.valueOf(vo.getCode()).equals(s)) {
							return vo;
						}
					}
				}
				return null;
			}

			@Override
			public String getAsString(FacesContext context, UIComponent component, Object obj) {
				if (obj != null) return ((LookupCategoryVO) obj).getCode().toString();
				return null;
			}

		};
	}


	public void onLookUpSelected(LookupCategoryVO lookupCategoryVO) throws BusinessException{
		try {
			this.lookupCategoryVO = lookupCategoryVO;

		}catch (Throwable t) {
			errorResult(t);
		}
	}

	public void onLookUpItemSelected(LookupItemVO lookupItemVO) throws BusinessException{
		try {
			this.lookupItemVO=lookupItemVO;
			temp=lookupItemVO.getCode();

		}catch (Throwable t) {
			errorResult(t);
		}
	}

	public void deleteLookUp() throws BusinessException{
		try {
			lookUpBO.deleteLookUp(lookupCategoryVO);
			search();
			//loadLookup();

		} catch (Throwable t){
			errorResult(t);
		}
	}

	public void deleteLookUpItem() throws BusinessException{
		try {
			lookUpBO.deleteLookUpItem(lookupItemVO);
			search();
			//loadLookup();

		} catch (Throwable t){
			errorResult(t);
		}
	}

	public void editLookUp() throws BusinessException{
		try {
			lookUpBO.updateLookUp(lookupCategoryVO);
			loadLookup();
			resetForm();
			successResult();

		} catch (Throwable t) {
			errorResult(t);
		}
	}

	public void editLookUpItem() throws BusinessException{
		try {
			if(temp.equalsIgnoreCase(lookupItemVO.getCode().toString()))
			{
				lookUpBO.updateLookUpItem(lookupItemVO);
			}
			else
			{
				lookUpCodeList=lookUpBO.getLookUpCodeList(lookupItemVO.getLookupCatCd());
				for(LookupItemVO vo : lookUpCodeList)
				{
					if(vo.getCode().toString().equalsIgnoreCase(lookupItemVO.getCode().toString()))
					{
						throw new BusinessException(CommonErrConstant.ERR_LOOKUP_LOOKUPITEM_CODE_DUPLICATE);
					}
				}
				lookUpBO.updateLookUpItem(lookupItemVO);
			}
			loadLookup();
			resetForm();
			successResult();

		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void onSetSeqNo() throws BusinessException
	{
		if(lookupItemViewVO.getLookupCategoryVO()!=null)
			lookupItemVO.setLookupCatCd(lookupItemViewVO.getLookupCategoryVO().getCode());
		else
			lookupItemVO.setLookupCatCd(lookupCategoryAllList.get(0).getCode());
		lookupItemVO.setSeqNo(1);
	}

	public LookupCategoryVO getLookupCategoryVO() 
	{
		return lookupCategoryVO;
	}

	public void setLookupCategoryVO(LookupCategoryVO lookupCategoryVO) 
	{
		this.lookupCategoryVO = lookupCategoryVO;
	}

	public List<LookupCategoryVO> getLookupCategoryList() {
		return lookupCategoryList;
	}

	public void setLookupCategoryList(List<LookupCategoryVO> lookupCategoryList) {
		this.lookupCategoryList = lookupCategoryList;
	}



	public LookupItemVO getLookupItemVO() {
		return lookupItemVO;
	}

	public void setLookupItemVO(LookupItemVO lookupItemVO) {
		this.lookupItemVO = lookupItemVO;
	}



	public List<LookupItemVO> getLookupItemList() {
		return lookupItemList;
	}

	public void setLookupItemList(List<LookupItemVO> lookupItemList) {
		this.lookupItemList = lookupItemList;
	}

	public LookupItemViewVO getLookupItemViewVO() {
		return lookupItemViewVO;
	}

	public void setLookupItemViewVO(LookupItemViewVO lookupItemViewVO) {
		this.lookupItemViewVO = lookupItemViewVO;
	}

	public List<LookupItemViewVO> getSearchlookupList() {
		return searchlookupList;
	}

	public void setSearchlookupList(List<LookupItemViewVO> searchlookupList) {
		this.searchlookupList = searchlookupList;
	}

	public List<LookupCategoryVO> getlookupCategoryAllList() {
		return lookupCategoryAllList;
	}

	public void setlookupCategoryAllList(List<LookupCategoryVO> lookupCategoryAllList) {
		this.lookupCategoryAllList = lookupCategoryAllList;
	}

	public List<LookupItemVO> getLookUpCodeList() {
		return lookUpCodeList;
	}

	public void setLookUpCodeList(List<LookupItemVO> lookUpCodeList) {
		this.lookUpCodeList = lookUpCodeList;
	}

	/**
	 * @return the temp
	 */
	public String getTemp() {
		return temp;
	}

	/**
	 * @param temp the temp to set
	 */
	public void setTemp(String temp) {
		this.temp = temp;
	}

	/**
	 * @return the lookUpItemSearchList
	 */
	public List<LookupItemVO> getLookUpItemSearchList() {
		return lookUpItemSearchList;
	}

	/**
	 * @param lookUpItemSearchList the lookUpItemSearchList to set
	 */
	public void setLookUpItemSearchList(List<LookupItemVO> lookUpItemSearchList) {
		this.lookUpItemSearchList = lookUpItemSearchList;
	}
	
	
}
