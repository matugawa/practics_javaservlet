package check;

public class ProductNameChecker extends abChecker{
	private static final String errMsg="product名は１文字以上５０文字以内です<br>";
	
	public ProductNameChecker() {
		super(errMsg);
	}
	
	public boolean check(String data) {
		data=data.replaceAll(" ", "");
		if(data.length()==0 ||data.length()>50) {
			return false;
		}
		return true;
	}
}
