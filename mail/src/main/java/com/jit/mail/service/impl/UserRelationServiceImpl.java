package com.jit.mail.service.impl;

import com.jit.mail.domain.UserRelation;
import com.jit.mail.repository.UserRelationRepository;
import com.jit.mail.service.UserRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRelationServiceImpl implements UserRelationService {
    @Autowired
    public UserRelationRepository userRelationRepository;

    @Override
    public UserRelation addUserRelation(UserRelation userRelation) {
        UserRelation userRelation1 = userRelationRepository.save(userRelation);
        return userRelation1;
    }

    @Override
    public void deleteUserRelation(UserRelation userRelation) {
        userRelationRepository.delete(userRelation);
//        return null;
    }

    @Override
    public List<UserRelation> findByUser1(String user1) {
        List<UserRelation> userRelationList = userRelationRepository.findByUser1(user1);
        return userRelationList;
    }

    @Override
    public List<UserRelation> findByUser2(String user2) {
        List<UserRelation> userRelationList = userRelationRepository.findByUser2(user2);
        return userRelationList;
    }

    @Override
    public UserRelation getOne(Integer id) {
        UserRelation userRelation = userRelationRepository.getOne(id);
        return userRelation;
    }

    @Override
    public UserRelation findByUser1AndUser2(String user1, String user2) {
        UserRelation userRelation = userRelationRepository.findByUser1AndUser2(user1, user2);
        return userRelation;
    }
}
