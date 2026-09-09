package com.bcs.zsg.bank.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.bcs.zsg.bank.vo.CashBookVO;
import com.bcs.zsg.core.dao.BaseHibernateDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.sales.vo.InvoicePaymentVO;


public class RefundDAOImpl extends BaseHibernateDAO implements RefundDAO {

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.bank.dao.DepositDAO#getInvoicePaymentListNoCashbook(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<InvoicePaymentVO> getInvoicePaymentListNoCashbook(Long CompId) throws BusinessException{
		StringBuilder sb = new StringBuilder();
		/*sb.append("select ip.id,i.code,ip.dt_pmnt,ip.pmnt_type_cd,ip.ref_no,ip.received_from,ip.amount,i.tourcode from invoice_pmnt ip ").
			append("inner join (").
			append("select iv.id, iv.code, t.code as 'tourcode' from invoice iv left join (select id, code from tour_dep) t on t.id = iv.id_tour_dep ").
			append("where iv.doc_type_cd = 'I' and iv.id_company = ").append(CompId).
			append(") i on ip.id_inv = i.id").
			append(" where (ip.id_cashbook is null or ip.id_cashbook='') and ip.pmnt_type_cd = 'rfd'").
			append(" and ip.status_cd = 'A'");*/
		sb.append("select ip.id,i.id as invId,ip.dt_pmnt,ip.pmnt_type_cd,ip.ref_no,ip.received_from,ip.amount,i.tourcode,i.id as 'invoiceId',i.code as 'invoiceNo', i.doc_type_cd, i.ps_no, i.doc_type_status ").
			append("from invoice_pmnt ip ").
			append("inner join ( ").
			append("select iv.id, iv.code, t.code as 'tourcode', iv.doc_type_cd, iv.ps_no, iv.doc_type_status from invoice iv left join (select id, code from tour_dep) t on t.id = iv.id_tour_dep ").
			append("where iv.doc_type_cd in ('I', 'P') and iv.id_company = ").append(CompId).append(" ").
			append(") i on ip.id_inv = i.id ").
			append("where ip.pmnt_type_cd = 'rfd' ").
			append("AND ip.id_parent_pmnt is null "). // to hide payment in split/convert doc
			append("and ip.status_cd = 'A' and ip.id not in ").
			append("(select l.id_inv_pmnt from inv_pmnt_cashbook_link l where l.status_cd = 'A' and l.id_company = ").append(CompId).append(") ");

		return MapInvoicePayment(createSQLQuery(sb.toString()).list());
	}
	
	/*
	 * 
	 * @param query
	 * @return
	 */
	private List<InvoicePaymentVO> MapInvoicePayment(List<Object> query) {
		InvoicePaymentVO vo;
		List<InvoicePaymentVO> ls = new ArrayList<InvoicePaymentVO>(query.size());
		
		for (int i = 0; i < query.size(); i++) {
			Object[] row = (Object[]) query.get(i);
			vo = new InvoicePaymentVO();
			if (row[0] != null) vo.setId(((BigInteger) row[0]).longValue()); 
			if (row[1] != null) vo.setInvId( Long.parseLong(row[1].toString()) ); 
			if (row[2] != null) vo.setPmntDt((Date) row[2]); 
			if (row[3] != null) vo.setPmntTypeCd(((String) row[3])); 
			if (row[4] != null) vo.setRefNo(((String) row[4])); 
			if (row[5] != null) vo.setRecievedFr((String) row[5]); 
			if (row[6] != null) vo.setAmount(Double.parseDouble( row[6].toString())); 
			if (row[7] != null) vo.setTourCode(row[7].toString());
			if (row[8] != null) vo.setInvoiceId(Long.parseLong(row[8].toString()));
			if (row[9] != null) vo.setInvoiceNo(row[9].toString());
			if (row[10] != null) vo.setInvoiceDocTypeCd(row[10].toString());
			if (row[11] != null) vo.setPsNo(row[11].toString());
			if (row[12] != null) vo.setInvoiceDocTypeStatus(row[12].toString());
			ls.add(vo);
		}
		return ls;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.bank.dao.DepositDAO#delInvPmnt(java.lang.String)
	 */
	@Override
	public void delInvPmnt(String cashBookId) throws BusinessException {
		StringBuilder sb = new StringBuilder();
		sb.append("Update invoice_pmnt set id_inv_pmnt='0',id_cashbook=null where id_cashbook= '" + cashBookId + "'");
		createSQLQuery(sb.toString()).executeUpdate();
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.bank.dao.DepositDAO#updInvPmnt(com.bcs.zsg.bank.vo.CashBookVO)
	 */
	@Override
	public void updInvPmnt(CashBookVO cashBookVO) throws BusinessException {
		StringBuilder sb = new StringBuilder();
		sb.append("Update InvoicePaymentVO set invPmnt = null, idCashBook = null where idCashBook = ").append(cashBookVO.getId());
		createQuery(sb.toString()).executeUpdate();

		if (CollectionUtils.isNotEmpty(cashBookVO.getInvPymtList())) {
			sb = new StringBuilder();
			sb.append("Update InvoicePaymentVO set invPmnt = '1', idCashBook = ").append(cashBookVO.getId()).append(" where id in (");
			for (int i = 0 ; i < cashBookVO.getInvPymtList().size() ; i++) {
				sb.append(cashBookVO.getInvPymtList().get(i).getId());
				if (i < cashBookVO.getInvPymtList().size() - 1) sb.append(",");
			}
			sb.append(")");
			createQuery(sb.toString()).executeUpdate();	
		}
	}
}
