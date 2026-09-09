package com.bcs.zsg.maintenance.web.bean;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.common.helper.LookupItemConstant;
import com.bcs.zsg.common.web.bean.AppBackingBean;
import com.bcs.zsg.maintenance.bo.TmpMessageBO;
import com.bcs.zsg.maintenance.vo.TmpMessageVO;

public class TmpMessageBean extends AppBackingBean {
	private static final long serialVersionUID = 1L;

	@Autowired
	private transient TmpMessageBO tmpMsgBO;
	
	private List<TmpMessageVO> tmpMsgList;
	private TmpMessageVO tmpMsgVO;
	
	/**
	 * 
	 */
	public void init() {
		try {
			initSearchParam();
//			CommonConstant.DEF_TOUR_MSG_CAT_CD
			searchParamVO.setObj1(LookupItemConstant.INV_CAT_TOUR);
			
		} catch (Throwable t) {
			errorResult(t);
		}
		
		loadList();
	}
	
	public void onCategorySelected() {
		loadList();
	}
	
	/**
	 * 
	 */
	public void addTmpMsg() {
		try {
			tmpMsgBO.insertTmpMsg(tmpMsgVO);
			successResult();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * 
	 */
	public void updTmpMsg() {
		try {
			tmpMsgBO.updateTmpMsg(tmpMsgVO);
			successResult();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * 
	 */
	public void delTmpMsg() {
		try {
			tmpMsgBO.deleteTmpMsg(tmpMsgVO);
			successResult();
			
		} catch (Throwable t) {
			errorResult(t);
		}
		
		loadList();
	}
	
	/**
	 * 
	 */
	public void loadList() {
		try {
			resetFilteredObjList();
			tmpMsgList = tmpMsgBO.getTmpMsgList(searchParamVO.getObj1().toString());
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * 
	 * @param vo
	 */
	public void onTmpMsgSelected(TmpMessageVO vo) {
		try {
			tmpMsgVO = vo;
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/***************
	 * RESET FORMS *
	 ***************/
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.core.web.swf.bean.BaseBackingBean#resetForm()
	 */
	@Override
	public void resetForm() {
		tmpMsgVO = new TmpMessageVO();
	}
	
	/***********
	 * GETTERS *
	 ***********/

	/**
	 * @return the tmpMsgList
	 */
	public List<TmpMessageVO> getTmpMsgList() {
		return tmpMsgList;
	}

	/**
	 * @return the tmpMsgVO
	 */
	public TmpMessageVO getTmpMsgVO() {
		return tmpMsgVO;
	}

}
