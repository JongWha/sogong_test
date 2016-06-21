package com.naver.api;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.catalina.connector.ClientAbortException;

import gram.config.GlobalValue;


public class NaverOpenAPI {

	public static ArrayList<HashMap<String, Object>> getLocation (String location) {

		ArrayList<HashMap<String, Object>> mapList = new ArrayList<HashMap<String, Object>>();
		int len = -1;
		
		
		try {
			String apiUrl = "https://openapi.naver.com/v1/search/local.xml";
			URL url = new URL(apiUrl + "?query=" + URLEncoder.encode(location, "UTF-8") + "&display=20&start=1&target=local&sort=random");

			URLConnection con = url.openConnection();
			con.setRequestProperty("X-Naver-Client-Id", GlobalValue.selectNaverClientId());
			con.setRequestProperty("X-Naver-Client-Secret", GlobalValue.selectNaverClientSecret());
			
			InputStream is = new DataInputStream(con.getInputStream());
			
			StringBuffer sb = new StringBuffer();
			byte[] buf = new byte[2048];
			
			while((len = is.read(buf, 0, buf.length)) != -1) {
				sb.append(new String(buf, 0, len));				
			}
			
			NaverParse naverAPI = new NaverParse();
			mapList = naverAPI.parse(sb.toString());
						
		} catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientAbortException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return mapList;
	}
}