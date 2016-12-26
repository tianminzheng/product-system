package com.tianyalan.product.open.test;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/applicationContext_test.xml" })
public class TestBase {

	protected static final Logger logger = LoggerFactory
			.getLogger(TestBase.class);
}
