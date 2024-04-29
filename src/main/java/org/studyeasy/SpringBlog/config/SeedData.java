package org.studyeasy.SpringBlog.config;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.studyeasy.SpringBlog.models.Account;
import org.studyeasy.SpringBlog.models.Authority;
import org.studyeasy.SpringBlog.models.Post;
import org.studyeasy.SpringBlog.services.AccountService;
import org.studyeasy.SpringBlog.services.AuthorityService;
import org.studyeasy.SpringBlog.services.PostService;
import org.studyeasy.SpringBlog.util.constants.Privillages;
import org.studyeasy.SpringBlog.util.constants.Roles;

@Component
public class SeedData implements CommandLineRunner {

     @Autowired
     private PostService postService;

     @Autowired
     private AccountService accountService;

     @Autowired
     private AuthorityService authorityService;

     @Override
     public void run(String... args) throws Exception {

          for (Privillages auth : Privillages.values()) {
               Authority authority = new Authority();
               authority.setId(auth.getId());
               authority.setName(auth.getPrivillage());
               authorityService.save(authority);

          }

          Account account01 = new Account();
          Account account02 = new Account();
          Account account03 = new Account();
          Account account04 = new Account();

          account01.setEmail("siddharthverma1201@gmail.com");
          account01.setPassword("pass987");
          account01.setFirstname("User");
          account01.setLastname("lastname");
          account01.setAge(25);
          account01.setDate_of_birth(LocalDate.parse("1990-01-01"));
          account01.setGender("Male");

          account02.setEmail("admin@admin.com");
          account02.setPassword("pass987");
          account02.setFirstname("Admin");
          account02.setLastname("lastname");
          account02.setRole(Roles.ADMIN.getRole());
          account02.setAge(27);
          account02.setDate_of_birth(LocalDate.parse("1990-01-01"));
          account02.setGender("female");

          account03.setEmail("editor@editor.com");
          account03.setPassword("pass987");
          account03.setFirstname("Editor");
          account03.setLastname("lastname");
          account03.setRole(Roles.EDITOR.getRole());
          account03.setAge(34);
          account03.setDate_of_birth(LocalDate.parse("1990-01-01"));
          account03.setGender("Male");

          account04.setEmail("super_editor@editor.com");
          account04.setPassword("pass987");
          account04.setFirstname("Editor");
          account04.setLastname("lastname");
          account04.setRole(Roles.EDITOR.getRole());
          account04.setAge(27);
          account04.setDate_of_birth(LocalDate.parse("1990-01-01"));
          account04.setGender("Male");
          Set<Authority> authorities = new HashSet<>();
          authorityService.findById(Privillages.ACCESS_ADMIN_PANEL.getId()).ifPresent(authorities::add);
          authorityService.findById(Privillages.RESET_ANY_USER_PASSWORD.getId()).ifPresent(authorities::add);
          account04.setAuthorities(authorities);

          accountService.save(account01);
          accountService.save(account02);
          accountService.save(account03);
          accountService.save(account04);

          List<Post> posts = postService.findAll();
          if (posts.size() == 0) {
               Post post01 = new Post();
               post01.setTitle("Post 01");
               post01.setBody("Post 01 body.....................");
               post01.setAccount(account01);
               postService.save(post01);

               Post post02 = new Post();
               post02.setTitle("Post 02");
               post02.setBody("Post 02 body.....................");
               post02.setAccount(account02);
               postService.save(post02);

          }

     }

}
