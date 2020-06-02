package com.halocom.Exercises.Repository;

import com.halocom.Exercises.Model.UserVoteAnswer;
import com.halocom.Exercises.Model.UserVoteQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserVoteAnswerRepository extends JpaRepository<UserVoteAnswer, Integer> {
    Optional<UserVoteAnswer> findByAnswerAndUser(@Param("answer") Integer answer, @Param("user") Integer user);
    List<UserVoteAnswer> findByAnswerAndUpVote(@Param("answer") Integer answer, @Param("upVote") boolean upVote);
    List<UserVoteAnswer> findByAnswerAndDownVote(@Param("answer") Integer answer, @Param("downVote") boolean downVote);
}
