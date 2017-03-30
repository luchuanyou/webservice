package cn.com.mobile;

public class TestDemo {

	public static void main(String[] args) {
		MobileCodeWSSoap mobile = new MobileCodeWS().getMobileCodeWSSoap();
		String mobileCode = "13761014210";
		String result = mobile.getMobileCodeInfo(mobileCode, "");
		System.out.println("调用结果："+result);
	}
}
