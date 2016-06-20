package gram.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import gram.model.MemberDAO;
import gram.model.TruckDAO;
import gram.model.domain.Member;

public class TruckController {

	
	private static final String className = "TruckController";
	
	public static void createTruck(HttpServletRequest req, HttpServletResponse res){
		
		
		try{
			
			HttpSession session = req.getSession();
			
			String memberId = (String) session.getAttribute("memberId");
			
			int memberNo = MemberDAO.getMemberNo(memberId);
			
			System.out.println(memberId);
			System.out.println(memberNo);
			
			
			String truckTitle = req.getParameter("inputTruckTitle") == null ? " " : req.getParameter("inputTruckTitle");
			System.out.println(truckTitle);
			System.out.println("contronller");
			String truckInfo = req.getParameter("#inputTruckInfo");
			System.out.println(truckInfo);
			
			Part filePart = req.getPart("inputTruckPic");
			
			if(filePart != null){
				System.out.println(filePart.getName());
				System.out.println(filePart.getSize());
				System.out.println(filePart.getContentType());
			}else{
				System.out.println("file access");
			}
			
			int check = TruckDAO.checkTruck(memberNo);
			
			if(check == 1){
				System.out.println("Exist Truck");
				res.getWriter().write("Exist Truck");
				return;
			}else{
				boolean entrollTruck = TruckDAO.createTruck(memberNo, truckTitle, truckInfo, filePart);
				if(!entrollTruck){
					return;
				}else {
					res.getWriter().write("create OK");
				}
			}
		
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
