package br.com.idp.cc.fp1.idpbook.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.idp.cc.fp1.idpbook.model.Post;
import br.com.idp.cc.fp1.idpbook.model.User;
import br.com.idp.cc.fp1.idpbook.repository.PostRepository;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> findAllPosts() {
        return postRepository.findAll();
    }

    public Optional<Post> findPostById(Long id) {
        return postRepository.findById(id);
    }

    public Post savePost(Post post, User user) {
        post.setPostedBy(user);
        return postRepository.save(post);
    }

    public Post updatePost(Long id, Post updatedPost) {
        Optional<Post> postOptional = postRepository.findById(id);

        if (postOptional.isPresent()) {
            Post existingPost = postOptional.get();
            existingPost.setTitle(updatedPost.getTitle());
            existingPost.setDescription(updatedPost.getDescription());
            existingPost.setFileUrl(updatedPost.getFileUrl());
            existingPost.setPostDate(updatedPost.getPostDate());

            return postRepository.save(existingPost);
        } else {
            throw new RuntimeException("Post não encontrado: " + id);
        }
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
