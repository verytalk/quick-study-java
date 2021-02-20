package com.quickstudy.api.admin.dao.word;

import com.quickstudy.api.admin.entity.word.Word;
import com.quickstudy.api.admin.entity.word.WordType;
import com.quickstudy.api.admin.domain.request.word.WordDataRequest;
import com.quickstudy.api.admin.domain.request.word.WordQueryRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@SuppressWarnings("ALL")
@Mapper
public interface WordDao {


    List<Word> wordList(WordQueryRequest wordQueryRequest);


    List<WordType> listWordType();

    int setY(WordQueryRequest wordQueryRequest);
    int setN(WordQueryRequest wordQueryRequest);
    int setD(WordQueryRequest wordQueryRequest);

    int setV(WordQueryRequest wordQueryRequest);

    Word findWordById(WordQueryRequest wordQueryRequest);

    int setInitialScore(WordQueryRequest wordQueryRequest);

    int updateWord(WordDataRequest wordDataRequest);
}
