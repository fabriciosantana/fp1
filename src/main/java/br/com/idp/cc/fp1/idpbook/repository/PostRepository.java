package br.com.idp.cc.fp1.idpbook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.idp.cc.fp1.idpbook.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByTitleContainingOrDescriptionContaining(String title, String description);
}