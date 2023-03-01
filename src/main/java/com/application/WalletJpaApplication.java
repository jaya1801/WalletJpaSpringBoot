package com.application;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WalletJpaApplication implements CommandLineRunner {

	public static void main(String[] args) {

		SpringApplication.run(WalletJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		WalletJpaDto wallt = new WalletJpaDto();
		System.out.println(wallt.getBalance());
	}



}
