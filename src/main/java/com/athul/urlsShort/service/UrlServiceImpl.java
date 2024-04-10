package com.athul.urlsShort.service;

import com.athul.urlsShort.dto.UrlDTO;
import com.athul.urlsShort.Repo.UrlRepository;
import com.athul.urlsShort.dto.UrlResponseDTO;
import com.athul.urlsShort.entity.Url;
import com.google.common.hash.Hashing;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UrlServiceImpl implements UrlService{

    private final UrlRepository urlRepository;
    @Override
    public UrlResponseDTO generateShortLink(UrlDTO urlDTO) {
            String encodedUrl = encodeUrl(urlDTO.getOriginalUrl());
            Url urlToPersist = new Url();
            urlToPersist.setCreationDate(LocalDateTime.now());
            urlToPersist.setShortLink(encodedUrl);
            Url urlToReturn  = urlRepository.save(urlToPersist);

            if(urlToReturn!=null){
                UrlResponseDTO urlResponseDTO = new UrlResponseDTO();
                urlResponseDTO.setOriginalUrl(urlToReturn.getOriginalUrl());
                urlResponseDTO.setShortLink(urlToReturn.getOriginalUrl());
                return urlResponseDTO;
            }else{
                return null;
            }

    }

    private String encodeUrl(String url) {
        String encodedUrl = "";
        LocalDateTime time = LocalDateTime.now();
        encodedUrl = Hashing.murmur3_32_fixed().hashString(url.concat(time.toString()), StandardCharsets.UTF_8).toString();
        return encodedUrl;
    }



    @Override
    public Url getEncodedUrl(String url) {
        return urlRepository.findByShortLink(url);
    }

    @Override
    public void deleteShortLink(Url url) {
        urlRepository.delete(url);
    }
}
