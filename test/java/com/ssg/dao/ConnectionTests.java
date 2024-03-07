package com.ssg.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionTests {

    @Test
    public void test1(){
        // @Test 어노테이션은 JUnit에게 이 메서드가 테스트 메서드임을 알려줍니다.
// test1() 메서드는 두 변수를 비교하는 단순한 테스트를 수행합니다.

// int 타입 변수 v1을 선언하고 10으로 초기화합니다.
        int v1 = 10;

// int 타입 변수 v2를 선언하고 10으로 초기화합니다.
        int v2 = 10;

// Assertions 클래스의 assertEquals 메서드를 사용하여 v1과 v2를 비교합니다.
// 이 메서드는 두 값이 동일한지 확인하고, 만약 다르다면 AssertionFailedError를 발생시킵니다.
        Assertions.assertEquals(v1, v2);

    }


    @Test
    public void testConnection() throws Exception{
        // @Test 어노테이션은 JUnit에게 이 메서드가 테스트 메서드임을 알려줍니다.
// testConnection() 메서드는 데이터베이스 연결을 테스트합니다.

// ClassNotFoundException을 방지하기 위해 MariaDB JDBC 드라이버를 로드합니다.
        Class.forName("org.mariadb.jdbc.Driver");

// DriverManager를 사용하여 MariaDB 데이터베이스에 연결합니다.
// 연결 정보는 "jdbc:mariadb://localhost:3307/webdb" (URL), "webuser" (사용자 이름), "9023" (비밀번호)입니다.
        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3307/webdb", "webuser", "9023");

// Assertions 클래스의 assertNotNull 메서드를 사용하여 연결 객체(conn)가 null이 아닌지 확인합니다.
        Assertions.assertNotNull(conn);

// 연결을 확인했으므로, 연결을 닫습니다.
        conn.close();

    }

    /**
     * 커넥션 풀 생성하는 메소드
     * @throws Exception
     */
    @Test
    public void testHikariCP()throws Exception{
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.mariadb.jdbc.Driver");
        config.setJdbcUrl("jdbc:mariadb://localhost:3307/webdb");
        config.setUsername("webuser");
        config.setPassword("9023");
        config.addDataSourceProperty("cachePrepStmts","true");
        config.addDataSourceProperty("prepStmtCacheSize","250");
        config.addDataSourceProperty("prepStmeCacheSqlLimit","2048");
        HikariDataSource ds = new HikariDataSource(config);
        Connection connection = ds.getConnection();
        System.out.println(connection);
        connection.close();
    }

}