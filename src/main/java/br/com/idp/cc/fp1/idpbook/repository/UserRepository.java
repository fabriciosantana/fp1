package br.com.idp.cc.fp1.idpbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.idp.cc.fp1.idpbook.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}