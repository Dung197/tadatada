package com.halocom.Exercises.Repository;

import com.halocom.Exercises.Model.UserVoteQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserVoteQuestionRepository extends JpaRepository<UserVoteQuestion, Integer> {
    Optional<UserVoteQuestion> findByQuestionAndUser(@Param("question") Integer question, @Param("user") Integer user);
    List<UserVoteQuestion> findByQuestionAndUpVote(@Param("question") Integer question, @Param("upVote") boolean upVote);
    List<UserVoteQuestion> findByQuestionAndDownVote(@Param("question") Integer question, @Param("downVote") boolean downVote);


}
