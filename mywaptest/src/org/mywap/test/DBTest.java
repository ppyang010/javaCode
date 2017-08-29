package org.mywap.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;
import org.mywap.util.DBUtils;
public class DBTest {
    private static Connection conn;
    private static Statement state;
    // 1，添加数据
    @Test
    public void insert() {
        conn = DBUtils.getConnection();
        try {
            String sql = "INSERT INTO users(id,username,age,job,address) VALUES(1,'花无缺',20,'武林大侠','云游天下，四海为家！')";
            state = conn.createStatement();
            int count = state.executeUpdate(sql);
            System.out.println("Operation is successful!");
            System.out.println("共插入了" + count + "条数据！");
            //操作后释放资源
            DBUtils.closeResources(conn, state, null);
        } catch (SQLException e) {
            System.out.println("insert error!");
            e.printStackTrace();
        }
    }
    // 删除数据
    @Test
    public void delete() {
        conn = DBUtils.getConnection();
        try {
            String sql = "DELETE FROM users WHERE username='花无缺1'";
            state = conn.createStatement();
            int count = state.executeUpdate(sql);
            System.out.println("Operation is successful!");
            System.out.println("共删除了" + count + "条数据！");
            DBUtils.closeResources(conn, state, null);
        } catch (SQLException e) {
            System.out.println("delete error!");
            e.printStackTrace();
        }
    }
                                         
    //修改数据
    @Test
    public void update(){
        conn = DBUtils.getConnection();
        try {
            String sql = "UPDATE users SET job = '天下第一' WHERE id = '1'";
            state = conn.createStatement();
            int count = state.executeUpdate(sql);
            System.out.println("Operation is successful!");
            System.out.println("共修改了" + count + "条数据！");
            DBUtils.closeResources(conn, state, null);
        } catch (SQLException e) {
            System.out.println("update error!");
            e.printStackTrace();
        }
    }
                                         
    //查询数据
    @Test
    public void query(){
        conn = DBUtils.getConnection();
        try {
            String sql = "SELECT * FROM user";
            state = conn.createStatement();
            ResultSet rs = state.executeQuery(sql);
            System.out.println("Operation is successful!");
            System.out.println("查询的数据结果如下：");
            while(rs.next()){
                int id = rs.getInt("id");
                int age = rs.getInt("age");
                String username = rs.getString("username");
                String job = rs.getString("job");
                String address = rs.getString("address");
                System.out.println("工号:"+id+",姓名："+username+",年龄："+age+",职位："+job+",地址："+address);
            }
            DBUtils.closeResources(conn, state, rs);
        } catch (SQLException e) {
            System.out.println("query error!");
            e.printStackTrace();
        }
    }
    
    //连接测试
    @Test
    public void link(){
        conn = DBUtils.getConnection();
        if(conn!=null){
        	System.out.println("success");
        }else{
        	System.out.println("error");
        }
    }
}
