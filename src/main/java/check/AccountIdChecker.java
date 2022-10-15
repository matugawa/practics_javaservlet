package check;

public class AccountIdChecker extends abChecker{
	
	private static final String errMsg="アカウントIDは１文字以上１０文字以内です<br>";
	
	public AccountIdChecker() {
		super(errMsg);
	}
	
	public boolean check(String data) {
		if(data.length()==0 ||data.length()>10) {
			return false;
		}
		return true;
	}

}
