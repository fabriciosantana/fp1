package br.com.idp.cc.fp1.idpbook.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.idp.cc.fp1.idpbook.model.Material;
import br.com.idp.cc.fp1.idpbook.model.User;
import br.com.idp.cc.fp1.idpbook.repository.MaterialRepository;

@Service
public class MaterialService {

    private final MaterialRepository materialRepository;

    public MaterialService(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    public List<Material> findAllMaterials() {
        return materialRepository.findAll();
    }

    public Optional<Material> findMaterialById(Long id) {
        return materialRepository.findById(id);
    }

    public Material saveMaterial(Material material, User user) {
        material.setUploadedBy(user);
        return materialRepository.save(material);
    }

    public Material updateMaterial(Long id, Material updatedMaterial) {
        Optional<Material> materialOptional = materialRepository.findById(id);

        if (materialOptional.isPresent()) {
            Material existingMaterial = materialOptional.get();
            existingMaterial.setTitle(updatedMaterial.getTitle());
            existingMaterial.setDescription(updatedMaterial.getDescription());
            existingMaterial.setFileUrl(updatedMaterial.getFileUrl());
            existingMaterial.setUploadDate(updatedMaterial.getUploadDate());
            // Adicionar outras propriedades conforme necessário
            return materialRepository.save(existingMaterial);
        } else {
            throw new RuntimeException("Material not found with id: " + id);
        }
    }

    public void deleteMaterial(Long id) {
        materialRepository.deleteById(id);
    }
}
