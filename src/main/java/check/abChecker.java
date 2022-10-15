package check;

public abstract class abChecker implements IChecker{
	String errMsg;
	public abChecker(String errMsg) {
		this.errMsg=errMsg;
	}
	public String getErrMsg() {
		return errMsg;
	}
}
