package MAT.gominsageori.repository;

import MAT.gominsageori.domain.NaverUrlLog;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class NaverUrlLogRepository implements NaverUrlLogInterface{

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    @Override
    public Optional<NaverUrlLog> findByAlluserId(String userId) {
        return Optional.empty();
    }

    @Override
    public void writeLog(NaverUrlLog naverUrlLog) {
        dynamoDBMapper.save(naverUrlLog);
    }
}
