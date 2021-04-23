package com.Jared.cryptopricesdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
@EnableScheduling
public class CryptoPricesDemoApplication {

	private static final Logger log = LoggerFactory.getLogger(CryptoPricesDemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CryptoPricesDemoApplication.class);
	}

	@Bean
	public RestTemplate restTemplate (RestTemplateBuilder builder) {
		return builder.build();
	}

	@Scheduled(fixedRate = 1000)
	public void printInfo() {
		RestTemplate restTemplate = new RestTemplate();
		CryptoInfo[] cryptoInfo = restTemplate.getForObject("https://api.n.exchange/en/api/v1/price/BTCLTC/latest/?market_code=nex", CryptoInfo[].class);
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		log.info("The time is now {}", dateFormat.format(new Date()));
		log.info(cryptoInfo[0].toString());
	}



}
