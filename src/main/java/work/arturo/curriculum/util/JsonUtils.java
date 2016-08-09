package work.arturo.curriculum.util;

import org.bson.Document;

import com.google.gson.Gson;

public class JsonUtils {

	public static <T> T fromJson(Document document, Class<T> type) {
		Gson gson = new Gson();
		return gson.fromJson(document.toJson(), type);
	}
}
