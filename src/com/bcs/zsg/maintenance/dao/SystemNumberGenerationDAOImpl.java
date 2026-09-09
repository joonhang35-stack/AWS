package com.bcs.zsg.maintenance.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import com.bcs.zsg.core.dao.BaseHibernateDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.vo.SystemNumberGenerationVO;

public class SystemNumberGenerationDAOImpl extends BaseHibernateDAO implements SystemNumberGenerationDAO 
{

	@SuppressWarnings("unchecked")
	@Override
	public List<SystemNumberGenerationVO> getSystemNumberGenerationList(Long idCompany)
			throws BusinessException {
		// TODO Auto-generated method stub
		Criteria criteria = createCriteria(SystemNumberGenerationVO.class);
		criteria.add(Restrictions.eq("idCompany",idCompany));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SystemNumberGenerationVO> getSysGenDefaultList(int isDefault) {
		// TODO Auto-generated method stub
		Criteria criteria = createCriteria(SystemNumberGenerationVO.class);
		criteria.add(Restrictions.eq("isDefault",isDefault));
		return criteria.list();
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.maintenance.dao.SystemNumberGenerationDAO#getSysNumGen(com.bcs.zsg.maintenance.vo.SystemNumberGenerationVO)
	 */
	@Override
	public SystemNumberGenerationVO getSysNumGen(SystemNumberGenerationVO systemNumberGenerationVO) throws BusinessException {
		Criteria criteria = createCriteria(SystemNumberGenerationVO.class);
		criteria.add(Restrictions.eq("code", systemNumberGenerationVO.getCode()));
		criteria.add(Restrictions.eq("idCompany", systemNumberGenerationVO.getIdCompany()));
		return (SystemNumberGenerationVO) criteria.uniqueResult();
	}

	/* (non-Javadoc)
	 * @see com.bcs.zsg.maintenance.dao.SystemNumberGenerationDAO#getSystemNumberGeneration(java.lang.String, java.lang.Long)
	 */
	//@SuppressWarnings("deprecation")
	@Override
	public SystemNumberGenerationVO getSystemNumberGeneration(String sysCd, Long idCompany) throws BusinessException {
		Criteria criteria = createCriteria(SystemNumberGenerationVO.class);
		criteria.add(Restrictions.eq("code", sysCd));
		criteria.add(Restrictions.eq("idCompany", idCompany));
		return (SystemNumberGenerationVO) criteria.uniqueResult();
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.maintenance.dao.SystemNumberGenerationDAO#getSystemNumberGeneration(java.lang.Long)
	 */
	@Override
	public SystemNumberGenerationVO getTestSystemNumberGeneration(String sysCd, Long id) throws BusinessException {
		Query query = createQuery("FROM SystemNumberGenerationVO s WHERE s.code = :code AND s.idCompany = :idCompany");
		query.setParameter("code", sysCd);
		query.setParameter("idCompany", id);
		query.setLockOptions(LockOptions.UPGRADE);
		SystemNumberGenerationVO vo = (SystemNumberGenerationVO) query.uniqueResult();
		vo.setNextnumber(vo.getNextnumber() + 1);
		return vo;
	}
}