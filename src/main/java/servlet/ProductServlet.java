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
import beans_entity.Product;
import model.CategoryLogic;
import model.ProductLogic;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		CategoryLogic categoryLogic = new CategoryLogic();
		List<Category> categoryList = categoryLogic.collectCategoryLogic();
		request.setAttribute("categoryList", categoryList);

		ProductLogic productLogic = new ProductLogic();
		List<Product> productList = productLogic.collectProductLogic();
		request.setAttribute("productList", productList);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/productMain.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		
		if (action.equals("register")) {
			String pId=request.getParameter("productId");
			String cId=request.getParameter("categoryId");
			String pName=request.getParameter("productName");
			String stPrice=request.getParameter("price");
	
			Product pr=new Product(pId, cId, pName, stPrice);
			
			ProductLogic pL=new ProductLogic();
			String msg=pL.createProductLogic(pr);
			
			CategoryLogic categoryLogic = new CategoryLogic();
			List<Category> categoryList = categoryLogic.collectCategoryLogic();
			request.setAttribute("categoryList", categoryList);

			ProductLogic productLogic = new ProductLogic();
			List<Product> productList = productLogic.collectProductLogic();
			request.setAttribute("productList", productList);
			request.setAttribute("msg",  msg);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/productMain.jsp");
			dispatcher.forward(request, response);
		
		}else if(action.equals("delete")) {
			String pId=request.getParameter("productId");
			Product pr=new Product(pId, "dummy", "dummy", 0);
			ProductLogic pL=new ProductLogic();
			String msg=pL.deleteProductLogic(pr);
			
			CategoryLogic categoryLogic = new CategoryLogic();
			List<Category> categoryList = categoryLogic.collectCategoryLogic();
			request.setAttribute("categoryList", categoryList);

			ProductLogic productLogic = new ProductLogic();
			List<Product> productList = productLogic.collectProductLogic();
			request.setAttribute("productList", productList);
			request.setAttribute("msg",  msg);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/productMain.jsp");
			dispatcher.forward(request, response);
		}else if(action.equals("edit")) {
			String pId=request.getParameter("productId");
			String cId=request.getParameter("categoryId");
			String pName=request.getParameter("productName");
			String stPrice=request.getParameter("price");
			Product pr=new Product(pId, cId, pName, stPrice);
			
			ProductLogic pL=new ProductLogic();
			String msg=pL.updateProductLogic(pr);
			CategoryLogic categoryLogic = new CategoryLogic();
			List<Category> categoryList = categoryLogic.collectCategoryLogic();
			request.setAttribute("categoryList", categoryList);

			ProductLogic productLogic = new ProductLogic();
			List<Product> productList = productLogic.collectProductLogic();
			request.setAttribute("productList", productList);
			request.setAttribute("msg",  msg);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/productMain.jsp");
			dispatcher.forward(request, response);
			
		}else if(action.equals("renewal")) {
			String sort = request.getParameter("sort");
			String type = request.getParameter("type");
		

			ProductLogic pL = new ProductLogic();
			List<Product> productList = pL.collectSortedProductLogic(sort, type);
			request.setAttribute("productList", productList);
			
			CategoryLogic categoryLogic = new CategoryLogic();
			List<Category> categoryList = categoryLogic.collectCategoryLogic();
			request.setAttribute("categoryList", categoryList);


			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/productMain.jsp");
			dispatcher.forward(request, response);
			
			


		
		}
	}

}
