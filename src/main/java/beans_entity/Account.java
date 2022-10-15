package beans_entity;

public class Account {
	String accountId;
	String stringPass;
	int intPass;
	
	public Account() {}
	public Account(String id, String pass) {
		this.accountId=id;
		this.stringPass=pass;
	}
	public Account(String id, int pass) {
		this.accountId=id;
		this.intPass =pass;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getStringPass() {
		return stringPass;
	}
	public void setStringPass(String stringPass) {
		this.stringPass = stringPass;
	}
	public int getIntPass() {
		return intPass;
	}
	public void setIntPass(int intPass) {
		this.intPass = intPass;
	}
	
	
	
	


	
	
}
