package com.ft.turorial.spring.boot.config;

import java.sql.SQLException;

import org.springframework.aop.Advisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DatabaseDriver;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;

/**
 * 
 * @author lc
 *
 */
@Configuration
@EnableConfigurationProperties(DataSourceProperties.class)
public class DruidConfiguration{
	
	@Bean
    @ConfigurationProperties("spring.datasource.*")
    public DruidDataSource dataSource(DataSourceProperties properties) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(properties.determineDriverClassName());
        dataSource.setUrl(properties.determineUrl());
        dataSource.setUsername(properties.determineUsername());
        dataSource.setPassword(properties.determinePassword());
        DatabaseDriver databaseDriver = DatabaseDriver
                .fromJdbcUrl(properties.determineUrl());
        String validationQuery = databaseDriver.getValidationQuery();
        if (validationQuery != null) {
            dataSource.setTestOnBorrow(true);
            dataSource.setValidationQuery(validationQuery);
        }
        try {
        	//enable monitoring
			dataSource.setFilters("mergeStat,wall,log4j");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return dataSource;
    }

	/**
     * StatViewServlet
     * @return
     */
    @Bean
    public ServletRegistrationBean DruidStatViewServlet(){
       //org.springframework.boot.context.embedded.ServletRegistrationBean
       ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
       
       //initParams
       servletRegistrationBean.addInitParameter("allow","192.168.1.88,127.0.0.1");
       servletRegistrationBean.addInitParameter("deny","192.168.1.80");
       servletRegistrationBean.addInitParameter("loginUsername","root");
       servletRegistrationBean.addInitParameter("loginPassword","123456");
       servletRegistrationBean.addInitParameter("resetEnable","false");
       return servletRegistrationBean;
    }
   
    /**
     * filterRegistrationBean
     * @return
     */
    @Bean
    public FilterRegistrationBean druidStatFilter(){
      
       FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
      
       //添加过滤规则.
       filterRegistrationBean.addUrlPatterns("/*");
      
       //添加不需要忽略的格式信息.
       filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid2/*");
       return filterRegistrationBean;
    }
    /**
     * DruidStatInterceptor
     * @return
     */
    @Bean
    public DruidStatInterceptor druidStatInterceptor(){
    	return new DruidStatInterceptor();
    }
    @Bean
    public JdkRegexpMethodPointcut druidStatPointcut(){
    	JdkRegexpMethodPointcut druidStatPointcut = new JdkRegexpMethodPointcut();
		String patterns = "com.ft.*.*.service.*";
		String patterns2 = "com.ft.*.*.repository.*";
		druidStatPointcut.setPatterns(patterns,patterns2);
    	return druidStatPointcut;
    }
    @Bean
    public Advisor druidStatAdvisor() {
		return new DefaultPointcutAdvisor(druidStatPointcut(), druidStatInterceptor());
    }
}
