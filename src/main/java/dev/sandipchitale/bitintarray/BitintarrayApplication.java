package dev.sandipchitale.bitintarray;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BitintarrayApplication {

	public static void main(String[] args) {
		SpringApplication.run(BitintarrayApplication.class, args);
	}

	@Bean
	public CommandLineRunner clr() {
		return (String... args) -> {

			BitIntArray bitIntArray = new BitIntArray(40);
			System.out.println(bitIntArray.allBits());

			bitIntArray.set(3);

			int which = 39;
			bitIntArray.set(which);
			System.out.println(bitIntArray.allBits(true));
			System.out.println(bitIntArray.isSet(which));

			bitIntArray.flip(which);
			System.out.println(bitIntArray.allBits(true));
			System.out.println(bitIntArray.isSet(which));

			bitIntArray.flip(which);
			System.out.println(bitIntArray.allBits(true));
			System.out.println(bitIntArray.isSet(which));
		};
	}
}
