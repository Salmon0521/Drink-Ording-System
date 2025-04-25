package start;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"controller", "service", "dao", "bean"})
@SpringBootApplication
public class DrinkShopApplication {
    private static final Logger logger = LoggerFactory.getLogger(DrinkShopApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DrinkShopApplication.class, args);
        logger.info("Spring Boot 啟動囉！");
    }
}
