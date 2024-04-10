package com.athul.urlsShort.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UrlErrorResponseDTO {
    private String status;
    private String error;
}
