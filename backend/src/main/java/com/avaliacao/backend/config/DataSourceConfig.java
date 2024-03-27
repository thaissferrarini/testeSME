package com.avaliacao.backend.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

public class DataSourceConfig {
  @Autowired
  Environment env;

  @Bean
  public DataSource dataSource() {
    final DataSourceBuilder dataSource = DataSourceBuilder.create();

    String driver = env.getProperty("driverClassName");
    String url = env.getProperty("url");
    String user = env.getProperty("user");
    String password = env.getProperty("password");

    System.out.println(String.format("Driver: %s \nUrl: %s\nuser: %s\nPassword: %s", driver, url, user, password));

    dataSource.driverClassName(driver);
    dataSource.url(url);
    dataSource.username(user);
    dataSource.password(password);

    return dataSource.build();
  }
}
