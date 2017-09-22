package test;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.code.server.model.User;
import org.code.server.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类  
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})  
public class MyBaits {
	  private static Logger logger = Logger.getLogger(MyBaits.class);  
	//  private ApplicationContext ac = null;  
	   @Resource  
	   private IUserService userService = null;  
	    @Resource  
	    SqlSessionFactory sqlSessionFactory;
	  
	    @Test  
	    public void test1() {  
	    System.out.println("ok");
	     User user = userService.getUserById(14);  
         System.out.println(user.getUsername()); 
//         SqlSession sqlSession = sqlSessionFactory.openSession();
//	    	User user = sqlSession.selectOne(User.class.getName()+".selectByPrimaryKey", 4);
	    	System.out.println(user.getUsername());
	        // logger.info("值："+user.getUserName());  
	        //logger.info(JSON.toJSONString(user));  
	    }
}
