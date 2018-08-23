package candy;

public class SyntacticSugar {

	public static void main(String[] args) {
		print("000","001","002","003");
		System.out.println(ifThenElse(true, "aaa", "bbb"));
	}
	
	public static void print(String... strs){
		for (int i = 0; i < strs.length; i++) {
			System.out.println(strs[i]);
		}
	}
	
	private static <T> T ifThenElse(boolean b, T first, T second){
		return b?first:second;
	}
}
