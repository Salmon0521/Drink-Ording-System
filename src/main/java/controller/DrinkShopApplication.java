package controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan(basePackages = "controller")
public class DrinkShopApplication {
    public static void main(String[] args) {
        SpringApplication.run(DrinkShopApplication.class, args);
        System.out.println("Spring Boot 啟動囉！");
    }
}
