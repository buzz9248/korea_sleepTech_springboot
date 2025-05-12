package com.example.korea_sleepTech_springboot.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/*
* === WebSecurityConfig ===
* : Spring Security를 통해서 웹 애플리케이션에 보안을 구성 (보안 환경설정)
* - JWT 필터를 적용하여 인증 처리, CORS 및 CSRF 설정을 비활성화
*       >> 서버 간의 통신을 원활하게 처리
* */

@Configuration
// : 해당 클래스가 Spring의 설정 클래스로 사용됨을 명시 (Spring이 관리하는 객체를 생성하는 데 사용)
@EnableWebSecurity
// : Spring Security의 웹 보완을 활성화
@RequiredArgsConstructor
public class WebSecurityConfig {

}
