package zzzzzz;

import org.apache.commons.lang.StringUtils;

public class Test {
	public static void main(String[] args) {
//		CommonAppResponseBody<Object> appReBody = new CommonAppResponseBody<Object>();
//		appReBody.setMsg("!!系统异常!!");
//		if(appReBody.getMsg().contains("系统异常")){
//			appReBody.setMsg("系统异常，请刷新重试");
//		}
//		System.out.println(appReBody.getMsg());
		
		
		//银行账号解密
		if(StringUtils.isNotEmpty(",")){
			//拆分银行名称和银行编码
			String[] nameAndCode = "".split(",");
			String[] nameAndCode1 = ",".split(",");
			String[] nameAndCode2 = ",,,".split(",");
//			productReserveParams.setBank_name(nameAndCode[0]);
//			if(nameAndCode.length>1){
//				productReserveParams.setBank_code(nameAndCode[1]);
//			}
			System.out.println(nameAndCode.length);
			System.out.println(nameAndCode1.length);
			System.out.println(nameAndCode2.length);
		}
	}
}
