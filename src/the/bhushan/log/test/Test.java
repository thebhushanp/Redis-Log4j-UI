package the.bhushan.log.test;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;


public class Test {
	@SuppressWarnings({ "deprecation", "static-access" })
	public static void main(String[] args) {
		Logger log = Logger.getLogger("bhushan");
		MDC.put("mytag","StackOverflow");

		// log.setLevel(Level.ALL);
		System.out.println(System.currentTimeMillis());
		for (int i = 0; i < 1000; i++) {
			log.debug("Hii, I m in debug");
			log.info("Hii, I m in info");
			try{
				@SuppressWarnings("unused")
				float whatever = 2/0;
			}catch(Exception e) {
				log.error("Hii, I m in error", e);
			}
			
		}
		System.out.println(System.currentTimeMillis());
		System.out.println("**DONE***");
		log.shutdown();
	}
}
