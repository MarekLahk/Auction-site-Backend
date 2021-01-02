package com.ibay.backend.theory.c_theory.question14.blogs;

import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequestMapping("/api/v1/blog")
@RestController
@NoArgsConstructor
public class BlogsController {

    //todo for question 14 there are 4 assignments in total
    // Each person has to do only 1. So 2 person team has to do 2 different ones, 3 person - 3, 4 person - 4.
    // Make sure to commit under your user otherwise points won't count.
    // I didn't number these so you can pick your favorite

    //todo
    // You are creating a rest controller for blogs. Think blog aggregator or blog collection.
    // You need to add necessary annotations and methods to this class.
    // This class should compile.
    // It should run successfully when moved to your application package.
    // Method body is not important and will not be graded.
    // Modifying other classes is unnecessary and will not be graded.

    //todo A add necessary annotations on the class


    //todo C create a method to query single blog
    @GetMapping(path = "{name}")
    public Blog getBlogByName(@PathVariable String name) {
        return new Blog();
    }

    //todo D create a method to save a new blog
    @PostMapping
    public Blog addNewBlog(@RequestBody Blog blog) {return new Blog();}

    //todo E create a method to update a blog
    @PutMapping
    public Blog updateBlog(@RequestBody Blog blog) {return new Blog();}

    //todo F create a method to delete a blog
    @DeleteMapping(path = "{name}")
    public void deleteBlog(@PathVariable String name) {}

    //todo G assuming each blog has only 1 author (one-to-one relation) create a method to query blog's author
    @GetMapping(path = "author/{blogName}")
    public Author getAuthor(@PathVariable String blogName) {
        return new Author();
    }

    //todo H create a method to update blog url (and nothing else)
    @PutMapping(path = "{name}")
    public Blog updateBlogUrl(@PathVariable String name, @RequestBody String newUrl) {return new Blog();}

    //todo B create a method to query blogs (plural)
    //querying for all blogs
    //todo I-J modify correct method to support pagination, pagination is done by page and size
    //todo I add page (pagination starts at page 1)
    //example blog?page=1
    //todo J add size (default page size is 20)
    //example blog?page=1&size=30
    @GetMapping
    public List<Blog> getBlogs(@RequestParam Map<String, String> parameters) {return new ArrayList<>();}

    //todo K modify correct method to order blogs
    // * by most recent first
    // * by least recent first
    // (you can assume that by default it searches by most popular first)
    //example blog?sorted=latest
    //example blog?sorted=recent

}
