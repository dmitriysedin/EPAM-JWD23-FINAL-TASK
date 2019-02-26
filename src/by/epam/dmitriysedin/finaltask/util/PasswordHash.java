package by.epam.dmitriysedin.finaltask.util;

import org.mindrot.jbcrypt.BCrypt;

/**
 * This code utilizes jBCrypt, which you need installed to use.
 * jBCrypt: http://www.mindrot.org/projects/jBCrypt/
 */

public class PasswordHash {

	// Define the BCrypt workload to use when generating password hashes. 10-31 is a valid value.
	private static int workload = 13;
	
	/**
	 * This method can be used to generate a string representing an account password
	 * suitable for storing in a database. It will be an OpenBSD-style crypt(3) formatted
	 * hash string of length=60
	 * The bcrypt workload is specified in the above static variable, a value from 10 to 31.
	 * This automatically handles secure 128-bit salt generation and storage within the hash.
	 * @param password The account's plaintext password as provided during account creation,
	 *			     or when changing an account's password.
	 * @return String - a string of length 60 that is the bcrypt hashed password in crypt(3) format.
	 */
	
	public static String hashPassword(String password) {
		
		String salt =BCrypt.gensalt(workload);
		
		return BCrypt.hashpw(password, salt);
		
	}
	
	/**
	 * This method can be used to verify a computed hash from a plaintext (e.g. during a login
	 * request) with that of a stored hash from a database. The password hash from the database
	 * must be passed as the second variable.
	 * @param password The account's plaintext password, as provided during a login request
	 * @param storedHashPassword The account's stored password hash, retrieved from the authorization database
	 * @return boolean - true if the password matches the password of the stored hash, false otherwise
	 */
	
	public static boolean checkPassword (String password, String storedHashPassword) {
		
		boolean checkedPass = false;
		
		if (password != null && storedHashPassword != null) {
			checkedPass = BCrypt.checkpw(password, storedHashPassword);
		}
		
		return checkedPass;
	}
}
