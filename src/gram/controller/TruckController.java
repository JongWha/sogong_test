package gram.controller;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.naver.api.NaverOpenAPI;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FilePart;
import com.oreilly.servlet.multipart.MultipartParser;
import com.oreilly.servlet.multipart.ParamPart;
import com.oreilly.servlet.multipart.Part;

import gram.model.MemberDAO;
import gram.model.TruckDAO;
import gram.model.domain.Member;
import gram.model.domain.Truck;

public class TruckController {

	
	private static final String className = "TruckController";
	
	public static void createTruck(HttpServletRequest req, HttpServletResponse res){
		
		Part part = null;
		
		int maxSize = 10 * 1024 * 1024;
		
		try{
			
			HttpSession session = req.getSession();
			
			MultipartParser multi = new MultipartParser(req, maxSize);
			multi.setEncoding("UTF-8");
			
			String memberId = (String) session.getAttribute("memberId");
			
			String truckTitle = "";
			String truckInfo = "";
			
			String filePath = "";
			String falseFilePath = "";
			
			String truckLong = "";
			String truckLati = "";
			
			int memberNo = MemberDAO.getMemberNo(memberId);
			
			System.out.println(memberId);
			System.out.println(memberNo);
			
			while((part = (Part) multi.readNextPart()) != null){
				
				String paramName = part.getName();
				
				if(part.isParam()) {
					String paramValue = "";
					ParamPart paramPart = (ParamPart) part;
					paramValue = paramPart.getStringValue();
					
					if(paramName.equals("inputTruckTitle")){
						truckTitle = (String) paramValue;
					}else if(paramName.equals("inputTruckInfo")){
						truckInfo = (String) paramValue;
					}else if(paramName.equals("inputTruckLongitude")){
						truckLong = (String) paramValue;
					}else if(paramName.equals("inputTruckLatitude")){
						truckLati = (String) paramValue;
					}
				}else if(part.isFile()){
					FilePart filePart = (FilePart)part;
					String fileName = filePart.getFileName();
					
					filePart.setRenamePolicy(new DefaultFileRenamePolicy());
					
					if((fileName != null)&&(paramName.equals("inputTruckPic"))){
						filePath = req.getRealPath("") + "temp" + File.separator + fileName;
						falseFilePath = "localhost:8080"+ File.separator +"temp" +File.separator + fileName;
						File file = new File(filePath);
						long fileSize = filePart.writeTo(file);
					}
				}
				
			}
			
			//File filePart = multi.getFile("inputTruckPic");
			
			//String originalFileName = multi.getOriginalFileName("inputTruckPic");
			
			//String fileName = multi.getFilesystemName("inputTruckPic");
			
			
			//String truckTitle = multi.getParameter("inputTruckTitle") == null ? " " : multi.getParameter("inputTruckTitle");
			//System.out.println(truckTitle);
			//System.out.println("contronller");
			//String truckInfo = multi.getParameter("inputTruckInfo") == null ? " " : multi.getParameter("inputTruckInfo");
			//System.out.println(truckInfo);
			
			
			if(filePath != null){
				
			}else{
				System.out.println("file access");
			}
			
			int check = TruckDAO.checkTruck(memberNo);
			
			if(check == 1){
				System.out.println("Exist Truck");
				res.getWriter().write("Exist Truck");
				return;
			}else{
				boolean entrollTruck = TruckDAO.createTruck(memberNo, truckTitle, truckInfo, falseFilePath, truckLati, truckLong);
				if(!entrollTruck){
					return;
				}else {
					res.getWriter().write("create OK");
					res.sendRedirect("main.jsp");
				}
			}
		
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void getTruck(HttpServletRequest req, HttpServletResponse res){
		
		try{
			
			HttpSession session = req.getSession();

			String memberId = (String) session.getAttribute("memberId");

			int memberNo = MemberDAO.getMemberNo(memberId);
			
			int check = TruckDAO.checkTruck(memberNo);

			if(check == 1) {
				Truck userTruck = TruckDAO.getTruck(memberNo);
				
				req.setAttribute("truckNo", userTruck.getTruck_num());
				req.setAttribute("truckTitle", userTruck.getTruck_name());
				req.setAttribute("truckInfo", userTruck.getTruck_info());
				req.setAttribute("truckPic", userTruck.getTruck_pic_name());
				System.out.println(userTruck.getTruck_pic_name());
			}
			
			req.getRequestDispatcher("/admin.jsp").forward(req, res);
			return;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	public static void getTruckLocation(HttpServletRequest req, HttpServletResponse res) {
		// TODO Auto-generated method stub

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("USER-IP", req.getRemoteAddr());
		
		try {
			String inputKeyword = req.getParameter("inputKeyword") != null ? req.getParameter("inputKeyword").toString() : "";
			
			// 파라미터 체크
			ArrayList<Object> parameterList = new ArrayList<Object>();
			parameterList.add(inputKeyword);	
			
			
			// 네이버 API를 이용하여 위치 정보 가져오기
			ArrayList<HashMap<String, Object>> mapList = new ArrayList<HashMap<String, Object>>();
			JSONObject jObject = new JSONObject();
			JSONArray jArray = new JSONArray();
			mapList = NaverOpenAPI.getLocation(inputKeyword);
				
			for(int i=0; i<mapList.size(); i++) {
				JSONObject jTempObject = new JSONObject();
				jTempObject.put("outputTitle", mapList.get(i).get("title"));
				jTempObject.put("outputAddress", mapList.get(i).get("address"));
				jTempObject.put("outputX", mapList.get(i).get("x"));
				jTempObject.put("outputY", mapList.get(i).get("y"));
				jArray.add(jTempObject);
			}
				
			jObject.put("outputTruckLocation", jArray);
				
			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
	
			res.getWriter().write(jObject.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	public static void checkTruck(HttpServletRequest req, HttpServletResponse res) {
//		// TODO Auto-generated method stub
//		
//		try{
//			
//			HttpSession session = req.getSession();
//			
//			String memberId = req.getParameter("inputMemberID");
//			
//			System.out.println(memberId);
//			
//			int checkUser = -1;
//			int memberNo = 0;
//			int check = 0;
//			
//			
//			checkUser = MemberDAO.getMemberNo(memberId);
//			
//			if(checkUser > -1){
//				memberNo = checkUser;
//			}
//			
//			check = TruckDAO.checkTruck(memberNo);
//			
//			if(check == 1){
//				res.getWriter().write("Exist Truck");
//				return;
//				
//			}else{
//				res.getWriter().write("No Exist Truck");
//				return;
//			}
//			
//			
//			
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		
//		
//	}
}
