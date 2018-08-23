package base64;

import java.util.Base64;
import java.util.Base64.Decoder;

public class Test {

	public static void main(String[] args) {
		String temp = "5LiK5Lyg5paH5Lu2KyAtLmRvYw==";
		Decoder decoder = Base64.getDecoder();
		byte[] decode = decoder.decode(temp);
		System.out.println(new String(decode));
	}
}
