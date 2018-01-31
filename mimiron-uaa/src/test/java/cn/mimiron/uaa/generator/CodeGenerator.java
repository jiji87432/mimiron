package cn.mimiron.uaa.generator;

import cn.mimiron.core.generator.Generator;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import org.junit.Test;

/**
 * 代码生成
 *
 * @author zhangxd
 */
public class CodeGenerator {

    private static final String[] INCLUDE_TABLES = new String[]{
        "user", "role"
    };
    private static final String BASE_PACKAGE = "cn.mimiron.uaa";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/mimiron-uaa";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";

    private static final String AUTHOR = "zhangxd";

    @Test
    public void generateCode() {
        generateByTables();
    }

    private void generateByTables() {
        new AutoGenerator()
            .setGlobalConfig(Generator.globalConfig(AUTHOR))
            .setDataSource(Generator.dataSourceConfig(DB_URL, DB_USERNAME, DB_PASSWORD))
            .setStrategy(Generator.strategyConfig(INCLUDE_TABLES))
            .setPackageInfo(Generator.packageConfig(BASE_PACKAGE))
            .execute();
    }

}
