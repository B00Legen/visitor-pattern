package filesystem;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SetupTest {
	
	@Test
	void setupWorks() {
		int result = 2 + 3;
		
		assertEquals(5, result);
	}
	
	@Test
	void setupFails() {
		int result = 2 + 3;
		
		assertFalse(4 == result);
	}
}
