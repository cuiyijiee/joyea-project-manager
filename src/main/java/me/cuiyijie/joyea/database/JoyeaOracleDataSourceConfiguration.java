package me.cuiyijie.joyea.database;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
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
import java.util.Properties;

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
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:/mapper/joyea/*.xml"));

        //分页插件
        Interceptor interceptor = new PageInterceptor();
        Properties properties = new Properties();
        //数据库
        properties.setProperty("helperDialect", "oracle");
        //是否将参数offset作为PageNum使用
        properties.setProperty("offsetAsPageNum", "true");
        //是否进行count查询
        properties.setProperty("rowBoundsWithCount", "true");
        //是否分页合理化
        // 注意的是reasonable参数，表示分页合理化，默认值为false。
        //如果该参数设置为 true 时，pageNum<=0 时会查询第一页，pageNum>pages（超过总数时），会查询最后一页。默认false 时，直接根据参数进行查询。
        properties.setProperty("reasonable", "true");
        interceptor.setProperties(properties);
        sessionFactoryBean.setPlugins(interceptor);

        return sessionFactoryBean.getObject();
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
