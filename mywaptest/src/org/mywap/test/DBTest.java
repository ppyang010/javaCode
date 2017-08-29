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
    // 1���������
    @Test
    public void insert() {
        conn = DBUtils.getConnection();
        try {
            String sql = "INSERT INTO users(id,username,age,job,address) VALUES(1,'����ȱ',20,'���ִ���','�������£��ĺ�Ϊ�ң�')";
            state = conn.createStatement();
            int count = state.executeUpdate(sql);
            System.out.println("Operation is successful!");
            System.out.println("��������" + count + "�����ݣ�");
            //�������ͷ���Դ
            DBUtils.closeResources(conn, state, null);
        } catch (SQLException e) {
            System.out.println("insert error!");
            e.printStackTrace();
        }
    }
    // ɾ������
    @Test
    public void delete() {
        conn = DBUtils.getConnection();
        try {
            String sql = "DELETE FROM users WHERE username='����ȱ1'";
            state = conn.createStatement();
            int count = state.executeUpdate(sql);
            System.out.println("Operation is successful!");
            System.out.println("��ɾ����" + count + "�����ݣ�");
            DBUtils.closeResources(conn, state, null);
        } catch (SQLException e) {
            System.out.println("delete error!");
            e.printStackTrace();
        }
    }
                                         
    //�޸�����
    @Test
    public void update(){
        conn = DBUtils.getConnection();
        try {
            String sql = "UPDATE users SET job = '���µ�һ' WHERE id = '1'";
            state = conn.createStatement();
            int count = state.executeUpdate(sql);
            System.out.println("Operation is successful!");
            System.out.println("���޸���" + count + "�����ݣ�");
            DBUtils.closeResources(conn, state, null);
        } catch (SQLException e) {
            System.out.println("update error!");
            e.printStackTrace();
        }
    }
                                         
    //��ѯ����
    @Test
    public void query(){
        conn = DBUtils.getConnection();
        try {
            String sql = "SELECT * FROM user";
            state = conn.createStatement();
            ResultSet rs = state.executeQuery(sql);
            System.out.println("Operation is successful!");
            System.out.println("��ѯ�����ݽ�����£�");
            while(rs.next()){
                int id = rs.getInt("id");
                int age = rs.getInt("age");
                String username = rs.getString("username");
                String job = rs.getString("job");
                String address = rs.getString("address");
                System.out.println("����:"+id+",������"+username+",���䣺"+age+",ְλ��"+job+",��ַ��"+address);
            }
            DBUtils.closeResources(conn, state, rs);
        } catch (SQLException e) {
            System.out.println("query error!");
            e.printStackTrace();
        }
    }
    
    //���Ӳ���
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
