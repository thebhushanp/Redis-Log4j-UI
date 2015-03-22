package the.bhushan.log.test;

import static org.junit.Assert.assertEquals;
import redis.clients.jedis.Jedis;

public class TestRedisConnection {

	@org.junit.Test
	public void testPrintMessage() {
		// Connecting to Redis server on localhost
		Jedis jedis = new Jedis("localhost");
		// set the data in redis string
		jedis.set("James", "Bond");
		assertEquals("Bond", jedis.get("James"));
		jedis.close();
	}
}
