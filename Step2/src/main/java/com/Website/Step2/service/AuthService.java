package com.Website.Step2.service;

import com.Website.Step2.repository.roleRepository.RoleRepositoryCustom;
import com.Website.Step2.repository.userRepository.UserRepository;
import com.Website.Step2.repository.verificationTokenRepository.VerificationTokenRepository;
import com.Website.Step2.security.JwtProvider;
import com.Website.Step2.dto.AuthenticationResponse;
import com.Website.Step2.dto.LoginRequest;
import com.Website.Step2.dto.RegisterRequest;
import com.Website.Step2.model.NotificationEmail;
import com.Website.Step2.model.User;
import com.Website.Step2.model.VerificationToken;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.Website.Step2.exception.SpringException;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.time.Instant.now;

import static com.Website.Step2.util.Constants.ACTIVATION_EMAIL;


@Service
@AllArgsConstructor
@Slf4j
public class AuthService {
    private  final JwtProvider jwtProvider;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final VerificationTokenRepository verificationTokenRepository;
    private final MailContentBuilder mailContentBuilder;
    private final MailService mailService;
    private final AuthenticationManager authenticationManager;
    private final RoleRepositoryCustom roleRepositoryCustom;

    @Transactional
    public void signup(RegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(encodePassword(registerRequest.getPassword()));
        user.setFullName(registerRequest.getFullname());
        user.setCreated(now());
        user.setEnabled(true);
        userRepository.save(user);
        String token = generateVerificationToken(user);
        String message = mailContentBuilder.build("Thank you for signing up to Spring Reddit, please click on the below url to activate your account : "
                + ACTIVATION_EMAIL + "/" + token);
        mailService.sendMail(new NotificationEmail("Please Activate your account", user.getEmail(), message));

    }

    private String generateVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        verificationTokenRepository.save(verificationToken);
        return token;
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    public void verifyAccount(String token) {
        Optional<VerificationToken> verificationTokenOptional = verificationTokenRepository.findByToken(token);
        verificationTokenOptional.orElseThrow(() -> new SpringException("Invalid Token"));
        fetchUserAndEnable(verificationTokenOptional.get());
    }

    @Transactional
    private void fetchUserAndEnable(VerificationToken verificationToken) {
        String username = verificationToken.getUser().getUsername();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new SpringException("User Not Found with id - " + username));
        user.setEnabled(true);
        userRepository.save(user);
    }

    public AuthenticationResponse login(LoginRequest loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String authenticationToken = jwtProvider.generateToken(authenticate);
        Optional<User> user = Optional.ofNullable(userRepository.findByUsername(loginRequest.getUsername()).orElseThrow(() -> new SpringException("User not Found")));
        List<String> listRole = roleRepositoryCustom.getListRoleByUserId(user.get().getUserId());
        return new AuthenticationResponse(authenticationToken, loginRequest.getUsername(),listRole);
    }
}

