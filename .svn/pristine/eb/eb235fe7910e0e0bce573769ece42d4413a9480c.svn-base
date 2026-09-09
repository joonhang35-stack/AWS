package com.bcs.zsg.product.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;

import com.bcs.zsg.core.dao.BaseHibernateDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.product.vo.RoomConfigVO;

public class RoomConfigDAOImpl extends BaseHibernateDAO implements RoomConfigDAO{
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.dao.RoomConfigDAO#deleteRoomConfigRegion(java.lang.Long)
	 */
	@Override
	public void deleteRoomConfigRegion(Long idRoomConfig) throws BusinessException {
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM room_config_region WHERE id_room_config = :idRoomConfig ");
		Query query = createSQLQuery(sb.toString());
		query.setParameter("idRoomConfig", idRoomConfig);
		query.executeUpdate();
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.dao.RoomConfigDAO#updateRoomConfigStatus(java.lang.String, java.lang.Long)
	 */
	@Override
	public void updateRoomConfigStatus(String statusCd, Long idRoomConfig) throws BusinessException {
		StringBuilder sb = new StringBuilder();
		sb = new StringBuilder();
		sb.append("UPDATE room_config_region r SET r.status_cd = :statusCd");
		sb.append(" WHERE r.status_cd = 'AC' AND r.id_room_config = :idRoomConfig ");
		
		Query query = createSQLQuery(sb.toString());
		query.setParameter("statusCd", statusCd);
		query.setParameter("idRoomConfig", idRoomConfig);
		query.executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RoomConfigVO> getRoomConfigList() throws BusinessException{
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT r.id, r.description, r.twn_qty, r.sgl_qty, r.ctw_qty, r.cwb_qty, r.cnb_qty, r.inft_qty ");
		sb.append(", (SELECT GROUP_CONCAT(li.description separator ', ') ").
			append("FROM room_config_region rc, lookup_item li").
			append(" WHERE rc.id_room_config = r.id and rc.status_cd = 'AC' and li.lookup_cat_cd = 'room_config_region' and li.code = rc.code ").
			append(") AS 'classes', ");
		sb.append(" r.status_cd, r.dt_created, r.created_by, r.dt_update, r.updated_by ");
		sb.append("FROM room_config r where r.status_cd = 'AC' ");
		
		Query query = this.createSQLQuery(sb.toString()); 
		List<Object> results = query.list();
		List<RoomConfigVO> roomConfigList = new ArrayList<RoomConfigVO>();
		
		for (Iterator<Object> it = results.iterator() ; it.hasNext() ;) {
			Object[] row = (Object[]) it.next();
			RoomConfigVO roomConfigVO = new RoomConfigVO();
			roomConfigVO.setId(((BigInteger) row[0]).longValue());
			roomConfigVO.setDescription((String) row[1]);
			roomConfigVO.setTwnQty(((Short) row[2]).intValue());
			roomConfigVO.setSglQty(((Short) row[3]).intValue());
			roomConfigVO.setCtwQty(((Short) row[4]).intValue());
			roomConfigVO.setCwbQty(((Short) row[5]).intValue());
			roomConfigVO.setCnbQty(((Short) row[6]).intValue());
			roomConfigVO.setInftQty(((Short) row[7]).intValue());
			roomConfigVO.setCodeDesc((String) row[8]);
			roomConfigVO.setStatusCode((String) row[9]);
			roomConfigVO.setCreatedDate((Date) row[10]);
			roomConfigVO.setCreatedBy((String) row[11]);
			roomConfigVO.setUpdatedDate((Date) row[12]);
			roomConfigVO.setUpdatedBy((String) row[13]);
			roomConfigList.add(roomConfigVO);
		}
		
		return roomConfigList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getRoomConfigRegionList(Long idRoomConfig) throws BusinessException{
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT id, id_room_config, code FROM room_config_region WHERE status_cd = 'AC' and id_room_config = :idRoomConfig ");
		
		Query query = createSQLQuery(sb.toString());
		query.setLong("idRoomConfig", idRoomConfig);
		
		List<Object> results = query.list();
		List<String> tagList = new ArrayList<String>();
		
		for (Iterator<Object> it = results.iterator() ; it.hasNext() ;) {
			Object[] row = (Object[]) it.next();
			tagList.add((String) row[2]);
		}
		
		return tagList;
	}
}
