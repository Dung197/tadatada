package com.halocom.Exercises.Model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
   // @OneToMany(mappedBy = "question_id")
    private int questionId;

    @Column(name="question_name", nullable = false)
    private String questionName;

    @Column(name = "user")
    private int user;

    @Column(name = "tag")
    private String tag;

    @Column(name = "create_date")
    private Timestamp createDate;

    @Column(name = "up_vote", columnDefinition = "integer default 0")
    private int upVote;

    @Column(name = "down_vote", columnDefinition = "integer default 0")
    private int downVote;

    @Column(name = "active",  columnDefinition = "tinyint(1) default 1")
    private boolean active=true;

    public int getQuestionId() {
        return questionId;
    }

    public String getQuestionName() {
        return questionName;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public int getUser() {
        return user;
    }

    public String getTag() {
        return tag;
    }

    public int getDownVote() {
        return downVote;
    }

    public int getUpVote() {
        return upVote;
    }

    public boolean getActive() {
        return active;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setUpVote(int upVote) {
        this.upVote = upVote;
    }

    public void setDownVote(int downVote) {
        this.downVote = downVote;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setUser(int user) {
        this.user = user;
    }
}
