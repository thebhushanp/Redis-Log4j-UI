package the.bhushan.log.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ TestRedisConnection.class, RedisClientTest.class })
public class RunAllTest {

}
