package com.jit.mail.service.impl;

import com.jit.mail.domain.Skin;
import com.jit.mail.repository.SkinRepository;
import com.jit.mail.service.SkinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkinServiceImpl implements SkinService {
    @Autowired
    public SkinRepository skinRepository;

    @Override
    public Skin getOne(Integer id) {
        Skin skin = skinRepository.getOne(id);
        return skin;
    }

    @Override
    public Skin addSkin(Skin skin) {
        Skin skin1 = skinRepository.save(skin);
        return skin1;
    }

    @Override
    public void deleteSkin(Skin skin) {
        skinRepository.delete(skin);

    }

    @Override
    public Skin updateSkin(Skin skin) {
        Skin skin1 = skinRepository.save(skin);
        return skin1;
    }

    @Override
    public List<Skin> findAll() {
        List<Skin> skinList = skinRepository.findAll();
        return skinList;
    }
}
