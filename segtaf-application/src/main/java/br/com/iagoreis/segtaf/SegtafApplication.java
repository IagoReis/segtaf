package br.com.iagoreis.segtaf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackageClasses = SegtafApplication.class)
public class SegtafApplication {

	public static void main(String[] args) {

		SpringApplication.run(SegtafApplication.class, args);
	}

}
