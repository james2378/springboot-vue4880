package com.jit.mail.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "choseskin")
public class ChosenSkin implements Serializable {
    @Id
    @GeneratedValue
    public Integer id;

    @Column(unique = true)
    public String username;

    public Integer skinNo;

    public Date createDate;

    public Date modifyDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getSkinNo() {
        return skinNo;
    }

    public void setSkinNo(Integer skinNo) {
        this.skinNo = skinNo;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}
