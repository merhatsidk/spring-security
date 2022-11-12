package edu.miu.cs545.restApi.service.impl;

import edu.miu.cs545.restApi.domain.User;
import edu.miu.cs545.restApi.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =  userRepo.findByUserName(username)
                .isPresent() ? userRepo.findByUserName(username).get()
                :userRepo.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found " + username));
        return new MyUserDetails(user);
    }
}
