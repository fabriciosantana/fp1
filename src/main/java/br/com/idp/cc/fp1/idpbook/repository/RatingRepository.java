package br.com.idp.cc.fp1.idpbook.repository;

import br.com.idp.cc.fp1.idpbook.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Long> {
}
