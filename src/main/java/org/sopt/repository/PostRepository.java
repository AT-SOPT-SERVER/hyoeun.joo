package org.sopt.repository;

import org.sopt.domain.Post;

import java.util.ArrayList;
import java.util.List;


public class PostRepository {
    List<Post> postList = new ArrayList<>();

    public void save(Post post) {
        postList.add(post);
    }

    public List<Post> findAll() {
        return postList;
    }

    public Post findPostById(int id) {
        for (Post post : postList) {
            if (post.getId() == id) {
                return post;
            }
        }
        return null;
    }

    public boolean isExistByTitle(String title) {
        for (Post post : postList) {
            //대소문자를 구별하지 않고 확인
            if (post.getTitle().equalsIgnoreCase(title)) {
                return true;
            }
        }
        return false;
    }

    public List<Post> findPostByKeyWord(String keyword) {
        List<Post> searchResult = new ArrayList<>();

        for (Post post : postList) {
            if (post.getTitle().contains(keyword)) {
                searchResult.add(post);
            }
        }
        return searchResult;
    }

    public boolean delete(int id) {
        for (Post post : postList) {
            if (post.getId() == id) {
                postList.remove(post);
                return true;
            }
        }
        return false;
    }
}
