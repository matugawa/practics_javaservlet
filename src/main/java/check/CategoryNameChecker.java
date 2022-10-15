package check;

public class CategoryNameChecker extends abChecker{


	private static final String errMsg="カテゴリNameは１文字以上５０文字以内です<br>"
			+ "また空文字の入力はできません<br>";

	public CategoryNameChecker() {
		super(errMsg);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public boolean check(String data) {
		data=data.replaceAll(" ", "");
		if(data.length()==0 ||data.length()>50) {
			return false;
		}
		return true;
	}
	
	

}
