package com.ig.training.services.implementation;

import com.ig.training.base.response.MessageResponse;
import com.ig.training.base.response.ObjectResponse;
import com.ig.training.dto.LogInReqDto;
import com.ig.training.dto.LoginDto;
import com.ig.training.dto.UserDto;
import com.ig.training.exceptions.ApiErrorException;
import com.ig.training.model.Users;
import com.ig.training.repositories.UserRepository;
import com.ig.training.security.payload.UserPrinciple;
import com.ig.training.services.UserService;
import com.ig.training.utilities.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwt;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new UserPrinciple();
    }

    @Override
    public ObjectResponse<LoginDto> login(LogInReqDto loginDto) {
        final var user = userRepository.findByUsernameIgnoreCaseOrEmailIgnoreCaseAndStatusTrue(loginDto.username(), loginDto.username()).orElseThrow(() ->
                new ApiErrorException(401, "Invalid username or password"));
        if (!passwordEncoder.matches(loginDto.password(), user.getPassword())) {
            throw new ApiErrorException(401, "Invalid username or password");
        }
        final var userAuthorities = user.getRoles().stream().map(r -> new SimpleGrantedAuthority(r.getRoleName())).toList();
        final var userDetail = new UserPrinciple(
                user.getId(), user.getUsername(), user.getPassword(), userAuthorities
        );
        final var token = jwt.generateToken(userDetail);
        return new ObjectResponse<>(new LoginDto(token));
    }

    @Override
    public MessageResponse registerUser(Users req) {
        req.setPassword(passwordEncoder.encode(req.getPassword()));
        userRepository.save(req);
        return new MessageResponse();
    }

    @Override
    public MessageResponse updateUser(Long id, UserDto req) {
        final var user = userRepository.findByIdAndStatusTrue(id).orElseThrow(() -> new ApiErrorException(404, "User not found"));
        var userUpdated = req.updateUser(user);
        userRepository.save(userUpdated);
        return new MessageResponse();
    }
}
