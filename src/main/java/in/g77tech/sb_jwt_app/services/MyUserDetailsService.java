package in.g77tech.sb_jwt_app.services;

import java.util.Collections;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import in.g77tech.sb_jwt_app.entity.UserEntity;
import in.g77tech.sb_jwt_app.repository.UserRepo;

@Service
public class MyUserDetailsService implements UserDetailsService{

    @Autowired
    private UserRepo userRepo;
    

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       
        UserEntity entity = userRepo.findByUname(username);
        // UserEntity entity = new UserEntity();
        // BeanUtils.copyProperties(byUname, entity);
        return new User(entity.getUname(), entity.getUpwd(), Collections.emptyList());
    }

    public boolean saveUser(UserEntity user) {
        return userRepo.save(user)!= null;
    }

}
