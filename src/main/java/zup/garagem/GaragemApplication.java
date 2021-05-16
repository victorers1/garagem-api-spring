package zup.garagem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackageClasses = {zup.garagem.client.FIPEClient.class})
public class GaragemApplication {
    public static void main(String[] args) {
        SpringApplication.run(GaragemApplication.class, args);
    }
}
