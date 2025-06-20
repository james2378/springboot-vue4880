package com.jit.mail.service;

import com.jit.mail.domain.ChosenSkin;

import java.util.List;

public interface ChosenSkinService {
    public ChosenSkin findByUsername(String username);

    public ChosenSkin addChosenSkin(ChosenSkin chosenSkin);

    public void deleteChosenSkin(ChosenSkin chosenSkin);

    public ChosenSkin updateChosenSkin(ChosenSkin chosenSkin);

    public List<ChosenSkin> findBySkinNo(Integer skinNo);
}
