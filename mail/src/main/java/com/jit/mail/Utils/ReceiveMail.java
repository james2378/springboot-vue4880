package com.jit.mail.Utils;

import com.jit.mail.domain.Mail;
import com.jit.mail.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.validation.constraints.Min;
import java.io.*;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class ReceiveMail {

    public MimeMessage mimeMessage = null;

    //    @Value("${test.attach}")
    public String saveAttachPath;//附件下载后存放目录

    public StringBuffer bodytext = new StringBuffer();//存放邮件内容
    //    public StringBuffer bodyhtml = new StringBuffer(5000);
    public String dateformat = "yyyy-MM-dd HH:mm";

    public ReceiveMail() {
    }

    public ReceiveMail(MimeMessage mimeMessage) {
        this.mimeMessage = mimeMessage;
        System.out.println("create a PraseMimeMessage object........");
    }

    public void setMimeMessage(MimeMessage mimeMessage) {
        this.mimeMessage = mimeMessage;
    }

    public static List<Mail> receiveMail(String host, String username, String password) throws Exception {
        // 初始化主机
//        String host = "luwei.com";
//        String username = "test1";
//        String password = "test1";
        // 配置服务器属性
        List<Mail> mailList = new ArrayList<>();
        Properties props = new Properties();
        props.put("mail.smtp.host", host); // smtp服务器
        props.put("mail.smtp.auth", "true"); // 是否smtp认证
        props.put("mail.smtp.port", "25"); // 设置smtp端口
        props.put("mail.transport.protocol", "smtp"); // 发邮件协议
        props.put("mail.store.protocol", "pop3"); // 收邮件协议
        // 获取会话
        Session session = Session.getDefaultInstance(props, null);
        // 获取Store对象，使用POP3协议，也可能使用IMAP协议
        try {
            Store store = session.getStore("pop3");
            // 接到邮件服务器
            store.connect(host, username, password);
            // 获取该用户Folder对象，并以只读方式打开
            Folder folder = store.getFolder("inbox");
            folder.open(Folder.READ_ONLY);
            // 检索所有邮件，按需填充
            Message message[] = folder.getMessages();
            ReceiveMail pmm = null;

            for (int i = 0; i < message.length; i++) {
                Mail mail = new Mail();
                pmm = new ReceiveMail((MimeMessage) message[i]);
                mail.setSmtpServer(host);
                mail.setFromUser(pmm.getFrom());
                System.out.println("Message " + i + " form: " + pmm.getFrom());
                mail.setSubject(pmm.getSubject());
                System.out.println("Message " + i + " subject: " + pmm.getSubject());
                mail.setSentDate(pmm.getSentDate());
                mail.setReceiveDate(pmm.getReceiveDate());
                System.out.println("Message " + i + " sentdate: " + pmm.getSentDate());
                mail.setHasRead(pmm.isNew());
                System.out.println("Message " + i + " 是否已读: " + pmm.isNew());
                System.out.println("Message " + i + " 是否包含附件: " + pmm.isContainAttach((Part) message[i]));
                mail.setToUser(pmm.getMailAddress("to"));
                mail.setCc(pmm.getMailAddress("cc"));
                mail.setBcc(pmm.getMailAddress("bcc"));
                System.out.println("Message " + i + " to: " + pmm.getMailAddress("to"));
                System.out.println("Message " + i + " cc: " + pmm.getMailAddress("cc"));
                mail.setMessageId(pmm.getMessageId());
                System.out.println("Message " + i + " Message-ID: " + pmm.getMessageId());
//                mail.setReplyTo(pmm.getReplyToEmail());
//                System.out.println("Message " + i + " 回复邮件: " + pmm.getReplyToEmail());
                pmm.getMailContent((Part) message[i]);
                mail.setContent(pmm.getBodyText());
                System.out.println("Message " + i + " bodycontent（文本内容）:" + pmm.getBodyText());
//                if(pmm.getBodyHtml() != null){
//                    System.out.println("Message "+i+" bodycontent（超文本内容）:"+pmm.getBodyText());
//                }
                pmm.setAttachPath("c:\\mail_attach");
                if (pmm.isContainAttach((Part) message[i])) {
                    String filenameList = pmm.saveAttachMent((Part) message[i]);
                    mail.setAttachName(filenameList);
                    mail.setAttachPath(pmm.saveAttachPath);
                    System.out.println("文件名的列表：" + filenameList);
                } else {
                    mail.setAttachPath(null);
                    mail.setAttachName(null);
                    System.out.println("没有附件");
                }
                mailList.add(mail);
            }
//            for (int i = 0; i < message.length; i++) {
//                Mail mail = new Mail();
//
////                mail.setSubject(message[i].getSubject());
////                mail.setContent(message[i].getContent().toString());
//                // 打印出每个邮件的发件人和主题
//                System.out.println(message[i]);
//                System.out.println(i + ":" + message[i].getFrom()[0] + "\t"
//                + message[i].getSubject());
//
//                System.out.println("内容类别:"+message[i].getContentType());
//            }
            folder.close(false);
            store.close();
        } catch (NoSuchProviderException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return mailList;
    }

    /**
     * 获得发件人的地址和姓名
     */
    public String getFrom() throws Exception {
        InternetAddress address[] = (InternetAddress[]) mimeMessage.getFrom();
        String from = address[0].getAddress();
        if (from == null) from = "";
        String personal = address[0].getPersonal();
        if (personal == null) personal = "";
//        String fromaddr = personal+"<"+from+">";
        return from;
    }
//    public String getReplyToEmail() throws Exception{
//        String replyToUser = "";
//        Message message = mimeMessage.reply(false);
//        String address = message
//    }


    /**
     * 获得邮件的收件人,抄送,和密送的地址和姓名,根据所传递的参数的不同
     * "to"----收件人 "cc"---抄送人地址 "bcc"---密送人地址
     */
    public String getMailAddress(String type) throws Exception {
        String mailaddr = "";
        String addtype = type.toUpperCase();
        InternetAddress[] address = null;
        if (addtype.equals("TO") || addtype.equals("CC") || addtype.equals("BCC")) {
            if (addtype.equals("TO")) {
                address = (InternetAddress[]) mimeMessage.getRecipients(Message.RecipientType.TO);
            } else if (addtype.equals("CC")) {
                address = (InternetAddress[]) mimeMessage.getRecipients(Message.RecipientType.CC);
            } else {
                address = (InternetAddress[]) mimeMessage.getRecipients(Message.RecipientType.BCC);
            }
            if (address != null) {
                for (int i = 0; i < address.length; i++) {
                    String email = address[i].getAddress();
                    if (email == null) email = "";
                    else {
                        email = MimeUtility.decodeText(email);
                    }
                    String personal = address[i].getPersonal();
                    if (personal == null) personal = "";
                    else {
                        personal = MimeUtility.decodeText(personal);
                    }
                    String compositeto = personal + "<" + email + ">";
//                    mailaddr+=","+compositeto;
                    mailaddr += "," + email;
                }
                mailaddr = mailaddr.substring(1);
            }
        } else {
            throw new Exception("Error emailaddr type!");
        }
        return mailaddr;
    }

    /**
     * 获得邮件主题
     */
    public String getSubject() throws MessagingException {
        String subject = "";
        try {
            subject = MimeUtility.decodeText(mimeMessage.getSubject());
            if (subject == null) subject = "";
        } catch (Exception e) {

        }
        return subject;
    }

    /**
     * 获得邮件发送日期
     */
    public Date getSentDate() throws Exception {
        Date sentDate = mimeMessage.getSentDate();
        return sentDate;
    }

    /**
     * 获得邮件接收日期
     */
    public Date getReceiveDate() throws Exception {
        Date receiveDate = mimeMessage.getReceivedDate();
        return receiveDate;
    }

    /*
    获得邮件正文内容
     */
    public String getBodyText() {
        return bodytext.toString();
    }
    /*
    获得邮件正文内容
     */
//    public String getBodyHtml(){
//        return bodyhtml.toString();
//    }

    /*
     * 解析邮件,把得到的邮件内容保存到一个StringBuffer对象中,解析邮件
     * 主要是根据MimeType类型的不同执行不同的操作,一步一步的解析
     */
    public void getMailContent(Part part) throws Exception {
        String contenttype = part.getContentType();
        int nameindex = contenttype.indexOf("name");
        boolean conname = false;
        if (nameindex != -1) conname = true;
//        System.out.println("CONTENTTYPE: "+contenttype);
        if (part.isMimeType("text/plain") && !conname) {
            bodytext.append((String) part.getContent());
        } else if (part.isMimeType("text/html") && !conname) {
            bodytext.append((String) part.getContent());
        } else if (part.isMimeType("multipart/*")) {
            Multipart multipart = (Multipart) part.getContent();
            int counts = multipart.getCount();
//            System.out.println("body计数:"+counts);
            for (int i = 0; i < counts; i++) {
                getMailContent(multipart.getBodyPart(i));
            }
        } else if (part.isMimeType("message/rfc822")) {
            getMailContent((Part) part.getContent());
        } else {
        }
    }

    /*
    获得此邮件的message-id
     */
    public String getMessageId() throws Exception {
        return mimeMessage.getMessageID();
    }

    /*
    判断此邮件是否已读，如果未读返回false，反之返回true
     */
    public boolean isNew() throws Exception {
        boolean isnew = false;
        Flags flags = ((Message) mimeMessage).getFlags();
        Flags.Flag[] flag = flags.getSystemFlags();
        System.out.println("flagss length: " + flag.length);

        for (int i = 0; i < flag.length; i++) {
            if (flag[i] == Flags.Flag.SEEN) {
                isnew = true;
                System.out.println("已经查看过邮件了");
                break;
            }
        }
        return isnew;
    }

    /*
    判断此邮件是否包含附件
     */
    public boolean isContainAttach(Part part) throws Exception {
        boolean attachflag = false;
        String contentType = part.getContentType();
        if (part.isMimeType("multipart/*")) {
            Multipart mp = (Multipart) part.getContent();
            for (int i = 0; i < mp.getCount(); i++) {
                BodyPart mpart = mp.getBodyPart(i);
                String disposition = mpart.getDisposition();
                if ((disposition != null) && ((disposition.equals(Part.ATTACHMENT)) || (disposition.equals(Part.INLINE)))) {
                    attachflag = true;
                } else if (mpart.isMimeType("multipart/*")) {
                    attachflag = isContainAttach((Part) mpart);
                } else {
                    String contype = mpart.getContentType();
                    if (contype.toLowerCase().indexOf("application") != -1) attachflag = true;
                    if (contype.toLowerCase().indexOf("name") != -1) attachflag = true;
                }
            }
        } else if (part.isMimeType("message/rfc822")) {
            attachflag = isContainAttach((Part) part.getContent());
        }
        return attachflag;
    }

    /*
    如果存在附件，保存附件，否则不执行
     */
    public String saveAttachMent(Part part) throws Exception {
        String fileNameList = "";
        String fileName = "";
        if (part.isMimeType("multipart/*")) {
            Multipart mp = (Multipart) part.getContent();
            for (int i = 0; i < mp.getCount(); i++) {
                BodyPart mpart = mp.getBodyPart(i);
                String disposition = mpart.getDisposition();
                if ((disposition != null) && ((disposition.equals(Part.ATTACHMENT)) || (disposition.equals(Part.INLINE)))) {
                    fileName = mpart.getFileName();
//                    if (fileName.toLowerCase().indexOf("gb2312") != -1) {
//                        fileName = MimeUtility.decodeText(fileName);
//                    }
                    fileName = MimeUtility.decodeText(fileName);
                    fileName = fileName.replaceAll("\r", "").replaceAll("\n", "");
                    fileName = fileName.replaceAll(" ","%20");
                    fileName = fileName.replaceAll("\u0018","\'");
                    URLDecoder.decode(fileName,"UTF-8");
                    fileNameList = fileNameList + "," + fileName;
                    System.out.println("*************************"+fileName);
                    saveFile(fileName, mpart.getInputStream());
                } else if (mpart.isMimeType("multipart/*")) {
                    saveAttachMent(mpart);
                } else {
                    fileName = mpart.getFileName();
                    if ((fileName != null) && (fileName.toLowerCase().indexOf("GB2312") != -1)) {
                        fileName = MimeUtility.decodeText(fileName);

                        fileName = fileName.replaceAll("\r", "").replaceAll("\n", "");
                        fileName = fileName.replaceAll("%20"," ");
                        fileNameList = fileNameList + "," + fileName;

                        saveFile(fileName, mpart.getInputStream());
                    }
                }
            }
        } else if (part.isMimeType("message/rfc822")) {
            saveAttachMent((Part) part.getContent());
        }
//        fileNameList=fileNameList.substring(1);
        return fileNameList;
    }

    /**
     * 【设置附件存放路径】
     */
    public void setAttachPath(String attachpath) {
        this.saveAttachPath = attachpath;
    }

    /**
     * 【获得附件存放路径】
     */
    public String getAttachPath() {
        return saveAttachPath;
    }

    /**
     * 真正保存附件到指定目录下
     **/
    public void saveFile(String fileName, InputStream in) throws Exception {
        String osName = System.getProperty("os.name");
        String storedir = getAttachPath();
        String separator = "";
        if (osName == null) osName = "";
        if (osName.toLowerCase().indexOf("win") != -1) {//判断系统部署的是windows还是linux下
            separator = "//";
            if (storedir == null || storedir.equals("")) {
                storedir = "c://tmp";
            }
        } else {
            separator = "/";
            storedir = "/tmp";
        }

        File storefile = new File(storedir + separator + fileName);
        System.out.println("storefiles path: " + storefile.toString());
        BufferedOutputStream bos = null;
        BufferedInputStream bis = null;
        try {
            bos = new BufferedOutputStream(new FileOutputStream(storefile));
            bis = new BufferedInputStream(in);
            int c;
            while ((c = bis.read()) != -1) {
                bos.write(c);
                bos.flush();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new Exception("文件保存失败!");
        } finally {
            bos.close();
            bis.close();
        }
    }


}
