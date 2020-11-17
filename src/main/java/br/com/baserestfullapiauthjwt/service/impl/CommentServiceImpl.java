package br.com.baserestfullapiauthjwt.service.impl;

import br.com.baserestfullapiauthjwt.model.Comment;
import br.com.baserestfullapiauthjwt.repository.CommentRepository;
import br.com.baserestfullapiauthjwt.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private static final Logger log = LoggerFactory.getLogger(CommentServiceImpl.class);

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Comment> findAll() {
        return this.commentRepository.findAll();
    }

    @Override
    public Optional<Comment> findById(Integer id) {
        return this.commentRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        this.commentRepository.deleteById(id);
    }

    @Override
    public void save(Comment comment) {
        this.commentRepository.save(comment);
    }
}