package tsoy.sergey.springCrudSecurity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tsoy.sergey.springCrudSecurity.dao.UserDao;
import tsoy.sergey.springCrudSecurity.models.User;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    private final UserDao userDao;

    @Autowired
    public UserDetailsServiceImp(UserDao userDao) {
        this.userDao = userDao;
    }

    // «Пользователь» – это просто Object. В большинстве случаев он может быть приведен к классу UserDetails.
    // Для создания UserDetails используется интерфейс UserDetailsService, с единственным методом:
    @Transactional
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDao.getByName(s);
        System.out.println(user);
        if (user == null) {
            throw new UsernameNotFoundException("User is unknown");
        } return user;
    }

//    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
//        User user = userDao.getByName(name);
//
//        if (user == null) {
//            throw new UsernameNotFoundException(String.format("User %1$s not found", name));
//        }
//
//        List<GrantedAuthority> authorities =
//                user.getAuthorities()
//                        .stream()
//                        .map(e -> new SimpleGrantedAuthority(e.getAuthority()))
//                        .collect(Collectors.toList());
//
//        return org.springframework.security.core.userdetails.User.withDefaultPasswordEncoder()
//                .username(user.getUsername())
//                .password(user.getPassword())
//                .authorities(authorities)
//                .build();
//    }


}
