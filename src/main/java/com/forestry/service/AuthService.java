package com.forestry.service;

import com.forestry.bean.User;
import com.forestry.dao.AuthDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthService implements UserDetailsService {
    @Autowired
    AuthDao authDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = authDao.loadUserByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }

        return user;
    }

    public int regUser(String username, String password) {
        if (authDao.loadUserByUsername(username) != null) {
            return -1;
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode(password);

        return authDao.regUser(username, encode);
    }
}
