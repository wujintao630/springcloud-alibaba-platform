package com.tonytaotao.scp.code.generation;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.apache.commons.lang.StringUtils;

import java.util.Scanner;

/**
 * @author tonytaotao
 */
public class CodeGeneration {

    public static String scanner(String tip) throws Exception {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.printf(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new Exception("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) throws Exception {

        // 注意修改src/main/resources/templates/controller.java.vm 中的 GlobalResult, QueryPage 包路径，因为这两个类可能放在其他jar包或者其他路径下
        //  import com.tonytaotao.service.common.base.GlobalResult;
        //  import com.tonytaotao.service.common.base.QueryPage;

        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setActiveRecord(true)
                .setAuthor("TonyTaotao")
                .setOutputDir(System.getProperty("user.dir") + "/scp-code-generation" + "/src/main/java")
                .setFileOverride(true)
                .setOpen(false)
                .setServiceName("%sService");

        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setUrl("jdbc:mysql://localhost:3306/tonytaotao?useSSL=false&useUnicode=true&characterEncoding=UTF8&allowPublicKeyRetrieval=true&serverTimezone=GMT")
                .setUsername("root")
                .setPassword("123456")
                .setDriverName("com.mysql.jdbc.Driver");

        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("com.tonytaotao.scp")
                .setModuleName(scanner("模块名"))
                .setController("controller")
                .setService("service")
                .setServiceImpl("service.impl")
                .setMapper("mapper")
                .setXml("mapper.xml")
                .setEntity("entity");

        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setNaming(NamingStrategy.underline_to_camel)
                .setColumnNaming(NamingStrategy.underline_to_camel)
                .setEntityLombokModel(true)
                .setRestControllerStyle(true)
                .setInclude(scanner("表名,多个用英文逗号分割").split(","))
                .setControllerMappingHyphenStyle(true);

        AutoGenerator autoGenerator = new AutoGenerator();
        autoGenerator.setGlobalConfig(globalConfig)
                .setDataSource(dataSourceConfig)
                .setPackageInfo(packageConfig)
                .setStrategy(strategyConfig);

        autoGenerator.execute();
    }

}
