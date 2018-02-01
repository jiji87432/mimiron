package cn.mimiron.core.generator;

import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @author zhangxd
 */
public class BaseGenerator extends AbstractGenerator {

    @Override
    public BaseGenerator templateConfig() {
        TemplateConfig config = new TemplateConfig()
            .setXml(null);
        this.setTemplate(config);
        return this;
    }

    @Override
    public BaseGenerator strategyConfig(String[] includeTables) {
        StrategyConfig config = new StrategyConfig()
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
            .setInclude(includeTables);
        this.setStrategy(config);
        return this;
    }

}
