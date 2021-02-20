package com.quickstudy.api.admin.common.util.translate;


import com.google.gson.Gson;
import com.quickstudy.api.admin.common.util.translate.translatedata.TranslateData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@SuppressWarnings("ALL")
@Component
public class DoTranslate {
    private static String APP_ID ;
    private static String SECURITY_KEY ;


    @Value("${site.translate.api.apiId}")
    private void setAppId(String appId){
        DoTranslate.APP_ID = appId;

    }
    @Value("${site.translate.api.securityKey}")
    private void setSecurityKey(String securityKey){
        DoTranslate.SECURITY_KEY = securityKey;
    }


    public static String translate(String query){
        Gson gson = new Gson();
        TransApi api = new TransApi(APP_ID, SECURITY_KEY);
        String apiResponse = api.getTransResult(query, "auto", "zh");
        TranslateData data = gson.fromJson(apiResponse, TranslateData.class);
        return data.getTransResult().get(0).getDst();
    }


}
