package egen.sampleapp.repo;

import java.util.List;

import egen.sampleapp.domain.User;

public interface UserDao
{
    public User findById(Long id);
    
    public User update(User user);

    public List<User> findAll();

    public Long create(User user);
    
}
