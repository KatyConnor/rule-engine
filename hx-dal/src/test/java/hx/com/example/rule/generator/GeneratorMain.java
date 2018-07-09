package hx.com.example.rule.generator;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author mingliang
 * @Date 2017-09-12 15:45
 */
public class GeneratorMain {

    /*
       生成方式是采用maven插件，在命令行输入mvn mybatis-generator:generate 即可生成
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(GeneratorMain.class);

    public static void main(String[] args) {
        List<String> warnings = new ArrayList<>();
        boolean overwrit = true;
        ConfigurationParser cp = new ConfigurationParser(warnings);
        try {
            Configuration configuration = cp.parseConfiguration(GeneratorMain.class.getResourceAsStream("/mybatis-generator-config.xml"));
            DefaultShellCallback defaultShellCallback = new DefaultShellCallback(overwrit);
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(configuration,defaultShellCallback,warnings);
            myBatisGenerator.generate(null);
            System.out.println("mybatis 代码生成成功。。。");
        } catch (IOException e) {
            LOGGER.info("",e);
        } catch (XMLParserException e) {
            LOGGER.info("",e);
        }catch (InvalidConfigurationException e) {
            LOGGER.info("",e);
        }catch (SQLException e) {
            LOGGER.info("",e);
        } catch (InterruptedException e) {
            LOGGER.info("",e);
        }
    }
}
