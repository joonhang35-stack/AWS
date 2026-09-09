package com.bcs.zsg.maintenance.dao;

import java.util.List;

import com.bcs.zsg.core.dao.BaseDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.vo.TmpMessageVO;

public interface TmpMessageDAO extends BaseDAO {

	/**
	 * 
	 * @param catCode
	 * @return
	 * @throws BusinessException
	 */
	public List<TmpMessageVO> getTmpMsgList(String catCode) throws BusinessException;

	/**
	 * 
	 * @param tmpMsgVO
	 * @return
	 * @throws BusinessException
	 */
	public boolean isCodeDuplicated(TmpMessageVO tmpMsgVO) throws BusinessException;

	public List<TmpMessageVO> getTNCMsgWithCompanyId(String catCode, Long companyId) throws BusinessException;

	public List<TmpMessageVO> getBankDetailsMsgWithCompanyId(String catCode, Long companyId) throws BusinessException;

	public List<TmpMessageVO> getFooterMsgWithCompanyId(String catCode, Long companyId) throws BusinessException;

}
