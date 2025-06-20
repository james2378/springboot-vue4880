package com.jit.mail.service;

import com.jit.mail.domain.Skin;

import java.util.List;

public interface SkinService {
    public Skin getOne(Integer id);

    public Skin addSkin(Skin skin);

    public void deleteSkin(Skin skin);

    public Skin updateSkin(Skin skin);

    public List<Skin> findAll();
}
