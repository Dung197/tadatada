package com.halocom.Exercises.Model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "user_vote_question")
public class UserVoteQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "user_id")
    private int user;

    @Column(name = "question_id")
    private int question;

    @Column(name = "up_vote")
    private Boolean upVote;

    @Column(name = "down_vote")
    private Boolean downVote;

    @Column(name = "create_date")
    private Timestamp createDate;

    @Column(name = "update_date")
    private Timestamp updateDate;

    public int getQuestion() {
        return question;
    }

    public int getUser() {
        return user;
    }

    public int getId() {
        return id;
    }

    public Boolean getDownVote() {
        return downVote;
    }

    public Boolean getUpVote() {
        return upVote;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setQuestion(int question) {
        this.question = question;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public void setDownVote(Boolean downVote) {
        this.downVote = downVote;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUpVote(Boolean upVote) {
        this.upVote = upVote;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }
}
