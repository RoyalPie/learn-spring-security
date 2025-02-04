package com.example.learn_security.service;

import com.example.learn_security.entity.UserInfo;
import com.example.learn_security.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserInfoDetailService implements UserDetailsService {
    private final UserInfoRepository userInfoRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userInfo = userInfoRepository.findByName(username);
        if (userInfo.isPresent()) {
            return userInfo.get();
        }
        throw new UsernameNotFoundException(username);
    }
}