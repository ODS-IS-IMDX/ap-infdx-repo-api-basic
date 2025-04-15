// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.common.aws;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;

/**
 * SecretManagerから値を取得する処理を担うクラス.
 * 
 * @auther ukai jun
 * @version 1.1 2024/10/01
 */
public class AwsSecretManager {
    
    /**
     * jsonオブジェクトマッパー．
     */
    private static final ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        
    /**
     * AWS Secrets Managerから取得した値をデシリアライズして返却する．
     * <p>
     * デシリアライズ先のクラスは、{@link BaseSecretsValue}を継承した具象クラスのみ許容する．
     * </p>
     * 
     * @param secretName シークレット名
     * @param clazz デシリアライズ先のクラス型
     * @return シークレット情報がデシリアライズされたオブジェクト
     */
    public static <T extends BaseSecretsValue> T getSecretsValue(String secretName, Class<T> clazz) throws Exception {
        
        final String secrets = getSecrets(secretName);
        
        return objectMapper.readValue(secrets, clazz);
        
    }
    
    /**
     * 指定されたシークレット名に対応するシークレット情報を、AWS Secrets Managerから取得する．
     * 
     * @param secretName シークレット名
     * @return 指定されたシークレット名に対応するシークレット情報
     */
    private static String getSecrets(String secretName) {
        
        try (SecretsManagerClient secretsClient = SecretsManagerClient.builder()
                .region(Region.AP_NORTHEAST_1)
                .build()) {
            
            // シークレット名を渡し、リクエスト作成
            GetSecretValueRequest valueRequest = GetSecretValueRequest.builder()
                    .secretId(secretName)
                    .build();

            // レスポンスのJsonを受け取る
            GetSecretValueResponse valueResponse = secretsClient.getSecretValue(valueRequest);

            // String型を取得
            String secret = valueResponse.secretString();
            
            // クライアントを閉じる
            secretsClient.close();

            return secret;
            
        } catch (Exception e) {
            
            throw e;
            
        }

    }

}