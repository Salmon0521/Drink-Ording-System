import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"controller", "service", "dao", "bean"})
@SpringBootApplication
public class DrinkShopApplication {
    public static void main(String[] args) {
        SpringApplication.run(DrinkShopApplication.class, args);
        System.out.println("Spring Boot 啟動囉！");
    }
}
