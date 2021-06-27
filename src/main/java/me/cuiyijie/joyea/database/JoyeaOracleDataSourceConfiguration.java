package me.cuiyijie.joyea.database;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "me.cuiyijie.joyea.dao.joyea", sqlSessionTemplateRef = "joyeaSqlSessionTemplate")
public class JoyeaOracleDataSourceConfiguration {

    @Value("${spring.datasource.joyea.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.joyea.url}")
    private String url;

    @Value("${spring.datasource.joyea.username}")
    private String userName;

    @Value("${spring.datasource.joyea.password}")
    private String password;

    @Bean(name = "joyeaDataSource")
    public DataSource dataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(driverClassName);
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(userName);
        druidDataSource.setPassword(password);
        return druidDataSource;
    }

    @Bean(name = "joyeaSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("joyeaDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:/mapper/joyea/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "joyeaTransactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("joyeaDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "joyeaSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("joyeaSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
