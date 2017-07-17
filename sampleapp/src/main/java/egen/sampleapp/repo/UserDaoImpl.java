package egen.sampleapp.repo;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import egen.sampleapp.domain.User;

@Repository
@Transactional
public class UserDaoImpl implements UserDao
{
    private static final String FIND_ALL_JQL = "Select u from User as u order by u.id asc";
    
	@Autowired
    private EntityManager em;

    public User findById(Long id)
    {
        return em.find(User.class, id);
    }
    
    public List<User> findAll()
    {
		return em.createQuery(FIND_ALL_JQL).getResultList();
    }

    public Long create(User user)
    {
        em.persist(user);
        return user.getId();
    }
    
    public User update(User user)
    {
        return em.merge(user);
    }
}
