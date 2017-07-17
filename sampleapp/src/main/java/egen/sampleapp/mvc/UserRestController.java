package egen.sampleapp.mvc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import egen.sampleapp.domain.User;
import egen.sampleapp.repo.UserDao;

@Controller
@RequestMapping("/rest/users")
public class UserRestController {
	@Autowired
	private UserDao userDao;

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<User>> listAlUsers() {
		List<User> users = userDao.findAll();
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<User> lookupUserById(@PathVariable("id") Long id) {
		if (null != id) {
			User existingUser = userDao.findById(id);
			if (null != existingUser) {
				return new ResponseEntity<User>(existingUser, HttpStatus.OK);
			} else {
				return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Long> createUser(@RequestBody User user) {
		if (null != user && user.getId() == null) {
			Long newId = userDao.create(user);
			return new ResponseEntity<Long>(newId, HttpStatus.OK);
		} else {
			return new ResponseEntity<Long>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	public ResponseEntity<User> updateUser(@RequestBody User user) {
		if (null != user && null != user.getId()) {
			User existingUser = userDao.findById(user.getId());
			if (null != existingUser) {
				User updatedUser = userDao.update(user);
				return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
			} else {
				return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}
	}
}
