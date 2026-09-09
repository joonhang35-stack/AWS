package com.bcs.zsg.db.bterp.dao.view.supplier;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.core.dao.BaseHibernateDAO;
import com.bcs.zsg.core.helper.BaseConstant;
import com.bcs.zsg.purchase.dao.PurchaseDAO;
import com.bcs.zsg.purchase.vo.AddressVO;
import com.bcs.zsg.purchase.vo.ContactVO;
import com.bcs.zsg.purchase.vo.CorAddressVO;
import com.bcs.zsg.purchase.vo.CorContactVO;
import com.bcs.zsg.purchase.vo.CorporateVO;
import com.bcs.zsg.purchase.vo.IdentityVO;
import com.bcs.zsg.purchase.vo.PersonVO;
import com.bcs.zsg.purchase.vo.RemarksVO;
import com.bcs.zsg.purchase.vo.SupplierVO;

public class SupplierViewDAOImpl extends BaseHibernateDAO implements SupplierViewDAO {

	@Autowired
	private PurchaseDAO purchaseDAO;
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.dao.BillPymtDAO#getSupplier()
	 */
	@Override
	public SupplierVO getSupplier(Long supplierId, Long companyId) {
		return getSupplier(supplierId,companyId,0) ;
	}
	
	private String getSupplierViewQueryMain() {
		return "SELECT s.id, s.ID_PERSON, s.ID_COMPANY, s.ID_ACCT, s.SYS_NO, s.code, "
				+ "	s.STATUS_CD, s.TYPE_CD AS supplierType, s.CAT_CD AS supplierCat, s.PMNT_TYPE_CD AS paymentTerm, s.ACCT_REF, s.CREDIT_LIMIT, s.BANK_NAME, "
				+ "	s.BANK_NO, s.BENEFICIARY_NAME, s.DT_CREATED, s.CREATED_BY, s.DT_UPD, s.UPD_BY, "
				+ "	case when c.name <> '' then c.name else concat_ws(' ', p.last_name, p.first_name) end as name, "
				+ "	concat_ws(' ', p.`last_name`, p.`first_name`) as contact_name, "
				+ " s.reg_no, s.gst_reg_no, s.group_cd "
				+ "FROM person p LEFT JOIN corporate c ON p.id = c.id_person"
				+ "	LEFT JOIN supplier s ON p.id = s.id_person ";
				
	}
	private String queryWhereClauseBuilder(Map<String, Object> params, boolean isAdditionalWhereClause) {
		StringBuilder sb = new StringBuilder();
		if(params.get("idCompany") != null)
			sb.append(" AND id_company = :idCompany ");

		if(params.get("idSupplier") != null)
			sb.append(" AND s.id = :idSupplier ");
		
		if(params.get("statusCode") != null)
			sb.append(" AND s.status_cd = :statusCode ");
		
		if(!isAdditionalWhereClause && sb.length() > 5 && sb.toString().startsWith(" AND ")) {
			return "WHERE " + sb.substring(4);
		}
		return sb.toString();
	}

	private Query queryParamMapping(Query _query, Map<String, Object> params) {
		Long idCompany, idSupplier;
		String statusCode;

		idCompany = (Long) ObjectUtils.defaultIfNull(params.get("idCompany"), null);
		idSupplier = (Long) ObjectUtils.defaultIfNull(params.get("idSupplier"), null);
		statusCode = (String) ObjectUtils.defaultIfNull(params.get("statusCode"), null);
		
		if(idCompany != null)
			_query.setParameter("idCompany", idCompany);
		
		if(idSupplier != null)
			_query.setParameter("idSupplier", idSupplier);
		
		if(statusCode != null)
			_query.setParameter("statusCode", statusCode);
		return _query;
	}
	private void commonSupplierResultObjectMapping(SupplierVO vo, Object[] row) {

		if (row[0] != null) vo.setId(((BigInteger) row[0]).longValue());
		if (row[1] != null) vo.setPersonId(((BigInteger) row[1]).longValue());
		if (row[2] != null) vo.setCompanyId(((BigInteger) row[2]).longValue());
		if (row[3] != null) vo.setAccountId(((BigInteger) row[3]).longValue());
		if (row[4] != null) vo.setSysNo((String) row[4]);
		if (row[5] != null) vo.setCode((String) row[5]);
		if (row[6] != null) vo.setStatus((String) row[6]);
		if (row[7] != null) vo.setSupplierType((String) row[7]);
		if (row[8] != null) vo.setSupplierCat((String) row[8]);
		if (row[9] != null) vo.setPaymentTerm((String) row[9]);
		if (row[10] != null) vo.setAccRef((String) row[10]);
		if (row[11] != null) {
			if(row[11] instanceof Float)
				vo.setCreditLimit(Double.parseDouble(((Float) row[11]).toString()));
			else
				vo.setCreditLimit((Double)row[11]);
		}
		if (row[12] != null) vo.setBankName((String) row[12]);
		if (row[13] != null) vo.setAccountNo((String) row[13]);
		if (row[14] != null) vo.setBeneficiaryName((String) row[14]);
		if (row[16] != null) vo.setCreatedBy((String) row[16]);
		if (row[19] != null) vo.setFullName((String) row[19]);
		if (row[20] != null) vo.setContactName((String) row[20]);
		if (row[21] != null) vo.setRegNo((String) row[21]);
		if (row[22] != null) vo.setGstRegNo((String) row[22]);
		if (row[23] != null) vo.setGroupCd((String) row[23]);
		
	}
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.dao.BillPymtDAO#getSupplier()
	 */
	@Override
	public SupplierVO getSupplier(Long supplierId, Long companyId, int includeInactive) {
		PersonVO personVO = new PersonVO();
		AddressVO addressVO = new AddressVO();
		CorporateVO corporateVO = new CorporateVO();
		List<ContactVO> contactList = new ArrayList<ContactVO>();
		List<CorContactVO> corContactList = new ArrayList<CorContactVO>();
		List<IdentityVO> identityList = new ArrayList<IdentityVO>();
		List<RemarksVO> remarksList = new ArrayList<RemarksVO>();

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("idCompany", companyId);
		params.put("idSupplier", supplierId);

		if (includeInactive == 0) {
			params.put("statusCode", BaseConstant.STATUS_ACTIVE);
		}
		Query query = createSQLQuery(getSupplierViewQueryMain() + queryWhereClauseBuilder(params, false)
				+ " order by case when c.name <> '' then c.name else concat_ws(' ', p.last_name, p.first_name) end");
		query = queryParamMapping(query, params);

		Object results = query.uniqueResult();
		SupplierVO vo = new SupplierVO();

		Object[] row = (Object[]) results;

		if (null != row) {
			commonSupplierResultObjectMapping(vo, row);

			personVO = purchaseDAO.getPerson(vo.getPersonId());
			vo.setPersonVO(personVO);

			if (vo.getCode().equals("C")) {
				corporateVO = purchaseDAO.getCompany(vo.getPersonId());
				CorAddressVO corAddressVO = purchaseDAO.getCorAddress(corporateVO.getId());
				corContactList = purchaseDAO.getCorContactList(corporateVO.getId());
				if (corContactList.size() != 0) {
					vo.setContactNo(corContactList.get(0).getContactNo());
					vo.setCorContactList(corContactList);
				}
				vo.setCorporateVO(corporateVO);
				vo.setAddressVO(new AddressVO());
				if (!(corAddressVO == null)) {

					vo.getAddressVO().setAddr1(corAddressVO.getAddr1());
					vo.getAddressVO().setAddr2(corAddressVO.getAddr2());
					vo.getAddressVO().setAddr3(corAddressVO.getAddr3());
					vo.getAddressVO().setCity(corAddressVO.getCity());
					vo.getAddressVO().setState(corAddressVO.getState());
					vo.getAddressVO().setPostcode(corAddressVO.getPostcode());
					vo.getAddressVO().setCountryId(corAddressVO.getCountryId());

					vo.setCorAddressVO(corAddressVO);
				}
				remarksList = purchaseDAO.getRemarksList(corporateVO.getId());
				vo.setVenderType(true);
			} else {
				addressVO = purchaseDAO.getAddress(vo.getPersonId());
				contactList = purchaseDAO.getContactList(vo.getPersonId());
				if (contactList.size() != 0) {
					vo.setContactNo(contactList.get(0).getContactNo());
					vo.setContactList(contactList);
				}
				vo.setAddressVO(addressVO);
				vo.setVenderType(false);
				remarksList = purchaseDAO.getRemarksList(vo.getPersonId());
			}

			identityList = purchaseDAO.getIdentityList(vo.getPersonId());

			if (identityList.size() != 0) {
				vo.setIdentityList(identityList);
			}

			if (remarksList.size() != 0) {
				DateFormat dtformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				for (int k = 0; k < remarksList.size(); k++) {
					remarksList.get(k).setStrTimestamp(dtformat.format(remarksList.get(k).getTimestamp()));
				}

				vo.setRemarksList(remarksList);
			}
		}
		return vo;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.dao.PurchaseDAO#getSuppNameList()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<SupplierVO> getSuppNameList(Long companyId) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("idCompany", companyId);
		params.put("statusCode", BaseConstant.STATUS_ACTIVE);
		Query query = createSQLQuery(getSupplierViewQueryMain() + queryWhereClauseBuilder(params, false)
									+ "order by case when c.name <> '' then c.name else concat_ws(' ', p.last_name, p.first_name) end");
		query = queryParamMapping(query, params);
		
		List<Object> results = query.list();
		List<SupplierVO> ls = new ArrayList<SupplierVO>(results.size());
			SupplierVO vo = null;
		 
		for (int i = 0; i < results.size(); i++) {
			vo = new SupplierVO();
			Object[] row = (Object[]) results.get(i);
			commonSupplierResultObjectMapping(vo, row);
			vo.setSupplierNameId("(" + vo.getPersonId() + ") " + vo.getFullName());
			ls.add(vo);
		}
		return ls;
		
	}
	
}
