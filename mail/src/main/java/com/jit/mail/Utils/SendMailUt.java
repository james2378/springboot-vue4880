package com.jit.mail.Utils;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class SendMailUt {
    public SendMailUt() {
    }

    public static Boolean sendMailUtil(String[] sendArr) throws Exception {
        // 初始化信息

        String sender = sendArr[0];
        String password = sendArr[1];
        String smtpServer = sendArr[2];
        String recipient = sendArr[3];
        String subject = sendArr[4];
        String fileAttachment = sendArr[5];   //附件
        String content = sendArr[6];
        Boolean result = false;

        // 配置服务器属性
        Properties proper = new Properties();
        proper.put("mail.smtp.host", smtpServer); // smtp服务器
        proper.put("mail.smtp.auth", "true"); // 是否smtp认证
        proper.put("mail.smtp.port", "25"); // 设置smtp端口
        proper.put("mail.transport.protocol", "smtp"); // 发邮件协议
        proper.put("mail.store.protocol", "pop3"); // 收邮件协议

        // 配置邮件接收地址
        InternetAddress[] receiveAddress = new InternetAddress[1];
        try {
            receiveAddress[0] = new InternetAddress(recipient);
        } catch (AddressException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // smtp认证，获取Session

        SmtpAuth sa = new SmtpAuth();
        sa.setUserinfo(sender, password);
        Session session = Session.getInstance(proper, sa);
        session.setPasswordAuthentication(new URLName(smtpServer), sa
                .getPasswordAuthentication());

        // 构建邮件体
        MimeMessage sendMess = new MimeMessage(session);
        MimeBodyPart mbp = new MimeBodyPart();
        MimeMultipart mmp = new MimeMultipart();
        try {
            // 邮件文本内容
            mbp.setContent(content, "text/plain; charset=UTF-8");
            mmp.addBodyPart(mbp);
            // 附件处理

            if (fileAttachment != null && fileAttachment != "") {
                String[] files = fileAttachment.split(",");
                for (int i = 0; i < files.length; i++) {
                    DataSource source = new FileDataSource(files[i]);
                    String name = source.getName();
                    mbp = new MimeBodyPart();
                    mbp.setDataHandler(new DataHandler(source));

                    String filename = MimeUtility.encodeText(name);
                    filename = filename.replaceAll("\r", "").replaceAll("\n", "");
                    mbp.setFileName(filename);

//                    mbp.setFileName(name);
                    mmp.addBodyPart(mbp);
                }
//                DataSource source = new FileDataSource(fileAttachment);
//                String name = source.getName();
//                mbp = new MimeBodyPart();
//                mbp.setDataHandler(new DataHandler(source));
//                mbp.setFileName(name);
//                mmp.addBodyPart(mbp);
            }

            // 邮件整体
            sendMess.setSubject(subject);
            sendMess.setContent(mmp);
            // 发送邮件
            sendMess.setFrom(new InternetAddress(sender));
            sendMess.setRecipients(Message.RecipientType.TO, receiveAddress);
            Transport.send(sendMess);
            System.out.println("发送成功");
            result = true;
        } catch (MessagingException ex) {
            result = false;
            ex.printStackTrace();

        }
        return result;
    }

}
