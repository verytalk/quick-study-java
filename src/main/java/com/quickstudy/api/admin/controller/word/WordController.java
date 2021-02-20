package com.quickstudy.api.admin.controller.word;

import com.github.pagehelper.PageInfo;
import com.quickstudy.api.admin.common.aspect.annotation.AuthRuleAnnotation;
import com.quickstudy.api.admin.common.util.translate.DoTranslate;
import com.quickstudy.api.admin.entity.word.Word;
import com.quickstudy.api.admin.entity.word.WordType;
import com.quickstudy.api.admin.domain.request.word.WordDataRequest;
import com.quickstudy.api.admin.domain.request.word.WordQueryRequest;
import com.quickstudy.api.admin.domain.response.PageSimpleResponse;
import com.quickstudy.api.admin.domain.response.word.WordQueryResponse;
import com.quickstudy.api.admin.service.word.WordService;
import com.quickstudy.api.admin.common.enums.ResultEnum;
import com.quickstudy.api.admin.domain.response.BaseResponse;
import com.quickstudy.api.admin.common.util.ResultVOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 管理员相关
 * @author Jason
 */
@RestController
public class WordController {

    @Resource
    private WordService wordService;

    /**
     * 获取列表
     */
    @AuthRuleAnnotation("admin/word/wordlist")
    @PostMapping("/admin/word/wordlist")
    public BaseResponse index(@Valid WordQueryRequest wordQueryRequest,
                              BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, bindingResult.getFieldError().getDefaultMessage());
        }

        List<Word> wordList = wordService.listWordPage(wordQueryRequest);
        List<WordQueryResponse> wordResponseList = wordList.stream().map(item -> {
            WordQueryResponse wordQueryResponse = new WordQueryResponse();
            BeanUtils.copyProperties(item, wordQueryResponse);
            return wordQueryResponse;

        }).collect(Collectors.toList());

        PageInfo<Word> pageInfo = new PageInfo<>(wordList);
        PageSimpleResponse<WordQueryResponse> pageSimpleResponse = new PageSimpleResponse<>();
        pageSimpleResponse.setTotal(pageInfo.getTotal());
        pageSimpleResponse.setList(wordResponseList);


        return ResultVOUtils.success(pageSimpleResponse);

    }


    @AuthRuleAnnotation("admin/word/getTypes")
    @RequestMapping("admin/word/getTypes")
    public BaseResponse getTypes() {

        List<WordType> wordTypes =  wordService.listWordType();
        return ResultVOUtils.success(wordTypes);
    }



    @AuthRuleAnnotation("admin/word/yes")
    @RequestMapping("admin/word/yes")
    public String setYes(
            @RequestParam("id") int id
    //        @PathVariable(name="id") int id
                  ) {
        int status = wordService.setY(id);
        String returnStatus = "error";

        if(status == 1 ){
            returnStatus = "success";

        }
        return returnStatus;
    }

    @AuthRuleAnnotation("admin/word/no")
    @RequestMapping("admin/word/no/{id}")
    public String setNo(@PathVariable(name="id") int id) {
        int status = wordService.setN(id);
        String returnStatus = "error";

        if(status == 1 ){
            returnStatus = "success";

        }

        return returnStatus;
    }

    @AuthRuleAnnotation("admin/word/setv")
    @RequestMapping("admin/word/setv/{id}")
    public String setV(@PathVariable(name="id") int id) {
        int status = wordService.setV(id);
        String returnStatus = "error";

        if(status == 1 ){
            returnStatus = "success";

        }

        return returnStatus;
    }

    @AuthRuleAnnotation("admin/word/setD")
    @RequestMapping("admin/word/setD/{id}")
    public String setD(@PathVariable(name="id") int id) {
        int status = wordService.setD(id);
        String returnStatus = "error";

        if(status == 1 ){
            returnStatus = "success";

        }

        return returnStatus;
    }




    @AuthRuleAnnotation("admin/word/updateWord")
    @PostMapping("admin/word/updateWord/{id}")
    public BaseResponse updateWord(@PathVariable(name="id") int id, WordDataRequest wordDataRequest) {

        int status = wordService.updateWord(wordDataRequest);
        String returnStatus = "error";
        if(status == 1 ){
            returnStatus = "success";

        }
        return ResultVOUtils.success(returnStatus);
    }

    @GetMapping("/say")
    public void say(@RequestParam String en, HttpServletResponse response) throws IOException {
        wordService.say(en,response);
    }

    @GetMapping("/translate")
    public String translate(@RequestParam String en) {
        return DoTranslate.translate(en);
    }



}
