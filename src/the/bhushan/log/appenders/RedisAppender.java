package the.bhushan.log.appenders;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Layout;
import org.apache.log4j.spi.LoggingEvent;

import the.bhushan.redis.RedisClient;

public class RedisAppender extends AppenderSkeleton {
	RedisClient redisClient;
	private String redisPort;
	private String redisHost;
	private String redisPassword;
	private String key;
	public RedisAppender() {
		
	}

	public RedisAppender(String host, String port, Layout layout) {
		this.redisHost = host;
		this.redisPort = port;
		this.layout = layout;
		initRedisClient();
	}
	
	public RedisAppender(String host, String port, String password, Layout layout) {
		this.redisHost = host;
		this.redisPort = port;
		this.redisPassword = password;
		this.layout = layout;
		initRedisClient();
	}
	
	private void initRedisClient() {
		if(redisHost != null && redisPort != null)
			redisClient = new RedisClient(redisHost, Integer.valueOf(redisPort));
		else
			redisClient = new RedisClient();
		if(redisPassword != null)
			redisClient.setPassword(redisPassword);
	}

	@Override
	public void activateOptions() {
		initRedisClient();
		super.activateOptions();
	}

	@Override
	public void close() {
		// System.out.println("onClose");
	}

	@Override
	public boolean requiresLayout() {
		return true;
	}

	@Override
	protected void append(LoggingEvent event) {
		redisClient.appendToList(key,
					this.layout.format(event));
	}

	public String getRedisPort() {
		return redisPort;
	}

	public void setRedisPort(String redisPort) {
		this.redisPort = redisPort;
	}

	public String getRedisHost() {
		return redisHost;
	}

	public void setRedisHost(String redisHost) {
		this.redisHost = redisHost;
	}

	public String getRedisPassword() {
		return redisPassword;
	}

	public void setRedisPassword(String redisPassword) {
		this.redisPassword = redisPassword;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}
