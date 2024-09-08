package com.aurionpro.model.dto;

import org.springframework.web.multipart.MultipartFile;


import lombok.Data;

@Data
public class DocumentDto {
    private String name;
    private MultipartFile file;
    private Long customerId;
}