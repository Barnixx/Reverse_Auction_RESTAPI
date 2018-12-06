package pl.barnixx.reverse_auction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@EnableAsync
public class ReverseAuctionRestApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReverseAuctionRestApiApplication.class, args);
    }
}
