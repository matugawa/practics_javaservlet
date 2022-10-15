package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans_entity.Account;
import model.LoginLogic;
import model.RegisterAccountLogic;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		
		if(action.equals("toRegister")) {
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/registerAccount.jsp");
			dispatcher.forward(request, response);
		}else if(action.equals("logout")) {
			HttpSession session =request.getSession();
			session.invalidate();
			
			
			request.setAttribute("msg", "ログアウトしました<br>");
			RequestDispatcher dispatcher = request.getRequestDispatcher("");
			dispatcher.forward(request, response);
			
			
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		
		if(action.equals("login")) {
			String msg="";
			String accountId = request.getParameter("accountId");
			String stringPass = request.getParameter("password");
			
			Account account =new Account(accountId, stringPass);
			LoginLogic lL=new LoginLogic();
			
			msg=lL.preCheckParam(account);
			if(msg.length()!=0) {
				
				request.setAttribute("msg", msg);
				RequestDispatcher dispatcher = request.getRequestDispatcher("");
				dispatcher.forward(request, response);
			}
			
			msg=lL.executeLogin(account);
			if(!msg.equals("permissioned")) {
				request.setAttribute("msg", msg);
				RequestDispatcher dispatcher = request.getRequestDispatcher("");
				dispatcher.forward(request, response);
			}else {
				
				HttpSession session=request.getSession();
				session.setAttribute("permission", msg);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/top.jsp");
				dispatcher.forward(request, response);
				
			}
			
			
			
			
			
		}else if(action.equals("createAccount")) {
			String msg="";
			String accountId = request.getParameter("accountId");
			String stringPass = request.getParameter("password");
			
			Account ac=new Account(accountId, stringPass);
			RegisterAccountLogic rL=new RegisterAccountLogic();
			
			
			msg=rL.createAccountLogic(ac);
			if(msg.length()==0) {
				msg="登録しました。<br>";
			}
		
			request.setAttribute("msg", msg);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/registerResult.jsp");
			dispatcher.forward(request, response);
			
		}
	}

}
