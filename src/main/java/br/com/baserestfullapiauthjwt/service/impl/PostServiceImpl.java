package br.com.baserestfullapiauthjwt.service.impl;

import br.com.baserestfullapiauthjwt.model.Post;
import br.com.baserestfullapiauthjwt.repository.PostRepository;
import br.com.baserestfullapiauthjwt.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private static final Logger log = LoggerFactory.getLogger(PostServiceImpl.class);

    @Autowired
    private PostRepository postRepository;

    @Override
    public List<Post> findAll() {
        return this.postRepository.findAll();
    }

    @Override
    public Optional<Post> findById(Integer id) {
        return this.postRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        this.postRepository.deleteById(id);
    }

    @Override
    public void save(Post post) {
        this.postRepository.save(post);
    }
}