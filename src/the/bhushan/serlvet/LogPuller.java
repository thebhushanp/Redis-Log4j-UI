package the.bhushan.serlvet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import the.bhushan.redis.RedisClient;

/**
 * Servlet implementation class LogPusher
 */
public class LogPuller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RedisClient redisClient;
    /**
     * Default constructor. 
     */
    public LogPuller() {
    	redisClient = new RedisClient();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> params = splitQueryString(request.getQueryString());
		List<String> logs = redisClient.getLogs(params.get("bucket"), Integer.valueOf(params.get("to")), Integer.valueOf(params.get("from")));
		PrintWriter out = response.getWriter();
		String json = new Gson().toJson(logs);
		out.println(json);
		response.setContentType("application/json;charset=UTF-8");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	public static Map<String, String> splitQueryString(String query) throws UnsupportedEncodingException {
	    Map<String, String> query_pairs = new LinkedHashMap<String, String>();
	    String[] pairs = query.split("&");
	    for (String pair : pairs) {
	        int idx = pair.indexOf("=");
	        query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
	    }
	    return query_pairs;
	}
}
