package com.bcs.zsg.maintenance.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.service.TmpMessageService;
import com.bcs.zsg.maintenance.vo.TmpMessageVO;

public class TmpMessageBOImpl implements TmpMessageBO {

	@Autowired
	private TmpMessageService tmpMsgService;
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.maintenance.bo.TmpMessageBO#getTmpMsgList(java.lang.String)
	 */
	@Override
	public List<TmpMessageVO> getTmpMsgList(String catCode) throws BusinessException {
		return tmpMsgService.getTmpMsgList(catCode);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.maintenance.bo.TmpMessageBO#insertTmpMsg(com.bcs.zsg.maintenance.vo.TmpMessageVO)
	 */
	@Override
	public void insertTmpMsg(TmpMessageVO tmpMsgVO) throws BusinessException {
		tmpMsgService.insertTmpMsg(tmpMsgVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.maintenance.bo.TmpMessageBO#updateTmpMsg(com.bcs.zsg.maintenance.vo.TmpMessageVO)
	 */
	@Override
	public void updateTmpMsg(TmpMessageVO tmpMsgVO) throws BusinessException {
		tmpMsgService.updateTmpMsg(tmpMsgVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.maintenance.bo.TmpMessageBO#deleteTmpMsg(com.bcs.zsg.maintenance.vo.TmpMessageVO)
	 */
	@Override
	public void deleteTmpMsg(TmpMessageVO tmpMsgVO) throws BusinessException {
		tmpMsgService.deleteTmpMsg(tmpMsgVO);
	}
	
	@Override
	public List<TmpMessageVO> getTNCMsgWithCompanyId(String catCode, Long companyId) throws BusinessException {
		return tmpMsgService.getTNCMsgWithCompanyId(catCode, companyId);
	}

	@Override
	public List<TmpMessageVO> getBankDetailsMsgWithCompanyId(String catCode, Long companyId) throws BusinessException {
		return tmpMsgService.getBankDetailsMsgWithCompanyId(catCode, companyId);
	}

	@Override
	public List<TmpMessageVO> getFooterMsgWithCompanyId(String catCode, Long companyId) throws BusinessException {
		return tmpMsgService.getFooterMsgWithCompanyId(catCode, companyId);
	}

}
