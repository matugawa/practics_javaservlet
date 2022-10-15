package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans_entity.Category;
import model.CategoryLogic;

/**
 * Servlet implementation class CategoryServlet
 */
@WebServlet("/CategoryServlet")
public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CategoryServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		HttpSession session = request.getSession();
		String perm=(String) session.getAttribute("permission");
		
		if(perm==null) {
			response.sendRedirect("");
		}*/
		

		CategoryLogic categoryLogic = new CategoryLogic();

		List<Category> categoryList = categoryLogic.collectCategoryLogic();
		request.setAttribute("categoryList", categoryList);

		List<Category> delCategoryList = categoryLogic.getDiffCategory();
		request.setAttribute("delCategoryList", delCategoryList);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/categoryMain.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		
		if (action.equals("register")) {

			String categoryId = request.getParameter("categoryId");
			String categoryName = request.getParameter("categoryName");
			Category category = new Category(categoryId, categoryName);

			CategoryLogic categoryLogic = new CategoryLogic();
			String msg = categoryLogic.createCategoryLogic(category);

			List<Category> categoryList = categoryLogic.collectCategoryLogic();
			request.setAttribute("categoryList", categoryList);

			List<Category> delCategoryList = categoryLogic.getDiffCategory();
			request.setAttribute("delCategoryList", delCategoryList);

			request.setAttribute("msg", msg);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/categoryMain.jsp");
			dispatcher.forward(request, response);
			

		} else if (action.equals("delete")) {
			String categoryId = request.getParameter("categoryId");
			Category category = new Category(categoryId, "dummy");
			CategoryLogic categoryLogic =new CategoryLogic();
			String msg=categoryLogic.deleteCategoryLogic(category);
			
			
			List<Category> categoryList = categoryLogic.collectCategoryLogic();
			request.setAttribute("categoryList", categoryList);
			List<Category> delCategoryList = categoryLogic.getDiffCategory();
			request.setAttribute("delCategoryList", delCategoryList);

			request.setAttribute("msg", msg);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/categoryMain.jsp");
			dispatcher.forward(request, response);
			

		} else if (action.equals("edit")) {
			String categoryId = request.getParameter("categoryId");
			String categoryName = request.getParameter("categoryName");
			Category category=new Category(categoryId, categoryName);
			
			CategoryLogic categoryLogic = new CategoryLogic();
			String msg=categoryLogic.updateCategoryLogic(category);
	
			List<Category> categoryList = categoryLogic.collectCategoryLogic();
			request.setAttribute("categoryList", categoryList);
			List<Category> delCategoryList = categoryLogic.getDiffCategory();
			request.setAttribute("delCategoryList", delCategoryList);
			
			request.setAttribute("msg", msg);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/categoryMain.jsp");
			dispatcher.forward(request, response);
			
		}else if(action.equals("renewal")) {
			String sort = request.getParameter("sort");
			String type = request.getParameter("type");
		

			CategoryLogic categoryLogic = new CategoryLogic();
			

			List<Category> categoryList = categoryLogic.collectSortedCategoryLogic(sort, type);
			request.setAttribute("categoryList", categoryList);

			List<Category> delCategoryList = categoryLogic.getDiffCategory();
			request.setAttribute("delCategoryList", delCategoryList);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/categoryMain.jsp");
			dispatcher.forward(request, response);
		}
	}
}
