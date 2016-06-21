package gram.view;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;



import gram.controller.TruckController;
import gram.model.MemberDAO;

/**
 * Servlet implementation class TruckView
 */
@WebServlet("/TruckView")
public class TruckView extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TruckView() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		control(req, res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		control(req, res);
	}
	
	public void control(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
	
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action").toString();
		
		switch(action){
		case "createTruck" :
			System.out.println("11");
			String inputTitle = req.getParameter("inputTruckLatitude");
			System.out.println(inputTitle);
			String inputTitle2 = req.getParameter("inputTruckLongitude");
			System.out.println(inputTitle2);

			TruckController.createTruck(req, res);
			break;
			
		case "getTruck" :
			TruckController.getTruck(req,res);
			break;
			
		case "getTruckLocation" :
			TruckController.getTruckLocation(req,res);
			break;
		
		}
		
		
		
	}

}
