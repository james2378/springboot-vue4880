package com.jit.mail.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class User implements Serializable {

//    @Id
//    @GeneratedValue
//    public Integer id;

    @Id
    @Column(unique = true)
    public String username;

    public String pwdHash;

    public String pwdAlgorithm;

    public Integer useForwarding;

    public String forwardDestination;

    public Integer useAlias;

    public String alias;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwdHash() {
        return pwdHash;
    }

    public void setPwdHash(String pwdHash) {
        this.pwdHash = pwdHash;
    }

    public String getPwdAlgorithm() {
        return pwdAlgorithm;
    }

    public void setPwdAlgorithm(String pwdAlgorithm) {
        this.pwdAlgorithm = pwdAlgorithm;
    }

    public Integer getUseForwarding() {
        return useForwarding;
    }

    public void setUseForwarding(Integer useForwarding) {
        this.useForwarding = useForwarding;
    }

    public String getForwardDestination() {
        return forwardDestination;
    }

    public void setForwardDestination(String forwardDestination) {
        this.forwardDestination = forwardDestination;
    }

    public Integer getUseAlias() {
        return useAlias;
    }

    public void setUseAlias(Integer useAlias) {
        this.useAlias = useAlias;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
