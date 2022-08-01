package MAT.gominsageori.repository;

import MAT.gominsageori.domain.NaverUrlLog;

import java.util.Optional;

public interface NaverUrlLogInterface {
    Optional<NaverUrlLog> findByAlluserId(String userId);
    void writeLog(NaverUrlLog naverUrlLog);
}
