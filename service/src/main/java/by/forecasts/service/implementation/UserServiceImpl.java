package by.forecasts.service.implementation;

import by.forecasts.dto.UserDetailDto;
import by.forecasts.entities.User;
import by.forecasts.repositories.UserRepository;
import by.forecasts.repositories.UserStateRepository;
import by.forecasts.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserStateRepository userStateRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserStateRepository userStateRepository) {
        this.userRepository = userRepository;
        this.userStateRepository = userStateRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void saveUser(User user) {
        user.setUserState(userStateRepository.getOne(1L));
        userRepository.save(user);
    }

    @Override
    public UserDetailDto loadUserByUsername(String login) throws UsernameNotFoundException {
        User loggedUser = userRepository.findByLogin(login);
        if (loggedUser == null) {
            throw new UsernameNotFoundException("User doesn't exist!");
        }
        List<GrantedAuthority> userRoles = new ArrayList<>();
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(loggedUser.getUserState().getUserState());
        userRoles.add(simpleGrantedAuthority);
        UserDetailDto userDetailDto = new UserDetailDto(loggedUser.getLogin(), loggedUser.getPassword(), userRoles);
        userDetailDto.setId(loggedUser.getId());
        return userDetailDto;
    }
}
