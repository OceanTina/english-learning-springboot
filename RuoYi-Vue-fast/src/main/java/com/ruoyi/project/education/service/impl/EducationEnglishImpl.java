package com.ruoyi.project.education.service.impl;

import com.ruoyi.project.education.domain.Word;
import com.ruoyi.project.education.domain.WordClass;
import com.ruoyi.project.education.domain.WordGroup;
import com.ruoyi.project.education.mapper.EducationEnglishMapper;
import com.ruoyi.project.education.service.IEducationEnglishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 岗位信息 服务层处理
 * 
 * @author ruoyi
 */
@Service
public class EducationEnglishImpl implements IEducationEnglishService
{
    @Autowired
    private EducationEnglishMapper educationEnglishMapper;

    @Override
    public List<Word> selectWordAll(Word word) {
        return educationEnglishMapper.selectWordAll(word);
    }

    @Override
    public List<Word> getGroupWordByGrouIdClassId(int gradeId, int classId, int groupId) {
        List<Word> wordList = educationEnglishMapper.getGroupWordByGrouIdClassId( gradeId,  classId, groupId);
        int groupIndex = groupId -1;
        List<Integer> groupIdList = wordList.parallelStream().map(x -> x.getGroupId()).distinct().collect(Collectors.toList());
        Collections.reverse(groupIdList);
        if (groupIndex >= groupIdList.size()) {
            return null;
        }else {
            int currGroupId = groupIdList.get(groupIndex);
            List<Word> currGroupWordList = wordList.parallelStream().filter(x -> x.getGroupId() == currGroupId)
                    .sorted(Comparator.comparing(Word::getId)).collect(Collectors.toList());

            return currGroupWordList;
        }

    }

    @Override
    public void addWord(Word word) {
        int groupState = word.getGroupState();
        int maxGroupId = educationEnglishMapper.getMaxGroupIdByClasId(word.getClassId());
        if (1 == groupState) {
            if (maxGroupId == 0) {
                WordGroup wordGroup = new WordGroup();
                wordGroup.setClassId(word.getClassId());
                educationEnglishMapper.insertGroupByClassId(wordGroup);
                word.setGroupId(wordGroup.getId());
            }else {
                word.setGroupId(maxGroupId);
            }

        }else {
            WordGroup wordGroup = new WordGroup();
            wordGroup.setClassId(word.getClassId());
            educationEnglishMapper.insertGroupByClassId(wordGroup);

            word.setGroupId(wordGroup.getId());
        }

        educationEnglishMapper.addWord(word);
    }

    @Override
    public boolean updateWordPic(int wordId, String filePath) {
        educationEnglishMapper.updateWordPic(wordId, filePath);
        return true;
    }

    @Override
    public List<WordClass> getWordClassAllList() {
        List<WordClass> wordClassList = educationEnglishMapper.getWordClassAllList();
        return wordClassList;
    }

    @Override
    public void updateWordByWordId(Word word) {
        educationEnglishMapper.updateWordByWordId(word);
    }

    public  Word getEnglishWordPicName(int wordId) {
        return educationEnglishMapper.getEnglishWordPicName(wordId);
    }

    public void deleteEnglishWordById(int wordId) {
        educationEnglishMapper.deleteEnglishWordById(wordId);
    }
}
