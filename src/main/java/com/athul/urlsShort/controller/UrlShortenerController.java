package com.athul.urlsShort.controller;

import com.athul.urlsShort.dto.UrlDTO;
import com.athul.urlsShort.dto.UrlErrorResponseDTO;
import com.athul.urlsShort.dto.UrlResponseDTO;
import com.athul.urlsShort.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UrlShortenerController {

    private final UrlService urlService;

    @PostMapping("/generate")
    public ResponseEntity<?> generateShortLink(@RequestBody UrlDTO urlDTO){
        UrlResponseDTO urlResponseDTO = urlService.generateShortLink(urlDTO);
        if(urlResponseDTO!=null){
            return new ResponseEntity<>(urlResponseDTO, HttpStatus.OK);
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("There was an error processing your request. Please try again ");

        }
    }



}
