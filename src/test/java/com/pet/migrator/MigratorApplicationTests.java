package com.pet.migrator;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class MigratorApplicationTests {

	@Test
	void contextLoads() {
	}

	public static void main(String[] args) {
		UUID uuid = UUID.randomUUID();

		System.out.println(uuid.variant());
		System.out.println(uuid.version());
	}

}
