package com.project.web_prj.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

// DB 관련 설정 클래스
@Configuration
@ComponentScan(basePackages = "com.project.web_prj") // root 기준으로 하면 전체를 스캔한다
public class DataBaseConfig {

    // DB 접속 정보 설정 (DataSource 설정)
    @Bean // 객체 빈 등록
    public DataSource dataSource() {

        // 커넥션 풀 : 데이터베이스 연결 객체를 모아둔 곳
        HikariConfig config = new HikariConfig();
        config.setUsername("spring4");
        config.setPassword("1234");
        config.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:xe");
        config.setDriverClassName("oracle.jdbc.driver.OracleDriver");

        return new HikariDataSource(config);
    }
}
