package com.bcs.zsg.product.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.common.helper.CommonErrConstant;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.db.bterp.dao.airline.AirlineDAO;
import com.bcs.zsg.product.dao.TourPackageDAO;
import com.bcs.zsg.product.vo.AirlineScheduleChargeVO;
import com.bcs.zsg.product.vo.AirlineScheduleItemVO;
import com.bcs.zsg.product.vo.AirlineScheduleVO;
import com.bcs.zsg.product.vo.AirlineVO;
import com.bcs.zsg.product.vo.TourDepartureVO;

public class AirlineServiceImpl implements AirlineService {

	@Autowired
	private AirlineDAO airlineDAO;
	@Autowired
	private TourPackageDAO tourPkgDAO;
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.service.AirlineService#getAirlineList()
	 */
	@Override
	public List<AirlineVO> getAirlineList() throws BusinessException {
		return airlineDAO.getAirlinelist();
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.service.AirlineService#addAirline(com.bcs.zsg.product.vo.AirlineVO)
	 */
	@Override
	public void addAirline(AirlineVO airlineVO) throws BusinessException {
		airlineDAO.insert(airlineVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.service.AirlineService#updAriline(com.bcs.zsg.product.vo.AirlineVO)
	 */
	@Override
	public void updAriline(AirlineVO airlineVO) throws BusinessException {
		airlineDAO.update(airlineVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.service.AirlineService#delAirline(com.bcs.zsg.product.vo.AirlineVO)
	 */
	@Override
	public void delAirline(AirlineVO airlineVO) throws BusinessException {
		if (airlineDAO.isAirlineScheduleExisted(airlineVO.getId())) throw new BusinessException(CommonErrConstant.ERR_AIRLINE_SCHEDULE_USED);
		airlineDAO.delete(airlineVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.service.AirlineService#addAirlineSchedule(com.bcs.zsg.product.vo.AirlineScheduleVO)
	 */
	@Override
	public void addAirlineSchedule(AirlineScheduleVO airlineScheduleVO) throws BusinessException {
		if (airlineDAO.isAirlineScheduleExisted(airlineScheduleVO)) throw new BusinessException(CommonErrConstant.ERR_AIRLINE_SCHEDULE_EXISTED);
		airlineDAO.insert(airlineScheduleVO);
		
		// insert extra item charge
		if (CollectionUtils.isNotEmpty(airlineScheduleVO.getExtraItemChargeList())) {
			for (AirlineScheduleChargeVO vo : airlineScheduleVO.getExtraItemChargeList()) {
				vo.setIdAirlineSchedule(airlineScheduleVO.getId());
				if (StringUtils.isEmpty(vo.getTypeCd())) vo.setTypeCd(UUID.randomUUID().toString());
				airlineDAO.insert(vo);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.service.AirlineService#delAirlineSchedule(com.bcs.zsg.product.vo.AirlineScheduleVO)
	 */
	@Override
	public void delAirlineSchedule(AirlineScheduleVO airlineScheduleVO) throws BusinessException {
		airlineDAO.delAirlineScheduleExtraCharges(airlineScheduleVO.getId());
		//if (del <= 0) throw new BusinessException(CommonErrConstant.ERR_AIRLINE_DEL_SCHEDULE_FAILED);
		List<AirlineScheduleItemVO> itemList = airlineDAO.getAirlineScheduleItemList(airlineScheduleVO.getId(), null);
		if (CollectionUtils.isNotEmpty(itemList)) {
			for (AirlineScheduleItemVO vo : itemList) {
				airlineDAO.delete(vo);
			}
		}
		boolean scheduleVacant = tourPkgDAO.getIsScheduleVacant(airlineScheduleVO.getId());
		
		if (scheduleVacant) {
			airlineDAO.delete(airlineScheduleVO);
		} else {
			throw new BusinessException(CommonErrConstant.ERR_AIRLINE_DEL_SCHEDULE_FAILED);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.service.AirlineService#addAirlineScheduleItem(com.bcs.zsg.product.vo.AirlineScheduleItemVO)
	 */
	@Override
	public void addAirlineScheduleItem(AirlineScheduleItemVO airlineScheduleItemVO) throws BusinessException {
		airlineDAO.insert(airlineScheduleItemVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.service.AirlineService#updAirlineScheduleItem(com.bcs.zsg.product.vo.AirlineScheduleItemVO)
	 */
	@Override
	public void updAirlineScheduleItem(AirlineScheduleItemVO airlineScheduleItemVO)throws BusinessException {
		airlineDAO.update(airlineScheduleItemVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.service.AirlineService#updAirlineScheduleItemCharge(java.util.List, java.util.List, java.util.List)
	 */
	@Override
	public void updAirlineScheduleCharge(List<AirlineScheduleChargeVO> addItemChargeList, List<AirlineScheduleChargeVO> updItemChargeList,
			List<AirlineScheduleChargeVO> delItemChargeList) throws BusinessException {
		// add item charges
		if (CollectionUtils.isNotEmpty(addItemChargeList)) {
			for (AirlineScheduleChargeVO vo : addItemChargeList) {
				if (StringUtils.isEmpty(vo.getTypeCd())) vo.setTypeCd(UUID.randomUUID().toString());
				airlineDAO.insert(vo);
			}
		}
		// update item charges
		if (CollectionUtils.isNotEmpty(updItemChargeList)) {
			for (AirlineScheduleChargeVO vo : updItemChargeList) {
				airlineDAO.update(vo);
			}
		}
		// delete item charges
		if (CollectionUtils.isNotEmpty(delItemChargeList)) {
			for (AirlineScheduleChargeVO vo : delItemChargeList) {
				airlineDAO.delete(vo);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.service.AirlineService#getAirlineScheduleList(java.lang.Long)
	 */
	@Override
	public List<AirlineScheduleVO> getAirlineScheduleList(Long idAirline) throws BusinessException {
		List<AirlineScheduleVO> airlineScheduleList = airlineDAO.getAirlineScheduleList(idAirline);
		
		if (CollectionUtils.isNotEmpty(airlineScheduleList)) {
			for (AirlineScheduleVO vo : airlineScheduleList) {
				vo.setAirlineScheduleItemList(airlineDAO.getAirlineScheduleItemList(vo.getId(), null));
			}
		}
		return airlineScheduleList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.service.AirlineService#getAirlineScheduleItemList(java.lang.Long)
	 */
	@Override
	public List<AirlineScheduleChargeVO> getAirlineScheduleChargeList(Long idAirlineSchedule) throws BusinessException {
		return airlineDAO.getAirlineScheduleChargeList(idAirlineSchedule);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.service.AirlineService#getAirline(java.lang.Long)
	 */
	@Override
	public AirlineVO getAirline(Long idAirline) throws BusinessException {
		return airlineDAO.getAirline(idAirline);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.service.AirlineService#getAirlineSchedule(com.bcs.zsg.product.vo.TourDepartureVO)
	 */
	@Override
	public AirlineScheduleVO getAirlineSchedule(TourDepartureVO tourDepVO) throws BusinessException {
		AirlineScheduleVO vo = airlineDAO.getAirlineSchedule(tourDepVO.getIdAirlineSchedule());
		
		// set item list
		if (vo != null) {
			if (StringUtils.isNotEmpty(tourDepVO.getAirlineScheduleItems()) && StringUtils.isNotEmpty(tourDepVO.getAirlineScheduleItemsBySeq())) {
				SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
				String[] airlineSchdlItems = tourDepVO.getAirlineScheduleItems().replaceAll(",$", "").split(",", -1);
				String[] airlineSchdlItemSeq =  tourDepVO.getAirlineScheduleItemsBySeq().replaceAll(",$", "").split(",", -1);
				String[] airlineSchdlItemEtd =  (StringUtils.isEmpty(tourDepVO.getAirlineScheduleItemsByEtd()) ? new String [] {} : tourDepVO.getAirlineScheduleItemsByEtd().replaceAll(",$", "").split(",", -1));
				String[] airlineSchdlItemEta =  (StringUtils.isEmpty(tourDepVO.getAirlineScheduleItemsByEta()) ? new String [] {} : tourDepVO.getAirlineScheduleItemsByEta().replaceAll(",$", "").split(",", -1));
				String[] airlineSchdlItemDepDt =  (StringUtils.isEmpty(tourDepVO.getAirlineScheduleItemsByDepDt()) ? new String [] {} : tourDepVO.getAirlineScheduleItemsByDepDt().replaceAll(",$", "").split(",", -1));
				String[] airlineSchdlItemArrDt =  (StringUtils.isEmpty(tourDepVO.getAirlineScheduleItemsByArrDt()) ? new String [] {} : tourDepVO.getAirlineScheduleItemsByArrDt().replaceAll(",$", "").split(",", -1));
				String[] airlineSchdlItemRemarks =  (StringUtils.isEmpty(tourDepVO.getAirlineScheduleItemsByRemarks()) ? new String [] {} : tourDepVO.getAirlineScheduleItemsByRemarks().replaceAll(",$", "").split(",", -1));
				Map<String, String> unsortMap = new HashMap<String, String>();
				Map<String, String> unsortMapEtd = new HashMap<String, String>();
				Map<String, String> unsortMapEta = new HashMap<String, String>();
				Map<String, String> unsortMapDepDt = new HashMap<String, String>();
				Map<String, String> unsortMapArrDt = new HashMap<String, String>();
				Map<String, String> unsortMapRemarks = new HashMap<String, String>();
				Long[] ids = new Long[airlineSchdlItems.length];
			    for (int i = 0; i < airlineSchdlItems.length; i++) {
			    	unsortMap.put(airlineSchdlItems[i], StringUtils.isBlank(airlineSchdlItemSeq[i]) ? "" : String.valueOf(airlineSchdlItemSeq[i]));
			    	if(airlineSchdlItemEtd.length > 0)
			    		unsortMapEtd.put(airlineSchdlItems[i], StringUtils.isBlank(airlineSchdlItemEtd[i]) ? "" : airlineSchdlItemEtd[i]);
			    	if(airlineSchdlItemEta.length > 0)
			    		unsortMapEta.put(airlineSchdlItems[i], StringUtils.isBlank(airlineSchdlItemEta[i]) ? "" : airlineSchdlItemEta[i]);
			    	if(airlineSchdlItemDepDt.length > 0)
			    		unsortMapDepDt.put(airlineSchdlItems[i], StringUtils.isBlank(airlineSchdlItemDepDt[i]) ? "" : airlineSchdlItemDepDt[i]);
			    	if(airlineSchdlItemArrDt.length > 0)
			    		unsortMapArrDt.put(airlineSchdlItems[i], StringUtils.isBlank(airlineSchdlItemArrDt[i]) ? "" : airlineSchdlItemArrDt[i]);
			    	ids[i] = Long.parseLong(airlineSchdlItems[i]);
			    	if(airlineSchdlItemRemarks.length > 0)
			    		unsortMapRemarks.put(airlineSchdlItems[i], StringUtils.isBlank(airlineSchdlItemRemarks[i]) ? "" : airlineSchdlItemRemarks[i]);
			    }
			    Map<String, String> airlineScheduleItemsMap = new TreeMap<String, String>(unsortMap);
			    Map<String, String> airlineScheduleItemsMapEtd = new TreeMap<String, String>(unsortMapEtd);
			    Map<String, String> airlineScheduleItemsMapEta = new TreeMap<String, String>(unsortMapEta);
			    Map<String, String> airlineScheduleItemsMapDepDt = new TreeMap<String, String>(unsortMapDepDt);
			    Map<String, String> airlineScheduleItemsMapArrDt = new TreeMap<String, String>(unsortMapArrDt);
			    Map<String, String> airlineScheduleItemsMapRemarks = new TreeMap<String, String>(unsortMapRemarks);
			    vo.setAirlineScheduleItemList(new ArrayList<AirlineScheduleItemVO>());
			    List<AirlineScheduleItemVO> tempAirlineScheduleItemList = airlineDAO.getAirlineScheduleItemList(vo.getId(), ids);
			    vo.setSelectedScheduleItems(new AirlineScheduleItemVO[airlineScheduleItemsMap.size()]);
			
				if (CollectionUtils.isNotEmpty(tempAirlineScheduleItemList)) {
					for (AirlineScheduleItemVO itemVO : tempAirlineScheduleItemList) {
						AirlineScheduleItemVO tempItemVO = SerializationUtils.clone(itemVO);
						
						for (Map.Entry<String, String> entry : airlineScheduleItemsMap.entrySet()) {
							if (tempItemVO.getId().longValue() == Long.parseLong((String)entry.getKey())) {
								tempItemVO.setSeq(StringUtils.isBlank(entry.getValue()) ? null : Integer.parseInt(entry.getValue()));
							}
						}
						for (Map.Entry<String, String> entry : airlineScheduleItemsMapEtd.entrySet()) {
							if (tempItemVO.getId().longValue() == Long.parseLong((String)entry.getKey())) {
								tempItemVO.setEtd(StringUtils.isBlank(entry.getValue()) ? "" : entry.getValue());
							}
						}
						for (Map.Entry<String, String> entry : airlineScheduleItemsMapEta.entrySet()) {
							if (tempItemVO.getId().longValue() == Long.parseLong((String)entry.getKey())) {
								tempItemVO.setEta(StringUtils.isBlank(entry.getValue()) ? "" : entry.getValue());
							}
						}
						for (Map.Entry<String, String> entry : airlineScheduleItemsMapDepDt.entrySet()) {
							if (tempItemVO.getId().longValue() == Long.parseLong((String)entry.getKey())) {
								try {
									tempItemVO.setDepartureDate(StringUtils.isBlank(entry.getValue()) ? null : sdf.parse(entry.getValue()));
								} catch (ParseException e) {
									e.printStackTrace();
								}
							}
						}
						for (Map.Entry<String, String> entry : airlineScheduleItemsMapArrDt.entrySet()) {
							if (tempItemVO.getId().longValue() == Long.parseLong((String)entry.getKey())) {
								try {
									tempItemVO.setArrivalDate(StringUtils.isBlank(entry.getValue()) ? null : sdf.parse(entry.getValue()));
								} catch (ParseException e) {
									e.printStackTrace();
								}
							}
						}
						for (Map.Entry<String, String> entry : airlineScheduleItemsMapRemarks.entrySet()) {
							if (tempItemVO.getId().longValue() == Long.parseLong((String)entry.getKey())) {
								tempItemVO.setRemarks(StringUtils.isBlank(entry.getValue()) ? "" : entry.getValue());
							}
						}
						
						if (tempItemVO.getSeq() != null) {
							vo.getAirlineScheduleItemList().add(tempItemVO);
						}
					}
					
					Collections.sort(vo.getAirlineScheduleItemList(), new Comparator<AirlineScheduleItemVO>() {
						@Override
						public int compare(AirlineScheduleItemVO u1, AirlineScheduleItemVO u2) {
							int count = 0;
							
							if (u1.getSeq() > u2.getSeq())
								count = 1;
							else if (u1.getSeq() < u2.getSeq())
								count = -1;
							
							return count;
						}
					});
				}
			}
		}
		return vo;
	}

	@Override
	public List<AirlineScheduleVO> getAirlineScheduleListByDesc(Long id, String scheduleSearch) throws BusinessException {
		List<AirlineScheduleVO> airlineScheduleList = airlineDAO.getAirlineScheduleListByDesc(id, scheduleSearch);
		
		if (CollectionUtils.isNotEmpty(airlineScheduleList)) {
			for (AirlineScheduleVO vo : airlineScheduleList) {
				vo.setAirlineScheduleItemList(airlineDAO.getAirlineScheduleItemList(vo.getId(), null));
			}
		}
		return airlineScheduleList;
	}

	@Override
	public void updAirlineSchedule(AirlineScheduleVO airlineScheduleVO) throws BusinessException {
		airlineDAO.update(airlineScheduleVO);
	}

	@Override
	public void delAirlineScheduleItem(AirlineScheduleItemVO airlineScheduleItemVO) throws BusinessException {
		airlineDAO.delete(airlineScheduleItemVO);
	}

	@Override
	public List<AirlineVO> getAirlineListByTypeCode(String typeCode) throws BusinessException {
		return airlineDAO.getAirlineListByTypeCode(typeCode);
	}

	@Override
	public AirlineScheduleVO getAirlineScheduleWithName(TourDepartureVO tourDepVO) throws BusinessException {
		AirlineScheduleVO vo = airlineDAO.getAirlineScheduleWithName(tourDepVO.getIdAirlineSchedule());
		return getAirlineScheduleDetails(vo, tourDepVO);
	}

	private AirlineScheduleVO getAirlineScheduleDetails(AirlineScheduleVO vo, TourDepartureVO tourDepVO)
			throws BusinessException {
		// set item list
		if (vo != null) {

			if (StringUtils.isNotEmpty(tourDepVO.getAirlineScheduleItems())
					&& StringUtils.isNotEmpty(tourDepVO.getAirlineScheduleItemsBySeq())) {
//				System.out.println("CYY tourDepVO.getAirlineScheduleItems(): " + tourDepVO.getAirlineScheduleItems());
//				System.out.println("CYY tourDepVO.getAirlineScheduleItemsBySeq(): " + tourDepVO.getAirlineScheduleItemsBySeq());
//				System.out.println("CYY tourDepVO.getAirlineScheduleItemsByEtd(): " + tourDepVO.getAirlineScheduleItemsByEtd());
//				System.out.println("CYY tourDepVO.getAirlineScheduleItemsByEta(): " + tourDepVO.getAirlineScheduleItemsByEta());
//				System.out.println("CYY tourDepVO.getAirlineScheduleItemsByDepDt(): " + tourDepVO.getAirlineScheduleItemsByDepDt());
//				System.out.println("CYY tourDepVO.getAirlineScheduleItemsByArrDt(): " + tourDepVO.getAirlineScheduleItemsByArrDt());
				SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
				String[] airlineSchdlItems = tourDepVO.getAirlineScheduleItems().replaceAll(",$", "").split(",", -1);
//				System.out.println("CYY airlineSchdlItems.length: " + airlineSchdlItems.length);
				String[] airlineSchdlItemSeq = tourDepVO.getAirlineScheduleItemsBySeq().replaceAll(",$", "").split(",", -1);
				String[] airlineSchdlItemEtd = (StringUtils.isEmpty(tourDepVO.getAirlineScheduleItemsByEtd())
						? new String[] {}
						: tourDepVO.getAirlineScheduleItemsByEtd().replaceAll(",$", "").split(",", -1));
				String[] airlineSchdlItemEta = (StringUtils.isEmpty(tourDepVO.getAirlineScheduleItemsByEta())
						? new String[] {}
						: tourDepVO.getAirlineScheduleItemsByEta().replaceAll(",$", "").split(",", -1));
				String[] airlineSchdlItemDepDt = (StringUtils.isEmpty(tourDepVO.getAirlineScheduleItemsByDepDt())
						? new String[] {}
						: tourDepVO.getAirlineScheduleItemsByDepDt().replaceAll(",$", "").split(",", -1));
				String[] airlineSchdlItemArrDt = (StringUtils.isEmpty(tourDepVO.getAirlineScheduleItemsByArrDt())
						? new String[] {}
						: tourDepVO.getAirlineScheduleItemsByArrDt().replaceAll(",$", "").split(",", -1));
				Map<String, String> unsortMap = new HashMap<String, String>();
				Map<String, String> unsortMapEtd = new HashMap<String, String>();
				Map<String, String> unsortMapEta = new HashMap<String, String>();
				Map<String, String> unsortMapDepDt = new HashMap<String, String>();
				Map<String, String> unsortMapArrDt = new HashMap<String, String>();
				Long[] ids = new Long[airlineSchdlItems.length];

				for (int i = 0; i < airlineSchdlItems.length; i++) {
					unsortMap.put(airlineSchdlItems[i],
							StringUtils.isBlank(airlineSchdlItemSeq[i]) ? "" : String.valueOf(airlineSchdlItemSeq[i]));
					if (airlineSchdlItemEtd.length > 0)
						unsortMapEtd.put(airlineSchdlItems[i],
								StringUtils.isBlank(airlineSchdlItemEtd[i]) ? "" : airlineSchdlItemEtd[i]);
					if (airlineSchdlItemEta.length > 0)
						unsortMapEta.put(airlineSchdlItems[i],
								StringUtils.isBlank(airlineSchdlItemEta[i]) ? "" : airlineSchdlItemEta[i]);
					if (airlineSchdlItemDepDt.length > 0 && airlineSchdlItemDepDt.length > i)
						unsortMapDepDt.put(airlineSchdlItems[i],
								StringUtils.isBlank(airlineSchdlItemDepDt[i]) ? "" : airlineSchdlItemDepDt[i]);
					else	unsortMapDepDt.put(airlineSchdlItems[i], "");
					if (airlineSchdlItemArrDt.length > 0 && airlineSchdlItemArrDt.length > i)
						unsortMapArrDt.put(airlineSchdlItems[i],
								StringUtils.isBlank(airlineSchdlItemArrDt[i]) ? "" : airlineSchdlItemArrDt[i]);
					else	unsortMapArrDt.put(airlineSchdlItems[i], "");
					ids[i] = Long.parseLong(airlineSchdlItems[i]);
				}
				Map<String, String> airlineScheduleItemsMap = new TreeMap<String, String>(unsortMap);
				Map<String, String> airlineScheduleItemsMapEtd = new TreeMap<String, String>(unsortMapEtd);
				Map<String, String> airlineScheduleItemsMapEta = new TreeMap<String, String>(unsortMapEta);
				Map<String, String> airlineScheduleItemsMapDepDt = new TreeMap<String, String>(unsortMapDepDt);
				Map<String, String> airlineScheduleItemsMapArrDt = new TreeMap<String, String>(unsortMapArrDt);
				vo.setAirlineScheduleItemList(new ArrayList<AirlineScheduleItemVO>());

				List<AirlineScheduleItemVO> tempAirlineScheduleItemList = airlineDAO
						.getAirlineScheduleItemList(vo.getId(), ids);
				vo.setSelectedScheduleItems(new AirlineScheduleItemVO[airlineScheduleItemsMap.size()]);

				if (CollectionUtils.isNotEmpty(tempAirlineScheduleItemList)) {
					for (AirlineScheduleItemVO itemVO : tempAirlineScheduleItemList) {
						AirlineScheduleItemVO tempItemVO = SerializationUtils.clone(itemVO);
						tempItemVO.setAirline(vo.getAirlineName());

						for (Map.Entry<String, String> entry : airlineScheduleItemsMap.entrySet()) {
							if (tempItemVO.getId().longValue() == Long.parseLong((String) entry.getKey())) {
								tempItemVO.setSeq(StringUtils.isBlank(entry.getValue()) ? null
										: Integer.parseInt(entry.getValue()));
							}
						}
						for (Map.Entry<String, String> entry : airlineScheduleItemsMapEtd.entrySet()) {
							if (tempItemVO.getId().longValue() == Long.parseLong((String) entry.getKey())) {
								tempItemVO.setEtd(StringUtils.isBlank(entry.getValue()) ? "" : entry.getValue());
							}
						}
						for (Map.Entry<String, String> entry : airlineScheduleItemsMapEta.entrySet()) {
							if (tempItemVO.getId().longValue() == Long.parseLong((String) entry.getKey())) {
								tempItemVO.setEta(StringUtils.isBlank(entry.getValue()) ? "" : entry.getValue());
							}
						}
						for (Map.Entry<String, String> entry : airlineScheduleItemsMapDepDt.entrySet()) {
							if (tempItemVO.getId().longValue() == Long.parseLong((String) entry.getKey())) {
								try {
									tempItemVO.setDepartureDate(
											StringUtils.isBlank(entry.getValue()) ? null : sdf.parse(entry.getValue()));
								} catch (ParseException e) {
									e.printStackTrace();
								}
							}
						}
						for (Map.Entry<String, String> entry : airlineScheduleItemsMapArrDt.entrySet()) {
							if (tempItemVO.getId().longValue() == Long.parseLong((String) entry.getKey())) {
								try {
									tempItemVO.setArrivalDate(
											StringUtils.isBlank(entry.getValue()) ? null : sdf.parse(entry.getValue()));
								} catch (ParseException e) {
									e.printStackTrace();
								}
							}
						}

						if (tempItemVO.getSeq() != null) {
							vo.getAirlineScheduleItemList().add(tempItemVO);
						}
					}

					Collections.sort(vo.getAirlineScheduleItemList(), new Comparator<AirlineScheduleItemVO>() {
						@Override
						public int compare(AirlineScheduleItemVO u1, AirlineScheduleItemVO u2) {
							int count = 0;

							if (u1.getSeq() > u2.getSeq())
								count = 1;
							else if (u1.getSeq() < u2.getSeq())
								count = -1;

							return count;
						}
					});
				}
			}
		}
		return vo;
	}
}
