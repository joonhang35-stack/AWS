package com.bcs.zsg.maintenance.web.bean;


import java.io.File;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.common.helper.AppConfigConstant;
import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.common.helper.CommonErrConstant;
import com.bcs.zsg.common.helper.FileUploadUtils;
import com.bcs.zsg.common.helper.LookupItemUtils;
import com.bcs.zsg.common.vo.AddUpdDelVO;
import com.bcs.zsg.common.vo.FileUploadVO;
import com.bcs.zsg.common.web.bean.AppBackingBean;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.bo.RegionBO;
import com.bcs.zsg.maintenance.helper.MaintConstant;
import com.bcs.zsg.maintenance.vo.CityVO;
import com.bcs.zsg.maintenance.vo.RegionVO;
import com.bcs.zsg.purchase.vo.CountryVO;
import com.bcs.zsg.purchase.vo.CountryViewVO;



public class RegionAndCountriesBean extends AppBackingBean 
{
	private static final long serialVersionUID = 1L;

	@Autowired
	private transient RegionBO regionBO;

	private RegionVO regionVO;
	private CountryVO countryVO;
	private CityVO cityVO;
	private CountryViewVO countryViewVO;
	private UploadedFile uploadedFile;
	protected AddUpdDelVO itineryAUDVO;

	// upload file properties
	protected Map<String,CountryVO> countryMap;
	protected FileUploadUtils fileUploadUtils;
	protected String destinationPathCountry;
	
	private List<RegionVO> regionMainList;
	private List<CountryVO> countryList;
	private List<CityVO> cityList;
	private LazyDataModel<RegionVO> lazyRegionDataModel;
	private LazyDataModel<CountryVO> lazyCountryDataModel;
	private LazyDataModel<CityVO> lazyCityDataModel;

	@Override
	public void resetForm() 
	{
		regionVO = new RegionVO();
		countryVO=new CountryVO();
		cityVO = new CityVO();
		countryViewVO=new CountryViewVO();
		countryMap = new HashMap<String,CountryVO>();
		itineryAUDVO = new AddUpdDelVO(new ArrayList<Object>(), new ArrayList<Object>(), new ArrayList<Object>());
		resetFileUploadForm();
	}

	/**
	 * Reset file upload instance
	 */
	public void resetFileUploadForm() {
		fileUploadUtils = new FileUploadUtils(AppConfigConstant.uploadPath);
	}

	public void init() {
		try {
			resetForm();
			loadRegionAndCountries();
			destinationPathCountry = LookupItemUtils.getGlobalConfigValue(MaintConstant.GLOBAL_CD_GLOBAL, MaintConstant.GLOBAL_CD_PATH_COUNTRY);
		
		}catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
	}

	public void addRegion() throws BusinessException 
	{
		regionBO.addRegion(regionVO);
		resetForm();
		loadRegionAndCountries();	
		successResult();
	}

	public void uploadImage(FileUploadEvent event) { 
		try{
			
			// set uploaded file
			uploadedFile = event.getFile();
			// rename the valid file name
			String fileName = uploadedFile.getFileName().replace(" ", "-");
			// file validation
			fileUploadUtils.uploadFileValidation(destinationPathCountry,fileName);
			countryVO.setPrevImage(countryVO.getImage());
			
			if(countryVO.getImage()!=null)
			{
				countryMap.remove("image");
				fileUploadUtils.getDelList().add(destinationPathCountry  + File.separator + countryVO.getImage());
				if(fileUploadUtils.getMap().size() > 0) {
					fileUploadUtils.deleteFileFromMap(countryVO.getImage());
				}
			}	
			// add file to map (cache)
			fileUploadUtils.getMap().put(fileName, new FileUploadVO(uploadedFile,destinationPathCountry,fileName));
			countryVO.setImage(fileName);
			countryVO.setPath(destinationPathCountry);
			// add file to temp map for view purpose
			countryMap.put("image",countryVO);
			//countryVO=new CountryVO();
			
			//copyFile(event.getFile().getFileName(),event.getFile().getInputstream());
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success! ", event.getFile().getFileName() + " is uploaded."); 
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}catch(Throwable t){
			errorResult(t);
		}

	} 
	
	/**public void copyFile(String fileName, java.io.InputStream inputStream) {
		try {
			// write the inputStream to a FileOutputStream
			FileOutputStream out = new FileOutputStream(new File(destinationPathCountry + fileName));
			int read = 0;
			byte[] bytes = new byte[1024];
			while ((read = inputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			inputStream.close();
			out.flush();
			out.close();
			System.out.println("New file created!");
		
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	**/

	public void addCountry() throws BusinessException{
		try{
			if(countryVO.getCode().isEmpty()||countryVO.getCountry().isEmpty())
			{
				if(countryVO.getCode().isEmpty()&&countryVO.getCountry().isEmpty()) throw new BusinessException(CommonErrConstant.ERR_REGION_COUNTRY_COUNTRY_CODE_COUNTRY_NAME_REQUIRED);
				else if(countryVO.getCode().isEmpty()) throw new BusinessException(CommonErrConstant.ERR_REGION_COUNTRY_COUNTRY_CODE_REQUIRED);
				else if(countryVO.getCountry().isEmpty()) throw new BusinessException(CommonErrConstant.ERR_REGION_COUNTRY_COUNTRY_NAME_REQUIRED);
			}
			else
			{	
				fileUploadUtils.uploadFile();
				resetFileUploadForm();
				for(CountryVO country : countryList) {
					if (country.getCode().toString().equalsIgnoreCase(countryVO.getCode().toString())) throw new BusinessException(CommonErrConstant.ERR_REGION_COUNTRY_COUNTRY_CODE_ALREADY_EXISTS);	
				}
				regionBO.addCountry(countryVO);
			}
			//loadRegionAndCountries();
			//resetForm();
			regionMainList=regionBO.getRegionList();
			search();
			countryVO=new CountryVO();
			//countryVO.setRegionId(countryViewVO.getRegionVO().getId());
			onCountry();
			successResult();
			
		}catch(Throwable t){
			errorResult(t);
		}

	}

	public void addCity() throws BusinessException{
		try{
			if(cityVO.getCode().isEmpty()||cityVO.getName().isEmpty())
			{
				if(cityVO.getCode().isEmpty()&&cityVO.getName().isEmpty()) throw new BusinessException(CommonErrConstant.ERR_REGION_COUNTRY_CITY_CODE_CITY_NAME_REQUIRED);
				else if(cityVO.getCode().isEmpty()) throw new BusinessException(CommonErrConstant.ERR_REGION_COUNTRY_CITY_CODE_REQUIRED);
				else if(cityVO.getName().isEmpty()) throw new BusinessException(CommonErrConstant.ERR_REGION_COUNTRY_CITY_NAME_REQUIRED);
			}
			else
			{	
				for(CityVO city : cityList) {
					if (city.getCode().toString().equalsIgnoreCase(cityVO.getCode().toString())) throw new BusinessException(CommonErrConstant.ERR_REGION_COUNTRY_CITY_CODE_ALREADY_EXISTS);	
				}
				regionBO.addCity(cityVO);
			}

			countryList=regionBO.getCountryList();
			search();
			cityVO=new CityVO();
			//countryVO.setRegionId(countryViewVO.getRegionVO().getId());
			onCountry();
			successResult();
			
		}catch(Throwable t){
			errorResult(t);
		}

	}


	public void search() throws BusinessException 
	{
		lazyRegionDataModel=new LazyRegionDataModel() ;
		lazyCountryDataModel=new LazyCountryDataModel();
		lazyCityDataModel=new LazyCityDataModel();
	}

	private void loadRegionAndCountries() throws BusinessException {
		try {
			regionMainList=regionBO.getRegionList();
			search();
			countryList=regionBO.getCountryList();
			cityList = regionBO.getCityList();
			resetForm();
			
		}catch (Throwable t) {
			errorResult(t);
		}
	}

	public void editRegion() throws BusinessException{
		try {
			regionBO.updateRegion(regionVO);
			search();
			/*loadRegionAndCountries();
			resetForm();*/
			successResult();

		} catch (Throwable t) {
			errorResult(t);
		}
	}



	public void editCountry() throws BusinessException {
		try {
			if(countryVO.getCode().isEmpty()||countryVO.getCountry().isEmpty())
			{
				if(countryVO.getCode().isEmpty()&&countryVO.getCountry().isEmpty()) throw new BusinessException(CommonErrConstant.ERR_REGION_COUNTRY_COUNTRY_CODE_COUNTRY_NAME_REQUIRED);
				else if(countryVO.getCode().isEmpty()) throw new BusinessException(CommonErrConstant.ERR_REGION_COUNTRY_COUNTRY_CODE_REQUIRED);
				else if(countryVO.getCountry().isEmpty()) throw new BusinessException(CommonErrConstant.ERR_REGION_COUNTRY_COUNTRY_NAME_REQUIRED);
			}
			else
			{	
				fileUploadUtils.deleteFiles();
				fileUploadUtils.uploadFile();
				resetFileUploadForm();
				regionBO.updateCountry(countryVO);
			}
			search();
			//loadRegionAndCountries();
			//resetForm();
			successResult();
			
		} catch (Throwable t) {
			errorResult(t);
			
		}
	}
	
	public void editCity() throws BusinessException{
		try{
			if(cityVO.getCode().isEmpty()||cityVO.getName().isEmpty())
			{
				if(cityVO.getCode().isEmpty()&&cityVO.getName().isEmpty()) throw new BusinessException(CommonErrConstant.ERR_REGION_COUNTRY_CITY_CODE_CITY_NAME_REQUIRED);
				else if(cityVO.getCode().isEmpty()) throw new BusinessException(CommonErrConstant.ERR_REGION_COUNTRY_CITY_CODE_REQUIRED);
				else if(cityVO.getName().isEmpty()) throw new BusinessException(CommonErrConstant.ERR_REGION_COUNTRY_CITY_NAME_REQUIRED);
			}
			else
			{	
				regionBO.updateCity(cityVO);
			}

			search();
			successResult();
			
		}catch(Throwable t){
			errorResult(t);
		}

	}

	public void deleteRegion() throws BusinessException{
		try {
			regionBO.deleteRegion(regionVO);
			search();
			//loadRegionAndCountries();
			//resetForm();
			successResult();

		} catch (Throwable t) {
			errorResult(t);
		}
	}


	public void deleteCountry() throws BusinessException{
		try {
			regionBO.deleteCountry(countryVO);
			countryMap.remove("image");
			fileUploadUtils.deleteFile(destinationPathCountry,countryVO.getImage());
			countryMap = new HashMap<String,CountryVO>();
			resetFileUploadForm();
			//loadRegionAndCountries();
			regionMainList=regionBO.getRegionList();
			search();
			//countryVO=new CountryVO();
			//resetForm();
			successResult();

		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void deleteCity() throws BusinessException{
		try {
			regionBO.deleteCity(cityVO);
			countryList=regionBO.getCountryList();
			search();
			successResult();

		} catch (Throwable t) {
			errorResult(t);
		}
	}

	public void onRegionSelected(RegionVO regionVO) throws BusinessException{
		try {
			this.regionVO = regionVO;

		}catch (Throwable t) {
			errorResult(t);
		}
	}


	public void onCountrySelected(CountryVO countryVO) throws BusinessException{
		try {
			this.countryVO=countryVO;
			countryMap = new HashMap<String,CountryVO>();
			fileUploadUtils.setDelList(new ArrayList<String>());
			resetFileUploadForm();
		}catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
	}
	
	public void onCitySelected(CityVO cityVO) throws BusinessException{
		try {
			this.cityVO=cityVO;
		}catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void onCountry() throws BusinessException{
		try{
			countryVO = new CountryVO();
			for (RegionVO vo : regionMainList) {
				if (CommonConstant.DEF_REGION_CD.equals(vo.getRegioncode())) {
					countryVO.setRegionId(vo.getId());
					break;
				}
			}
			countryMap = new HashMap<String,CountryVO>();
			fileUploadUtils.setDelList(new ArrayList<String>());
			resetFileUploadForm();
		}catch(Throwable t){
			errorResult(t);
		}
	}
	
	public void onCity() throws BusinessException{
		try{
			cityVO = new CityVO();
		}catch(Throwable t){
			errorResult(t);
		}
	}

	public Converter getRegionConverter() {
		return new Converter() {

			@Override
			public Object getAsObject(FacesContext context, UIComponent component, String s) {
				if (s != null) {
					for (RegionVO vo : regionMainList) {
						if (String.valueOf(vo.getId()).equals(s)) {
							return vo;
						}
					}
				}
				return null;
			}

			@Override
			public String getAsString(FacesContext context, UIComponent component, Object obj) {
				if (obj != null) return ((RegionVO) obj).getId().toString();
				return null;
			}

		};
	}
	
	public void cancel() throws BusinessException{
		try{
			if(countryVO.getImage()!=null)
			{	
				//fileUploadUtils.deleteFileFromMap(countryVO.getImage());
				countryMap.remove("image");
				fileUploadUtils.deleteFile(destinationPathCountry,countryVO.getImage());
				countryMap = new HashMap<String,CountryVO>();
				resetFileUploadForm();
			}
			resetForm();
			
		}catch(Throwable t){
			errorResult(t);
		}
	}
	
	public void EditCountryCancel() throws BusinessException{
		try{
			if(countryVO.getPrevImage()!=null)
			{	
				//fileUploadUtils.deleteFileFromMap(countryVO.getImage());
				countryMap.remove("image");
				fileUploadUtils.deleteFile(destinationPathCountry,countryVO.getImage());
			}
			resetForm();
			
		}catch(Throwable t){
			errorResult(t);
		}
	}
	
	public void updateActualCountry(CountryVO countryVO) {
//		System.out.println("RegionAndCountriesBean.updateActualCountry()");
//		System.out.println(countryVO.getIsActualCountry());
		
		try {
			regionBO.updateActualCountry(countryVO.getId(), countryVO.getIsActualCountry());
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
	}
	
	/**
	 * 
	 * @author hueyshian
	 *
	 */
	
	class LazyRegionDataModel extends LazyDataModel<RegionVO> implements Serializable {
		private static final long serialVersionUID = 1L;

		/*
		 * (non-Javadoc)
		 * @see org.primefaces.model.LazyDataModel#load(int, int, java.lang.String, org.primefaces.model.SortOrder, java.util.Map)
		 */
		@Override
		public List<RegionVO> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
			try {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("first", first);
				params.put("pageSize", pageSize);
				params.put("sortField", sortField);
				params.put("sortOrder", sortOrder);
				params.put("filters", filters);
				List<RegionVO> list=new ArrayList<RegionVO>(); 
				if (countryViewVO.getRegionVO()==null ) 
				{
					int size =regionBO.getRegionListSize(params,null);
					setRowCount(size);
					if (size > 0)
					{
						return regionBO.getRegionListParam(params,null);
					}
				}		
				else 
				{
					int size=regionBO.getRegionListSize(params,countryViewVO.getRegionVO());
					setRowCount(size);
					if (size > 0)
					{
						return regionBO.getRegionListParam(params,countryViewVO.getRegionVO());								
					}
				}
			} catch (Throwable t) {
				errorResult(t);
			}
			return null;
		}
		@Override
		public void setRowIndex(int rowIndex) {
			
			if (rowIndex == -1 || getPageSize() == 0) {
				super.setRowIndex(-1);
			} else super.setRowIndex(rowIndex % getPageSize());
		}
	}
	class LazyCountryDataModel extends LazyDataModel<CountryVO> implements Serializable {
		private static final long serialVersionUID = 1L;

		/*
		 * (non-Javadoc)
		 * @see org.primefaces.model.LazyDataModel#load(int, int, java.lang.String, org.primefaces.model.SortOrder, java.util.Map)
		 */
		@Override
		public List<CountryVO> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
			try {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("first", first);
				params.put("pageSize", pageSize);
				params.put("sortField", sortField);
				params.put("sortOrder", sortOrder);
				params.put("filters", filters);
				List<CountryVO> list=new ArrayList<CountryVO>(); 
				if (countryViewVO.getRegionVO()==null ) 
				{
					int size=regionBO.getCountryListSize(params,null);
					setRowCount(size);
					if (size > 0)
					{
						return regionBO.getCountryListParam(params,null);
					}
				}		
				else 
				{
					int size=regionBO.getCountryListSize(params,countryViewVO.getRegionVO());
					setRowCount(size);
					if (size > 0)
					{
						return regionBO.getCountryListParam(params,countryViewVO.getRegionVO());
					}
				}
			
			} catch (Throwable t) {
				t.printStackTrace();
				errorResult(t);
			}
			return null;
		}
		@Override
		public void setRowIndex(int rowIndex) {
			
			if (rowIndex == -1 || getPageSize() == 0) {
				super.setRowIndex(-1);
			} else super.setRowIndex(rowIndex % getPageSize());
		}
	}
	
	class LazyCityDataModel extends LazyDataModel<CityVO> implements Serializable {
		private static final long serialVersionUID = 1L;

		/*
		 * (non-Javadoc)
		 * @see org.primefaces.model.LazyDataModel#load(int, int, java.lang.String, org.primefaces.model.SortOrder, java.util.Map)
		 */
		@Override
		public List<CityVO> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
			try {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("first", first);
				params.put("pageSize", pageSize);
				params.put("sortField", sortField);
				params.put("sortOrder", sortOrder);
				params.put("filters", filters);
				List<CityVO> list=new ArrayList<CityVO>(); 
				if (countryViewVO.getRegionVO()==null ) 
				{
					int size=regionBO.getCityListSize(params,null);
					setRowCount(size);
					if (size > 0)
					{
						return regionBO.getCityListParam(params,null);
					}
				}		
				else 
				{
					int size=regionBO.getCityListSize(params,countryViewVO.getRegionVO());
					setRowCount(size);
					if (size > 0)
					{
						return regionBO.getCityListParam(params,countryViewVO.getRegionVO());
					}
				}
			
			} catch (Throwable t) {
				errorResult(t);
			}
			return null;
		}
		@Override
		public void setRowIndex(int rowIndex) {
			
			if (rowIndex == -1 || getPageSize() == 0) {
				super.setRowIndex(-1);
			} else super.setRowIndex(rowIndex % getPageSize());
		}
	}

	public RegionVO getRegionVO() 
	{
		return regionVO;
	}

	public void setRegionVO(RegionVO regionVO) 
	{
		this.regionVO = regionVO;
	}

	public CountryVO getCountryVO() {
		return countryVO;
	}

	public void setCountryVO(CountryVO countryVO) {
		this.countryVO = countryVO;
	}

	public CountryViewVO getCountryViewVO() {
		return countryViewVO;
	}

	public void setCountryViewVO(CountryViewVO countryViewVO) {
		this.countryViewVO = countryViewVO;
	}

	
	

	public List<RegionVO> getRegionMainList() {
		return regionMainList;
	}

	public void setRegionMainList(List<RegionVO> regionMainList) {
		this.regionMainList = regionMainList;
	}


	


	/**
	 * @return the countryMap
	 */
	public Map<String, CountryVO> getCountryMap() {
		return countryMap;
	}

	/**
	 * @param countryMap the countryMap to set
	 */
	public void setCountryMap(Map<String, CountryVO> countryMap) {
		this.countryMap = countryMap;
	}

	/**
	 * @return the uploadedFile
	 */
	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	/**
	 * @param uploadedFile the uploadedFile to set
	 */
	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	

	public List<CountryVO> getCountryList() {
		return countryList;
	}

	public void setCountryList(List<CountryVO> countryList) {
		this.countryList = countryList;
	}

	public LazyDataModel<RegionVO> getLazyRegionDataModel() {
		return lazyRegionDataModel;
	}

	public void setLazyRegionDataModel(LazyDataModel<RegionVO> lazyRegionDataModel) {
		this.lazyRegionDataModel = lazyRegionDataModel;
	}

	public LazyDataModel<CountryVO> getLazyCountryDataModel() {
		return lazyCountryDataModel;
	}

	public void setLazyCountryDataModel(
			LazyDataModel<CountryVO> lazyCountryDataModel) {
		this.lazyCountryDataModel = lazyCountryDataModel;
	}

	public CityVO getCityVO() {
		return cityVO;
	}

	public void setCityVO(CityVO cityVO) {
		this.cityVO = cityVO;
	}

	public LazyDataModel<CityVO> getLazyCityDataModel() {
		return lazyCityDataModel;
	}

	public void setLazyCityDataModel(LazyDataModel<CityVO> lazyCityDataModel) {
		this.lazyCityDataModel = lazyCityDataModel;
	}

	public List<CityVO> getCityList() {
		return cityList;
	}

	public void setCityList(List<CityVO> cityList) {
		this.cityList = cityList;
	}






}
