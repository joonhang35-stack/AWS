package com.bcs.zsg.maintenance.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.bcs.zsg.acct.vo.AcctVO;
import com.bcs.zsg.acct.vo.AcctViewVO;
import com.bcs.zsg.core.dao.BaseHibernateDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.BaseConstant;
import com.bcs.zsg.db.bterp.vo.CompanyTaxVO;
import com.bcs.zsg.maintenance.vo.CompanyAddressVO;
import com.bcs.zsg.maintenance.vo.CompanyContactVO;
import com.bcs.zsg.maintenance.vo.CompanyNameVO;
import com.bcs.zsg.maintenance.vo.CompanyVO;
import com.bcs.zsg.maintenance.vo.SystemNumberGenerationVO;
import com.bcs.zsg.purchase.vo.CountryVO;



public class CorporateProfileDAOImpl extends BaseHibernateDAO implements CorporateProfileDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<CountryVO> getCountryList() throws BusinessException {
		Criteria criteria = createCriteria(CountryVO.class);
		criteria.addOrder(Order.asc("country"));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CompanyVO> getCompanyList() throws BusinessException {
		Criteria criteria = createCriteria(CompanyVO.class);
		criteria.addOrder(Order.asc("id"));
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CompanyContactVO> getCompanyContactList(Long companyid) throws BusinessException {
		Criteria criteria = getSession().createCriteria(CompanyContactVO.class);
		criteria.add(Restrictions.eq("companyid", companyid));
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CompanyNameVO> getCompanyNameList(Long companyid) throws BusinessException{
		Criteria criteria = getSession().createCriteria(CompanyNameVO.class);
		criteria.add(Restrictions.eq("idCompany", companyid));
		return criteria.list();
	}
	
	@Override
	public AcctViewVO getAcctViewListId(String code,String subCode)
			throws BusinessException {
		Criteria criteria = getSession().createCriteria(AcctViewVO.class);
		criteria.add(Restrictions.eq("code",code));
		criteria.add(Restrictions.eq("subCode",subCode));
		return (AcctViewVO)criteria.list();
	}
	
	@Override
	public AcctVO getAcctList(Long idAcctSales) throws BusinessException {
		Criteria criteria = getSession().createCriteria(AcctVO.class);
		criteria.add(Restrictions.eq("id", idAcctSales));
		return (AcctVO)criteria.uniqueResult();
	}
	
	@Override
	public CompanyAddressVO getCompanyAddress(Long id) throws BusinessException {
		Criteria criteria = getSession().createCriteria(CompanyAddressVO.class);
		criteria.add(Restrictions.eq("companyid", id));
		return (CompanyAddressVO)criteria.uniqueResult();
	}
	
	@Override
	public CompanyTaxVO getCompanyTax(Long id) throws BusinessException {
		Criteria criteria = getSession().createCriteria(CompanyTaxVO.class);
		criteria.add(Restrictions.eq("companyId", id));
		return (CompanyTaxVO)criteria.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SystemNumberGenerationVO> getSysNumGenList(Long id)
			throws BusinessException {
		Criteria criteria = getSession().createCriteria(SystemNumberGenerationVO.class);
		criteria.add(Restrictions.eq("idCompany",id));
		return criteria.list();
	}

	/* (non-Javadoc)
	 * @see com.bcs.zsg.maintenance.dao.CorporateProfileDAO#getCompanyDetails(java.lang.Long)
	 */
	@Override
	public CompanyVO getCompanyDetails(Long companyId) throws BusinessException {
		Criteria criteria = getSession().createCriteria(CompanyVO.class);
		criteria.add(Restrictions.eq("id", companyId));
		
		CompanyVO companyVO = (CompanyVO) criteria.uniqueResult();
		companyVO.setCompanyAddressVO(getCompanyAddress(companyVO.getId()));
		companyVO.setCompanyContactList(getCompanyContactList(companyVO.getId()));
		companyVO.setCompanyNameList(getCompanyNameList(companyVO.getId()));
		
		return companyVO;
	}
	
	@Override
	public boolean getAcctIdCompanyList(Long id) throws BusinessException {
	/**	Criteria criteria = getSession().createCriteria(AcctVO.class);
		criteria.add(Restrictions.eq("idCompany",id));
		criteria.add(Restrictions.eq("statusCode", BaseConstant.STATUS_ACTIVE));
		return criteria.list();
		**/
		Query qry = createQuery("FROM AcctVO WHERE id_company = :companyId AND status_cd = :statsCd");
		qry.setParameter("companyId",id);
		qry.setParameter("statsCd", BaseConstant.STATUS_ACTIVE);
		return qry.list().isEmpty(); 
	}
	
	@Override
	public boolean getAcctSubCatIdCompList(Long id)
			throws BusinessException {
		/**Criteria criteria = getSession().createCriteria(AcctSubCatVO.class);
		criteria.add(Restrictions.eq("idCompany",id));
		criteria.add(Restrictions.eq("statusCode", BaseConstant.STATUS_ACTIVE));
		return criteria.list();**/
		Query qry = createQuery("FROM AcctSubCatVO WHERE id_company = :companyId AND status_cd = :statsCd");
		qry.setParameter("companyId",id);
		qry.setParameter("statsCd", BaseConstant.STATUS_ACTIVE);
		return qry.list().isEmpty(); 
	}
	
	@Override
	public boolean getAcctTransIdCompList(Long id)
			throws BusinessException {
/**		Criteria criteria = getSession().createCriteria(AcctTransVO.class);
		criteria.add(Restrictions.eq("companyId",id));
		criteria.add(Restrictions.eq("statusCode", BaseConstant.STATUS_ACTIVE));
		return criteria.list();**/
		Query qry = createQuery("FROM AcctTransVO WHERE id_company = :companyId AND status_cd = :statsCd");
		qry.setParameter("companyId",id);
		qry.setParameter("statsCd", BaseConstant.STATUS_ACTIVE);
		return qry.list().isEmpty(); 
	}
	
	@Override
	public boolean getBankIdCompList(Long id) throws BusinessException {
		/**Criteria criteria = getSession().createCriteria(BankAcctVO.class);
		criteria.add(Restrictions.eq("idCompany",id));
		criteria.add(Restrictions.eq("statusCode", BaseConstant.STATUS_ACTIVE));
		return criteria.list();**/
		System.out.println("qry return type is ");
		Query qry = createQuery("FROM BankAcctViewVO WHERE id_company = :companyId");
		qry.setParameter("companyId",id);
		System.out.println("qry return type is "+qry.list().isEmpty());
		//qry.setParameter("statsCd", BaseConstant.STATUS_ACTIVE);
		return qry.list().isEmpty(); 
	}
	
	@Override
	public boolean getCustIdCompList(Long id) throws BusinessException {
	/**	Criteria criteria = getSession().createCriteria(CustomerVO.class);
		criteria.add(Restrictions.eq("companyId",id));
		criteria.add(Restrictions.eq("status",BaseConstant.STATUS_ACTIVE));
		return criteria.list();**/
		Query qry = createQuery("FROM CustomerVO WHERE id_company = :companyId AND status_cd = :statsCd");
		qry.setParameter("companyId",id);
		qry.setParameter("statsCd", BaseConstant.STATUS_ACTIVE);
		return qry.list().isEmpty(); 
	}
	
	@Override
	public boolean getEmpIdCompList(Long id) throws BusinessException {
		/**Criteria criteria = getSession().createCriteria(EmployeeVO.class);
		criteria.add(Restrictions.eq("companyId",id));
		return criteria.list();**/
		Query qry = createQuery("FROM EmployeeVO WHERE id_company = :companyId");
		qry.setParameter("companyId",id);
		//qry.setParameter("statsCd", BaseConstant.STATUS_ACTIVE);
		return qry.list().isEmpty(); 
	}
	
	@Override
	public boolean getExOrderBillIdCompList(Long id)
			throws BusinessException {
		/**Criteria criteria = getSession().createCriteria(ExOrderBillVO.class);
		criteria.add(Restrictions.eq("companyId",id));
		return criteria.list();**/
		Query qry = createQuery("FROM ExOrderBillVO WHERE id_company = :companyId");
		qry.setParameter("companyId",id);
		//qry.setParameter("statsCd", BaseConstant.STATUS_ACTIVE);
		return qry.list().isEmpty(); 
	}
	
	@Override
	public boolean getFinancialPeriodIdCompList(Long id)
			throws BusinessException {
		/**Criteria criteria = getSession().createCriteria(FinancialPeriodVO.class);
		criteria.add(Restrictions.eq("idCompany",id));
		return criteria.list();**/
		Query qry = createQuery("FROM FinancialPeriodVO WHERE id_company = :companyId");
		qry.setParameter("companyId",id);
		//qry.setParameter("statsCd", BaseConstant.STATUS_ACTIVE);
		return qry.list().isEmpty(); 
	}

	@Override
	public boolean getFinPeriodLockIdCompList(Long id)
			throws BusinessException {
		// TODO Auto-generated method stub
		/**Criteria criteria = getSession().createCriteria(FinancialPeriodLockVO.class);
		criteria.add(Restrictions.eq("idCompany",id));
		return criteria.list();**/
		Query qry = createQuery("FROM FinancialPeriodLockVO WHERE id_company = :companyId");
		qry.setParameter("companyId",id);
		//qry.setParameter("statsCd", BaseConstant.STATUS_ACTIVE);
		return qry.list().isEmpty(); 
	}

	@Override
	public boolean getInvIdCompList(Long id) throws BusinessException {
		// TODO Auto-generated method stub
		/**Criteria criteria = getSession().createCriteria(InvoiceVO.class);
		criteria.add(Restrictions.eq("companyId",id));
		return criteria.list();
		**/
		Query qry = createQuery("FROM InvoiceVO WHERE id_company = :companyId");
		qry.setParameter("companyId",id);
		//qry.setParameter("statsCd", BaseConstant.STATUS_ACTIVE);
		return qry.list().isEmpty(); 
	}

	@Override
	public boolean getInvHisIdCompList(Long id) {
		// TODO Auto-generated method stub
		/**Criteria criteria = getSession().createCriteria(InvoiceHistoryVO.class);
		criteria.add(Restrictions.eq("companyId",id));
		return criteria.list();**/
		Query qry = createQuery("FROM InvoiceHistoryVO WHERE id_company = :companyId");
		qry.setParameter("companyId",id);
		//qry.setParameter("statsCd", BaseConstant.STATUS_ACTIVE);
		return qry.list().isEmpty(); 
	}

	@Override
	public boolean getBookingIdCompList(Long id) {
		// TODO Auto-generated method stub
		/**Criteria criteria = getSession().createCriteria(BookingVO.class);
		criteria.add(Restrictions.eq("idCompany",id));
		return criteria.list();**/
		Query qry = createQuery("FROM BookingVO WHERE id_company = :companyId");
		qry.setParameter("companyId",id);
		//qry.setParameter("statsCd", BaseConstant.STATUS_ACTIVE);
		return qry.list().isEmpty(); 
	}

	@Override
	public boolean getTourDepItemIdCompList(Long id) {
		/**Criteria criteria = getSession().createCriteria(TourDepItemVO.class);
		criteria.add(Restrictions.eq("companyVO.id",id));
		return criteria.list();**/
		Query qry = createQuery("FROM TourDepItemVO WHERE id_company = :companyId");
		qry.setParameter("companyId",id);
		//qry.setParameter("statsCd", BaseConstant.STATUS_ACTIVE);
		return qry.list().isEmpty(); 
	}

	@Override
	public boolean getExOrderList(Long id) throws BusinessException {
		Query qry = createQuery("FROM ExOrderVO WHERE id_company = :companyId");
		qry.setParameter("companyId",id);
		//qry.setParameter("statsCd", BaseConstant.STATUS_ACTIVE);
		return qry.list().isEmpty(); 
	}

	@Override
	public boolean getInvoiceAndExchangeOrderList(Long id)
			throws BusinessException {
		Query qry = createQuery("FROM InvoiceAndExchangeOrderVO WHERE id_company = :companyId");
		qry.setParameter("companyId",id);
		//qry.setParameter("statsCd", BaseConstant.STATUS_ACTIVE);
		return qry.list().isEmpty(); 
	}

	@Override
	public boolean getJournalList(Long id) throws BusinessException {
		Query qry = createQuery("FROM JournalVO WHERE id_company = :companyId");
		qry.setParameter("companyId",id);
		//qry.setParameter("statsCd", BaseConstant.STATUS_ACTIVE);
		return qry.list().isEmpty(); 
	}

	@Override
	public boolean getSupplierList(Long id) throws BusinessException {
		// TODO Auto-generated method stub
		Query qry = createQuery("FROM SupplierVO WHERE id_company = :companyId");
		qry.setParameter("companyId",id);
		//qry.setParameter("statsCd", BaseConstant.STATUS_ACTIVE);
		return qry.list().isEmpty(); 
	}
	
}