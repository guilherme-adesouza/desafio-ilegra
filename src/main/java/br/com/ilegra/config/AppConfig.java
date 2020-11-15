package br.com.ilegra.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.io.File;

@Configuration
public class AppConfig {

    private static final Logger logger = LogManager.getLogger(AppConfig.class);

    @Bean
    public boolean watchDir(@Value("${app.watch.dir}") String watchDir) {
        final File directory = new File(watchDir);
        if (!directory.exists()) {
            logger.info("Creating app.watch.dir on [{}]", watchDir);
            return directory.mkdirs();
        }
        logger.info("app.watch.dir already exists on [{}]", watchDir);;
        return true;
    }

    @Bean
    public boolean reportDir(@Value("${app.report.dir}") String reportDir) {
        final File directory = new File(reportDir);
        if (!directory.exists()) {
            logger.info("Creating app.report.dir on [{}]", reportDir);
            return directory.mkdirs();
        }
        logger.info("app.report.dir already exists on [{}]", reportDir);;
        return true;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
