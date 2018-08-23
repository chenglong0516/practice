package utils;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import entitys.MyBean;
import entitys.Student;

/**
 * json对象转bean测试类
 * @author chenglong
 *
 */
public class convertJsonUtils {
	private static Logger logger = LoggerFactory.getLogger(convertJsonUtils.class);
	
	private static final String JSONSTR1 = "{\"name\":\"JSON\",\"address\":\"北京市西城区\",\"age\":25}";
	private static final String JSONSTR2 = "{\"name\":\"JSON\",\"address\":\"北京市西城区\",\"age\":25,\"gender\":\"male\"}";
	private static final String JSONSTR3 = "{\"name\":\"JSON\",\"address\":\"北京市西城区\"}";
	
	private static final String JSONSTR4 = "{\"first\":{\"address\":\"中国上海\",\"age\":\"23\",\"name\":\"JSON\"}}";
	private static final String JSONSTR5 = "{\"first\":{\"address\":\"中国上海\",\"age\":\"23\",\"name\":\"JSON\",\"gender\":\"male\"}}";
	private static final String JSONSTR6 = "{\"first\":{\"address\":\"中国上海\",\"age\":\"23\"}}";
	
	public static void main(String[] args) {
		test1();
	}

	//简单结构json转bean
	private static void test1() {
		Student stu = jsonToBean(JSONSTR2, Student.class);
		logger.debug("stu:{}", stu.toString());
	}
	
	//复杂结构json转bean
	private static void test2() {
		JSONObject jsonObject = JSONObject.fromObject(JSONSTR5);
		Map map = new HashMap();
		map.put("first", Student.class);
		MyBean myBean = (MyBean)jsonObject.toBean(jsonObject, MyBean.class, map);
		System.out.println(myBean.getFirst());
	}
	
	public static <T> T jsonToBean(String jsonStr, Class<T> clazz){
		JSONObject jsonObject = JSONObject.fromObject(jsonStr);
		return (T)jsonObject.toBean(jsonObject, clazz);
	}
}
