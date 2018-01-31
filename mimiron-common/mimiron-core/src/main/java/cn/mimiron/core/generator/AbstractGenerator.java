package cn.mimiron.core.generator;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangxd
 */
public abstract class AbstractGenerator extends AutoGenerator {

    private static final Logger logger = LoggerFactory.getLogger(AbstractGenerator.class);

    public AbstractGenerator dataSourceConfig(String dbUrl, String username, String password) {
        DataSourceConfig config = new DataSourceConfig()
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
        this.setDataSource(config);
        return this;
    }

    public AbstractGenerator packageConfig(String basePackage) {
        PackageConfig config = new PackageConfig()
            .setParent(basePackage)
            .setController("controller");
        this.setPackageInfo(config);
        return this;
    }

    public AbstractGenerator globalConfig(String author) {
        GlobalConfig config = new GlobalConfig()
            .setActiveRecord(false)
            .setAuthor(author)
            .setOutputDir("src/main/java")
            .setEnableCache(false)
            .setBaseResultMap(true)
            .setBaseColumnList(true)
            .setOpen(false)
            .setFileOverride(false);
        this.setGlobalConfig(config);
        return this;
    }

    public AbstractGenerator injectionConfig() {
        InjectionConfig config = new InjectionConfig() {
            @Override
            public void initMap() {
            }
        };

        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                File dir = new File("src/main/resources/mapper");
                if (!dir.exists()) {
                    boolean result = dir.mkdirs();
                    if (result) {
                        logger.debug("创建目录： [src/main/resources/mapper]");
                    }
                }
                return dir.getAbsolutePath() + File.separator + tableInfo.getXmlName() + ConstVal.XML_SUFFIX;
            }
        });
        config.setFileOutConfigList(focList);
        this.setCfg(config);
        return this;
    }

    public abstract AbstractGenerator templateConfig();

    public abstract AbstractGenerator strategyConfig(String[] includeTables);

}
