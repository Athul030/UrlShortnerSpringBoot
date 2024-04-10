package com.athul.urlsShort.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UrlResponseDTO {

    private String originalUrl;
    private String shortLink;
    private String expirationDate;
}
