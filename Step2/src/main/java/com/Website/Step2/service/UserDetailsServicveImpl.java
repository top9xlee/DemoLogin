package com.Website.Step2.service;

import com.Website.Step2.model.Role;
import com.Website.Step2.model.User;

import com.Website.Step2.repository.roleRepository.RoleRepositoryCustom;
import com.Website.Step2.repository.userRepository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Collections.singletonList;

@Service
@AllArgsConstructor
public class UserDetailsServicveImpl implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepositoryCustom roleRepositoryCustom;
    @Override
    @Transactional(readOnly = true)

    public UserDetails loadUserByUsername(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("No user Found with username : " + username));
        List<String> listRole = roleRepositoryCustom.getListRoleByUserId(user.getUserId());
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : listRole) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                user.isEnabled(), true, true, true, authorities);
    }

}