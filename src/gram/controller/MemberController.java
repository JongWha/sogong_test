package gram.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import gram.model.MemberDAO;
import gram.model.domain.Member;

public class MemberController {
	
	private static final String className = "MemberController";
	
	public static void loginMember(HttpServletRequest req, HttpServletResponse res){
		
		
		try{

			HttpSession session = req.getSession();

			String memberId = req.getParameter("inputMemberId");
			String memberPassword = req.getParameter("inputMemberPassword");

			int check = 0;

			check = MemberDAO.checkMember(memberId);

			if(check == 1){

				Member checkMember = MemberDAO.getMember(memberId, memberPassword);

				if(checkMember == null){
					
					res.getWriter().write("Please check the Id or Password");
					return;
				}
				else{
					
					session.setAttribute("memberId", checkMember.getUser_id());
					session.setAttribute("memberPassword",checkMember.getUser_password());
					session.setAttribute("memberNum", checkMember.getUser_num());
					session.setAttribute("memberStatus", checkMember.getUser_status());
					
					res.getWriter().write("LoginOK");
					return;
				}
			} else{

				res.getWriter().write("No member Exist");
			}
		}catch(Exception e){
			e.printStackTrace();
		}


	}
	
	public static void joinMember(HttpServletRequest req, HttpServletResponse res){
		
		
		try{

			HttpSession session = req.getSession();

			String memberId = req.getParameter("inputMemberId");
			String memberPassword = req.getParameter("inputMemberPassword");
			String checkStatus = req.getParameter("inputMemberStatus");
			int memberStatus = 0;
			
			if(checkStatus == "1"){
				memberStatus = 1;
			}

			int check = 0;

			check = MemberDAO.checkMember(memberId);

			if(check == 1){

				res.getWriter().write("Exist Member");
				return;
				
			} else{
				boolean checkJoin = MemberDAO.joinMember(memberId, memberPassword, memberStatus);
				
				if(!checkJoin){
					return;
				}
				else{
					res.getWriter().write("JoinOK");
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}

	
	public static void logoutMember(HttpServletRequest req, HttpServletResponse res){
		
		
		try{
			HttpSession session = req.getSession(false);
			
			if(session == null){
				return;
			}
			
			session.invalidate();
			res.getWriter().write("LogoutOk");
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	public static void checkMember(HttpServletRequest req, HttpServletResponse res){
		
		
		String id = req.getParameter("inputMemberId");
		String password = req.getParameter("inputMemberPassword");
		
		
	}
	public static void getMember(HttpServletRequest req, HttpServletResponse res){
		
		
		String id = req.getParameter("inputMemberId");
		String password = req.getParameter("inputMemberPassword");
		
		
	}
	
	public static void loginCheck(HttpServletRequest req, HttpServletResponse res){
		
		
		String id = req.getParameter("inputMemberId");
		String password = req.getParameter("inputMemberPassword");
		
		
	}



}
