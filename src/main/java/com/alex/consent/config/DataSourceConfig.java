package com.alex.consent.config;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig implements InitializingBean {

    @Value("${execute_seed_file:false}")
    private Boolean executeSeedFile;

    @Autowired
    private DataSource dataSource;

    @Override
    public void afterPropertiesSet() throws Exception {

        if (BooleanUtils.isFalse(executeSeedFile)) {
            return;
        }

        Resource initSchema = new ClassPathResource("seed.sql");
        DatabasePopulator databasePopulator = new ResourceDatabasePopulator(initSchema);
        DatabasePopulatorUtils.execute(databasePopulator, dataSource);
    }
}
