package the.bhushan.log.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;

import the.bhushan.redis.RedisClient;

public class TestRedisConnection {

	static RedisClient client;

	@Before
	public void setup() {
		client = new RedisClient();
	}

	String message = "Robert";

	@org.junit.Test
	public void testPrintMessage() {
		client.appendToList("test", message);
		List<String> strlist = client.getLogs("test", 0, -1);
		assertEquals(message, strlist.get(0));
	}
}
