package check;

public class ProductIdChecker extends abChecker{
	private static final String errMsg="プロダクトIDは１文字以上１０文字以内です<br>";
	
	public ProductIdChecker() {
		super(errMsg);
	}
	
	public boolean check(String data) {
		data=data.replaceAll(" ", "");
		if(data.length()==0 ||data.length()>10) {
			return false;
		}
		return true;
	}
	
}
