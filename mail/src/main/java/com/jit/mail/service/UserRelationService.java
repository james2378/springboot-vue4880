package com.jit.mail.service;

import com.jit.mail.domain.UserRelation;

import java.util.List;

public interface UserRelationService {

    public UserRelation addUserRelation(UserRelation userRelation);

    public void deleteUserRelation(UserRelation userRelation);

    public List<UserRelation> findByUser1(String user1);

    public List<UserRelation> findByUser2(String user2);

    public UserRelation getOne(Integer id);

    public UserRelation findByUser1AndUser2(String user1, String user2);
}
