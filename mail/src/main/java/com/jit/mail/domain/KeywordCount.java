package com.jit.mail.domain;

import java.io.Serializable;

public class KeywordCount implements Serializable {

    public KeywordCount(String keyword, int spam, int spamAll, int legit, int legitAll) {
        super();
        this.keyword = keyword;
        this.spam = spam;
        this.spamAll = spamAll;
        this.legit = legit;
        this.legitAll = legitAll;
    }

    // 关键词
    public String keyword;
    // 此关键词在垃圾邮件中出现的次数
    public int spam;
    // 垃圾邮件总数量
    public int spamAll;
    // 此关键词在正常邮件中出现的次数
    public int legit;
    // 正常邮件总数量
    public int legitAll;
    // 这个关键词存在的情况下,是垃圾邮件的概率
    public double combiningProbabilities;

    @Override
    public String toString() {
        return "[keyword=" + keyword + ", spam=" + spam + ", spamAll=" + spamAll + ", legit=" + legit + ", legitAll=" + legitAll + ", combiningProbabilities="
                + combiningProbabilities + "]";
    }
}
