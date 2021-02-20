package com.quickstudy.api.admin.service.word.impl;

import com.github.pagehelper.PageHelper;
import com.quickstudy.api.admin.dao.word.WordDao;
import com.quickstudy.api.admin.entity.word.Word;
import com.quickstudy.api.admin.entity.word.WordType;
import com.quickstudy.api.admin.common.exception.JsonException;
import com.quickstudy.api.admin.domain.request.word.WordDataRequest;
import com.quickstudy.api.admin.domain.request.word.WordQueryRequest;
import com.quickstudy.api.admin.service.word.WordService;
import com.quickstudy.api.admin.common.enums.ResultEnum;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("ALL")
@Service
public class WordServiceImpl implements WordService {


    @Resource
    private WordDao wordDao;



    private int getAdminId(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        String id = request.getHeader("X-Adminid");
        Integer adminId;
        try {
            adminId = Integer.valueOf(id);
        }catch (Exception e) {
            throw new JsonException(ResultEnum.LOGIN_VERIFY_FALL);
        }
        return adminId;

    }

    @Override
    public List<Word> listWordPage(WordQueryRequest wordQueryRequest) {

        System.out.println("wordQueryRequest listWordPage - :"+ wordQueryRequest);
        if (wordQueryRequest == null) {
            return Collections.emptyList();
        }

        wordQueryRequest.setAdminId(getAdminId());
        int offset = (wordQueryRequest.getPage() - 1) * wordQueryRequest.getLimit();

        System.out.println("wordQueryRequest listWordPage - :"+ wordQueryRequest);
        PageHelper.offsetPage(offset, wordQueryRequest.getLimit());

        if(wordQueryRequest.getOrderProp() != null && wordQueryRequest.getOrderProp() != ""){

            PageHelper.orderBy(wordQueryRequest.getOrderProp()+ " "+wordQueryRequest.getOrderType());
        }else{
            PageHelper.orderBy("score DESC");

        }

        System.out.println("wordQueryRequest listWordPage :"+ wordQueryRequest);
        List<Word>  wordList = wordDao.wordList(wordQueryRequest);

        return wordList;
    }

    @Override
    public List<WordType> listWordType() {

        return  wordDao.listWordType();
    }

    @Override
    public Word findWordById(WordQueryRequest wordQueryRequest) {
        return wordDao.findWordById(wordQueryRequest);
    }

    @Override
    public int setY(int id) {
        WordQueryRequest wordQueryRequest = new WordQueryRequest();
        wordQueryRequest.setAdminId(getAdminId());
        wordQueryRequest.setId(id);
        Word word = findWordById(wordQueryRequest);
        int status = 0;
        if(word !=  null){
            status =  wordDao.setY(wordQueryRequest);
            System.out.println("update status: "+status);
        }else{
            wordDao.setInitialScore(wordQueryRequest);
            status =  wordDao.setY(wordQueryRequest);
        }
        return status;
    }

    @Override
    public int setN(int id) {


        WordQueryRequest wordQueryRequest = new WordQueryRequest();
        wordQueryRequest.setAdminId(getAdminId());
        wordQueryRequest.setId(id);
        Word word = findWordById(wordQueryRequest);
        int status = 0;
        if(word !=  null){
            status =  wordDao.setN(wordQueryRequest);
            System.out.println("update status: "+status);
        }else{
            wordDao.setInitialScore(wordQueryRequest);
            status =  wordDao.setN(wordQueryRequest);
        }
        return status;

    }

    @Override
    public int setD(int id) {
        WordQueryRequest wordQueryRequest = new WordQueryRequest();
        wordQueryRequest.setAdminId(getAdminId());
        wordQueryRequest.setId(id);
        Word word = findWordById(wordQueryRequest);
        int status = 0;
        if(word !=  null){
            status =  wordDao.setD(wordQueryRequest);
            System.out.println("update status: "+status);
        }else{
            wordDao.setInitialScore(wordQueryRequest);
            status =  wordDao.setD(wordQueryRequest);
        }


        return status;
    }

    @Override
    public int setV(int id) {
        WordQueryRequest wordQueryRequest = new WordQueryRequest();
        wordQueryRequest.setAdminId(getAdminId());
        wordQueryRequest.setId(id);
        Word word = findWordById(wordQueryRequest);
        int status = 0;
        if(word !=  null){
            status =  wordDao.setV(wordQueryRequest);
            System.out.println("update status: "+status);
        }else{
            wordDao.setInitialScore(wordQueryRequest);
            status =  wordDao.setV(wordQueryRequest);
        }

        return status;
    }

    public static String sendPostJson(String apiUrl, String parameters) throws IOException {
        String body = null;

        int status = 0;

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost method = new HttpPost(apiUrl);

        // set timeout
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(5000).setConnectionRequestTimeout(15000)
                .setSocketTimeout(5000).build();
        method.setConfig(requestConfig);



        if (method != null & parameters != null
                && !"".equals(parameters.trim())) {
//            method.addHeader("Content-type","application/json; charset=utf-8");
//            method.setHeader("Accept", "application/json");
//            method.setEntity(new StringEntity(parameters, Charset.forName("UTF-8")));
            HttpResponse response = httpclient.execute(method);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {

            }
            // Read the response body
            body = EntityUtils.toString(response.getEntity());

        }
        return body;
    }



    @Override
    public void say(String en, HttpServletResponse response) throws IOException {


        String urlString="https://fanyi.baidu.com/gettts?lan=en&text="+en+"&spd=3&source=webhidden";;
        URL url = null;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        URLConnection connection = url.openConnection();
        InputStream inputStream = connection.getInputStream();
        BufferedOutputStream outputStream = null;
        try {
            outputStream = new BufferedOutputStream(response.getOutputStream());
            byte[] buff = new byte[4096];
            int len = 0;
            while((len = inputStream.read(buff)) != -1) {
                outputStream.write(buff,0, len);
            }
            outputStream.flush();
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }

    }

    @Override
    public int updateWord(WordDataRequest wordDataRequest) {
        return wordDao.updateWord(wordDataRequest);
    }


}
