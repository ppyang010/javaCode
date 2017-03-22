package org.mywap.util;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Vector;

public class ConnectionPool {
    private Vector<Connection> pool;
    private String url;
    private String username;
    private String password;
    private String driverClassName;
    private int poolSize = 1;
    private static ConnectionPool instance = null;
    
    //˽�й��췽������ֹ�ⲿ��������Ķ���Ҫ���ñ���Ķ���ͨ��<code>getInstance</code>����
    private ConnectionPool(){
        System.out.println("���캯��");
        init();
    }
    
    //���ӳس�ʼ����������ȡ�����ļ������ݣ��������ӳ��еĳ�ʼ����
    private void init(){
        readConfig();
        pool = new Vector<Connection>(poolSize);
        addConnection();
    }
    
    //�������ӵ����ӳ���
    public synchronized void release(Connection coon){//synchronized��֤��ͬһʱ�����ֻ��һ���߳�ִ����δ���
        pool.add(coon);
    }
    
    //�ر����ӳ��е��������ݿ�����
    public synchronized void closePool(){
        for (int i = 0; i < pool.size(); i++) {
            try {
                ((Connection)pool.get(i)).close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            pool.remove(i);
        }
    }
    
    //���ص�ǰ���ӳص�һ������
    public static ConnectionPool getInstance(){
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }
    
    //�������ӳ��е�һ�����ݿ�����
    public synchronized Connection getConnection(){
        if (pool.size() > 0) {
            Connection conn = pool.get(0);
            pool.remove(conn);
            return conn;
        }else {
            return null;
        }
    }
    
    //�����ӳ��д�����ʼ���õ����ݿ�����
    private void addConnection(){
        Connection coon = null;
        for (int i = 0; i < poolSize; i++) {
            try {
                Class.forName(driverClassName);
                coon = java.sql.DriverManager.getConnection(url, username, password);
                pool.add(coon);
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    
    //��ȡ�������ӳص������ļ�
    private void readConfig(){
        try {
            //String path = System.getProperty("user.dir") + "\\dbpool.properties";
        	String path=this.getClass().getResource("/dbpool.properties").getPath();//��ȡ�����ļ�	
            FileInputStream is = new FileInputStream(path);
            Properties props = new Properties();
            props.load(is);
            this.driverClassName = props.getProperty("driverClassName");
            this.username = props.getProperty("username");
            this.password = props.getProperty("password");
            this.url = props.getProperty("url");
            this.poolSize = Integer.parseInt(props.getProperty("poolSize"));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.err.println("�����ļ��Ҳ���");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.err.println("��ȡ�����ļ�����");
        }
    }
    
}