package com.bcs.zsg.product.web.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.common.helper.TrackingLogUtils;
import com.bcs.zsg.common.web.bean.AppBackingBean;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.product.bo.CampaignBO;
import com.bcs.zsg.product.vo.CampaignCountryPackageVO;
import com.bcs.zsg.product.vo.CampaignCountryVO;
import com.bcs.zsg.product.vo.CampaignVO;
import com.bcs.zsg.product.vo.TourPackageVO;

public class CampaignBean extends AppBackingBean {

	@Autowired
	protected transient CampaignBO campaignBO;
	
	TrackingLogUtils trackingLogUtils;

	private static final long serialVersionUID = 1L;

	public List<CampaignVO> campaignList;
	public CampaignVO campaignVO;
	public CampaignCountryVO campaignCountryVO;
	public CampaignCountryPackageVO campaignCountryPackageVO;
	
	public List<TourPackageVO> selectedTourPkgList;
	public List<TourPackageVO> tourPkgList;
	public List<CampaignCountryPackageVO> campaignCountryPkgList;
	public LazyDataModel<TourPackageVO> lazyDataModel;

	protected Object[] selectedTourPkgs;
	
	public void init() {
		try {
			initSearchParam();
			resetForm();

			trackingLogUtils = new TrackingLogUtils(getClass());

			loadCampaign();

		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
	}

	@Override
	public void resetForm() {
		campaignVO = new CampaignVO();
		campaignCountryVO = new CampaignCountryVO();
		
		resetCampaignCountryPackageSelection();
	}

	public void loadCampaign() throws BusinessException {
		campaignList = campaignBO.getCampaignList(getSessionInfoBean().getCompanyVO().getId());
	}
	
	public void searchCampaign() throws BusinessException {
		campaignList = campaignBO.getCampaignList(getSessionInfoBean().getCompanyVO().getId(), searchParamVO.getFromDate(), searchParamVO.getToDate());
	}

	public void saveCampaign() {
		
		try {
			trackingLogUtils.startLogs();
			
			campaignVO.setCampaignStatus("IDLE");

			if (campaignVO.getId() == null) {
				campaignBO.addCampaign(campaignVO, getSessionInfoBean().getCompanyVO().getId());
			} else {
				campaignBO.updCampaign(campaignVO, getSessionInfoBean().getCompanyVO().getId());
			}
	
			successResult();
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		} finally {
			trackingLogUtils.endLogs("saveCampaign");
		}
		
		onCampaignSelected(campaignVO);
		
	}
	
	public void loadTourPkgList(CampaignCountryVO campaignCountryVO) {
		try {
			resetCampaignCountryPackageSelection();
			
			setCampaignCountryVO(campaignCountryVO);
			
			lazyDataModel = new LazyTourPkgDataModel(campaignCountryVO);
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		} finally {
		}
	}

	public void loadTourPkgList() {
		try {
			lazyDataModel = new LazyTourPkgDataModel();
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		} finally {
		}
	}

	public void addCampaignCountryPkg() {
		try {
			trackingLogUtils.startLogs();
			
//			System.out.println("CYY selectedTourPkgs.length: " + selectedTourPkgs.length);
//			System.out.println("CYY selectedTourPkgList.size(): " + selectedTourPkgList.size());
			
			System.out.println("CampaignBean.addCampaignCountryPkg()");
			for (TourPackageVO object : selectedTourPkgList) {
				System.out.println(object.getId());
			}
			
			if (CollectionUtils.isEmpty(campaignCountryVO.getCampaignCountryPackageList())) {
				campaignCountryVO.setCampaignCountryPackageList(new ArrayList<CampaignCountryPackageVO>());
			}
			for (TourPackageVO pkgVO : selectedTourPkgList) {
				
				// check if pkg added before, if added then skip
				boolean exist = false;
				for (CampaignCountryPackageVO vo : campaignCountryVO.getCampaignCountryPackageList()) {
					if (vo.getIdTourPkg().equals(pkgVO.getId())) {
						exist = true;
						break;
					}
				}
				if (exist)	continue;
				
				CampaignCountryPackageVO campaignCountryPackageVO = new CampaignCountryPackageVO();
//				campaignCountryPackageVO.setIdCampaignCountry(campaignCountryVO.getId()); // campaignCountryVO might not have an ID yet
				campaignCountryPackageVO.setIdTourPkg(pkgVO.getId());
				campaignCountryPackageVO.setTourPkgVO(pkgVO);
				
				campaignCountryVO.getCampaignCountryPackageList().add(campaignCountryPackageVO);
			}
			
			resetCampaignCountryPackageSelection();
			
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		} finally {
			trackingLogUtils.endLogs("addCampaignCountryPkg");
		}
	}
	
	public void deleteCampaignCountryPkg(CampaignCountryPackageVO campaignCountryPackageVO, CampaignCountryVO campaignCountryVO) {
		campaignCountryVO.getCampaignCountryPackageList().remove(campaignCountryPackageVO);
	}

	public void onCampaignSelected(CampaignVO campaignVO) {
		try {
			
			trackingLogUtils.startLogs();

			this.campaignVO = campaignVO;
			campaignBO.getCampaignDetails(this.campaignVO);
			
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		} finally {
			trackingLogUtils.endLogs("onCampaignSelected");
		}
	}

	public void setCampaign(CampaignVO campaignVO) {
		try {

			this.campaignVO = campaignVO;

		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
	}

	public void deleteCampaign() {
		try {
			campaignBO.delCampaign(campaignVO);
			loadCampaign();
			
			successResult();
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
	}

	public void startCampaign(CampaignVO campaignVO) {
		try {
			
//			if (!campaignVO.getStatus().equals("RUNNING")) {
//				campaignBO.startSelectedCampaign(campaignVO);
//				campaignVO.setStatus("RUNNING");
//				
//				loadSelectedTours(campaignVO);
//
//				successResult();
//			}
			
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
	}

	public void stopCampaign(CampaignVO campaignVO) {
		try {
			
//			if (campaignVO.getStatus().equals("RUNNING")) {
//				campaignBO.stopSelectedCampaign(campaignVO);
//				campaignVO.setStatus("IDLE");
//
//				loadSelectedTours(campaignVO);
//
//				successResult();
//			}
			
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
	}
	
	public void resetCampaignCountryForm() {
		campaignCountryVO = new CampaignCountryVO();
	}
	
	private void resetCampaignCountryPackageSelection() {
		selectedTourPkgs = new Object[1];
		selectedTourPkgList = new ArrayList<TourPackageVO>();
	}
	
	public void onAddCampaignCountry() {
		try {
			resetCampaignCountryForm();
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
	}
	
	public void addCampaignCountry() {
		try {
//			campaignBO.addCampaignCountry(campaignCountryVO, campaignVO.getId());
			if (CollectionUtils.isEmpty(campaignVO.getCampaignCountryList())) {
				campaignVO.setCampaignCountryList(new ArrayList<CampaignCountryVO>());
			}
			
			campaignCountryVO.setSeq(campaignVO.getCampaignCountryList().size() + 1);
			campaignVO.getCampaignCountryList().add(campaignCountryVO);
			
			resetCampaignCountryForm();
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
	}
	
	public void updateCampaignCountry() {
		try {
			resetCampaignCountryForm();
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
	}
	
	public void deleteCampaignCountry() {
		try {
			campaignVO.getCampaignCountryList().remove(campaignCountryVO);
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
	}
	
	public void assignItemSeq(CampaignCountryVO campaignCountryVO) {

		campaignCountryPkgList = campaignCountryVO.getCampaignCountryPackageList();

	    int maxSeq = 0;
	    boolean hasSeq = false;

	    for (int i = 0; i < campaignCountryPkgList.size(); i++) {
	    	campaignCountryPackageVO = campaignCountryPkgList.get(i);

	        if (campaignCountryPackageVO != null && campaignCountryPackageVO.getSeq() != null) {
	            hasSeq = true;
	            if (campaignCountryPackageVO.getSeq().intValue() > maxSeq) {
	                maxSeq = campaignCountryPackageVO.getSeq();
	            }
	        }
	    }

	    int nextSeq = hasSeq ? (maxSeq + 1) : 1;

	    for (int i = 0; i < campaignCountryPkgList.size(); i++) {
	    	campaignCountryPackageVO = campaignCountryPkgList.get(i);

	        if (campaignCountryPackageVO != null && campaignCountryPackageVO.getSeq() == null) {
	        	campaignCountryPackageVO.setSeq(nextSeq);
	            nextSeq++;
	        }
	    }
	}
	
	/**********************
	 * Lazy loading model *
	 **********************/
	class LazyTourPkgDataModel extends LazyDataModel<TourPackageVO> implements Serializable {
		private static final long serialVersionUID = 1L;
		
		private CampaignCountryVO campaignCountryVO;
		
		LazyTourPkgDataModel() {
			
		}
		
		LazyTourPkgDataModel(CampaignCountryVO campaignCountryVO) {
			this.campaignCountryVO = campaignCountryVO;
		}
		
		@Override
		public List<TourPackageVO> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
			try {
				trackingLogUtils.startLogs();
				
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("idCompany", getSessionInfoBean().getCompanyVO().getId());
				params.put("first", first);
				params.put("pageSize", pageSize);
				params.put("sortField", sortField);
				params.put("sortOrder", sortOrder);
				params.put("filters", filters);
				List<String> typeList = Arrays.asList("TOUR");
				params.put("typeList", typeList);
				
				if (campaignCountryVO != null) {
					if(CollectionUtils.isNotEmpty(campaignCountryVO.getCampaignCountryPackageList())) {
						List<Long> excludedPkgIdList = new ArrayList<Long>();
						if (CollectionUtils.isNotEmpty(campaignCountryVO.getCampaignCountryPackageList())) {
							for (CampaignCountryPackageVO vo : campaignCountryVO.getCampaignCountryPackageList()) {
								excludedPkgIdList.add(vo.getIdTourPkg());
//								System.out.println(vo.getIdTourPkg());
							}
						}
						params.put("excludedPkgIdList", excludedPkgIdList);
					}
				}
				
				setRowCount(campaignBO.getTourPkgListSize(params));
				
//				System.out.println("CYY getRowCount(): " + getRowCount());
				
				if (0 < getRowCount()) {
					tourPkgList = campaignBO.getTourPkgList(params);
//					System.out.println("CYY tourPkgList.size(): " + tourPkgList.size());
					return tourPkgList;
				}
				
			} catch (Throwable t) {
				t.printStackTrace();
				errorResult(t);
			} finally {
				trackingLogUtils.endLogs("LazyTourPkgDataModel");
			}
			return new ArrayList<TourPackageVO>();
		}
		
		@Override
		public void setRowIndex(int rowIndex) {
			/*
			 * The following is in ancestor (LazyDataModel):
			 * this.rowIndex = rowIndex == -1 ? rowIndex : (rowIndex % pageSize);
			 */
			if (rowIndex == -1 || getPageSize() == 0) {
				super.setRowIndex(-1);
			} else super.setRowIndex(rowIndex % getPageSize());
		}
		
	}

	public List<CampaignVO> getCampaignList() {
		return campaignList;
	}

	public CampaignVO getCampaignVO() {
		return campaignVO;
	}

	public void setCampaignVO(CampaignVO campaignVO) {
		this.campaignVO = campaignVO;
	}

	public CampaignCountryVO getCampaignCountryVO() {
		return campaignCountryVO;
	}

	public void setCampaignCountryVO(CampaignCountryVO campaignCountryVO) {
		this.campaignCountryVO = campaignCountryVO;
	}

	public List<TourPackageVO> getTourPkgList() {
		return tourPkgList;
	}

	public void setTourPkgList(List<TourPackageVO> tourPkgList) {
		this.tourPkgList = tourPkgList;
	}

	public List<TourPackageVO> getSelectedTourPkgList() {
		return selectedTourPkgList;
	}

	public void setSelectedTourPkgList(List<TourPackageVO> selectedTourPkgList) {
		this.selectedTourPkgList = selectedTourPkgList;
	}

	public Object[] getSelectedTourPkgs() {
		return selectedTourPkgs;
	}

	public void setSelectedTourPkgs(Object[] selectedTourPkgs) {
		this.selectedTourPkgs = selectedTourPkgs;
		if (CollectionUtils.isEmpty(selectedTourPkgList))	selectedTourPkgList = new ArrayList<TourPackageVO>();
		if (selectedTourPkgs != null) {
			for (Object obj : selectedTourPkgs) {
				TourPackageVO tourPkgVO = (TourPackageVO) obj;
				boolean existed = false;
				for (TourPackageVO selectedVO : selectedTourPkgList) {
					if (selectedVO.getId().equals(tourPkgVO.getId())) {
						existed = true;
						break;
					}
				}
				if (!existed)	selectedTourPkgList.add(tourPkgVO);
			}
		}
		
		List<TourPackageVO> dataList = new ArrayList<TourPackageVO>(tourPkgList);
		if (dataList != null && dataList.size() > 0) {
			List<TourPackageVO> removeList = new ArrayList<TourPackageVO>();
			for (TourPackageVO data : dataList) {
				for (Object obj : selectedTourPkgs) {
					TourPackageVO tourPkgVO = (TourPackageVO) obj;
					if (data.getId().equals(tourPkgVO.getId())) {
						removeList.add(data);
						break;
					}
				}
			}
			if (removeList.size() > 0)
				dataList.removeAll(removeList);
			removeList = new ArrayList<TourPackageVO>();
			for (TourPackageVO data : dataList) {
				for (TourPackageVO selectedVO : selectedTourPkgList) {
					if (data.getId().equals(selectedVO.getId())) {
						removeList.add(selectedVO);
						break;
					}
				}
			}
			if (removeList.size() > 0)
				selectedTourPkgList.removeAll(removeList);
		}
		
//		System.out.println("CampaignBean.setSelectedTourPkgs()");
//		for (TourPackageVO tourPackageVO : selectedTourPkgList) {
//			System.out.println(tourPackageVO.getId() + " " + tourPackageVO.getNameEn());
//		}
		this.selectedTourPkgs = this.selectedTourPkgList.toArray(new Object[this.selectedTourPkgList.size()]);
	}

	public LazyDataModel<TourPackageVO> getLazyDataModel() {
		return lazyDataModel;
	}

	public void setLazyDataModel(LazyDataModel<TourPackageVO> lazyDataModel) {
		this.lazyDataModel = lazyDataModel;
	}
	
//	public void onPagination() {
//		selectedTourPkgs = new Object[selectedTourPkgList.size()];
//		for (int j = 0; j < selectedTourPkgList.size(); j++) {
//			selectedTourPkgs[j] = selectedTourPkgList.get(j);
//		}
//	}
	
}
