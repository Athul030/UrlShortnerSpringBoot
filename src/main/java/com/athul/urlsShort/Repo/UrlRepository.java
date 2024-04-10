package com.athul.urlsShort.Repo;

import com.athul.urlsShort.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<Url,Long> {

    Url findByShortLink(String shortLink);
}
