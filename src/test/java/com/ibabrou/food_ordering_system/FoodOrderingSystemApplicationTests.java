package com.ibabrou.food_ordering_system;

import com.ibabrou.food_ordering_system.config.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(TestConfig.class)
class FoodOrderingSystemApplicationTests {

	@Test
	void contextLoads() {
	}

}
