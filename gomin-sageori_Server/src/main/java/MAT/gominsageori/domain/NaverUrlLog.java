package MAT.gominsageori.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Date;

@DynamoDBTable(tableName = "naverUrlLog")
public class NaverUrlLog {
    private Long userId;
    private String naverUrl;
    private Date accessDate;

    @DynamoDBHashKey(attributeName = "userId") // 기본키
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @DynamoDBAttribute(attributeName = "naverUrl")
    public String getNaverUrl() {
        return naverUrl;
    }

    public void setNaverUrl(String naverUrl) {
        this.naverUrl = naverUrl;
    }

    @DynamoDBAttribute(attributeName = "accessDate")
    @DynamoDBAutoGeneratedKey // 정렬키
    public Date getAccessDate() {
        return accessDate;
    }

    public void setAccessDate(Date accessDate) {
        this.accessDate = accessDate;
    }
}
