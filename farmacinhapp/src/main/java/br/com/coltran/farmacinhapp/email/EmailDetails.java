package br.com.coltran.farmacinhapp.email;

public class EmailDetails {

    private String recipient;
    private String msgBody;
    private String subject;
    private String attachment;
    private boolean isHtml = true;

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getMsgBody() {
        return msgBody;
    }

    public void setMsgBody(String msgBody) {
        this.msgBody = msgBody;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public boolean isHtml() {
        return isHtml;
    }

    public void setHtml(boolean html) {
        isHtml = html;
    }

    public static class Builder {

        private EmailDetails details;

        public Builder() {
            details = new EmailDetails();
        }

        public Builder recipient(String recipient){
            this.details.setRecipient(recipient);
            return this;
        }

        public Builder msgBody(String msgBody){
            this.details.setMsgBody(msgBody);
            return this;
        }

        public Builder subject(String subject){
            this.details.setSubject(subject);
            return this;
        }

        public Builder attachment(String attachment){
            this.details.setAttachment(attachment);
            return this;
        }

        public Builder isHtml(boolean html){
            this.details.setHtml(html);
            return this;
        }

        public EmailDetails build(){
            return details;
        }
    }
}
