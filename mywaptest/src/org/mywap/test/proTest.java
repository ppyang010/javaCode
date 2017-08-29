package org.mywap.test;

import org.junit.Test;
import org.mywap.util.properties.ServerProperties;

/**
 * Created by s on 2017/8/29.
 */
public class proTest {
    public static  void main(String[] strs){
        String username=ServerProperties.getString("username",null);
        System.out.println(username);
    }
    @Test
    public void test(){
        String username=ServerProperties.getString("username",null);
        System.out.println(username);
    }
}
