import com.example.payment.paymentservice.client.BankClient;
import feign.Feign;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public BankClient bankClient() {
        return Feign.builder()
                .target(BankClient.class, "http://localhost:8081");
    }
}
