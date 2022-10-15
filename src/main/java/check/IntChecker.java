package check;

public class IntChecker extends abChecker{
	
	private static final String errMsg="数字で入力してください<br>";
	
	public IntChecker() {
		super(errMsg);
	}
	
	public boolean check(String data) {
		
		try{
			Integer.parseInt(data);
		}catch(NumberFormatException e){
			return false;
		}
		return true;
	
	}
	

}
