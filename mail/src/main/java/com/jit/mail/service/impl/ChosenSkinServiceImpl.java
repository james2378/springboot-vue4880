package com.jit.mail.service.impl;

import com.jit.mail.domain.ChosenSkin;
import com.jit.mail.repository.ChosenSkinRepository;
import com.jit.mail.service.ChosenSkinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChosenSkinServiceImpl implements ChosenSkinService {
    @Autowired
    public ChosenSkinRepository chosenSkinRepository;

    @Override
    public ChosenSkin findByUsername(String username) {
        ChosenSkin chosenSkin = chosenSkinRepository.findByUsername(username);
        return chosenSkin;
    }

    @Override
    public ChosenSkin addChosenSkin(ChosenSkin chosenSkin) {
        ChosenSkin chosenSkin1 = chosenSkinRepository.save(chosenSkin);
        return chosenSkin1;
    }

    @Override
    public void deleteChosenSkin(ChosenSkin chosenSkin) {
        chosenSkinRepository.delete(chosenSkin);
    }

    @Override
    public ChosenSkin updateChosenSkin(ChosenSkin chosenSkin) {
        ChosenSkin chosenSkin1 = chosenSkinRepository.save(chosenSkin);
        return chosenSkin1;
    }

    @Override
    public List<ChosenSkin> findBySkinNo(Integer skinNo) {
        List<ChosenSkin> chosenSkinList = chosenSkinRepository.findBySkinNo(skinNo);
        return chosenSkinList;
    }
}
