package the.bhushan.redis;

import java.util.List;

import redis.clients.jedis.Jedis;

public class RedisClient {
	private String host = "localhost";
	private int port = 6379;
	private Jedis jedis;

	public RedisClient() {
		jedis = new Jedis(host, port);
	}

	public RedisClient(String host, int port) {
		this.host = host;
		this.port = port;
		jedis = new Jedis(host, port);
	}

	/**
	 * This method returns list of logs.
	 * 
	 * @param bucket
	 * @param indexFrom
	 * @param indexTo
	 * @return
	 */
	public List<String> getLogs(String bucket, int indexFrom, int indexTo) {
		return jedis.lrange(bucket, indexFrom, indexTo);
	}

	/**
	 * This method writes logs to Redis server.
	 * 
	 * @param bucket
	 * @param msg
	 */
	public void appendToList(String bucket, String msg) {
		jedis.rpush(bucket, msg);
	}

	/**
	 * @param bucket
	 * @param listMsg
	 */
	public void bulkAppendToList(String bucket, List<String> listMsg) {
		// jedis.lpush()
	}

	public void setPassword(String redisPassword) {
		if (jedis != null)
			jedis.auth(redisPassword);
	}
}
