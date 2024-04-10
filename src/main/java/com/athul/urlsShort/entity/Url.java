package com.athul.urlsShort.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.A;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Url {


    @Id
    @GeneratedValue
    private Long id;

    @Lob
    private String originalUrl;

    private String shortLink;

    private LocalDateTime creationDate;
    private LocalDateTime expirationDate;


}
