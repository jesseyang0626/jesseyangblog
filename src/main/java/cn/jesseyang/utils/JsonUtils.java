package cn.jesseyang.utils;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Locale;
import java.util.TimeZone;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JsonUtils {
	private static ObjectMapper mapper = null;

	static {
		mapper = new ObjectMapper();
		mapper.setLocale(Locale.getDefault());
		mapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
	}

	public static ObjectMapper getMapper() {
		if (null == mapper) {
			mapper = new ObjectMapper();
			mapper.setLocale(Locale.getDefault());
			mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		}
		return mapper;
	}

	public static String toString(Object obj) {
		try {
	//		mapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
			return mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "";
		}
	}

 
	public static <T> T toObjectBean(String jsonStr, Class<T> objClass) {
		try {
			return mapper.readValue(jsonStr, objClass);
		} catch (Exception e) {
			e.printStackTrace();
			return  null;
		}
	}


	public static JavaType getType(Class<? extends Collection> collectionClass, Class<?> elementClass) {
		return mapper.getTypeFactory().constructCollectionType(collectionClass, elementClass);
	}

	public static ObjectNode newObjectNode() {
		return mapper.createObjectNode();
	}

	public static ArrayNode newArrayNode() {
		return mapper.createArrayNode();
	}



	
}
