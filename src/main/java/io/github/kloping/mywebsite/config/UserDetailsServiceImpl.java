package io.github.kloping.mywebsite.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.github.kloping.mywebsite.entitys.database.UserTemp;
import io.github.kloping.mywebsite.mapper.UserTempMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author github-kloping
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    public static final String EMAIL_TYPE = "email";
    public static final String GITHUB_TYPE = "github";

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserTempMapper mapper;

    public static final String NOT_FOUND_USER = "用户名不存在";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserTemp temp = null;
        QueryWrapper<UserTemp> queryWrapper = null;
        queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("eid", username);
        temp = mapper.selectOne(queryWrapper);
        if (temp == null) {
            queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("qid", username);
            temp = mapper.selectOne(queryWrapper);
            if (temp == null) {
                return null;
            }
        }
        if (EMAIL_TYPE.equals(temp.getType())) {
            return new User(temp.getEid(), temp.getPwd().toString(),
                    AuthorityUtils.commaSeparatedStringToAuthorityList("user"));
        } else return null;
    }
}