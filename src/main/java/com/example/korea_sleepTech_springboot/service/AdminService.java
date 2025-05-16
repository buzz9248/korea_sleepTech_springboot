package com.example.korea_sleepTech_springboot.service;

import com.example.korea_sleepTech_springboot.dto.admin.request.PutAuthorityReqDto;
import com.example.korea_sleepTech_springboot.dto.admin.response.DemoteToAdminRespDto;
import com.example.korea_sleepTech_springboot.dto.admin.response.PromoteToAdminRespDto;
import com.example.korea_sleepTech_springboot.dto.response.ResponseDto;

public interface AdminService {
    ResponseDto<PromoteToAdminRespDto> promoteUserToAdmin(PutAuthorityReqDto dto);

    ResponseDto<DemoteToAdminRespDto> demoteUserFromAdmin(PutAuthorityReqDto dto);
}
