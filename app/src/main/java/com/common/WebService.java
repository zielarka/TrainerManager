package com.common;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import com.trainmanager.MyApplication;

public class WebService
{
	private static final  String URL = "http://opticounter.pl/1511199303031990/Service.asmx";
	private static String NAMESPACE = "http://opticounter.pl/";
	private static String METHOD_DownloadPlanIdPhone="DownloadPlanIdPhone";
	private static String METHOD_DownloadPlanPIN="DownloadPlanPIN";
	private static String METHOD_SharePlan="SharePlan";
	
	private String writer;
	
	public static String DownloadPlanIdPhone(String idPhone,String pin )
	{
		String Error="";
		SoapObject request = new SoapObject(NAMESPACE,METHOD_DownloadPlanIdPhone);	
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER12);
		
		PropertyInfo applicationAuthenticationIdProperty = new PropertyInfo();
		applicationAuthenticationIdProperty.setNamespace(NAMESPACE);
		applicationAuthenticationIdProperty.setType(PropertyInfo.STRING_CLASS);
		applicationAuthenticationIdProperty.setName("idPhone");
		applicationAuthenticationIdProperty.setValue(idPhone);
		request.addProperty(applicationAuthenticationIdProperty);
		
		PropertyInfo applicationAuthenticationIdProperty1 = new PropertyInfo();
		applicationAuthenticationIdProperty1.setNamespace(NAMESPACE);
		applicationAuthenticationIdProperty1.setType(PropertyInfo.STRING_CLASS);
		applicationAuthenticationIdProperty1.setName("pin");
		applicationAuthenticationIdProperty1.setValue(pin);
		request.addProperty(applicationAuthenticationIdProperty1);
		
		envelope.setOutputSoapObject(request);
		envelope.dotNet = true;

		String result = "";
		try
		{
			HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
			androidHttpTransport.call(NAMESPACE+METHOD_DownloadPlanIdPhone, envelope);
			SoapObject response = (SoapObject) envelope.bodyIn;
			if(response != null)
			{
				if(response.getPropertyCount()>0)
				{
					result = (response.getProperty(0).toString());
				}
			}
		}
		catch (Exception e)
		{
			Error=e.getMessage();
		}
		
		try 
		{
			result=URLDecoder.decode(result, "UTF-8");
		} catch (UnsupportedEncodingException e) 
		{
			e.printStackTrace();
		}
		return result;
	}

	public static String DownloadPlanPIN(String pin )
	{
		String Error="";
		SoapObject request = new SoapObject(NAMESPACE,METHOD_DownloadPlanPIN);	
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER12);
		
		PropertyInfo applicationAuthenticationIdProperty = new PropertyInfo();
		applicationAuthenticationIdProperty.setNamespace(NAMESPACE);
		applicationAuthenticationIdProperty.setType(PropertyInfo.STRING_CLASS);
		applicationAuthenticationIdProperty.setName("pin");
		applicationAuthenticationIdProperty.setValue(pin);
		request.addProperty(applicationAuthenticationIdProperty);
		
		envelope.setOutputSoapObject(request);
		envelope.dotNet = true;

		String result = "";
		try
		{
			HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
			androidHttpTransport.call(NAMESPACE+METHOD_DownloadPlanPIN, envelope);
			SoapObject response = (SoapObject) envelope.bodyIn;
			if(response != null)
			{
				if(response.getPropertyCount()>0)
				{
					result = (response.getProperty(0).toString());
				}
			}
		}
		catch (Exception e)
		{
			Error=e.getMessage();
		}
		try
		{
			result=URLDecoder.decode(result, "UTF-8");
		} catch (UnsupportedEncodingException e) 
		{
			e.printStackTrace();
		}
		return result;
	}
	
	public static String SharePlan(String idPhone,String file )
	{
		String Error="";
		file=URLEncoder.encode(file);
		SoapObject request = new SoapObject(NAMESPACE,METHOD_SharePlan);	
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		
		PropertyInfo applicationAuthenticationIdProperty = new PropertyInfo();
		applicationAuthenticationIdProperty.setNamespace(NAMESPACE);
		applicationAuthenticationIdProperty.setType(PropertyInfo.STRING_CLASS);
		applicationAuthenticationIdProperty.setName("idPhone");
		applicationAuthenticationIdProperty.setValue(idPhone);
		request.addProperty(applicationAuthenticationIdProperty);
		
		PropertyInfo applicationAuthenticationIdProperty1 = new PropertyInfo();
		applicationAuthenticationIdProperty1.setNamespace(NAMESPACE);
		applicationAuthenticationIdProperty1.setType(PropertyInfo.STRING_CLASS);
		applicationAuthenticationIdProperty1.setName("file");
		applicationAuthenticationIdProperty1.setValue(file);
		request.addProperty(applicationAuthenticationIdProperty1);
		
		envelope.setOutputSoapObject(request);
		envelope.dotNet = true;

		String result = "";
		try
		{
			HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
			androidHttpTransport.call(NAMESPACE+METHOD_SharePlan, envelope);
			SoapObject response = (SoapObject) envelope.bodyIn;
			if(response != null)
			{
				if(response.getPropertyCount()>0)
				{
					result = (response.getProperty(0).toString());
				}
			}
		}
		catch (Exception e)
		{
			Error=e.getMessage();
		}
		return result;
	}
}
