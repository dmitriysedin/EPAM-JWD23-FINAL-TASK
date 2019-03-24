package by.epam.dmitriysedin.finaltask.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class PasswordHashTest {

	public static final String PLAINTEXT_PASSWORD = "Admin1234";
	public static final String HASHED_PASSWORD = "$2a$13$BLTuZAqi3VzMEpGPpY9UuuygpdAvDNlH2Qpb92pKBgdZHpNM1tp7O";
	public static final int HASHED_PASSWORD_LENGTH = 60;
	
	@Test
	public void testHashPassword() {
		
		int expected = HASHED_PASSWORD_LENGTH;
		int actual = PasswordHash.hashPassword(PLAINTEXT_PASSWORD).length();
		
		assertEquals(expected, actual);
	}

	@Test
	public void testCheckPassword() {
		
		assertTrue(PasswordHash.checkPassword(PLAINTEXT_PASSWORD, HASHED_PASSWORD));
	}

}
