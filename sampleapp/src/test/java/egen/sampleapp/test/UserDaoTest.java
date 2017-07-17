package egen.sampleapp.test;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import egen.sampleapp.domain.User;
import egen.sampleapp.domain.Gender;
import egen.sampleapp.repo.UserDao;
import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/META-INF/applicationContext-test.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class UserDaoTest {
	@Autowired
	private UserDao userDao;

	@Test
	public void testFindById() {
		User user = userDao.findById(99L);

		Assert.assertEquals("Ajay", user.getFirstName());
		Assert.assertEquals("Kumar", user.getMiddleName());
		Assert.assertEquals("Malepati", user.getLastName());
		Assert.assertEquals(Gender.M, user.getGender());
		Assert.assertEquals(new Integer(78200), user.getZip());
		Assert.assertEquals(new Short("33"), user.getAge());
		Assert.assertEquals(new Long(2101110000), user.getPhone());
		return;
	}

	@Test
	public void testCreate() {
		User user = new User();
		user.setFirstName("Ajay1");
		user.setMiddleName("Kumar1");
		user.setLastName("Malepati1");
		user.setGender(Gender.M);
		user.setPhone(2101111111L);
		user.setZip(78240);
		user.setAge(new Short("33"));

		userDao.create(user);
		Long id = user.getId();
		Assert.assertNotNull(id);

		Assert.assertEquals(2, userDao.findAll().size());
		User newUser = userDao.findById(id);

		Assert.assertEquals("Ajay1", newUser.getFirstName());
		Assert.assertEquals("Kumar1", newUser.getMiddleName());
		Assert.assertEquals("Malepati1", newUser.getLastName());
		Assert.assertEquals(Gender.M, newUser.getGender());
		Assert.assertEquals(new Integer(78240), newUser.getZip());
		Assert.assertEquals(new Short("33"), newUser.getAge());
		Assert.assertEquals(new Long(2101111111L), newUser.getPhone());
		return;
	}

	@Test
	public void testFindAll() {
		User user = new User();
		user.setFirstName("Ajay1");
		user.setMiddleName("Kumar1");
		user.setLastName("Malepati1");
		user.setGender(Gender.M);
		user.setPhone(2101111111L);
		user.setZip(78240);
		user.setAge(new Short("33"));

		userDao.create(user);
		Long id = user.getId();
		Assert.assertNotNull(id);

		List<User> users = userDao.findAll();
		Assert.assertEquals(2, users.size());
		User user1 = users.get(0);

		Assert.assertEquals("Ajay", user1.getFirstName());
		Assert.assertEquals("Kumar", user1.getMiddleName());
		Assert.assertEquals("Malepati", user1.getLastName());
		Assert.assertEquals(Gender.M, user1.getGender());
		Assert.assertEquals(new Integer(78200), user1.getZip());
		Assert.assertEquals(new Short("33"), user1.getAge());
		Assert.assertEquals(new Long(2101110000L), user1.getPhone());

		User user2 = users.get(1);
		Assert.assertEquals("Ajay1", user2.getFirstName());
		Assert.assertEquals("Kumar1", user2.getMiddleName());
		Assert.assertEquals("Malepati1", user2.getLastName());
		Assert.assertEquals(Gender.M, user2.getGender());
		Assert.assertEquals(new Integer(78240), user2.getZip());
		Assert.assertEquals(new Short("33"), user2.getAge());
		Assert.assertEquals(new Long(2101111111L), user2.getPhone());

		return;
	}

	@Test
	public void testUpdate() {
		List<User> users = userDao.findAll();
		Assert.assertEquals(1, users.size());
		User user1 = users.get(0);

		Assert.assertEquals("Ajay", user1.getFirstName());
		Assert.assertEquals("Kumar", user1.getMiddleName());
		Assert.assertEquals("Malepati", user1.getLastName());
		Assert.assertEquals(Gender.M, user1.getGender());
		Assert.assertEquals(new Integer(78200), user1.getZip());
		Assert.assertEquals(new Short("33"), user1.getAge());
		Assert.assertEquals(new Long(2101110000L), user1.getPhone());

		user1.setFirstName("Ajay2");
		user1.setMiddleName("Kumar2");
		user1.setLastName("Malepati2");

		userDao.update(user1);

		User updatedUser = userDao.findById(user1.getId());

		Assert.assertEquals("Ajay2", updatedUser.getFirstName());
		Assert.assertEquals("Kumar2", updatedUser.getMiddleName());
		Assert.assertEquals("Malepati2", updatedUser.getLastName());
		Assert.assertEquals(Gender.M, updatedUser.getGender());
		Assert.assertEquals(new Integer(78200), updatedUser.getZip());
		Assert.assertEquals(new Short("33"), updatedUser.getAge());
		Assert.assertEquals(new Long(2101110000L), updatedUser.getPhone());

		return;
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void testAgeConstraint() {
		User user = new User();
		user.setFirstName("Ajay1");
		user.setMiddleName("Kumar1");
		user.setLastName("Malepati1");
		user.setGender(Gender.M);
		user.setPhone(2101111111L);
		user.setZip(78240);
		user.setAge(new Short("-33"));
		
		userDao.create(user);
	}
	
	@Test(expected = PersistenceException.class)
	public void testNotNullables() {
		User user = new User();
		user.setMiddleName("Kumar1");
		user.setZip(78240);
		user.setAge(new Short("33"));
		
		userDao.create(user);
	}
}
