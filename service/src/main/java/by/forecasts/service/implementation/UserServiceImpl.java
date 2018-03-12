package by.forecasts.service.implementation;

import by.forecasts.entities.User;
import by.forecasts.repositories.UserRepository;
import by.forecasts.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User loggedUser = findByLogin(login);
        if (loggedUser == null) {
            throw new UsernameNotFoundException("User doesn't exist!");
        }
        List<GrantedAuthority> userRoles = new ArrayList<>();
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(loggedUser.getUserState().getUserState());
        userRoles.add(simpleGrantedAuthority);

        return new org.springframework.security.core.userdetails.User(loggedUser.getLogin(), loggedUser.getPassword(), userRoles);
    }


}
