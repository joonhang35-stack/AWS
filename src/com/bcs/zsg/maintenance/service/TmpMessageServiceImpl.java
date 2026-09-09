package com.bcs.zsg.maintenance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.common.helper.CommonErrConstant;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.BaseConstant;
import com.bcs.zsg.maintenance.dao.TmpMessageDAO;
import com.bcs.zsg.maintenance.vo.TmpMessageVO;

public class TmpMessageServiceImpl implements TmpMessageService {

	@Autowired
	private TmpMessageDAO tmpMsgDAO;
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.maintenance.service.TmpMessageService#getTmpMsgList(java.lang.String)
	 */
	@Override
	public List<TmpMessageVO> getTmpMsgList(String catCode) throws BusinessException {
		return tmpMsgDAO.getTmpMsgList(catCode);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.maintenance.service.TmpMessageService#insertTmpMsg(com.bcs.zsg.maintenance.vo.TmpMessageVO)
	 */
	@Override
	public void insertTmpMsg(TmpMessageVO tmpMsgVO) throws BusinessException {
		if (tmpMsgDAO.isCodeDuplicated(tmpMsgVO)) throw new BusinessException(CommonErrConstant.ERR_TMP_MSG_CODE_DUPLICATED);
		tmpMsgVO.setStatusCode(BaseConstant.STATUS_ACTIVE);
		tmpMsgDAO.insert(tmpMsgVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.maintenance.service.TmpMessageService#updateTmpMsg(com.bcs.zsg.maintenance.vo.TmpMessageVO)
	 */
	@Override
	public void updateTmpMsg(TmpMessageVO tmpMsgVO) throws BusinessException {
		if (tmpMsgDAO.isCodeDuplicated(tmpMsgVO)) throw new BusinessException(CommonErrConstant.ERR_TMP_MSG_CODE_DUPLICATED);
		tmpMsgDAO.update(tmpMsgVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.maintenance.service.TmpMessageService#deleteTmpMsg(com.bcs.zsg.maintenance.vo.TmpMessageVO)
	 */
	@Override
	public void deleteTmpMsg(TmpMessageVO tmpMsgVO) throws BusinessException {
		tmpMsgVO.setStatusCode(BaseConstant.STATUS_DELETED);
		tmpMsgDAO.update(tmpMsgVO);
	}

	@Override
	public List<TmpMessageVO> getTNCMsgWithCompanyId(String catCode, Long companyId) throws BusinessException {
		return tmpMsgDAO.getTNCMsgWithCompanyId(catCode, companyId);
	}
	
	@Override
	public List<TmpMessageVO> getBankDetailsMsgWithCompanyId(String catCode, Long companyId) throws BusinessException {
		return tmpMsgDAO.getBankDetailsMsgWithCompanyId(catCode, companyId);
	}

	@Override
	public List<TmpMessageVO> getFooterMsgWithCompanyId(String catCode, Long companyId) throws BusinessException {
		return tmpMsgDAO.getFooterMsgWithCompanyId(catCode, companyId);
	}

}
