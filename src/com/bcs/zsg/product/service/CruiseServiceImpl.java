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

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.common.helper.CommonErrConstant;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.product.dao.CruiseDAO;
import com.bcs.zsg.product.dao.TourPackageDAO;
import com.bcs.zsg.product.vo.CruiseScheduleChargeVO;
import com.bcs.zsg.product.vo.CruiseScheduleItemVO;
import com.bcs.zsg.product.vo.CruiseScheduleVO;
import com.bcs.zsg.product.vo.CruiseVO;
import com.bcs.zsg.product.vo.TourDepartureVO;

public class CruiseServiceImpl implements CruiseService {

	@Autowired
	private CruiseDAO cruiseDAO;
	@Autowired
	private TourPackageDAO tourPkgDAO;

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.service.CruiseService#getCruiseList()
	 */
	@Override
	public List<CruiseVO> getCruiseList() throws BusinessException {
		return cruiseDAO.getCruiselist();
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.service.CruiseService#addCruise(com.bcs.zsg.product.vo.CruiseVO)
	 */
	@Override
	public void addCruise(CruiseVO cruiseVO) throws BusinessException {
		cruiseDAO.insert(cruiseVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.service.CruiseService#updAriline(com.bcs.zsg.product.vo.CruiseVO)
	 */
	@Override
	public void updAriline(CruiseVO cruiseVO) throws BusinessException {
		cruiseDAO.update(cruiseVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.service.CruiseService#delCruise(com.bcs.zsg.product.vo.CruiseVO)
	 */
	@Override
	public void delCruise(CruiseVO cruiseVO) throws BusinessException {
		if (cruiseDAO.isCruiseScheduleExisted(cruiseVO.getId())) throw new BusinessException(CommonErrConstant.ERR_AIRLINE_SCHEDULE_USED);
		cruiseDAO.delete(cruiseVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.service.CruiseService#addCruiseSchedule(com.bcs.zsg.product.vo.CruiseScheduleVO)
	 */
	@Override
	public void addCruiseSchedule(CruiseScheduleVO cruiseScheduleVO) throws BusinessException {
		if (cruiseDAO.isCruiseScheduleExisted(cruiseScheduleVO)) throw new BusinessException(CommonErrConstant.ERR_AIRLINE_SCHEDULE_EXISTED);
		cruiseDAO.insert(cruiseScheduleVO);

		// insert extra item charge
		if (CollectionUtils.isNotEmpty(cruiseScheduleVO.getExtraItemChargeList())) {
			for (CruiseScheduleChargeVO vo : cruiseScheduleVO.getExtraItemChargeList()) {
				vo.setIdCruiseSchedule(cruiseScheduleVO.getId());
				//vo.setTypeCd(UUID.randomUUID().toString());
				cruiseDAO.insert(vo);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.service.CruiseService#delCruiseSchedule(com.bcs.zsg.product.vo.CruiseScheduleVO)
	 */
	@Override
	public void delCruiseSchedule(CruiseScheduleVO cruiseScheduleVO) throws BusinessException {
		cruiseDAO.delCruiseScheduleExtraCharges(cruiseScheduleVO.getId());
		//if (del <= 0) throw new BusinessException(CommonErrConstant.ERR_AIRLINE_DEL_SCHEDULE_FAILED);
		List<CruiseScheduleItemVO> itemList = cruiseDAO.getCruiseScheduleItemList(cruiseScheduleVO.getId(), null);
		if (CollectionUtils.isNotEmpty(itemList)) {
			for (CruiseScheduleItemVO vo : itemList) {
				cruiseDAO.delete(vo);
			}
		}
		boolean scheduleVacant = tourPkgDAO.getIsScheduleVacant(cruiseScheduleVO.getId());

		if (scheduleVacant) {
			cruiseDAO.delete(cruiseScheduleVO);
		} else {
			throw new BusinessException(CommonErrConstant.ERR_AIRLINE_DEL_SCHEDULE_FAILED);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.service.CruiseService#addCruiseScheduleItem(com.bcs.zsg.product.vo.CruiseScheduleItemVO)
	 */
	@Override
	public void addCruiseScheduleItem(CruiseScheduleItemVO cruiseScheduleItemVO) throws BusinessException {
		cruiseDAO.insert(cruiseScheduleItemVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.service.CruiseService#updCruiseScheduleItem(com.bcs.zsg.product.vo.CruiseScheduleItemVO)
	 */
	@Override
	public void updCruiseScheduleItem(CruiseScheduleItemVO cruiseScheduleItemVO)throws BusinessException {
		cruiseDAO.update(cruiseScheduleItemVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.service.CruiseService#updCruiseScheduleItemCharge(java.util.List, java.util.List, java.util.List)
	 */
	@Override
	public void updCruiseScheduleCharge(List<CruiseScheduleChargeVO> addItemChargeList, List<CruiseScheduleChargeVO> updItemChargeList,
			List<CruiseScheduleChargeVO> delItemChargeList) throws BusinessException {
		// add item charges
		if (CollectionUtils.isNotEmpty(addItemChargeList)) {
			for (CruiseScheduleChargeVO vo : addItemChargeList) {
				//vo.setTypeCd(UUID.randomUUID().toString());
				cruiseDAO.insert(vo);
			}
		}
		// update item charges
		if (CollectionUtils.isNotEmpty(updItemChargeList)) {
			for (CruiseScheduleChargeVO vo : updItemChargeList) {
				cruiseDAO.update(vo);
			}
		}
		// delete item charges
		if (CollectionUtils.isNotEmpty(delItemChargeList)) {
			for (CruiseScheduleChargeVO vo : delItemChargeList) {
				cruiseDAO.delete(vo);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.service.CruiseService#getCruiseScheduleList(java.lang.Long)
	 */
	@Override
	public List<CruiseScheduleVO> getCruiseScheduleList(Long idCruise) throws BusinessException {
		List<CruiseScheduleVO> cruiseScheduleList = cruiseDAO.getCruiseScheduleList(idCruise);

		if (CollectionUtils.isNotEmpty(cruiseScheduleList)) {
			for (CruiseScheduleVO vo : cruiseScheduleList) {
				vo.setCruiseScheduleItemList(cruiseDAO.getCruiseScheduleItemList(vo.getId(), null));
			}
		}
		return cruiseScheduleList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.service.CruiseService#getCruiseScheduleItemList(java.lang.Long)
	 */
	@Override
	public List<CruiseScheduleChargeVO> getCruiseScheduleChargeList(Long idCruiseSchedule) throws BusinessException {
		return cruiseDAO.getCruiseScheduleChargeList(idCruiseSchedule);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.service.CruiseService#getCruise(java.lang.Long)
	 */
	@Override
	public CruiseVO getCruise(Long idCruise) throws BusinessException {
		return cruiseDAO.getCruise(idCruise);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.service.CruiseService#getCruiseSchedule(com.bcs.zsg.product.vo.TourDepartureVO)
	 */
	@Override
	public CruiseScheduleVO getCruiseSchedule(TourDepartureVO tourDepVO) throws BusinessException {
		CruiseScheduleVO vo = cruiseDAO.getCruiseSchedule(tourDepVO.getIdCruiseSchedule());

		// set item list
		if (vo != null) {
			if (StringUtils.isNotEmpty(tourDepVO.getCruiseScheduleItems()) && StringUtils.isNotEmpty(tourDepVO.getCruiseScheduleItemsBySeq())) {
				SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
				String[] cruiseSchdlItems = tourDepVO.getCruiseScheduleItems().replaceAll(",$", "").split(",", -1);
				String[] cruiseSchdlItemSeq =  tourDepVO.getCruiseScheduleItemsBySeq().replaceAll(",$", "").split(",", -1);
				String[] cruiseSchdlItemEtd =  (StringUtils.isEmpty(tourDepVO.getCruiseScheduleItemsByEtd()) ? new String [] {} : tourDepVO.getCruiseScheduleItemsByEtd().replaceAll(",$", "").split(",", -1));
				String[] cruiseSchdlItemEta =  (StringUtils.isEmpty(tourDepVO.getCruiseScheduleItemsByEta()) ? new String [] {} : tourDepVO.getCruiseScheduleItemsByEta().replaceAll(",$", "").split(",", -1));
				String[] cruiseSchdlItemDepDt =  (StringUtils.isEmpty(tourDepVO.getCruiseScheduleItemsByDepDt()) ? new String [] {} : tourDepVO.getCruiseScheduleItemsByDepDt().replaceAll(",$", "").split(",", -1));
				String[] cruiseSchdlItemArrDt =  (StringUtils.isEmpty(tourDepVO.getCruiseScheduleItemsByArrDt()) ? new String [] {} : tourDepVO.getCruiseScheduleItemsByArrDt().replaceAll(",$", "").split(",", -1));
				String[] cruiseSchdlItemRemarks =  (StringUtils.isEmpty(tourDepVO.getCruiseScheduleItemsByRemarks()) ? new String [] {} : tourDepVO.getCruiseScheduleItemsByRemarks().replaceAll(",$", "").split(",", -1));
				Map<String, String> unsortMap = new HashMap<String, String>();
				Map<String, String> unsortMapEtd = new HashMap<String, String>();
				Map<String, String> unsortMapEta = new HashMap<String, String>();
				Map<String, String> unsortMapDepDt = new HashMap<String, String>();
				Map<String, String> unsortMapArrDt = new HashMap<String, String>();
				Map<String, String> unsortMapRemarks = new HashMap<String, String>();
				Long[] ids = new Long[cruiseSchdlItems.length];
			    for (int i = 0; i < cruiseSchdlItems.length; i++) {
			    	unsortMap.put(cruiseSchdlItems[i], StringUtils.isBlank(cruiseSchdlItemSeq[i]) ? "" : String.valueOf(cruiseSchdlItemSeq[i]));
			    	if(cruiseSchdlItemEtd.length > 0)
			    		unsortMapEtd.put(cruiseSchdlItems[i], StringUtils.isBlank(cruiseSchdlItemEtd[i]) ? "" : cruiseSchdlItemEtd[i]);
			    	if(cruiseSchdlItemEta.length > 0)
			    		unsortMapEta.put(cruiseSchdlItems[i], StringUtils.isBlank(cruiseSchdlItemEta[i]) ? "" : cruiseSchdlItemEta[i]);
			    	if(cruiseSchdlItemDepDt.length > 0)
			    		unsortMapDepDt.put(cruiseSchdlItems[i], StringUtils.isBlank(cruiseSchdlItemDepDt[i]) ? "" : cruiseSchdlItemDepDt[i]);
			    	if(cruiseSchdlItemArrDt.length > 0)
			    		unsortMapArrDt.put(cruiseSchdlItems[i], StringUtils.isBlank(cruiseSchdlItemArrDt[i]) ? "" : cruiseSchdlItemArrDt[i]);
			    	ids[i] = Long.parseLong(cruiseSchdlItems[i]);
			    	if(cruiseSchdlItemRemarks.length > 0)
			    		unsortMapRemarks.put(cruiseSchdlItems[i], StringUtils.isBlank(cruiseSchdlItemRemarks[i]) ? "" : cruiseSchdlItemRemarks[i]);
			    }
			    Map<String, String> cruiseScheduleItemsMap = new TreeMap<String, String>(unsortMap);
			    Map<String, String> cruiseScheduleItemsMapEtd = new TreeMap<String, String>(unsortMapEtd);
			    Map<String, String> cruiseScheduleItemsMapEta = new TreeMap<String, String>(unsortMapEta);
			    Map<String, String> cruiseScheduleItemsMapDepDt = new TreeMap<String, String>(unsortMapDepDt);
			    Map<String, String> cruiseScheduleItemsMapArrDt = new TreeMap<String, String>(unsortMapArrDt);
			    Map<String, String> cruiseScheduleItemsMapRemarks = new TreeMap<String, String>(unsortMapRemarks);
			    vo.setCruiseScheduleItemList(new ArrayList<CruiseScheduleItemVO>());
			    List<CruiseScheduleItemVO> tempCruiseScheduleItemList = cruiseDAO.getCruiseScheduleItemList(vo.getId(), ids);
			    vo.setSelectedScheduleItems(new CruiseScheduleItemVO[cruiseScheduleItemsMap.size()]);

				if (CollectionUtils.isNotEmpty(tempCruiseScheduleItemList)) {
					for (CruiseScheduleItemVO itemVO : tempCruiseScheduleItemList) {
						CruiseScheduleItemVO tempItemVO = SerializationUtils.clone(itemVO);

						for (Map.Entry<String, String> entry : cruiseScheduleItemsMap.entrySet()) {
							if (tempItemVO.getId().longValue() == Long.parseLong((String)entry.getKey())) {
								tempItemVO.setSeq(StringUtils.isBlank(entry.getValue()) ? null : Integer.parseInt(entry.getValue()));
							}
						}
						for (Map.Entry<String, String> entry : cruiseScheduleItemsMapEtd.entrySet()) {
							if (tempItemVO.getId().longValue() == Long.parseLong((String)entry.getKey())) {
								tempItemVO.setEtd(StringUtils.isBlank(entry.getValue()) ? "" : entry.getValue());
							}
						}
						for (Map.Entry<String, String> entry : cruiseScheduleItemsMapEta.entrySet()) {
							if (tempItemVO.getId().longValue() == Long.parseLong((String)entry.getKey())) {
								tempItemVO.setEta(StringUtils.isBlank(entry.getValue()) ? "" : entry.getValue());
							}
						}
						for (Map.Entry<String, String> entry : cruiseScheduleItemsMapDepDt.entrySet()) {
							if (tempItemVO.getId().longValue() == Long.parseLong((String)entry.getKey())) {
								try {
									tempItemVO.setDepartureDate(StringUtils.isBlank(entry.getValue()) ? null : sdf.parse(entry.getValue()));
								} catch (ParseException e) {
									e.printStackTrace();
								}
							}
						}
						for (Map.Entry<String, String> entry : cruiseScheduleItemsMapArrDt.entrySet()) {
							if (tempItemVO.getId().longValue() == Long.parseLong((String)entry.getKey())) {
								try {
									tempItemVO.setArrivalDate(StringUtils.isBlank(entry.getValue()) ? null : sdf.parse(entry.getValue()));
								} catch (ParseException e) {
									e.printStackTrace();
								}
							}
						}
						for (Map.Entry<String, String> entry : cruiseScheduleItemsMapRemarks.entrySet()) {
							if (tempItemVO.getId().longValue() == Long.parseLong((String)entry.getKey())) {
								tempItemVO.setRemarks(StringUtils.isBlank(entry.getValue()) ? "" : entry.getValue());
							}
						}

						if (tempItemVO.getSeq() != null) {
							vo.getCruiseScheduleItemList().add(tempItemVO);
						}
					}

					Collections.sort(vo.getCruiseScheduleItemList(), new Comparator<CruiseScheduleItemVO>() {
						@Override
						public int compare(CruiseScheduleItemVO u1, CruiseScheduleItemVO u2) {
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

		List<CruiseScheduleChargeVO> chargeList = getCruiseScheduleChargeList(vo.getId());
		if (CollectionUtils.isNotEmpty(chargeList)) {
			vo.setExtraItemChargeList(chargeList);
		}

		return vo;
	}

	@Override
	public List<CruiseScheduleVO> getCruiseScheduleListByDesc(Long id, String scheduleSearch) throws BusinessException {
		List<CruiseScheduleVO> cruiseScheduleList = cruiseDAO.getCruiseScheduleListByDesc(id, scheduleSearch);

		if (CollectionUtils.isNotEmpty(cruiseScheduleList)) {
			for (CruiseScheduleVO vo : cruiseScheduleList) {
				vo.setCruiseScheduleItemList(cruiseDAO.getCruiseScheduleItemList(vo.getId(), null));
			}
		}
		return cruiseScheduleList;
	}

	@Override
	public void updCruiseSchedule(CruiseScheduleVO cruiseScheduleVO) throws BusinessException {
		cruiseDAO.update(cruiseScheduleVO);
	}

	@Override
	public void delCruiseScheduleItem(CruiseScheduleItemVO cruiseScheduleItemVO) throws BusinessException {
		cruiseDAO.delete(cruiseScheduleItemVO);
	}

	@Override
	public List<CruiseVO> getCruiseListByTypeCode(String typeCode) throws BusinessException {
		return cruiseDAO.getCruiseListByTypeCode(typeCode);
	}

	@Override
	public CruiseScheduleVO getCruiseScheduleWithName(TourDepartureVO tourDepVO) throws BusinessException {
		CruiseScheduleVO vo = cruiseDAO.getCruiseScheduleWithName(tourDepVO.getIdCruiseSchedule());
		return getCruiseScheduleDetails(vo, tourDepVO);
	}

	private CruiseScheduleVO getCruiseScheduleDetails(CruiseScheduleVO vo, TourDepartureVO tourDepVO)
			throws BusinessException {
		// set item list
		if (vo != null) {

			if (StringUtils.isNotEmpty(tourDepVO.getCruiseScheduleItems())
					&& StringUtils.isNotEmpty(tourDepVO.getCruiseScheduleItemsBySeq())) {
//				System.out.println("CYY tourDepVO.getCruiseScheduleItems(): " + tourDepVO.getCruiseScheduleItems());
//				System.out.println("CYY tourDepVO.getCruiseScheduleItemsBySeq(): " + tourDepVO.getCruiseScheduleItemsBySeq());
//				System.out.println("CYY tourDepVO.getCruiseScheduleItemsByEtd(): " + tourDepVO.getCruiseScheduleItemsByEtd());
//				System.out.println("CYY tourDepVO.getCruiseScheduleItemsByEta(): " + tourDepVO.getCruiseScheduleItemsByEta());
//				System.out.println("CYY tourDepVO.getCruiseScheduleItemsByDepDt(): " + tourDepVO.getCruiseScheduleItemsByDepDt());
//				System.out.println("CYY tourDepVO.getCruiseScheduleItemsByArrDt(): " + tourDepVO.getCruiseScheduleItemsByArrDt());
				SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
				String[] cruiseSchdlItems = tourDepVO.getCruiseScheduleItems().replaceAll(",$", "").split(",");
//				System.out.println("CYY cruiseSchdlItems.length: " + cruiseSchdlItems.length);
				String[] cruiseSchdlItemSeq = tourDepVO.getCruiseScheduleItemsBySeq().replaceAll(",$", "").split(",");
				String[] cruiseSchdlItemEtd = (StringUtils.isEmpty(tourDepVO.getCruiseScheduleItemsByEtd())
						? new String[] {}
						: tourDepVO.getCruiseScheduleItemsByEtd().replaceAll(",$", "").split(","));
				String[] cruiseSchdlItemEta = (StringUtils.isEmpty(tourDepVO.getCruiseScheduleItemsByEta())
						? new String[] {}
						: tourDepVO.getCruiseScheduleItemsByEta().replaceAll(",$", "").split(","));
				String[] cruiseSchdlItemDepDt = (StringUtils.isEmpty(tourDepVO.getCruiseScheduleItemsByDepDt())
						? new String[] {}
						: tourDepVO.getCruiseScheduleItemsByDepDt().replaceAll(",$", "").split(","));
				String[] cruiseSchdlItemArrDt = (StringUtils.isEmpty(tourDepVO.getCruiseScheduleItemsByArrDt())
						? new String[] {}
						: tourDepVO.getCruiseScheduleItemsByArrDt().replaceAll(",$", "").split(","));
				Map<String, String> unsortMap = new HashMap<String, String>();
				Map<String, String> unsortMapEtd = new HashMap<String, String>();
				Map<String, String> unsortMapEta = new HashMap<String, String>();
				Map<String, String> unsortMapDepDt = new HashMap<String, String>();
				Map<String, String> unsortMapArrDt = new HashMap<String, String>();
				Long[] ids = new Long[cruiseSchdlItems.length];

				for (int i = 0; i < cruiseSchdlItems.length; i++) {
					unsortMap.put(cruiseSchdlItems[i],
							StringUtils.isBlank(cruiseSchdlItemSeq[i]) ? "" : String.valueOf(cruiseSchdlItemSeq[i]));
					if (cruiseSchdlItemEtd.length > 0)
						unsortMapEtd.put(cruiseSchdlItems[i],
								StringUtils.isBlank(cruiseSchdlItemEtd[i]) ? "" : cruiseSchdlItemEtd[i]);
					if (cruiseSchdlItemEta.length > 0)
						unsortMapEta.put(cruiseSchdlItems[i],
								StringUtils.isBlank(cruiseSchdlItemEta[i]) ? "" : cruiseSchdlItemEta[i]);
					if (cruiseSchdlItemDepDt.length > 0 && cruiseSchdlItemDepDt.length > i)
						unsortMapDepDt.put(cruiseSchdlItems[i],
								StringUtils.isBlank(cruiseSchdlItemDepDt[i]) ? "" : cruiseSchdlItemDepDt[i]);
					else	unsortMapDepDt.put(cruiseSchdlItems[i], "");
					if (cruiseSchdlItemArrDt.length > 0 && cruiseSchdlItemArrDt.length > i)
						unsortMapArrDt.put(cruiseSchdlItems[i],
								StringUtils.isBlank(cruiseSchdlItemArrDt[i]) ? "" : cruiseSchdlItemArrDt[i]);
					else	unsortMapArrDt.put(cruiseSchdlItems[i], "");
					ids[i] = Long.parseLong(cruiseSchdlItems[i]);
				}
				Map<String, String> cruiseScheduleItemsMap = new TreeMap<String, String>(unsortMap);
				Map<String, String> cruiseScheduleItemsMapEtd = new TreeMap<String, String>(unsortMapEtd);
				Map<String, String> cruiseScheduleItemsMapEta = new TreeMap<String, String>(unsortMapEta);
				Map<String, String> cruiseScheduleItemsMapDepDt = new TreeMap<String, String>(unsortMapDepDt);
				Map<String, String> cruiseScheduleItemsMapArrDt = new TreeMap<String, String>(unsortMapArrDt);
				vo.setCruiseScheduleItemList(new ArrayList<CruiseScheduleItemVO>());

				List<CruiseScheduleItemVO> tempCruiseScheduleItemList = cruiseDAO
						.getCruiseScheduleItemList(vo.getId(), ids);
				vo.setSelectedScheduleItems(new CruiseScheduleItemVO[cruiseScheduleItemsMap.size()]);

				if (CollectionUtils.isNotEmpty(tempCruiseScheduleItemList)) {
					for (CruiseScheduleItemVO itemVO : tempCruiseScheduleItemList) {
						CruiseScheduleItemVO tempItemVO = SerializationUtils.clone(itemVO);
						tempItemVO.setCruise(vo.getCruiseName());

						for (Map.Entry<String, String> entry : cruiseScheduleItemsMap.entrySet()) {
							if (tempItemVO.getId().longValue() == Long.parseLong((String) entry.getKey())) {
								tempItemVO.setSeq(StringUtils.isBlank(entry.getValue()) ? null
										: Integer.parseInt(entry.getValue()));
							}
						}
						for (Map.Entry<String, String> entry : cruiseScheduleItemsMapEtd.entrySet()) {
							if (tempItemVO.getId().longValue() == Long.parseLong((String) entry.getKey())) {
								tempItemVO.setEtd(StringUtils.isBlank(entry.getValue()) ? "" : entry.getValue());
							}
						}
						for (Map.Entry<String, String> entry : cruiseScheduleItemsMapEta.entrySet()) {
							if (tempItemVO.getId().longValue() == Long.parseLong((String) entry.getKey())) {
								tempItemVO.setEta(StringUtils.isBlank(entry.getValue()) ? "" : entry.getValue());
							}
						}
						for (Map.Entry<String, String> entry : cruiseScheduleItemsMapDepDt.entrySet()) {
							if (tempItemVO.getId().longValue() == Long.parseLong((String) entry.getKey())) {
								try {
									tempItemVO.setDepartureDate(
											StringUtils.isBlank(entry.getValue()) ? null : sdf.parse(entry.getValue()));
								} catch (ParseException e) {
									e.printStackTrace();
								}
							}
						}
						for (Map.Entry<String, String> entry : cruiseScheduleItemsMapArrDt.entrySet()) {
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
							vo.getCruiseScheduleItemList().add(tempItemVO);
						}
					}

					Collections.sort(vo.getCruiseScheduleItemList(), new Comparator<CruiseScheduleItemVO>() {
						@Override
						public int compare(CruiseScheduleItemVO u1, CruiseScheduleItemVO u2) {
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
