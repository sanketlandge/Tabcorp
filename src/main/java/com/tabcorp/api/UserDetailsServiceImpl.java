package com.tabcorp.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tabcorp.domain.ApiUser;
import com.tabcorp.repository.ApiUserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
    private ApiUserRepository apiUserRepository;
    
    public UserDetailsServiceImpl(ApiUserRepository apiUserRepository) {
        this.apiUserRepository = apiUserRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApiUser apiUser = apiUserRepository.findByUsername(username);
        if (apiUser == null) {
            throw new UsernameNotFoundException(username);
        }

        return new User(apiUser.getUsername(), apiUser.getPassword(), getGrantedAuthorities());
    }
    
    private List<GrantedAuthority> getGrantedAuthorities() {
    	List<GrantedAuthority> authorities = new ArrayList<>();

    	authorities.add(new SimpleGrantedAuthority("ADMIN"));

    	return authorities;
    }
    
}