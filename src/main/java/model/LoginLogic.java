package model;

import beans_entity.Account;
import check.AccountIdChecker;
import check.IChecker;
import check.IntChecker;
import dao.AccountDAO;

public class LoginLogic {
	
	public String preCheckParam(Account account) {
		String msg="";
		
		String accountId=account.getAccountId();
		IChecker chc =new AccountIdChecker();
		if(!chc.check(accountId)) {
			msg=chc.getErrMsg();
		}
		
		String accountPass=account.getStringPass();
		chc =new IntChecker();
		if(!chc.check(accountPass)) {
			msg+=chc.getErrMsg();
		}	
		return msg;
	}
	
	public String executeLogin(Account account) {
		String msg="アカウントIdまたはパスが間違っています<br>";
		account.setIntPass( Integer.parseInt(account.getStringPass()));
		AccountDAO da=new AccountDAO();
		if(da.loginByAccount(account)) {
			msg ="permissioned";
		}
		return msg;
	}
}
