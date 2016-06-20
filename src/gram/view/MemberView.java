package gram.view;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gram.controller.MemberController;

/**
 * Servlet implementation class MemberView
 */
@WebServlet("/MemberView")
public class MemberView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberView() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		process(req, res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(req, res);
	}
	
	public void process(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action").toString();
		
		switch(action){
		case "loginMember" :
			MemberController.loginMember(req,res);
			break;
			
		case "joinMember" :
			MemberController.joinMember(req,res);
			break;
		
		case "logoutMember" :
			MemberController.logoutMember(req,res);
			break;
			
		case "checkMember" :
			MemberController.checkMember(req,res);
			break;
			
		case "getMember" :
			MemberController.getMember(req,res);
			break;
			
		case "loginCheck" :
			MemberController.loginCheck(req,res);
			break;
			
		}
		
		
	}

}
