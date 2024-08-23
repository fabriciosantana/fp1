package br.com.idp.cc.fp1.idpbook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.idp.cc.fp1.idpbook.model.Material;

public interface MaterialRepository extends JpaRepository<Material, Long> {
    List<Material> findByTitleContainingOrDescriptionContaining(String title, String description);
}