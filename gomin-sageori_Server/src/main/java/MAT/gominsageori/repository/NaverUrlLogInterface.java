package MAT.gominsageori.repository;

import MAT.gominsageori.domain.NaverUrlLog;

import java.util.Date;
import java.util.Optional;

public interface NaverUrlLogInterface {
    NaverUrlLog findByAlluserId(Long userId, Date accessDate);
    void writeLog(NaverUrlLog naverUrlLog);
    public void updateLog(NaverUrlLog naverUrlLog);
    public void deleteLog(NaverUrlLog naverUrlLog);
}
