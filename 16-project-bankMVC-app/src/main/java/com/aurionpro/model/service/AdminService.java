package com.aurionpro.model.service;

import com.aurionpro.model.dto.AdminDataDto;
import com.aurionpro.model.dto.AdminDto;
import com.aurionpro.model.dto.PageResponse;

public interface AdminService {
	
	AdminDto addNewAdmin(AdminDataDto customerDataDto);

	AdminDto getAdminById(Long adminId);
	
	PageResponse<AdminDto> getAllAdmins(int pageNo, int pageSize, String sortBy, String sortDir);
	
	AdminDto updateAdminById(Long adminId, AdminDataDto customerDataDto);

	String deleteAdminById(Long adminId);
}
