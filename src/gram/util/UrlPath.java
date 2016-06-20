package gram.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class UrlPath implements ServletContextListener{
	
	public UrlPath(){}
	
	public void contextInitialized(ServletContextEvent arg0){
		arg0.getServletContext().setAttribute("path", arg0.getServletContext().getContextPath());
	}

	public void contextDestroyed(ServletContextEvent arg0){}
}
