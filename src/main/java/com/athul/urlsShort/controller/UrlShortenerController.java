package com.athul.urlsShort.controller;

import com.athul.urlsShort.dto.UrlDTO;
import com.athul.urlsShort.dto.UrlErrorResponseDTO;
import com.athul.urlsShort.dto.UrlResponseDTO;
import com.athul.urlsShort.entity.Url;
import com.athul.urlsShort.service.UrlService;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class UrlShortenerController {

    private final UrlService urlService;

    @PostMapping("/generate")
    public ResponseEntity<?> generateShortLink(@RequestBody UrlDTO urlDTO){
        if(urlService.isUrlValid(urlDTO.getOriginalUrl())) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please Enter valid url");
        UrlResponseDTO urlResponseDTO = urlService.generateShortLink(urlDTO);
        if(urlResponseDTO!=null){
            return new ResponseEntity<>(urlResponseDTO, HttpStatus.OK);
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("There was an error processing your request. Please try again ");
        }
    }

    @GetMapping("/{shortLink}")
    public ResponseEntity<?> redirectToOriginal(@PathVariable String shortLink, HttpServletResponse response) throws IOException {
        if(StringUtils.isEmpty(shortLink)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid URL");
        }
        Url urlToReturn = urlService.getOriginalUrl(shortLink);
        if(urlToReturn == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Given short link is not available");

        //for redirection
        response.sendRedirect(urlToReturn.getOriginalUrl());
        return null;
    }




}
