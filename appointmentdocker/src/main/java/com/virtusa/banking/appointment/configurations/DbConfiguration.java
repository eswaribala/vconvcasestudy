package com.virtusa.banking.appointment.configurations;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.web.client.RestTemplate;



//import com.virtusa.customerapidemo.configurations.DbConfiguration;


@Configuration
//@EnableConfigurationProperties(VaultConfiguration.class)
public class DbConfiguration {
	private static final Logger logger = LoggerFactory.getLogger(DbConfiguration.class);
	//virtusa-banking.properties
		@Value("${db_url}")
		private String url;
		@Value("${db_username}")
		private String userName;
		@Value("${db_password}")
		private String password;
	
		//private final VaultConfiguration vaultConfiguration;
		//vault initialization
		/*
		public DbConfiguration(VaultConfiguration configuration) {
		    this.vaultConfiguration = configuration;
		  }
		*/
		
		@Bean		
		public DataSource getInstance()
		{
			
			DataSourceBuilder builder= DataSourceBuilder.create();

		/*
		 * logger.info("----------------------------------------");
		 * logger.info("Configuration properties");
		 * logger.info("   mysql.username is {}", vaultConfiguration.getUsername());
		 * logger.info("   mysql.password is {}", vaultConfiguration.getPassword());
		 * logger.info("----------------------------------------");
		 */
			builder.url(url);
			builder.username(userName);
			builder.password(password);
			return builder.build();		
		}
        
		
}
