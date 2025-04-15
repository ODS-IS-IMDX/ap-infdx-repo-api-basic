// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.common.util;

import java.util.ArrayList;
import java.util.List;

public class ConvertUtils {
    
    /**
     * プライベートコンストラクタ<br>
     * ユーティリティクラスのため、インスタンス化を禁じる．
     */
    private ConvertUtils() {
        
    }
    
    /**
     * String型のリスト内要素に対して、&quot;&#047;&quot;による分割を行う．
     * 
     * @param list String型のリスト
     * @param delimiter 区切り文字
     * @return delimiterで分割された文字列配列を内包するリスト
     */
    public static List<String[]> splitStrInList(List<String> list, String delimiter) {
        
        List<String[]> splitedList = new ArrayList<String[]>();
        
        for (String element : list) {
            splitedList.add(element.split(delimiter));
        }
        
        return splitedList;
    }
    
    /**
     * リスト内の文字列配列に対して、int型への変換を行う．
     * 
     * @param list 文字列配列を内包したリスト
     * @return int型に変換された配列を持つリスト
     */
    public static List<int[]> convertListToInt(List<String[]> list) {
        
        List<int[]> convertedList = new ArrayList<int[]>();
        
        for (String[] elements : list) {
            
            int[] convertedElement  = new int[elements.length];
            
            int loopCnt = 0;
            for (String element : elements) {
                
                convertedElement[loopCnt] = Integer.parseInt(element);
                
                loopCnt++;
                
            }
            
            convertedList.add(convertedElement);
            
        }
                
        return convertedList;
    }

}
