package the.bhushan.log.appenders;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

import the.bhushan.redis.RedisClient;

public class RedisAppender extends AppenderSkeleton {
	RedisClient redisClient;
	private String redisPort;
	private String redisHost;
	private String redisPassword;

	@Override
	public void activateOptions() {
		super.activateOptions();
		if(redisHost != null && redisPort != null)
			redisClient = new RedisClient(redisHost, Integer.valueOf(redisPort));
		else
			redisClient = new RedisClient();
		if(redisPassword != null)
			redisClient.setPassword(redisPassword);
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
		if (event.getThrowableStrRep() != null) {
			StringBuffer strBuffer = new StringBuffer(layout.format(event));
			for (String logStep : event.getThrowableStrRep()) {
				strBuffer.append(logStep);
			}
			redisClient.appendToList(event.getLoggerName(),
					strBuffer.toString());
		} else
			redisClient.appendToList(event.getLoggerName(),
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

}
