// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.common.aws;

import java.time.Duration;

import org.springframework.stereotype.Component;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;

/**
 * AWS S3マネージャー<br>
 * <p>
 * AWS S3で使用する機能を提供する
 * <p>
 *
 * @author NTT-Data
 * @version 1.0
 */
@Component
public class AwsS3Manager {

    /**
     * 署名付きURL作成
     * 
     * @param bucketName      バケット名
     * @param keyName         オブジェクトキー
     * @param durationMinutes 有効期限（単位：分）
     * @return 署名付きURL
     */
    public static String createPresignedGetUrl(String bucketName, String keyName, int durationMinutes) {

        return createPresignedGetUrl(bucketName, keyName, durationMinutes, Region.AP_NORTHEAST_1.toString());
    }

    /**
     * 署名付きURL作成
     * 
     * @param bucketName      バケット名
     * @param keyName         オブジェクトキー
     * @param durationMinutes 有効期限（単位：分）
     * @return 署名付きURL
     */
    public static String createPresignedGetUrl(String bucketName, String keyName, int durationMinutes, String region) {

        // S3Presignerオブジェクトを生成します。
        // ※ S3Presigner : 署名付きURLを生成するために使用されるクラス
        S3Presigner presigner = S3Presigner.builder().region(Region.of(region)).build();

        GetObjectRequest objectRequest = GetObjectRequest.builder().bucket(bucketName).key(keyName).build();

        GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
                .signatureDuration(Duration.ofMinutes(durationMinutes)).getObjectRequest(objectRequest).build();

        PresignedGetObjectRequest presignedRequest = presigner.presignGetObject(presignRequest);

        return presignedRequest.url().toExternalForm();
    }

}