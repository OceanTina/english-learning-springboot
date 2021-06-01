package com.ruoyi.project.education.service;

import com.ruoyi.project.education.domain.Word;
import com.ruoyi.project.education.domain.WordClass;

import java.util.List;

/**
 * 岗位信息 服务层
 * 
 * @author ruoyi
 */
public interface IEducationEnglishService
{

    /**
     * 查询所有岗位
     * 
     * @return 岗位列表
     */
    public List<Word> selectWordAll(Word word);

    List<Word> getGroupWordByGrouIdClassId(int gradeId, int classId, int groupId);

    public void addWord(Word word);

    public boolean updateWordPic(int wordId, String filePath);

    List<WordClass> getWordClassAllList();

    void updateWordByWordId(Word word);

    Word getEnglishWordPicName(int wordId);

    void deleteEnglishWordById(int wordId);
}
