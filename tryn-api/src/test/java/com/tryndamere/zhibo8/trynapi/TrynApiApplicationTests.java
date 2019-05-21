package com.tryndamere.zhibo8.trynapi;

import com.tryndamere.zhibo8.redis.repostory.RedisManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrynApiApplicationTests {
	@Autowired
	RedisManager redisManager;
	@Test
	public void contextLoads() {
		redisManager.set("aa","aa");
		System.out.println(redisManager.get("aa"));
	}


}

