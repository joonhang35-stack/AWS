package com.bcs.zsg.web.servlet;
import java.lang.reflect.Field;
import java.util.List;

public class WebServicePretender {

	public WebServicePretender()
	{
		
	}
	
	public String convertToXML(List<?> listTempObjClass)
	{ 
		try{ 
			String returnXML = "<ServletResponse xmlns=\"http://\">";
			for(int x=0; x<listTempObjClass.size();x++)
			{ 
				returnXML += "<return>";
		        Class cls = listTempObjClass.get(x).getClass();
		        String className = cls.getName();
		        Field fieldlist[] = cls.getDeclaredFields();
		
		        for(int i = 0; i < fieldlist.length; i++) {
 
		        	Field field = cls.getField(fieldlist[i].getName());
		        	Object tempValue = field.get(listTempObjClass.get(x)); 
					returnXML += "<" + fieldlist[i].getName() +">";
					if(tempValue == null)
						tempValue = "";

					String value = tempValue.toString();
					//replace all special charaters that is not allowed in xml 
					value = value.replaceAll("&", "&amp;");
					value = value.replaceAll("\"", "&quot;");
					value = value.replaceAll("\'", "&apos;");
					value = value.replaceAll("<", "&lt;");
					value = value.replaceAll(">", "&gt;");
					
					returnXML += value.toString();
					returnXML += "</" + fieldlist[i].getName() +">";
		        }

				returnXML += "</return>";
				
			}
 
			returnXML +="</ServletResponse>";
			return returnXML;
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			
			return "";
	}
	 
	 
	
}
