package lambda;

public enum GenderColumn {
	BOY("0"),
	GIRL("1"),
	LADYBOY("2");
	
	private String code;

	private GenderColumn(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
	
}
