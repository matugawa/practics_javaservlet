package check;

public class CategoryIdChecker extends abChecker{
	


	private static final String errMsg="カテゴリIDは１文字以上１０文字以内です<br>"
			+ "また空文字の入力はできません<br>";
	public CategoryIdChecker() {
		super(errMsg);
	}
	
	@Override
	public boolean check(String data) {
		
		data=data.replaceAll(" ", "");
		if(data.length()==0 ||data.length()>10) {
			return false;
		}
		return true;
	}
	
	

}
