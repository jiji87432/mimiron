package cn.mimiron.core.generator;

import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @author zhangxd
 */
public class Generator {

    public static PackageConfig packageConfig(String basePackage) {
        return new PackageConfig()
            .setParent(basePackage)
            .setController("controller")
            .setEntity("entity")
            .setXml("mapper.mapping");
    }

    public static GlobalConfig globalConfig(String author) {
        return new GlobalConfig()
            .setActiveRecord(false)
            .setAuthor(author)
            .setOutputDir("src/main/java")
            .setEnableCache(false)
            .setBaseResultMap(true)
            .setBaseColumnList(true)
            .setOpen(false)
            .setFileOverride(false);
    }

    public static DataSourceConfig dataSourceConfig(String dbUrl, String username, String password) {
        return new DataSourceConfig()
            .setDbType(DbType.MYSQL)
            .setUrl(dbUrl)
            .setUsername(username)
            .setPassword(password)
            .setDriverName("com.mysql.jdbc.Driver")
            .setTypeConvert(new MySqlTypeConvert() {
                @Override
                public DbColumnType processTypeConvert(String fieldType) {
                    if (fieldType.startsWith("tinyint(1)")) {
                        return DbColumnType.BOOLEAN;
                    }
                    return super.processTypeConvert(fieldType);
                }
            });
    }

    public static StrategyConfig strategyConfig(String[] includeTables) {
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
            .setSuperEntityColumns("id", "gmt_create", "gmt_modified", "is_deleted")
            .setLogicDeleteFieldName("is_deleted")
            .setVersionFieldName("version")
            .setInclude(includeTables);
    }

}
