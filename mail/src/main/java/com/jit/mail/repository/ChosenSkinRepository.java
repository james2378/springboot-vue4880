package com.jit.mail.repository;

import com.jit.mail.domain.ChosenSkin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChosenSkinRepository extends JpaRepository<ChosenSkin, Integer> {
    public ChosenSkin findByUsername(String username);

    public List<ChosenSkin> findBySkinNo(Integer skinNo);
}
