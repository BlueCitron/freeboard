package bluecitron.sample.freeboard.domain;

import bluecitron.sample.freeboard.domain.exception.InvalidOperationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

@Transactional
@SpringBootTest
class PostTest {

    @Autowired
    EntityManager em;

    @BeforeEach
    void 초기화() {
        Member member = new Member("BlueCitron", "sds901234", "1234", "sds901234@naver.com");
        Category sample = new Category(null, "sample");
        Post post = new Post(sample, "제목", "본문", member);

        em.persist(member);
        em.persist(sample);
        em.persist(post);
    }

    @Test
    void 좋아요() {
        Post post = em.createQuery("select p from Post p", Post.class).getSingleResult();
        Member member = em.createQuery("select m from Member m", Member.class).getSingleResult();

        // when
        PostLike postLike = post.like(member);


        // then
        PostLike findPostLike = em.createQuery("select pl from PostLike pl", PostLike.class).getSingleResult();

        Assertions.assertEquals(post.getPostLikes().size(), 1);
        Assertions.assertEquals(postLike, findPostLike);
    }

    @Test
    void 좋아요_취소() {
        Post post = em.createQuery("select p from Post p", Post.class).getSingleResult();
        Member member = em.createQuery("select m from Member m", Member.class).getSingleResult();
        PostLike postLike = post.like(member);

        // when
        post.likeCancel(member);

        // then
        Assertions.assertThrows(NoResultException.class, () -> {
            em.createQuery("select pl from PostLike pl", PostLike.class).getSingleResult();
        });
    }

    @Test
    void 좋아요_중복() {
        Post post = em.createQuery("select p from Post p", Post.class).getSingleResult();
        Member member = em.createQuery("select m from Member m", Member.class).getSingleResult();
        PostLike postLike = post.like(member);
        // when
        // then
        Assertions.assertThrows(InvalidOperationException.class, () -> {
            post.like(member);
        });
    }

    @Test
    void 싫어요() {
        Post post = em.createQuery("select p from Post p", Post.class).getSingleResult();
        Member member = em.createQuery("select m from Member m", Member.class).getSingleResult();

        // when
        PostDislike postDislike = post.dislike(member);


        // then
        PostDislike findPostDislike = em.createQuery("select pd from PostDislike pd", PostDislike.class).getSingleResult();

        Assertions.assertEquals(post.getPostDislikes().size(), 1);
        Assertions.assertEquals(postDislike, findPostDislike);
    }

    @Test
    void 싫어요_취소() {
        Post post = em.createQuery("select p from Post p", Post.class).getSingleResult();
        Member member = em.createQuery("select m from Member m", Member.class).getSingleResult();
        PostDislike postDislike = post.dislike(member);

        // when
        post.dislikeCancel(member);

        // then
        Assertions.assertThrows(NoResultException.class, () -> {
            em.createQuery("select pd from PostDislike pd", PostDislike.class).getSingleResult();
        });
    }

    @Test
    void 싫어요_중복() {
        Post post = em.createQuery("select p from Post p", Post.class).getSingleResult();
        Member member = em.createQuery("select m from Member m", Member.class).getSingleResult();
        PostDislike postDislike = post.dislike(member);

        // when
        // then
        Assertions.assertThrows(InvalidOperationException.class, () -> {
            post.dislike(member);
        });
    }
}