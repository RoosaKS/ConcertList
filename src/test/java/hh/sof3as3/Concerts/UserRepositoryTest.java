package hh.sof3as3.Concerts;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.sof3as3.Concerts.domain.User;
import hh.sof3as3.Concerts.domain.UserRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryTest {

	@Autowired
	private UserRepository repository;

	@Test
	public void findByUsername() {
		User user = repository.findByUsername("admin");
        assertThat(user).isNotNull();
        assertThat(user.getRole()).isEqualTo("ADMIN");
	}
	
	@Test
	public void createNewUser() {
		User user = new User("mikko", "$2a$10$WO6yfoL28VJefK9FznG9/Oj1rLPR74H3YUsjpNf2K8ge9JQ9um69W", "mikko.mikkonen@email.com", "USER");
		repository.save(user);
		assertThat(user.getId()).isNotNull();
	}
	
	@Test
	public void deleteUser() {
		repository.delete(repository.findByUsername("admin"));
		User user = repository.findByUsername("admin");
		assertThat(user).isNull();
	}
}
