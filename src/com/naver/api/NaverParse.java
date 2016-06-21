package com.naver.api;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class NaverParse {

	public static String getContent(Element element, String tagName) {
	
		NodeList list = element.getElementsByTagName(tagName);
		Element cElement = (Element)list.item(0);
		
		if(cElement.getFirstChild() != null) {
			return cElement.getFirstChild().getNodeValue();
		} else {
			return "";
		}
	}
	
	public ArrayList<HashMap<String, Object>> parse(String xml) {
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		ArrayList<HashMap<String, Object>> mapList = new ArrayList<HashMap<String, Object>>();
		
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(new InputSource(new ByteArrayInputStream(xml.getBytes("UTF-8"))));
			Element root = doc.getDocumentElement();
			NodeList list = root.getElementsByTagName("item");
			
			for(int i=0; i <list.getLength(); i++) {
				Element element = (Element)list.item(i);
				HashMap<String, Object> map = new HashMap<String, Object>();
				
				map.put("title", getContent(element, "title"));
				map.put("address", getContent(element, "address"));
				map.put("x", getContent(element, "mapx"));
				map.put("y", getContent(element, "mapy"));

				mapList.add(map);
			}	
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return mapList;
	}
}