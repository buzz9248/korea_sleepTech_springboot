package com.example.korea_sleepTech_springboot.controller;

import com.example.korea_sleepTech_springboot.common.constants.ApiMappingPattern;
import com.example.korea_sleepTech_springboot.dto.admin.request.PutAuthorityReqDto;
import com.example.korea_sleepTech_springboot.dto.admin.response.DemoteToAdminRespDto;
import com.example.korea_sleepTech_springboot.dto.admin.response.PromoteToAdminRespDto;
import com.example.korea_sleepTech_springboot.dto.response.ResponseDto;
import com.example.korea_sleepTech_springboot.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiMappingPattern.ADMIN_API)
public class AdminController {

    private final AdminService adminService;

    private static final String PUT_AUTHORITY_TO_ADMIN = "/promote";
    private static final String PUT_AUTHORITY_DEMOTE = "/demote";

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(PUT_AUTHORITY_TO_ADMIN)
    public ResponseEntity<ResponseDto<PromoteToAdminRespDto>> promoteUserToAdmin(@RequestBody PutAuthorityReqDto dto) {
        ResponseDto<PromoteToAdminRespDto> response = adminService.promoteUserToAdmin(dto);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(PUT_AUTHORITY_DEMOTE)
    public ResponseEntity<ResponseDto<DemoteToAdminRespDto>> demoteUserFromAdmin(@RequestBody PutAuthorityReqDto dto) {

        ResponseDto<DemoteToAdminRespDto> response = adminService.demoteUserFromAdmin(dto);
        return ResponseEntity.ok(response);


    }

}
