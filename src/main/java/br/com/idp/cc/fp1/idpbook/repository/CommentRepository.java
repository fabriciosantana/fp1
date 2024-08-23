package br.com.idp.cc.fp1.idpbook.repository;

import br.com.idp.cc.fp1.idpbook.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
