package com.quickstudy.api.admin.service.word;


import com.quickstudy.api.admin.entity.word.Word;
import com.quickstudy.api.admin.entity.word.WordType;
import com.quickstudy.api.admin.domain.request.word.WordDataRequest;
import com.quickstudy.api.admin.domain.request.word.WordQueryRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Jason
 */
public interface WordService {

    /**
     * listWordPage
     * @param wordQueryRequest
     * @return
     */
    List<Word> listWordPage(WordQueryRequest wordQueryRequest);


    /**
     * listWordType
     * @return
     */
    List<WordType> listWordType();

    /**
     * findWordById
     * @param wordQueryRequest
     * @return
     */
    Word findWordById(WordQueryRequest wordQueryRequest);


    /**
     * setY
     * @param id
     * @return
     */
    int setY(int id);

    /**
     * setN
     * @param id
     * @return
     */
    int setN(int id);

    /**
     * setD
     * @param id
     * @return
     */
    int setD(int id);

    /**
     * setV
     * @param id
     * @return
     */
    int setV(int id);

    /**
     * say
     * @param en
     * @param response
     */
    void say(String en,HttpServletResponse response) throws IOException;


    /**
     * updateWord
     * @param wordQueryRequest WordDataRequest
     * @return int
     */
    int updateWord(WordDataRequest wordQueryRequest);

}
