package com.erp.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.erp.Dao.DepartDao;
import com.erp.Dao.Stuff_DepartDao;
import com.erp.Entry.DepartEntry;
import com.erp.Entry.StuffEntry;


/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("/*")
public class LoginFilter implements Filter {
	
	private static final String TAG ="LoginFilter";
	List<String> list = null;;
    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    	list = new ArrayList<String>();
    	list.add("Login");
    	list.add("css");
    	list.add("img");
    	list.add("tmp");
    	list.add("js");
    	list.add("fonts");
    	list.add("api");
    }
    
    
    
	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String path = req.getRequestURI();
		for(String str:list){
			if(path.contains(str)){
				chain.doFilter(request, response);
				return;
			}
		}
		
		StuffEntry stuff =  (StuffEntry) req.getSession().getAttribute("stuff");
		if(stuff == null){
			((HttpServletResponse)response).sendRedirect("LoginServlet");
		}else{
			if(!stuff.isType0_1()){
				DepartEntry depart = Stuff_DepartDao.getDepartByStuffAccount(stuff.getAccount());
				req.getSession().setAttribute("depart", depart);
			}
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
