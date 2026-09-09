package com.bcs.zsg.maintenance.service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.net.ssl.HttpsURLConnection;

import org.primefaces.context.RequestContext;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.DateUtils;
import com.bcs.zsg.maintenance.vo.CustPointVO;
import com.bcs.zsg.maintenance.vo.CustVoucherVO;
import com.bcs.zsg.web.SystemProperty;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class CustPointServiceImpl implements CustPointService{
	@Override
	public CustPointVO getCustomerLoyaltyPoint(Integer customerId) throws IOException {
		String url = SystemProperty.CUST_POINT_API_GET_CUSTOMER_LOYALTY_POINT;
		url = url.concat(String.valueOf(customerId));
		URL obj = new URL(url);
		
		if(obj.getProtocol().startsWith("https")) {
			HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
			// optional default is GET
			con.setRequestMethod("GET");
			con.setRequestProperty("Host", SystemProperty.CUST_POINT_API_REQUEST_HEADER_HOST);
			con.setRequestProperty("ApiKey", SystemProperty.CUST_POINT_REQUEST_HEADER_AUTHORIZATION);
			con.setRequestProperty("Content-Type", SystemProperty.CUST_POINT_API_REQUEST_HEADER_CONTENT_TYPE);
			con.setRequestProperty("User-Agent", "Mozilla/5.0");

			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
	 
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			GsonBuilder gsonBuilder = new GsonBuilder();  
			Gson gson = gsonBuilder.create();
			
			Map<String, Object> mapObj = gson.fromJson(response.toString(), new TypeToken<HashMap<String, Object>>() {}.getType());
			CustPointVO vo = gson.fromJson(new Gson().toJson(mapObj.get("data")), new TypeToken<CustPointVO>(){}.getType());
			
			return vo;
		}else {
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			// optional default is GET
			con.setRequestMethod("GET");
			con.setRequestProperty("Host", SystemProperty.CUST_POINT_API_REQUEST_HEADER_HOST);
			con.setRequestProperty("ApiKey", SystemProperty.CUST_POINT_REQUEST_HEADER_AUTHORIZATION);
			con.setRequestProperty("Content-Type", SystemProperty.CUST_POINT_API_REQUEST_HEADER_CONTENT_TYPE);
			con.setRequestProperty("User-Agent", "Mozilla/5.0");

			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
	 
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			GsonBuilder gsonBuilder = new GsonBuilder();  
			Gson gson = gsonBuilder.create();
			
			Map<String, Object> mapObj = gson.fromJson(response.toString(), new TypeToken<HashMap<String, Object>>() {}.getType());
			CustPointVO vo = gson.fromJson(new Gson().toJson(mapObj.get("data")), new TypeToken<CustPointVO>(){}.getType());
			
			return vo;
		}
	}
	
	@Override
	public List<CustVoucherVO> getVoucherList() throws IOException {
		String url = SystemProperty.CUST_POINT_API_GET_VOUCHER;
		
		URL obj = new URL(url);
		if(obj.getProtocol().startsWith("https")) {
			HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
			// optional default is GET
			con.setRequestMethod("GET");
			con.setRequestProperty("Host", SystemProperty.CUST_POINT_API_REQUEST_HEADER_HOST);
			con.setRequestProperty("ApiKey", SystemProperty.CUST_POINT_REQUEST_HEADER_AUTHORIZATION);
			con.setRequestProperty("Content-Type", SystemProperty.CUST_POINT_API_REQUEST_HEADER_CONTENT_TYPE);
			con.setRequestProperty("User-Agent", "Mozilla/5.0");

			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
	 
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			GsonBuilder gsonBuilder = new GsonBuilder();  
			Gson gson = gsonBuilder.create();
			
			Map<String, Object> mapObj = gson.fromJson(response.toString(), new TypeToken<HashMap<String, Object>>() {}.getType());
			List<CustVoucherVO> voList = gson.fromJson(new Gson().toJson(mapObj.get("data")), new TypeToken<List<CustVoucherVO>>(){}.getType());
			
			return voList;
		}else {
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			// optional default is GET
			con.setRequestMethod("GET");
			con.setRequestProperty("Host", SystemProperty.CUST_POINT_API_REQUEST_HEADER_HOST);
			con.setRequestProperty("ApiKey", SystemProperty.CUST_POINT_REQUEST_HEADER_AUTHORIZATION);
			con.setRequestProperty("Content-Type", SystemProperty.CUST_POINT_API_REQUEST_HEADER_CONTENT_TYPE);
			con.setRequestProperty("User-Agent", "Mozilla/5.0");

			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
	 
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			GsonBuilder gsonBuilder = new GsonBuilder();  
			Gson gson = gsonBuilder.create();
			
			Map<String, Object> mapObj = gson.fromJson(response.toString(), new TypeToken<HashMap<String, Object>>() {}.getType());
			List<CustVoucherVO> voList = gson.fromJson(new Gson().toJson(mapObj.get("data")), new TypeToken<List<CustVoucherVO>>(){}.getType());
			
			return voList;
		}	
	}
	
	@Override
	public void convertCustomerVoucher(CustPointVO custPointVO) throws BusinessException, IOException {
		String url = SystemProperty.CUST_POINT_API_CONVERT_VOUCHER;
		URL obj = new URL(url);
		if(obj.getProtocol().startsWith("https")) {
			HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
			//add reuqest header
			con.setRequestMethod("POST");
			con.setRequestProperty("Host", SystemProperty.CUST_POINT_API_REQUEST_HEADER_HOST);
			con.setRequestProperty("ApiKey", SystemProperty.CUST_POINT_REQUEST_HEADER_AUTHORIZATION);
			con.setRequestProperty("Content-Type", SystemProperty.CUST_POINT_API_REQUEST_HEADER_CONTENT_TYPE);
			con.setRequestProperty("User-Agent", "Mozilla/5.0");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			custPointVO.setTimestamp(sdf.format(DateUtils.getCurrentDate()));
			Gson gson = new Gson();
			byte[] out = gson.toJson(custPointVO).getBytes(StandardCharsets.UTF_8);
			int length = out.length;

			con.setFixedLengthStreamingMode(length);
			
			// Send post request
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.write(out);
			wr.flush();
			wr.close();

			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'POST' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);
			
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			
			//print result
			//System.out.println(response.toString());
			
			Map<String, Object> params = gson.fromJson(response.toString(), new TypeToken<HashMap<String, Object>>() {}.getType());
			
			if(params.get("successful").toString().equals("true")) {
				System.out.println("Conversion is successful.");
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Conversion Is Successful", "");
				FacesContext.getCurrentInstance().addMessage(null, msg);
				RequestContext.getCurrentInstance().addCallbackParam("isSuccess", true);
			}else {
				System.out.println("Conversion is failed.");
				System.out.println("errorCode: " + params.get("errorCode"));
				System.out.println("errorMessage: " + params.get("errorMessage"));
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion is unsuccesful.", "");
				FacesContext.getCurrentInstance().addMessage(null, msg);
				RequestContext.getCurrentInstance().addCallbackParam("isSuccess", false);
			}
		}else {
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			//add reuqest header
			con.setRequestMethod("POST");
			con.setRequestProperty("Host", SystemProperty.CUST_POINT_API_REQUEST_HEADER_HOST);
			con.setRequestProperty("ApiKey", SystemProperty.CUST_POINT_REQUEST_HEADER_AUTHORIZATION);
			con.setRequestProperty("Content-Type", SystemProperty.CUST_POINT_API_REQUEST_HEADER_CONTENT_TYPE);
			con.setRequestProperty("User-Agent", "Mozilla/5.0");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			custPointVO.setTimestamp(sdf.format(DateUtils.getCurrentDate()));
			Gson gson = new Gson();
			byte[] out = gson.toJson(custPointVO).getBytes(StandardCharsets.UTF_8);
			int length = out.length;

			con.setFixedLengthStreamingMode(length);
			
			// Send post request
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.write(out);
			wr.flush();
			wr.close();

			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'POST' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);
			
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			
			//print result
			//System.out.println(response.toString());
			
			Map<String, Object> params = gson.fromJson(response.toString(), new TypeToken<HashMap<String, Object>>() {}.getType());
			
			if(params.get("successful").toString().equals("true")) {
				System.out.println("Conversion is successful.");
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Conversion Is Successful", "");
				FacesContext.getCurrentInstance().addMessage(null, msg);
				RequestContext.getCurrentInstance().addCallbackParam("isSuccess", true);
			}else {
				System.out.println("Conversion is failed.");
				System.out.println("errorCode: " + params.get("errorCode"));
				System.out.println("errorMessage: " + params.get("errorMessage"));
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion is unsuccesful.", "");
				FacesContext.getCurrentInstance().addMessage(null, msg);
				RequestContext.getCurrentInstance().addCallbackParam("isSuccess", false);
			}
		}
	}
	
	@Override
	public void redeemCustomerVoucher(CustPointVO custPointVO) throws BusinessException, IOException {
		String url = SystemProperty.CUST_POINT_API_REDEEM_VOUCHER;
		URL obj = new URL(url);
		if(obj.getProtocol().startsWith("https")) {
			HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
			//add reuqest header
			con.setRequestMethod("POST");
			con.setRequestProperty("Host", SystemProperty.CUST_POINT_API_REQUEST_HEADER_HOST);
			con.setRequestProperty("ApiKey", SystemProperty.CUST_POINT_REQUEST_HEADER_AUTHORIZATION);
			con.setRequestProperty("Content-Type", SystemProperty.CUST_POINT_API_REQUEST_HEADER_CONTENT_TYPE);
			con.setRequestProperty("User-Agent", "Mozilla/5.0");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			custPointVO.setTimestamp(sdf.format(DateUtils.getCurrentDate()));
			Gson gson = new Gson();
			byte[] out = gson.toJson(custPointVO).getBytes(StandardCharsets.UTF_8);
			int length = out.length;

			con.setFixedLengthStreamingMode(length);
			
			// Send post request
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.write(out);
			wr.flush();
			wr.close();

			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'POST' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);
			
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			
			//print result
			//System.out.println(response.toString());
			Map<String, Object> params = gson.fromJson(response.toString(), new TypeToken<HashMap<String, Object>>() {}.getType());
			
			if(params.get("successful").toString().equals("true")) {
				System.out.println("Successfully Redemption.");
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Successful Redemption", "");
				FacesContext.getCurrentInstance().addMessage(null, msg);
				RequestContext.getCurrentInstance().addCallbackParam("isSuccess", true);
			}else {
				System.out.println("Unsuccessful Redemption.");
				System.out.println("errorCode: " + params.get("errorCode"));
				System.out.println("errorMessage: " + params.get("errorMessage"));
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Unsuccessful Redemption", "");
				FacesContext.getCurrentInstance().addMessage(null, msg);
				RequestContext.getCurrentInstance().addCallbackParam("isSuccess", false);
			}
		}else {
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			//add reuqest header
			con.setRequestMethod("POST");
			con.setRequestProperty("Host", SystemProperty.CUST_POINT_API_REQUEST_HEADER_HOST);
			con.setRequestProperty("ApiKey", SystemProperty.CUST_POINT_REQUEST_HEADER_AUTHORIZATION);
			con.setRequestProperty("Content-Type", SystemProperty.CUST_POINT_API_REQUEST_HEADER_CONTENT_TYPE);
			con.setRequestProperty("User-Agent", "Mozilla/5.0");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			custPointVO.setTimestamp(sdf.format(DateUtils.getCurrentDate()));
			Gson gson = new Gson();
			byte[] out = gson.toJson(custPointVO).getBytes(StandardCharsets.UTF_8);
			int length = out.length;

			con.setFixedLengthStreamingMode(length);
			
			// Send post request
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.write(out);
			wr.flush();
			wr.close();

			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'POST' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);
			
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			
			//print result
			//System.out.println(response.toString());
			Map<String, Object> params = gson.fromJson(response.toString(), new TypeToken<HashMap<String, Object>>() {}.getType());
			
			if(params.get("successful").toString().equals("true")) {
				System.out.println("Successfully Redemption.");
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Successful Redemption", "");
				FacesContext.getCurrentInstance().addMessage(null, msg);
				RequestContext.getCurrentInstance().addCallbackParam("isSuccess", true);
			}else {
				System.out.println("Unsuccessful Redemption.");
				System.out.println("errorCode: " + params.get("errorCode"));
				System.out.println("errorMessage: " + params.get("errorMessage"));
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Unsuccessful Redemption", "");
				FacesContext.getCurrentInstance().addMessage(null, msg);
				RequestContext.getCurrentInstance().addCallbackParam("isSuccess", false);
			}
		}
	}
}
