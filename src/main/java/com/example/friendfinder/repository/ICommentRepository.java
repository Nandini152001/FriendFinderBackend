package com.example.friendfinder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.friendfinder.entity.Comment;

public interface ICommentRepository extends JpaRepository<Comment,Long> {

}
