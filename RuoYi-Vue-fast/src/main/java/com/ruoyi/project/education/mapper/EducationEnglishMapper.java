package com.ruoyi.project.education.mapper;

import com.ruoyi.project.education.domain.Word;
import com.ruoyi.project.education.domain.WordClass;
import com.ruoyi.project.education.domain.WordGroup;

import java.util.List;

/**
 * 角色表 数据层
 * 
 * @author ruoyi
 */
public interface EducationEnglishMapper
{

    /**
     * 查询所有角色
     * 
     * @return 角色列表
     */
    public List<Word> selectWordAll(Word word);

    List<Word> getGroupWordByGrouIdClassId(int gradeId, int  classId, int groupId);

    int getMaxGroupIdByClasId(int classId);

    int insertGroupByClassId(WordGroup wordGroup);

    public  void addWord(Word word);

    void updateWordPic(int wordId, String filePath);

    List<WordClass> getWordClassAllList();

    void updateWordByWordId(Word word);

    Word getEnglishWordPicName(int wordId);

    void deleteEnglishWordById(int wordId);
}
