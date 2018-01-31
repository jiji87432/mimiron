package cn.mimiron.uaa.generator;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

/**
 * 代码生成
 *
 * @author zhangxd
 */
public class Generator {

    private static final String[] INCLUDE_TABLES = new String[]{
        "user", "authority"
    };
    private static final String BASE_PACKAGE = "cn.mimiron.uaa";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/mimiron-uaa";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";

    @Test
    public void generateCode() {
        generateByTables();
    }

    private void generateByTables() {
        new AutoGenerator().setGlobalConfig(globalConfig())
            .setDataSource(dataSourceConfig())
            .setStrategy(strategyConfig())
            .setPackageInfo(
                new PackageConfig()
                    .setParent(BASE_PACKAGE)
                    .setController("controller")
                    .setEntity("entity")
            ).execute();
    }

    private GlobalConfig globalConfig() {
        return new GlobalConfig()
            .setActiveRecord(false)
            .setAuthor("zhangxd")
            .setOutputDir("src/main/java")
            .setEnableCache(false)
            .setBaseResultMap(true)
            .setBaseColumnList(true)
            .setOpen(false)
            .setFileOverride(true);
    }

    private DataSourceConfig dataSourceConfig() {
        return new DataSourceConfig()
            .setDbType(DbType.MYSQL)
            .setUrl(DB_URL)
            .setUsername(DB_USERNAME)
            .setPassword(DB_PASSWORD)
            .setDriverName("com.mysql.jdbc.Driver")
            .setTypeConvert(new MySqlTypeConvert() {
                // 字段转换
                @Override
                public DbColumnType processTypeConvert(String fieldType) {
                    if (fieldType.startsWith("tinyint(1)")) {
                        return DbColumnType.BOOLEAN;
                    }
                    return super.processTypeConvert(fieldType);
                }
            });
    }

    private StrategyConfig strategyConfig() {
        return new StrategyConfig()
            .setEntityLombokModel(false)
            .setDbColumnUnderline(true)
            .setNaming(NamingStrategy.underline_to_camel)
            .setSuperEntityClass("cn.mimiron.core.entity.BaseEntity")
            .setSuperMapperClass("cn.mimiron.core.mapper.BaseMapper")
            .setSuperServiceClass("cn.mimiron.core.service.IBaseService")
            .setSuperServiceImplClass("cn.mimiron.core.service.impl.BaseServiceImpl")
            .setSuperControllerClass("cn.mimiron.core.controller.BaseController")
            .setEntityBooleanColumnRemoveIsPrefix(true)
            .setRestControllerStyle(true)
            .setControllerMappingHyphenStyle(true)
            .setSuperEntityColumns("id", "gmt_create", "gmt_modified")
            .setLogicDeleteFieldName("is_deleted")
            .setVersionFieldName("version")
            .setInclude(INCLUDE_TABLES);
    }

}
