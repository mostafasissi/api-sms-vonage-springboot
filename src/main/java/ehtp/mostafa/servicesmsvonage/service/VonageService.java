package ehtp.mostafa.servicesmsvonage.service;

import com.vonage.client.VonageClient;
import com.vonage.client.sms.MessageStatus;
import com.vonage.client.sms.SmsSubmissionResponse;
import com.vonage.client.sms.messages.TextMessage;
import ehtp.mostafa.servicesmsvonage.domain.MessageSMS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VonageService {
    @Autowired
    private VonageClient vonageClient ;

    public boolean SendSMS(MessageSMS sms){
        TextMessage message = new TextMessage(
                    sms.getFrom() ,
                    sms.getTo(),
                    sms.getMessageBody()      );

        SmsSubmissionResponse smsSubResp =
                vonageClient.getSmsClient().submitMessage(message);

        if (smsSubResp.getMessages().get(0).getStatus() == MessageStatus.OK) {
            return true;
        } else {
            return false ;
        }
    }
}
