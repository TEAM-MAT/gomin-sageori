package MAT.gominsageori.repository;

import MAT.gominsageori.config.DynamoDbConfig;
import MAT.gominsageori.domain.NaverUrlLog;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public class NaverUrlLogRepository implements NaverUrlLogInterface{

    @Autowired private AmazonDynamoDB amazonDynamoDB;
    private DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);

    @Override
    public NaverUrlLog findByAlluserId(Long userId, Date accessDate) {
        return dynamoDBMapper.load(NaverUrlLog.class, userId, accessDate);
    }

    @Override
    public void writeLog(NaverUrlLog naverUrlLog) {
        dynamoDBMapper.save(naverUrlLog);
    }

    @Override
    public void updateLog(NaverUrlLog naverUrlLog) {
        try {
            dynamoDBMapper.save(naverUrlLog, buildDynamoDBSaveExpression(naverUrlLog));
        } catch (Exception e) {

        }
    }

    @Override
    public void deleteLog(NaverUrlLog naverUrlLog) {
        dynamoDBMapper.delete(naverUrlLog);
    }

    public DynamoDBSaveExpression buildDynamoDBSaveExpression(NaverUrlLog naverUrlLog) {
        DynamoDBSaveExpression saveExpression = new DynamoDBSaveExpression();
        return saveExpression;
    }
}
