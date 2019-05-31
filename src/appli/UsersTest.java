package appli;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Users;

class UsersTest {

	@Test
	void addtest() throws Exception {
		UsersDAO userdao = new UsersDAO(); 
		Users user = new Users("arcoffen","president","zaeazd");
		userdao.addUser(user);
		boolean output = userdao.Verifier(user.getUsername(), user.getPassword());
		assertTrue(output);
	}
	
	@Test
	void deletetest() throws Exception {
		UsersDAO userdao = new UsersDAO();
		Users user = new Users("zakaria","president","ba3");
		userdao.deleteUser(user.getId());
		boolean output = userdao.Verifier(user.getUsername(), user.getPassword());
		assertFalse(output);
	}
	@Test
	void updatetest() throws Exception {
		UsersDAO userdao = new UsersDAO();
		Users user = new Users("houda","vice presidente","ba3");
		user.setPoste("presidente");
	
		userdao.updateUser(user);
		boolean output = userdao.VerifierInfo(user.getUsername(), user.getPoste());
		assertFalse(output);
	}
	
	

}
