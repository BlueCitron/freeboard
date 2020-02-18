package bluecitron.sample.freeboard.service;

import bluecitron.sample.freeboard.domain.Category;
import bluecitron.sample.freeboard.domain.Member;
import bluecitron.sample.freeboard.domain.Post;
import bluecitron.sample.freeboard.model.command.PostCommand;
import bluecitron.sample.freeboard.model.dto.PostDto;
import bluecitron.sample.freeboard.repository.CategoryRepository;
import bluecitron.sample.freeboard.repository.MemberRepository;
import bluecitron.sample.freeboard.repository.PostRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class PostServiceTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PostService postService;

    @BeforeEach
    void 초기화 () {
        Category category = Category.create("샘플_카테고리");
        Member member = Member.create("nickname", "admin", "admin", "admin@admin.com");
        Post post = Post.create(category, "샘플_포스트", "샘플_내용", member);

        categoryRepository.save(category);
        memberRepository.save(member);
        postRepository.save(post);
    }

    @Test
    void 포스트_조회 () {
        // given
        Category category = categoryRepository.findAll().get(0);
        PostCommand postCommand = new PostCommand();
        postCommand.setCategoryId(category.getId());

        // then
        List<PostDto> posts = postService.getPosts(postCommand);
        PostDto postDto = posts.get(0);
        Assertions.assertEquals(posts.size(), 1);
        Assertions.assertEquals(postDto.getTitle(), "샘플_포스트");
        Assertions.assertEquals(postDto.getContent(), "샘플_내용");
    }

    @Test
    void 엔티티_DTO_변환() {
        // given
        List<Post> all = postRepository.findAll();

        // when
        PostDto postDto = postService.convertToDto(all.get(0));

        // then
        Assertions.assertEquals(postDto.getTitle(), "샘플_포스트");
        Assertions.assertEquals(postDto.getContent(), "샘플_내용");
    }

    @Test
    void 포스트_생성() {
        Category category = categoryRepository.findAll().get(0);
        PostCommand postCommand = new PostCommand();
        postCommand.setCategoryId(category.getId());
        postCommand.setTitle("포스트_생성_테스트");
        postCommand.setContent("테스트");

        // when
        postService.insert("admin", postCommand);

        // then
        long count = postRepository.count();

        Assertions.assertEquals(count, 2);
    }

    @Test
    void 포스트_수정() {
        Post post = postRepository.findAll().get(0);
        PostCommand postCommand = new PostCommand();
        postCommand.setPostId(post.getId());
        postCommand.setTitle("포스트_수정_테스트");
        postCommand.setContent("포스트_수정_테스트");

        // when
        postService.update("admin", postCommand);

        // then
        Post findPost = postRepository.findAll().get(0);

        Assertions.assertEquals(findPost.getTitle(), "포스트_수정_테스트");
        Assertions.assertEquals(findPost.getContent(), "포스트_수정_테스트");
    }

    @Test
    void 포스트_삭제() {

    }
}