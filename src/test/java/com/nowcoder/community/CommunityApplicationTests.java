package com.nowcoder.community;

import com.nowcoder.community.config.AlphaConfig;
import com.nowcoder.community.dao.AlphaDao;
import com.nowcoder.community.service.AlphaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
class CommunityApplicationTests implements ApplicationContextAware {
	private ApplicationContext applicationContext;


	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext=applicationContext;
	}

	@Test
	public void testApplicationContext(){
		System.out.println(applicationContext);
	}

	@Test
	public void testAlphaDao(){
		//主动获取AlphaDao对象
		AlphaDao alphaDao=applicationContext.getBean("alphaHiberateImpl",AlphaDao.class);
		System.out.println(alphaDao.select());
		alphaDao=applicationContext.getBean(AlphaDao.class);
		System.out.println(alphaDao.select());
	}

	@Autowired
	AlphaService alphaService;
	@Autowired
	AlphaConfig alphaConfig;
	@Autowired
	@Qualifier(value = "alphaHiberateImpl")
	AlphaDao alphaDao;

	@Test
	public void testAlphaService(){
		System.out.println(alphaDao.select());
		System.out.println(alphaService.find());
		System.out.println(alphaConfig.find());
	}
}
