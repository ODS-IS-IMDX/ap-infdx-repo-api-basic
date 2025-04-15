// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.common.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * あるフィールドに値が存在した場合のみ、別フィールドが必須であることを強制するアノテーションクラス．<br>
 * 直接バリデーションの提供は行わず、{@link CheckCrossFieldConditions}へバリデーション条件・内容を媒介する．
 * 
 * @author matsumoto kentaro
 * @version 1.1 2024/10/23
 */
@Target({ ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ConditionalMandatory {
    
    String conditionalField();
    
    String mandatoryField();
    
}
