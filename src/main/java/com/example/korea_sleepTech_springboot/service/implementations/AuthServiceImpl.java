package com.example.korea_sleepTech_springboot.service.implementations;

import com.example.korea_sleepTech_springboot.common.ResponseMessage;
import com.example.korea_sleepTech_springboot.dto.response.ResponseDto;
import com.example.korea_sleepTech_springboot.dto.user.request.UserSignInReqDto;
import com.example.korea_sleepTech_springboot.dto.user.request.UserSignUpReqDto;
import com.example.korea_sleepTech_springboot.dto.user.response.UserSignInRespDto;
import com.example.korea_sleepTech_springboot.dto.user.response.UserSignUpRespDto;
import com.example.korea_sleepTech_springboot.entity.Role;
import com.example.korea_sleepTech_springboot.entity.User;
import com.example.korea_sleepTech_springboot.provider.JwtProvider;
import com.example.korea_sleepTech_springboot.repository.RoleRepository;
import com.example.korea_sleepTech_springboot.repository.UserRepository;
import com.example.korea_sleepTech_springboot.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtProvider jwtProvider;

    private final RoleRepository roleRepository;

    @Override
    public ResponseDto<UserSignUpRespDto> signup(UserSignUpReqDto dto) {
        String email = dto.getEmail();
        String password = dto.getPassword();
        String confirmPassword = dto.getConfirmPassword();

        UserSignUpRespDto data = null;
        User user = null;

            // 패스워드 일치 여부 확인
            if (!password.equals(confirmPassword)) {
                // 일치하지 않은 경우
                return ResponseDto.setFailed(ResponseMessage.NOT_MATCH_PASSWORD);
            }

            // 중복되는 이메일 검증
            if (userRepository.existsByEmail(email)) {
                // 중복되는 경우 (사용할 수 X)
                return ResponseDto.setFailed(ResponseMessage.EXIST_DATA);
            }

            // 패스워드 암호화
            String encodePassword = bCryptPasswordEncoder.encode(password);

            // 권한 정보 확인
            Role userRole = roleRepository.findByRoleName("USER")
                    .orElseGet(() -> roleRepository.save(Role.builder().roleName("USER").build()));

            Set<Role> roleSet = new HashSet<>();
            roleSet.add(userRole);

            user = User.builder()
                    .email(email)
                    .password(encodePassword)
                    .createdAt(LocalDateTime.now())
                    .roles(roleSet)
                    .build();

            userRepository.save(user);

            data = new UserSignUpRespDto(user);

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<UserSignInRespDto> login(UserSignInReqDto dto) {
        String email = dto.getEmail();
        String password = dto.getPassword();

        UserSignInRespDto data = null;
        User user = null;

        int exprTime = jwtProvider.getExpiration();

        user = userRepository.findByEmail(email)
                .orElse(null);

        if (user == null) {
            return ResponseDto.setFailed(ResponseMessage.NOT_EXISTS_USER);
        }

        if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            // .matches(평문 비밀번호, 암호화된 비밀번호)
            // : 평문 비밀번호(실제 비밀번호)와 암호화된 비밀번호를 비교하여 일치 여부 반환(boolean)
            return ResponseDto.setFailed(ResponseMessage.NOT_MATCH_PASSWORD);
        }

        // 사용자 정보의 권한 정보를 호출
        Set<String> roles = user.getRoles().stream()
                .map(Role::getRoleName)
                .collect(Collectors.toSet());

        String token = jwtProvider.generateJwtToken(email, roles); // username에 email 저장

        data = new UserSignInRespDto(token, user, exprTime);

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
}
















