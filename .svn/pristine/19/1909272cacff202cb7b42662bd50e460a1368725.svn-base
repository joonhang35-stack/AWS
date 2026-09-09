package com.bcs.zsg.acctreport.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Query;

import com.bcs.zsg.bank.vo.BankAcctVO;
import com.bcs.zsg.core.dao.BaseHibernateDAO;
import com.bcs.zsg.core.exception.BusinessException;

public class BankReportDAOImpl extends BaseHibernateDAO implements BankReportDAO{

	@Override
	public List<BankAcctVO> getBankAcctList() throws BusinessException {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT b.*,(IFNULL(ba.addr_1, '')) as addr_1,IFNULL(ba.addr_2, '') as addr_2, IFNULL(ba.addr_3,'') as addr_3, IFNULL(ba.city, '') AS city, IFNULL(ba.state, '') as state, IFNULL(ba.postcode, '') as postcode, a.code as acctCode, li.description as bankTypeDescription ");
		sb.append("FROM bank b "); 
		sb.append("left join bank_address ba on ba.id_bank = b.id ");
		sb.append("left join account a  on a.id = b.id_acct and a.id_company = b.id_company ");
		sb.append("left join lookup_item li on li.code = b.type_cd ");
		sb.append("where b.status_cd = 'A'  ");
		
		Query query = createSQLQuery(sb.toString());
		
		List<Object> results = query.list();
		List<BankAcctVO>bankAcctList = new ArrayList<BankAcctVO>();
		if (CollectionUtils.isNotEmpty(results)) {
			BankAcctVO vo = null;
			for (Iterator<Object> it = results.iterator() ; it.hasNext() ;) {
				Object[] row = (Object[]) it.next();
				vo = new BankAcctVO();
			
				vo.setId(((Number) row[0]).longValue());
				vo.setIdCompany(((Number) row[1]).longValue());
				vo.setIdAcct(((Number) row[2]).longValue());
				vo.setAcctNo((String) row[4]);
				vo.setName((String) row[5]);
				vo.setBankAddress((String) row[13] +" " + row[14] + " " + row[15]+ " " + row[16]+ " " + row[17]+ " " + row[18]);
				vo.setDescription((String)row[20]);
				bankAcctList.add(vo);
				
			}
		}
		return bankAcctList;
	}


}
