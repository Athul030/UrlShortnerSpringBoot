package com.athul.urlsShort.service;

import com.athul.urlsShort.dto.UrlDTO;
import com.athul.urlsShort.dto.UrlResponseDTO;
import com.athul.urlsShort.entity.Url;

public interface UrlService {

     UrlResponseDTO generateShortLink(UrlDTO urlDTO);
     Url getEncodedUrl(String url);
     void deleteShortLink(Url url);
}
