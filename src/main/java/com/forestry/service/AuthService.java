package com.forestry.service;

import com.forestry.bean.User;
import com.forestry.dao.AuthDao;
import com.forestry.util.RedisUtil;
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
    @Autowired
    RedisUtil redisUtil;

    @Override
    public UserDetails loadUserByUsername(String usernameAndLoginType) throws UsernameNotFoundException {
        String args[]  = usernameAndLoginType.split("-@_");
        String username = args[0];
        String loginType = args[1];

        User user = authDao.loadUserByUsername(username);

        if(user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }

        if(loginType.equals("code")) {
            user.setPassword(user.getCode());
        }
        return user;
    }

    public int regUser(String username, String password) {
        if(isReg(username)) {
            return -1;
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodePwd = encoder.encode(password);

        return authDao.regUser(username, encodePwd);
    }

    public int checkCode(String username, String code) {
        Object validCode = redisUtil.get(username);
        if(validCode == null) {
            return 1;
        }
        else if(code.equals(validCode)) {
            return 0;
        }
        return 2;
    }

    public int setCode4Login(String phone, String code) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodeCode = encoder.encode(code);

        return authDao.updateCode(phone, encodeCode);
    }

    public int updateUser(String username, String password) {
        if(!isReg(username)) {
            return -1;
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodePwd = encoder.encode(password);

        return authDao.updateUser(username, encodePwd);
    }

    public boolean isReg(String username) {
        if(authDao.loadUserByUsername(username) != null) {
            return true;
        }
        return false;
    }

    public int addRole(String username, int roleId) {
        User user = authDao.loadUserByUsername(username);

        return authDao.addRole(user.getId(), roleId);
    }
}
