package com.example.korea_sleepTech_springboot.controller;

import com.example.korea_sleepTech_springboot.common.constants.ApiMappingPattern;
import com.example.korea_sleepTech_springboot.dto.auth.PasswordResetReqDto;
import com.example.korea_sleepTech_springboot.dto.auth.SendMailReqDto;
import com.example.korea_sleepTech_springboot.dto.response.ResponseDto;
import com.example.korea_sleepTech_springboot.dto.user.request.UserSignInReqDto;
import com.example.korea_sleepTech_springboot.dto.user.request.UserSignUpReqDto;
import com.example.korea_sleepTech_springboot.dto.user.response.UserSignInRespDto;
import com.example.korea_sleepTech_springboot.dto.user.response.UserSignUpRespDto;
import com.example.korea_sleepTech_springboot.service.AuthService;
import com.example.korea_sleepTech_springboot.service.MailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(ApiMappingPattern.AUTH_API)
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final MailService mailService;

    // === AuthController mapping pattern === //
    private static final String POST_SIGN_UP = "/signup";
    private static final String POST_SIGN_IN = "/login";

    // 1) 회원가입
    // - HTTP 메서드: POST
    // - URI 경로: /signup
    // @Params: UserSignUpReqDto
    // @Return: UserSignUpRespDto
    @PostMapping(POST_SIGN_UP)
    public ResponseEntity<ResponseDto<UserSignUpRespDto>> signup(@Valid @RequestBody UserSignUpReqDto dto) {
        ResponseDto<UserSignUpRespDto> response = authService.signup(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 2) 로그인
    // - HTTP 메서드:
    // - URI 경로: /signin
    // @Params: UserSignInReqDto
    // @Return: UserSignInRespDto

    // GET vs. POST
    // : POST 사용을 권장
    // - 로그인 과정에서 사용자 이름과 비밀번호와 같은 민감한 데이터를 서버로 전송하기 때문
    // cf) GET 요청은 URL에 데이터가 노출(데이터 조회 시 데이터 구분값(PK) 요청 시 사용)

    @PostMapping(POST_SIGN_IN)
    public ResponseEntity<ResponseDto<UserSignInRespDto>> login(@Valid @RequestBody UserSignInReqDto dto) {
        ResponseDto<UserSignInRespDto> response = authService.login(dto);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 3) 이메일 전송
    @PostMapping("/send-email")
    public Mono<ResponseEntity<String>> sendEmail(@Valid @RequestBody SendMailReqDto dto) {
        return mailService.sendSimpleMessage(dto.getEmail());
    }

    // 4) 이메일 인증
    @GetMapping("/verify")
    public Mono<ResponseEntity<String>> verifyEmail(@RequestParam String token) {
        return mailService.verifyEmail(token);
    }

    // 비밀번호 재설정
    @PostMapping("/reset-password")
    public Mono<ResponseEntity<String>> resetPassword(@Valid @RequestBody PasswordResetReqDto dto) {
        return authService.resetPassword(dto);
    }


}
