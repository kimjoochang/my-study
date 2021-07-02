package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ListDao;
import dto.List;

/**
 * Servlet implementation class AddServlet
 */
@WebServlet("/AddServlet")
public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ListDao dao = null;
	
	public AddServlet() {
        super();
        dao = new ListDao();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List dto = new List();
		
		request.setCharacterEncoding("utf-8");
		
		String name = request.getParameter("name");
		int priority = Integer.parseInt(request.getParameter("priority"));
		String content = request.getParameter("content");
		
		dao.addList(dto);
		
		response.sendRedirect("./MainServlet");
		
		//dao.
	}

}
