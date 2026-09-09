package com.bcs.zsg.db.bterp.dao.tourdephistory;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Query;
import org.primefaces.model.SortOrder;

import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.core.dao.BaseHibernateDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.history.vo.TourDepHistoryViewVO;

public class TourDepHistoryDAOImpl extends BaseHibernateDAO implements TourDepHistoryDAO {

	private Map<String, String> filters;
	
	@Override
	public int getListSizeHistoryView(Map<String, Object> params) throws BusinessException {
		StringBuilder sb = new StringBuilder();
		
		sb.append("SELECT COUNT(1) ")
			.append("FROM ( SELECT 1 ")
			.append("FROM tour_dep_history h LEFT JOIN airline a ON h.id_airline = a.id ")
			.append(" JOIN (SELECT max(id) as id FROM tour_dep_history GROUP BY id_hist) h2 ON h.id = h2.id")
			.append(" WHERE h.dt_upd between :fromDate AND :toDate ");
		
		sb = genListFilter(sb, params, "getListHistoryView");

		sb.append(" GROUP BY h.id_hist ")
			.append(" ) A ");
		
		Query query = createSQLQuery(sb.toString());
		query.setParameter("fromDate", (Date)params.get("fromDate"));
		query.setParameter("toDate", (Date)params.get("toDate"));
		
		return ((BigInteger) query.uniqueResult()).intValue();
	}

	@SuppressWarnings("unchecked")
	private StringBuilder genListFilter(StringBuilder _sb, Map<String, Object> params, String callerMethod) {
		filters = (Map<String, String>) params.get("filters");
		
		if (!filters.isEmpty()) {
			_sb.append(" AND (");
			for (Iterator<Entry<String, String>> it = filters.entrySet().iterator() ; it.hasNext() ;) {
				 
				if(callerMethod.equals("getListHistoryView"))
					_sb = filterListInvoiceHistoryView(it.next(), _sb);
				
				if (filters.size() > 1 && it.hasNext()) _sb.append(" AND ");
			}
			_sb.append(") ");
		}
		return _sb;
	}
	
	private StringBuilder filterListInvoiceHistoryView(Entry<String, String> entry, StringBuilder _sb) {
		//TODO need filter list
		if ("updatedBy".equals(entry.getKey())) {
			_sb.append("h.upd_by LIKE '%").append(entry.getValue()).append("%'");

		} else if ("code".equals(entry.getKey())) 
			_sb.append("h.code LIKE '%").append(entry.getValue()).append("%'");
		
		else if ("reason".equals(entry.getKey())) {
			_sb.append("h.reason LIKE '%").append(entry.getValue()).append("%'");
			
		} else if ("actionCd".equals(entry.getKey()))
			_sb.append("h.action_cd = '").append(entry.getValue()).append("'");
		
		return _sb;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TourDepHistoryViewVO> getListHistoryView(Map<String, Object> params) throws BusinessException {
		StringBuilder sb = new StringBuilder();
		
		sb.append("SELECT h.id, h.id_hist, h.id_tour_pkg, h.id_airline,")
			.append("h.`id_airline_schedule` AS `id_airline_schedule`,h.`id_tour_operator` AS `id_tour_operator`,")
			.append("h.`dt_dep` AS `dt_dep`,h.`code` AS `code`,h.`description` AS `description`,")
			.append("h.`full_twn` AS `full_twn`,h.`full_sgl` AS `full_sgl`,h.`full_ctw` AS `full_ctw`,")
			.append("h.`full_ceb` AS `full_ceb`,h.`full_cnb` AS `full_cnb`,h.`grnd_twn` AS `grnd_twn`,")
			.append("h.`grnd_sgl` AS `grnd_sgl`,h.`grnd_ctw` AS `grnd_ctw`,h.`grnd_ceb` AS `grnd_ceb`,")
			.append("h.`grnd_cnb` AS `grnd_cnb`,h.`tour_mgr_cost` AS `tour_mgr_cost`,h.`tfair_discount` AS `tfair_discount`,")
			.append("h.`cna_adt` AS `cna_adt`,h.`cpa_adt` AS `cpa_adt`,h.`csi_adt` AS `csi_adt`,h.`misc_adt` AS `misc_adt`,")
			.append("h.`misc_chd` AS `misc_chd`,h.`full_remarks` AS `full_remarks`,h.`grnd_remarks` AS `grnd_remarks`,")
			.append("h.`prn` AS `prn`,h.`seat_allotment` AS `seat_allotment`,h.`reserved_seat` AS `reserved_seat`,")
			.append("h.`tour_mgr_pax` AS `tour_mgr_pax`,h.`travel_ins_policy_s` AS `travel_ins_policy_s`,")
			.append("h.`travel_ins_policy_f` AS `travel_ins_policy_f`,h.`is_show_airline` AS `is_show_airline`,")
			.append("h.`is_issued_s` AS `is_issued_s`,h.`is_issued_f` AS `is_issued_f`,h.`is_deposit_paid` AS `is_deposit_paid`,")
			.append("h.`is_push` AS `is_push`,h.`is_hot_deal` AS `is_hot_deal`,h.`inv_remarks` AS `inv_remarks`,")
			.append("h.`tour_status_cd` AS `tour_status_cd`,h.`reason` AS `reason`,h.`action_cd` AS `action_cd`,")
			.append("h.`status_cd` AS `status_cd`,h.`dt_created` AS `dt_created`,h.`created_by` AS `created_by`,")
			.append("h.dt_upd AS dt_upd,h.upd_by AS upd_by,a.code AS `airline_cd`, h.`seq_no` AS `seq_no`")
			.append(" FROM tour_dep_history h LEFT JOIN airline a ON h.id_airline = a.id ")
			.append(" JOIN (SELECT max(id) as id FROM tour_dep_history GROUP BY id_hist) h2 ON h.id = h2.id")
			.append(" WHERE h.dt_upd between :fromDate AND :toDate ");
		
		sb = genListFilter(sb, params, "getListHistoryView");
		sb.append(" GROUP BY h.id_hist ");
		
		sb.append(" ORDER BY ");
		
		String sortField = (String) params.get("sortField");
		String sortDirection = "";
		
		if (CommonConstant.SORT_ASC.equals(((SortOrder) params.get("sortOrder")).toString())) 
			sortDirection = " ASC";
		else 
			sortDirection = " DESC";
		

		//No sort field as the page already contain filtering.
		if (filters.containsKey("code")) {
			sb.append("CASE WHEN h.code like '").append(filters.get("code"))
				.append("%' THEN h.code ELSE concat('{0} ', h.code) END").append(sortDirection).append(", ");
		}
		
		sb.append("h.id")
			.append(sortDirection);
		
		Query query = createSQLQuery(sb.toString());
		query.setFirstResult((int) params.get("first"));
		query.setMaxResults((int) params.get("pageSize"));
		query.setParameter("fromDate", (Date)params.get("fromDate"));
		query.setParameter("toDate", (Date)params.get("toDate"));
		
		List<Object> queryResultList = query.list();
		List<TourDepHistoryViewVO> returnResultList = new ArrayList<TourDepHistoryViewVO>();

		for (Iterator<Object> it = queryResultList.iterator() ; it.hasNext() ;) {
			Object[] row = (Object[]) it.next();
			TourDepHistoryViewVO vo = new TourDepHistoryViewVO();
			if (row[0] != null) vo.setId(((BigInteger) row[0]).longValue());
			if (row[1] != null) vo.setIdHist(((BigInteger) row[1]).longValue());
			if (row[2] != null) vo.setIdTourPkg(((BigInteger) row[2]).longValue());
			if (row[3] != null) vo.setIdAirline(((BigInteger) row[3]).longValue());
	        if (row[4] != null) vo.setIdAirlineSchedule(((BigInteger) row[4]).longValue());
	        if (row[5] != null) vo.setIdTourOperator(((BigInteger) row[5]).longValue());
	        if (row[6] != null) vo.setDtDep((Date) row[6]);
	        if (row[7] != null) vo.setCode((String) row[7]);
	        if (row[8] != null) vo.setDesc((String) row[8]);
	        if (row[9] != null) vo.setFullTwn(Double.parseDouble(row[9].toString()));
	        if (row[10] != null) vo.setFullSgl(Double.parseDouble(row[10].toString()));
	        if (row[11] != null) vo.setFullCtw(Double.parseDouble(row[11].toString()));
	        if (row[12] != null) vo.setFullCeb(Double.parseDouble(row[12].toString()));
	        if (row[13] != null) vo.setFullCnb(Double.parseDouble(row[13].toString()));
	        if (row[14] != null) vo.setGrndTwn(Double.parseDouble(row[14].toString()));
	        if (row[15] != null) vo.setGrndSgl(Double.parseDouble(row[15].toString()));
	        if (row[16] != null) vo.setGrndCtw(Double.parseDouble(row[16].toString()));
	        if (row[17] != null) vo.setGrndCeb(Double.parseDouble(row[17].toString()));
	        if (row[18] != null) vo.setGrndCnb(Double.parseDouble(row[18].toString()));
	        if (row[19] != null) vo.setTourMgrCost(Double.parseDouble(row[19].toString()));
	        if (row[20] != null) vo.setTfairDiscount(Double.parseDouble(row[20].toString()));
	        if (row[21] != null) vo.setCnaAdt(Double.parseDouble(row[21].toString()));
	        if (row[22] != null) vo.setCpaAdt(Double.parseDouble(row[22].toString()));
	        if (row[23] != null) vo.setCsiAdt(Double.parseDouble(row[23].toString()));
	        if (row[24] != null) vo.setMiscAdt(Double.parseDouble(row[24].toString()));
	        if (row[25] != null) vo.setMiscChd(Double.parseDouble(row[25].toString()));
	        if (row[26] != null) vo.setFullRemarks((String) row[26]);
	        if (row[27] != null) vo.setGrndRemarks((String) row[27]);
	        if (row[28] != null) vo.setPrn((String) row[28]);
	        if (row[29] != null) vo.setSeatAllot(((Short) row[29]).intValue());
	        if (row[30] != null) vo.setReservedSeat(((Short) row[30]).intValue());
	        if (row[31] != null) vo.setTourManagerPax(((Short) row[31]).intValue());
	        if (row[32] != null) vo.setTravelInsPolicyS((String) row[32]);
	        if (row[33] != null) vo.setTravelInsPolicyF((String) row[33]);
	        if (row[34] != null) vo.setIsShowAirline((Boolean) row[34]);
	        if (row[35] != null) vo.setIsIssuedS((Boolean) row[35]);
	        if (row[36] != null) vo.setIsIssuedF((Boolean) row[36]);
	        if (row[37] != null) vo.setIsDepositPaid((Boolean) row[37]);
	        if (row[38] != null) vo.setIsPush((Boolean) row[38]);
	        if (row[39] != null) vo.setIsHotDeal((Boolean) row[39]);
	        if (row[40] != null) vo.setInvRemarks((String) row[40]);
	        if (row[41] != null) vo.setTourStatusCd((String) row[41]);
	        if (row[42] != null) vo.setReason((String) row[42]);
	        if (row[43] != null) vo.setActionCd((String) row[43]);
	        if (row[44] != null) vo.setStatusCode((String) row[44]);
			if (row[45] != null) vo.setCreatedDate((Date) row[45]);
			if (row[46] != null) vo.setCreatedBy((String) row[46]);
			if (row[47] != null) vo.setUpdatedDate((Date) row[47]);
			if (row[48] != null) vo.setUpdatedBy((String) row[48]);
			if (row[49] != null) vo.setAirlineCd((String) row[49]);
			if (row[50] != null) vo.setSeqNo(Integer.parseInt(row[50].toString()));

			returnResultList.add(vo);
		}
		
		return returnResultList;
	}
	
}
