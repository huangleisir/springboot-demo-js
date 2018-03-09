package com.jst.demo.test.commom.util;

import org.junit.Test;

import com.jst.demo.rabbit.DemoQueue;
import com.jst.demo.rabbit.PayDelay5QueueConfig;
import com.jst.demo.test.AbstractTest;
import com.jst.framework.common.util.SnowflakeUtil;


public class SnowFlakeTest extends AbstractTest{

	@Test
	public void generate() throws Exception {
		 
		for (int i = 0; i < 1000; i++) {
    		System.out.println(SnowflakeUtil.generate());
    	}


	}

}
