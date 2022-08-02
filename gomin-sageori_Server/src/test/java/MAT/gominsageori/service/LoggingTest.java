package MAT.gominsageori.service;

import MAT.gominsageori.domain.NaverUrlLog;
import MAT.gominsageori.repository.NaverUrlLogRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import javax.transaction.Transactional;
import java.util.Date;

@SpringBootTest
@Transactional
public class LoggingTest {

    @Autowired NaverUrlLogRepository naverUrlLogRepository;

    @Test
    //@Commit
    void 로그_작성() {
        Date accessDate = new Date();
        NaverUrlLog naverUrlLog = new NaverUrlLog();
        naverUrlLog.setUserId(1L);
        naverUrlLog.setNaverUrl("www.naver.com");
        naverUrlLog.setAccessDate(accessDate);

        naverUrlLogRepository.writeLog(naverUrlLog);
    }
}
