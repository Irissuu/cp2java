package br.com.fiap.api_rest.service;

import br.com.fiap.api_rest.dto.BoloResponse;
import br.com.fiap.api_rest.mapper.BoloMapper;
import br.com.fiap.api_rest.model.Bolo;
import br.com.fiap.api_rest.repository.BoloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BoloService {

    private final BoloRepository boloRepository;
    private final BoloMapper boloMapper = new BoloMapper();

    @Autowired
    public BoloService(BoloRepository boloRepository) {
        this.boloRepository = boloRepository;
    }

    public BoloResponse save(Bolo bolo) {
        return boloMapper.boloToResponse(boloRepository.save(bolo));
    }

    public Page<BoloResponse> findAll(Pageable pageable) {
        return boloRepository.findAll(pageable).map(boloMapper::boloToResponse);
    }

    public BoloResponse findById(Long id) {
        Optional<Bolo> bolo = boloRepository.findById(id);
        return bolo.map(boloMapper::boloToResponse).orElse(null);
    }

    public boolean deleteById(Long id) {
        Optional<Bolo> bolo = boloRepository.findById(id);
        if (bolo.isPresent()) {
            boloRepository.delete(bolo.get());
            return true;
        }
        return false;
    }
}
