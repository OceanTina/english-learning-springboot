package com.ruoyi.project.education.controller;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.config.RuoYiConfig;
import com.ruoyi.framework.security.LoginUser;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.education.domain.WordClass;
import com.ruoyi.project.system.domain.SysPost;
import com.ruoyi.project.education.domain.Word;
import com.ruoyi.project.education.service.IEducationEnglishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 岗位信息操作处理
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/education/english")
public class EducationEnglishController extends BaseController
{
    @Autowired
    IEducationEnglishService iEducationEnglishService;

    /**
     * 获取Word列表
     */
    @PreAuthorize("@ss.hasPermi('education:english:list')")
    @GetMapping("/list")
    public TableDataInfo list(Word word)
    {
        startPage();
        List<Word> list = iEducationEnglishService.selectWordAll(word);
        return getDataTable(list);
    }

    /**
     * 获取Word列表
     */
    @PreAuthorize("@ss.hasPermi('education:english:list')")
    @GetMapping("/getGroupWordByGrouIdClassId")
    public List<Word> getGroupWordByGrouIdClassId(int gradeId,int classId, int groupId)
    {
        startPage();
        List<Word> list = iEducationEnglishService.getGroupWordByGrouIdClassId(gradeId, classId, groupId);
        return list;
    }


    /**
     * 新增岗位
     */
    @PreAuthorize("@ss.hasPermi('education:english:list')")
    @Log(title = "岗位管理", businessType = BusinessType.INSERT)
    @PostMapping("/addWord")
    public AjaxResult add(@Validated @RequestBody Word word)
    {
        String en = word.getWordEn();
        //保存Word
        iEducationEnglishService.addWord(word);
        return null;
    }

    @PostMapping("/uploadFile")
    public AjaxResult uploadFile( MultipartFile file,int wordId) throws IOException
    {
        if (!file.isEmpty())
        {
            Word thisWord = iEducationEnglishService.getEnglishWordPicName(wordId);
            String wordPicName = thisWord.getGradeName() + "_" + thisWord.getClassName() + "_" +
                    thisWord.getGroupId() + "_" + thisWord.getId() + "_" + thisWord.getWordEn();
            String filePath = FileUploadUtils.uploadEnglishWordPic(RuoYiConfig.getAvatarPath(), file, wordPicName);
            if (iEducationEnglishService.updateWordPic(wordId, filePath))
            {
                AjaxResult ajax = AjaxResult.success();
                ajax.put("imgUrl", filePath);

                return ajax;
            }
        }
        return AjaxResult.error("上传图片异常，请联系管理员");
    }

    /**
     * 获取Word列表
     */
    @PreAuthorize("@ss.hasPermi('education:english:list')")
    @GetMapping("/getWordClassAllList")
    public List<WordClass> getWordClassAllList()
    {
        startPage();
        List<WordClass> list = iEducationEnglishService.getWordClassAllList();
        return list;
    }

    /**
     * 修改Word
     */
    @PreAuthorize("@ss.hasPermi('education:english:list')")
    @PostMapping("/updateWordByWordId")
    public AjaxResult updateWordByWordId(@Validated @RequestBody Word word)
    {
        iEducationEnglishService.updateWordByWordId(word);
        return null;
    }

    @PreAuthorize("@ss.hasPermi('education:english:list')")
    @PostMapping("/deleteEnglishWordById")
    public void deleteEnglishWordById(int wordId)
    {
        iEducationEnglishService.deleteEnglishWordById(wordId);
    }

}
