package application;

import application.data.service.ProductService;
import application.data.service.UserService;
import application.service.FileStorageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

@SpringBootApplication
@ComponentScan
@EnableJpaRepositories(basePackages = "application")
public class Application implements CommandLineRunner{
    private static final Logger logger = LogManager.getLogger(Application.class);

    @Resource
    FileStorageService storageService;

    @Bean(name = "userService")
    public UserService getUserService() {
        return new UserService();
    }

    @Bean(name = "productService")
    public ProductService getProductService() {
        return new ProductService();
    }

    @Bean(name = "passwordEncoder")
    public PasswordEncoder getPasswordEncoder() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }

    public static void main(String[] args) throws Exception {
        logger.debug("Start---");
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... arg) throws Exception {

    }
}
