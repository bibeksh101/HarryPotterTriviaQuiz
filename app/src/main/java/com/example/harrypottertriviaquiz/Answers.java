package com.example.harrypottertriviaquiz;

public class Answers {
    private int mQuestionID;
    private String manswerID01;
    private String manswerID02;
    private String manswerID03;
    private String manswerID04;
    private String manswerID;


    public Answers(int qID, String ansID, String ansOpt01, String ansOpt02, String ansOpt03, String ansOpt04){
        mQuestionID = qID;
        manswerID = ansID;
        manswerID01 = ansOpt01;
        manswerID02 = ansOpt02;
        manswerID03 = ansOpt03;
        manswerID04 = ansOpt04;
    }

    public String getManswerID() {
        return manswerID;
    }

    public void setManswerID(String manswerID) {
        this.manswerID = manswerID;
    }

    public int getQuestionID() {
        return mQuestionID;
    }

    public void setQuestionID(int questionID) {
        mQuestionID = questionID;
    }

    public String getManswerID01() {
        return manswerID01;
    }

    public void setManswerID01(String manswerID01) {
        this.manswerID01 = manswerID01;
    }

    public String getManswerID02() {
        return manswerID02;
    }

    public void setManswerID02(String manswerID02) {
        this.manswerID02 = manswerID02;
    }

    public String getManswerID03() {
        return manswerID03;
    }

    public void setManswerID03(String manswerID03) {
        this.manswerID03 = manswerID03;
    }

    public String getManswerID04() {
        return manswerID04;
    }

    public void setManswerID04(String manswerID04) {
        this.manswerID04 = manswerID04;
    }
}
