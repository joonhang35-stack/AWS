package com.bcs.zsg.maintenance.service;

import java.util.List;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.vo.TmpMessageVO;

public interface TmpMessageService {

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
	 * @throws BusinessException
	 */
	public void insertTmpMsg(TmpMessageVO tmpMsgVO) throws BusinessException;

	/**
	 * 
	 * @param tmpMsgVO
	 * @throws BusinessException
	 */
	public void updateTmpMsg(TmpMessageVO tmpMsgVO) throws BusinessException;

	/**
	 * 
	 * @param tmpMsgVO
	 * @throws BusinessException
	 */
	public void deleteTmpMsg(TmpMessageVO tmpMsgVO) throws BusinessException;

	public List<TmpMessageVO> getTNCMsgWithCompanyId(String catCode, Long companyId) throws BusinessException;
	
	public List<TmpMessageVO> getBankDetailsMsgWithCompanyId(String catCode, Long companyId) throws BusinessException;

	public List<TmpMessageVO> getFooterMsgWithCompanyId(String catCode, Long companyId) throws BusinessException;


}
