//package cn.mimiron.uaa.security;
//
//import cn.mimiron.uaa.mapper.UserMapper;
//import cn.mimiron.uaa.model.User;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.Locale;
//import java.util.stream.Collectors;
//
///**
// * Authenticate a user from the database.
// *
// * @author zhangxd
// */
//@Component("userDetailsService")
//public class DomainUserDetailsService implements UserDetailsService {
//
//    private final Logger log = LoggerFactory.getLogger(DomainUserDetailsService.class);
//
//    private final UserMapper userMapper;
//
//    public DomainUserDetailsService(UserMapper userMapper) {
//        this.userMapper = userMapper;
//    }
//
//    @Override
//    @Transactional
//    public UserDetails loadUserByUsername(final String login) {
//        log.debug("Authenticating {}", login);
//        String lowercaseLogin = login.toLowerCase(Locale.ENGLISH);
//        User user = userMapper.selectOneWithAuthorityByLoginOrEmail(lowercaseLogin);
//
//        if (user == null) {
//            throw new UsernameNotFoundException("User " + lowercaseLogin + " was not found in the database");
//        }
//        if (!user.getActivated()) {
//            throw new UserNotActivatedException("User " + lowercaseLogin + " was not activated");
//        }
//
//        List<GrantedAuthority> grantedAuthorities = user.getAuthorities().stream()
//            .map(authority -> new SimpleGrantedAuthority(authority.getName()))
//            .collect(Collectors.toList());
//
//        return new org.springframework.security.core.userdetails.User(user.getLogin(),
//            user.getPassword(),
//            grantedAuthorities);
//
//    }
//}
