package com.jit.mail.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "skin")
public class Skin implements Serializable {
    @Id
    @GeneratedValue
    public Integer id;

    @Column(unique = true)
    public String skinName;

    @Column(nullable = false)
    public String skinShowLogo;
    @Column(nullable = false)
    public String skinHeader;
    @Column(nullable = false)
    public String skinHeaderBgLeft;
    @Column(nullable = false)
    public String skinHeaderBgRight;

    public String skinMenuBgx;
    @Column(nullable = false)
    public String skinBodyLeft;
    @Column(nullable = false)
    public String skinBodyLeftBottom;

    public String mailLogo;

    public Date createDate;

    public Date modifyDate;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSkinName() {
        return skinName;
    }

    public void setSkinName(String skinName) {
        this.skinName = skinName;
    }

    public String getSkinShowLogo() {
        return skinShowLogo;
    }

    public void setSkinShowLogo(String skinShowLogo) {
        this.skinShowLogo = skinShowLogo;
    }

    public String getSkinHeader() {
        return skinHeader;
    }

    public void setSkinHeader(String skinHeader) {
        this.skinHeader = skinHeader;
    }

    public String getSkinHeaderBgLeft() {
        return skinHeaderBgLeft;
    }

    public void setSkinHeaderBgLeft(String skinHeaderBgLeft) {
        this.skinHeaderBgLeft = skinHeaderBgLeft;
    }

    public String getSkinHeaderBgRight() {
        return skinHeaderBgRight;
    }

    public void setSkinHeaderBgRight(String skinHeaderBgRight) {
        this.skinHeaderBgRight = skinHeaderBgRight;
    }

    public String getSkinMenuBgx() {
        return skinMenuBgx;
    }

    public void setSkinMenuBgx(String skinMenuBgx) {
        this.skinMenuBgx = skinMenuBgx;
    }

    public String getSkinBodyLeft() {
        return skinBodyLeft;
    }

    public void setSkinBodyLeft(String skinBodyLeft) {
        this.skinBodyLeft = skinBodyLeft;
    }

    public String getMailLogo() {
        return mailLogo;
    }

    public void setMailLogo(String mailLogo) {
        this.mailLogo = mailLogo;
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

    public String getSkinBodyLeftBottom() {
        return skinBodyLeftBottom;
    }

    public void setSkinBodyLeftBottom(String skinBodyLeftBottom) {
        this.skinBodyLeftBottom = skinBodyLeftBottom;
    }
}
