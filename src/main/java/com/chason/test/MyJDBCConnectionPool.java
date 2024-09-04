package com.chason.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class MyJDBCConnectionPool {

    private final BlockingQueue<Connection> connectionPool;
    private final String jdbcUrl;
    private final String jdbcUser;
    private final String jdbcPassword;

    public MyJDBCConnectionPool(int poolSize, String jdbcUrl, String jdbcUser, String jdbcPassword)
            throws SQLException {
        this.jdbcUrl = jdbcUrl;
        this.jdbcUser = jdbcUser;
        this.jdbcPassword = jdbcPassword;
        this.connectionPool = new ArrayBlockingQueue<>(poolSize);

        for (int i = 0; i < poolSize; i++) {
            Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
            connectionPool.offer(connection);
        }
    }

    public Connection getConnection()
            throws InterruptedException {
        return connectionPool.take();
    }

    public void returnConnection(Connection connection) {
        connectionPool.offer(connection);
    }

    public void closeAllConnections() throws SQLException {
        for (Connection connection : connectionPool) {
            connection.close();
        }
    }

    public static void main(String[] args) {

        final int POOL_SIZE = 10;
        final String JDBC_URL = "jdbc:mysql://localhost:3306/mysql";
        final String JDBC_USER = "glue";
        final String JDBC_PASSWORD = "Glue01_glue";

        try {
            // 初始化连接池
            MyJDBCConnectionPool connectionPool = new MyJDBCConnectionPool(POOL_SIZE, JDBC_URL, JDBC_USER, JDBC_PASSWORD);

            // 启动20个线程
            for (int i = 0; i < 20; i++) {
                new Thread(() -> {
                    while (true) {
                        try {
                            // 从连接池获取连接
                            Connection connection = connectionPool.getConnection();

                            // 执行SQL语句
                            PreparedStatement stmt = connection.prepareStatement("SELECT CONNECTION_ID()");
                            ResultSet rs = stmt.executeQuery();
                            if (rs.next()) {
                                System.out.println("Thread " + Thread.currentThread().getId() + " Connection ID: " + rs.getInt(1));
                            }

                            // 关闭资源
                            rs.close();
                            stmt.close();

                            // 将连接归还到连接池
                            connectionPool.returnConnection(connection);

                            // 短暂休眠100ms
                            Thread.sleep(100);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }

            // 运行约1分钟后关闭程序
            Thread.sleep(60000);
            connectionPool.closeAllConnections();
            System.out.println("Connection pool closed.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
