package cn.mimiron.core.generator;

import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @author zhangxd
 */
public class SimpleGenerator extends AbstractGenerator {

    public SimpleGenerator templateConfig() {
        TemplateConfig config = new TemplateConfig()
            .setController(null)
            .setService(null)
            .setServiceImpl(null)
            .setXml(null);
        this.setTemplate(config);
        return this;
    }

    public SimpleGenerator strategyConfig(String[] includeTables) {
        StrategyConfig config = new StrategyConfig()
            .setEntityLombokModel(false)
            .setDbColumnUnderline(true)
            .setNaming(NamingStrategy.underline_to_camel)
            .setEntityBooleanColumnRemoveIsPrefix(true)
            .setInclude(includeTables);
        this.setStrategy(config);
        return this;
    }

}
