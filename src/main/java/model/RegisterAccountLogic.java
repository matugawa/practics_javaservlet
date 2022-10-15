package model;

import beans_entity.Account;
import check.AccountIdChecker;
import check.IChecker;
import check.IntChecker;
import dao.AccountDAO;

public class RegisterAccountLogic {

	public String createAccountLogic(Account account) {
		String msg="";
		
		String id=account.getAccountId();
		IChecker chk=new AccountIdChecker();
		if(!chk.check(id)){
			msg=chk.getErrMsg();
		}
		
		String pass=account.getStringPass();
		chk=new IntChecker();
		if(!chk.check(pass)) {
			msg+=chk.getErrMsg();
		}
		
		if(msg.length()!=0) {
			return msg;
		}
	
		account.setIntPass(Integer.parseInt(pass));
		
		AccountDAO dao=new AccountDAO();
		
		if(dao.isExistAccountId(account)) {
			return msg="既存アカウントIDです<br>";
		}
		
		if(!dao.createAccount(account)) {
			msg="登録に失敗しました<br>";
		}
		
		return msg;		
		
	}
}
