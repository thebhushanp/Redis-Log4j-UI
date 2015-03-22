package the.bhushan.log.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import the.bhushan.redis.RedisClient;

public class RedisClientTest {

	@Test
	public void addToList() {
		String key = "best detective in the world";
		String value = "Sherlock Homes";
		RedisClient client = new RedisClient();
		client.appendToList(key, value);
		List<String> strlist = client.getLogs(key, 0, -1);
		client.close();
		assertEquals(value, strlist.get(0));
	}

}
