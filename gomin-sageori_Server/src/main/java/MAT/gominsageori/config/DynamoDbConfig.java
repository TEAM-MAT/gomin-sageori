package MAT.gominsageori.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:/application.properties")
@Configuration
public class DynamoDbConfig {
    @Value("${amazon.aws.accesskey}")
    private String accessKey;

    @Value("${amazon.aws.secretkey}")
    private String secretKey;

    @Value("${amazon.dynamodb.endpoint}")
    private String endPoint;

    @Value("${amazon.aws.region}")
    private String region;

    @Bean
    public AWSCredentials awsCredentials() {
        AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey,secretKey);
        return awsCredentials;
    }

    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        AmazonDynamoDB dynamoDB = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endPoint,region))
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials()))
                .build();
        return dynamoDB;
    }
}
