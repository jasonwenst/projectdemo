package com.asiainfo.demo.configure;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.asiainfo.demo.configuration.MybatisConfiguration;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MybatisConfiguration.class })
//@EnableTransactionManagement
public class BaseJUnit4Test {

}
