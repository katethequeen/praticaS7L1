package com.example.praticaS7L1.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.praticaS7L1.entities.Dipendente;
import com.example.praticaS7L1.repositories.DipendenteRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
public class DipendenteSrv {
    @Autowired
    private DipendenteRepo dipendenteRepo;

    @Autowired
    private Cloudinary cloudinary;

    public List<Dipendente> trovaTuttiDipendenti() {
        return dipendenteRepo.findAll();
    }

    public Dipendente creaDipendente(Dipendente dipendente) {
        return dipendenteRepo.save(dipendente);
    }

    public Dipendente aggiornaImmagineProfilo(Long id, MultipartFile file) throws Exception {
        Dipendente dipendente = dipendenteRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Dipendente non trovato"));
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        String imageUrl = (String) uploadResult.get("url");
        dipendente.setImmagineProfilo(imageUrl);
        return dipendenteRepo.save(dipendente);
    }

    public Dipendente getDipendente(Long id) {
        return dipendenteRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Dipendente non trovato"));
    }

    public void eliminaDipendente(Long id) {
        dipendenteRepo.deleteById(id);
    }
}
