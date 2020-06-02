package com.halocom.Exercises.Model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="answers")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int answerId;


    @Column(name = "question_id", nullable = false)
    private int question;

    @Column(name = "answer")
    private String answer;

    @Column(name = "user")
    private int user;

    @Column(name = "up_vote", columnDefinition = "integer default 0")
    private int upVote;

    @Column(name = "down_vote", columnDefinition = "integer default 0")
    private int downVote;

    @Column(name = "create_date")
    private Timestamp createDate;

    public int getAnswerId() {
        return answerId;
    }

    public int getQuestion() {
        return question;
    }

    public int getUser() {
        return user;
    }

    public int getUpVote() {
        return upVote;
    }

    public int getDownVote() {
        return downVote;
    }

    public String getAnswer() {
        return answer;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public void setQuestion(int question) {
        this.question = question;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public void setDownVote(int downVote) {
        this.downVote = downVote;
    }

    public void setUpVote(int upVote) {
        this.upVote = upVote;
    }

}
