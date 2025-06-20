package com.jit.mail.repository;

import com.jit.mail.domain.UserRelation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRelationRepository extends JpaRepository<UserRelation, Integer> {
    public List<UserRelation> findByUser1(String user1);

    public List<UserRelation> findByUser2(String user2);

    public UserRelation findByUser1AndUser2(String user1, String user2);
}
