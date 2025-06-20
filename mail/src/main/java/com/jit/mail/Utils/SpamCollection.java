package com.jit.mail.Utils;

import com.jit.mail.domain.KeywordCount;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.PatternSyntaxException;

public class SpamCollection {

    // 统计垃圾邮件出现的次数
    static int spamNumber = 0;
    // 统计正常邮件出现的次数
    static int legitNumber = 0;

    public static Map<String, KeywordCount> getSpamCollection() {
        // 得到所有单词
        String[] banword = getAllWordsFromFile("src/main/resources/static/TrainingSet/SMSSpamCollection");
        // 预热数据,将所有关键字放在一个Map中
        Map<String, KeywordCount> keywordMap = new HashMap<String, KeywordCount>();
        for (String s : banword) {
            keywordMap.put(s, new KeywordCount(s, 0, 0, 0, 0));
        }

        // 得到所有训练邮件列表
        List<String> mailList = getContentFromFile("src/main/resources/static/TrainingSet/SMSSpamCollection");

        // 统计垃圾邮件出现的次数
        int spamNumber = 0;
        // 统计正常邮件出现的次数
        int legitNumber = 0;
        // 统计每个关键字在正常邮件和垃圾邮件中出现的次数
        for (int i = 0; i < mailList.size(); i++) {
            String mailContent = mailList.get(i);

            // 看真实情况是否是垃圾邮件
            if (mailContent.startsWith("spam")) {
                for (Map.Entry<String, KeywordCount> entry : keywordMap.entrySet()) {
                    boolean containFlag = FilterKeyWord(mailContent, entry.getKey());
                    KeywordCount keywordCount = entry.getValue();

                    if (containFlag == true) {
                        keywordCount.spam += 1;
                    }
                    keywordCount.spamAll += 1;
                }
                spamNumber++;
            } else {
                for (Map.Entry<String, KeywordCount> entry : keywordMap.entrySet()) {
                    boolean containFlag = FilterKeyWord(mailContent, entry.getKey());
                    KeywordCount keywordCount = entry.getValue();

                    if (containFlag == true) {
                        keywordCount.legit += 1;
                    }
                    keywordCount.legitAll += 1;

                }
                legitNumber++;
            }
        }

        List<String> needRemoveKey = new ArrayList<String>();
        // 得到每一个关键字出现的情况下是垃圾邮件的概率的概率
        for (Map.Entry<String, KeywordCount> entry : keywordMap.entrySet()) {
            KeywordCount kcTemp = entry.getValue();
            if (kcTemp.spam + kcTemp.legit == 0) {
                needRemoveKey.add(entry.getKey());
                continue;
            }

            double Spam = 1.0 * kcTemp.spam / kcTemp.spamAll;
            double SpamAll = 1.0 * kcTemp.spamAll / (kcTemp.spamAll + kcTemp.legitAll);
            double Legit = 1.0 * kcTemp.legit / kcTemp.legitAll;
            double LegitAll = 1.0 * kcTemp.legitAll / (kcTemp.spamAll + kcTemp.legitAll);

            kcTemp.combiningProbabilities = (Spam * SpamAll) / (Spam * SpamAll + Legit * LegitAll); // 根据（公式2）

            if (kcTemp.combiningProbabilities < 0.93) {
                needRemoveKey.add(entry.getKey());
            }
        }

        // 过滤得到所有符合要求的对垃圾邮件有较高识别度的关键词
        for (String s : needRemoveKey) {
            keywordMap.remove(s);
        }

        // 查看结果
//        for (Map.Entry<String, KeywordCount> entry : keywordMap.entrySet())
//        {
//            System.out.println(entry.getValue());
//        }
        File file1 = new File("src/main/resources/static/TrainingSet/test_para");
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("1", spamNumber);
        hashMap.put("2", legitNumber);
        FileOutputStream out1;
        try {
            out1 = new FileOutputStream(file1);
            ObjectOutputStream objOut1 = new ObjectOutputStream(out1);
            objOut1.writeObject(hashMap);
            objOut1.flush();
            objOut1.close();
            System.out.println("write object para hashMap success!");
        } catch (IOException e) {
            System.out.println("write object para hashMap failed");
            e.printStackTrace();
        }
        return keywordMap;
    }


    //进行邮件的判定垃圾邮件的检测
    public static List<String> checkSpam(HashMap<String, String> testMailList, Map<String, KeywordCount> keywordMap) {
        // 得到所有测试邮件列表
//        List<String> testMailList = getContentFromFile("TestSet/TestFile1.txt");
        //这个list是用来存放判定为垃圾的邮件的id；

        HashMap<String, Integer> temp1 = new HashMap<>();
        File file3 = new File("src/main/resources/static/TrainingSet/test_para");

        FileInputStream in3;
        try {
            in3 = new FileInputStream(file3);
            ObjectInputStream objIn3 = new ObjectInputStream(in3);
            temp1 = (HashMap<String, Integer>) objIn3.readObject();
            objIn3.close();
            System.out.println("read object test_para hasMap success!");
        } catch (IOException e) {
            System.out.println("read object test_para hashMap failed");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        int spamNumber = temp1.get("1");
        int legitNumber = temp1.get("2");
        System.out.println("测试参数++++++++++++++++" + spamNumber + "::::::::" + legitNumber);

        List<String> list = new ArrayList<>();
        // 成功识别的数量
        int rightCount = 0;
        //识别错误的数量
        int wrongCount = 0;
        // 总共垃圾邮件数量
        int spamCount = 0;
        for (String test_key : testMailList.keySet()) {
            String testMail_key = test_key;
            // 找到这封邮件含有的关键字
            String thisMail = testMailList.get(test_key);
            System.out.println("过滤之前的邮件内容*****************");
            System.out.println(thisMail);
            //过滤掉html标签和转义字符
            thisMail = stringFilter(thisMail);
            System.out.println("过滤之后的邮件内容*****************");
            System.out.println(thisMail);


            List<String> oneMailKeywordList = new ArrayList<String>();

            for (Map.Entry<String, KeywordCount> entry : keywordMap.entrySet()) {
                boolean containFlag = FilterKeyWord(thisMail, entry.getKey());
                if (containFlag == true) {
                    oneMailKeywordList.add(entry.getKey());
                }
            }

            if (oneMailKeywordList.size() <= 0) {
                // System.out.println("没有含有关键字,应该是正常邮件");
                continue;
            }

            // 得到这封邮件所有关键词的联合概率,根据(公式3)
            double Pup = 1.0 * spamNumber / (spamNumber + legitNumber);
            double Pdown = 1.0f;
            for (String kw : oneMailKeywordList) {
                Pup = Pup * keywordMap.get(kw).spam / keywordMap.get(kw).spamAll;
                Pdown = Pdown * (keywordMap.get(kw).spam + keywordMap.get(kw).legit) / (spamNumber + legitNumber);
            }
            double Pmail = Pup / (Pup + Pdown);

            System.out.println("该封邮件是垃圾邮件的概率为:" + Pmail);

            // 成功识别
            if (Pmail > 0.999) {
                System.out.println("检测垃圾邮箱的key值" + testMail_key);
                list.add(testMail_key);
                rightCount++;
            }
            // 识别错误
            if (Pmail > 0.999) {
                wrongCount++;
            }
        }
        System.out.println("垃圾邮件总数为" + spamCount + ",正确识别了" + rightCount + "封垃圾邮件，召回率" + rightCount * 1.0 / spamCount + ",准确率：" + rightCount * 1.0
                / (rightCount + wrongCount));
        return list;
    }

    /**
     * 将banword 的关键字词与邮件内容逐字比较，若邮件内容中包含此关键字，则返回true
     *
     * @param strContent
     * @param strKeyWord
     * @return
     */
    private static boolean FilterKeyWord(String strContent, String strKeyWord) {
        boolean retVal = false;

        if (strContent.toLowerCase().indexOf(strKeyWord.toLowerCase()) >= 0) {
            retVal = true;
        }

        return retVal;
    }

    /**
     * 读取文件内容
     *
     * @param fileName
     * @return
     */
    public static List<String> getContentFromFile(String fileName) {
        List<String> totalList = new ArrayList<String>();
        try {
            File file = new File(fileName);

            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
            String str;
            while ((str = br.readLine()) != null) {
                totalList.add(str.trim());
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return totalList;
    }

    /**
     * 从文件中获得所有的单词
     *
     * @param fileName
     * @return
     */
    public static String[] getAllWordsFromFile(String fileName) {
        StringBuffer sb = new StringBuffer();
        try {
            File file = new File(fileName);

            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
            String str;
            while ((str = br.readLine()) != null) {
                sb.append(str.replaceAll("spam", "").replaceAll("ham", "").trim());
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] a = sb.toString().split("[^a-zA-Z]+");//匹配字符串中非a-z也非A-Z
        return a;
    }

    public static String stringFilter(String str) throws PatternSyntaxException{
        String regEx = "<(S*?)[^>]*>.*?|<.*? />";
        str = str.replaceAll(regEx,"");
        str = str.replaceAll("&.{2,6}?;","");
        return str;

    }
}


