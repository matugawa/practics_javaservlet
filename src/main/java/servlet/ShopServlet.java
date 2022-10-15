package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans_entity.Product;
import model.ProductLogic;
import model.ShopLogic;

/**
 * Servlet implementation class ShopServlet
 */
@WebServlet("/ShopServlet")
public class ShopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShopServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		ProductLogic productLogic = new ProductLogic();
		List<Product> productList = productLogic.collectProductLogic();
		request.setAttribute("productList", productList);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/shopMain.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		
		if(action.equals("toBag")) {
			String[] bag=request.getParameterValues("bag");
			
			List<Product> reLi=new ArrayList<>();
			ShopLogic sL=new ShopLogic();
			reLi =sL.collectProductByProductIdLogic(bag);
			HttpSession session=request.getSession();
			session.setAttribute("bagList", reLi);
			

			
			ProductLogic productLogic = new ProductLogic();
			List<Product> productList = productLogic.collectProductLogic();
			request.setAttribute("productList", productList);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/shopMain.jsp");
			dispatcher.forward(request, response);
			
		}else if(action.equals("toBuy")) {

			System.out.println("ssssss");
				
		}
	}

}
