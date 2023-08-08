package sample.cafekiosk.spring.api.service.mail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import sample.cafekiosk.spring.client.mail.MailSendClient;
import sample.cafekiosk.spring.domain.history.mail.MailSendHistory;
import sample.cafekiosk.spring.domain.history.mail.MailSendHistoryRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MailServiceTest {

    @Mock
    private MailSendClient mailSendClient;

    @Mock
    private MailSendHistoryRepository mailSendHistoryRepository;

    @InjectMocks
    private MailService mailService;


    @DisplayName("메일 전송 테스트")
    @Test
    void sendMail(){
        //given
        //@Mock 쓰고 @ExtendWith 어노테이션으로 대체할 수 있음
//        MailSendClient mailSendClient = Mockito.mock(MailSendClient.class);
//        MailSendHistoryRepository mailSendHistoryRepository = Mockito.mock(MailSendHistoryRepository.class);

        //@InjectMocks 어노테이션으로 대체할 수 있음.
//        MailService mailService = new MailService(mailSendClient, mailSendHistoryRepository);

        when(mailSendClient.sendEmail(anyString(),anyString(),anyString(),anyString()))
                .thenReturn(true);

        //@spy 일때 when 을 사용할 수 없어서 doReturn 을 사용함
//        doReturn(true)
//                .when(mailSendClient)
//                .sendEmail(anyString(),anyString(),anyString(),anyString());


        //when
        boolean result = mailService.sendMail("", "", "", "");


        //then
        assertThat(result).isTrue();

        //save 가 한번 불렸는지를 검증하는 method(verify)
        Mockito.verify(mailSendHistoryRepository,times(1)).save(any(MailSendHistory.class));
    }

}