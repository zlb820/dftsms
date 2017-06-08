package cn.zlb.tools;

import com.google.gson.Gson;

public class JsonUtils {
	private static Gson gson = null;

    private JsonUtils() {

    }

    public static Gson getInstance() {
        if (gson == null) {
            synchronized (Gson.class) {
                if (gson == null) {
                	gson = new Gson();
                }
            }
        }
        return gson;
    }
	
	
	
}
