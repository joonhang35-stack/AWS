package com.bcs.zsg.web.object;
import java.util.Date;


public class objGroupPackage {
	public int ID;
	public Date depDate;
	public String description;
	public String airline;
	public String adultTwin;
	public String adultSingle;

	public String miscTaxes;
	public String miscChd;
	public String itenaryEng;
	public String itenaryCn;
	public String status;
	public String pkgName; 
	// fields below for page group_package_list.asp
	public String year;
	public String numDays;
	public String numNights;
	public String country;
	public String seasonCode;
	public String part;
	public String isThemeTour;
	public String nameEn;  
	
	//fields below for page golf/default.asp
	public String ctw;
	public String cwb;
	public String cnb;
	
	
	//fields below for page moreinfo/deafault.asp
	public String fullRemarks;
	public String grdCtw;
	public String grdCwb;
	public String grdCnb;
	public String grdTwin;
	public String grdSingle;
	public String grdRemarks;
	public String itenaryAgtEng;
	public String itenaryAgtCn;
	public String highlight;
	
	//fields below for page agent/home
	public String isMuslim;
	public String deposit;
	public String bagDeduction;
	public String discount;
	public String cnaAdt;
	public String cpaAdt;
	public String csiAdt;
	
	public objGroupPackage()
	{
		
	} 
	public void setNameEn(String tempStr)
	{
		nameEn = tempStr;
	}
	
	public String getNameEn()
	{
		return nameEn;
	} 
	public void setHighlight(String tempStr)
	{
		highlight = tempStr;
	}
	
	public String getHighlight()
	{
		return highlight;
	} 
	public void setCsiAdt(String tempStr)
	{
		csiAdt = tempStr;
	}
	
	public String getCsiAdt()
	{
		return csiAdt;
	}
	public void setCpaAdt(String tempStr)
	{
		cpaAdt = tempStr;
	}
	
	public String getCpaAdt()
	{
		return cpaAdt;
	}
	public void setCnaAdt(String tempStr)
	{
		cnaAdt = tempStr;
	}
	
	public String getCnaAdt()
	{
		return cnaAdt;
	}
	public void setDiscount(String tempStr)
	{
		discount = tempStr;
	}
	
	public String getDiscount()
	{
		return discount;
	}
	public void setBagDeduction(String tempStr)
	{
		bagDeduction = tempStr;
	}
	
	public String getBagDeduction()
	{
		return bagDeduction;
	}
	public void setDeposit(String tempStr)
	{
		deposit = tempStr;
	}
	
	public String getDeposit()
	{
		return deposit;
	}

	public void setIsMuslim(String tempStr)
	{
		isMuslim = tempStr;
	}
	
	public String getIsMuslim()
	{
		return isMuslim;
	}

	public void setGrdRemarks(String tempStr)
	{
		grdRemarks = tempStr;
	}
	
	public String getGrdRemarks()
	{
		return grdRemarks;
	}

	public void setGrdTwin(String tempStr)
	{
		grdTwin = tempStr;
	}
	
	public String getGrdTwin()
	{
		return grdTwin;
	}

	public void setGrdSingle(String tempStr)
	{
		grdSingle = tempStr;
	}
	
	public String getGrdSingle()
	{
		return grdSingle;
	}
	public void setGrdCnb(String tempStr)
	{
		grdCnb = tempStr;
	}
	
	public String getGrdCnb()
	{
		return grdCnb;
	}
	

	public void setGrdCwb(String tempStr)
	{
		grdCwb = tempStr;
	}
	
	public String getGrdCwb()
	{
		return grdCwb;
	}
	
	
	public void setGrdCtw(String tempStr)
	{
		grdCtw = tempStr;
	}
	
	public String getGrdCtw()
	{
		return grdCtw;
	}
	
	
	public void setFullRemarks(String tempStr)
	{
		fullRemarks = tempStr;
	}
	
	public String getFullRemarks()
	{
		return fullRemarks;
	}


	public void setMiscChd(String tempStr)
	{
		miscChd = tempStr;
	}
	
	public String getMiscChd()
	{
		return miscChd;
	}

	public void setCtw(String tempStr)
	{
		ctw = tempStr;
	}
	
	public String getCtw()
	{
		return ctw;
	}

	public void setCwb(String tempStr)
	{
		cwb = tempStr;
	}
	
	public String getCwb()
	{
		return cwb;
	}

	public void setCnb(String tempStr)
	{
		cnb = tempStr;
	}
	
	public String getCnb()
	{
		return cnb;
	}



	public void setIsThemeTour(String tempStr)
	{
		isThemeTour = tempStr;
	}
	
	public String getIsThemeTour()
	{
		return isThemeTour;
	}

	public void setPart(String tempStr)
	{
		part = tempStr;
	}
	
	public String getPart()
	{
		return part;
	}
	public void setSeasonCode(String tempStr)
	{
		seasonCode = tempStr;
	}
	
	public String getSeasonCode()
	{
		return seasonCode;
	}

	
	public void setPkgName(String tempStr)
	{
		pkgName = tempStr;
	}
	
	public String getPkgName()
	{
		return pkgName;
	}

	
	public void setID(int tempStr)
	{
		ID = tempStr;
	}
	
	public int getID()
	{
		return ID;
	}
	public void setDepDate(Date tempStr)
	{
		depDate = tempStr;
	}
	
	public Date getDepDate()
	{
		return depDate;
	}

	public void setDescription(String tempStr)
	{
		description = tempStr;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public void setAirline(String tempStr)
	{
		airline = tempStr;
	}
	
	public String getAirline()
	{
		return airline;
	}

	public void setAdultTwin(String tempStr)
	{
		adultTwin = tempStr;
	}
	
	public String getAdultTwin()
	{
		return adultTwin;
	}
	
	
	public void setAdultSingle(String tempStr)
	{
		adultSingle = tempStr;
	}
	
	public String getAdultSingle()
	{
		return adultSingle;
	}
	
	public void setMiscTaxes(String tempStr)
	{
		miscTaxes = tempStr;
	}
	
	public String getMiscTaxes()
	{
		return miscTaxes;
	}
	

	public void setItenaryEng(String tempStr)
	{
		itenaryEng = tempStr;
	}
	
	public String getItenaryEng()
	{
		return itenaryEng;
	}
	

	public void setItenaryCn(String tempStr)
	{
		itenaryCn = tempStr;
	}
	
	public String getItenaryCn()
	{
		return itenaryCn;
	}
	


	public void setStatus(String tempStr)
	{
		status = tempStr;
	}
	
	public String getStatus()
	{
		return status;
	}


	public void setYear(String tempStr)
	{
		year = tempStr;	
	}
	
	public String getYear()
	{
		return year;
	}

	public void setNumDays(String tempStr)
	{
		numDays = tempStr;	
	}
	
	public String getNumDays()
	{
		return numDays;
	}

	public void setNumNights(String tempStr)
	{
		numNights = tempStr;	
	}
	
	public String getNumNights()
	{
		return numNights;
	}
	
	public void setCountry(String tempStr)
	{
		country = tempStr;	
	}
	
	public String getCountry()
	{
		return country;
	} 

	public void setItenaryAgtEng(String tempStr)
	{
		itenaryAgtEng = tempStr;
	}
	
	public String getItenaryAgtEng()
	{
		return itenaryAgtEng;
	}
	

	public void setItenaryAgtCn(String tempStr)
	{
		itenaryAgtCn = tempStr;
	}
	
	public String getItenaryAgtCn()
	{
		return itenaryAgtCn;
	}
	
}
