package com.ruoyi.project.education.domain;

public class Word {
    private int id;
    private int gradeId;
    private int groupState;
    private int classId;
    private int groupId;
    private String wordEn;
    private String wordCn;
    private String wordPic;

    private String gradeName;
    private String className;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGradeId() {
        return gradeId;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getWordEn() {
        return wordEn;
    }

    public void setWordEn(String wordEn) {
        this.wordEn = wordEn;
    }

    public String getWordCn() {
        return wordCn;
    }

    public void setWordCn(String wordCn) {
        this.wordCn = wordCn;
    }

    public String getWordPic() {
        return wordPic;
    }

    public void setWordPic(String wordPic) {
        this.wordPic = wordPic;
    }

    public int getGroupState() {
        return groupState;
    }

    public void setGroupState(int groupState) {
        this.groupState = groupState;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
