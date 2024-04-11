package com.athul.urlsShort.service;

import com.athul.urlsShort.dto.UrlDTO;
import com.athul.urlsShort.Repo.UrlRepository;
import com.athul.urlsShort.dto.UrlResponseDTO;
import com.athul.urlsShort.entity.Url;
import com.google.common.hash.Hashing;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UrlServiceImpl implements UrlService{

    private final UrlRepository urlRepository;
    @Override
    public UrlResponseDTO generateShortLink(UrlDTO urlDTO) {
            String shortUrlCode = createShortLink(urlDTO.getOriginalUrl());

            Url urlToPersist = new Url();
            urlToPersist.setCreationDate(LocalDateTime.now());
            urlToPersist.setShortLink(shortUrlCode);
            urlToPersist.setOriginalUrl(urlDTO.getOriginalUrl());
            urlToPersist.setExpirationDate(LocalDateTime.now().plusHours(24));
            Url urlToReturn  = urlRepository.save(urlToPersist);

            if(urlToReturn!=null){
                UrlResponseDTO urlResponseDTO = new UrlResponseDTO();
                urlResponseDTO.setExpirationDate(urlToReturn.getExpirationDate().toString());
                urlResponseDTO.setOriginalUrl(urlToReturn.getOriginalUrl());
                urlResponseDTO.setShortLink(urlToReturn.getShortLink());
                return urlResponseDTO;
            }else{
                return null;
            }

    }

    public boolean isUrlValid(String url){
        boolean result = UrlValidator.getInstance().isValid(url);
        return result;
    }

    private String createShortLink(String url) {
        String shortUrlCode = "";
        LocalDateTime time = LocalDateTime.now();
        shortUrlCode = Hashing.murmur3_32_fixed().hashString(url.concat(time.toString()), StandardCharsets.UTF_8).toString();
        return shortUrlCode;
    }



    @Override
    public Url getOriginalUrl(String url) {
        return urlRepository.findByShortLink(url);
    }

    @Override
    public void deleteShortLink(Url url) {
        urlRepository.delete(url);
    }
}
