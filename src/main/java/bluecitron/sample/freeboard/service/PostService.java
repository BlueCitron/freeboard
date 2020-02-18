package bluecitron.sample.freeboard.service;

import bluecitron.sample.freeboard.domain.Category;
import bluecitron.sample.freeboard.domain.Member;
import bluecitron.sample.freeboard.domain.MemberGrade;
import bluecitron.sample.freeboard.domain.Post;
import bluecitron.sample.freeboard.model.command.PostCommand;
import bluecitron.sample.freeboard.model.dto.PostDto;
import bluecitron.sample.freeboard.repository.CategoryRepository;
import bluecitron.sample.freeboard.repository.MemberRepository;
import bluecitron.sample.freeboard.repository.PostRepository;
import bluecitron.sample.freeboard.service.exception.CategoryEntityNotFoundException;
import bluecitron.sample.freeboard.service.exception.MemberEntityNotFoundException;
import bluecitron.sample.freeboard.service.exception.PostEntityNotFoundException;
import bluecitron.sample.freeboard.service.exception.UnauthorizedOperationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;

    /**
     * 1. 포스트 조회(리스트/단건)
     * 2. 포스트 등록
     * 3. 포스트 수정
     * 4. 포스트 삭제
     */

    public List<PostDto> getPosts(PostCommand postCommand) {
        Long categoryId = postCommand.getCategoryId();
        Category category = getCategory(categoryId);

        PageRequest pageRequest =
                PageRequest.of(postCommand.getPage(), postCommand.getOffset(), Sort.Direction.DESC, "createdDate");

        return postRepository.findByCategory(category, pageRequest).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public PostDto getPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostEntityNotFoundException(postId));
        return convertToDto(post);
    }

    public Long insert(String account, PostCommand postCommand) {
        Long categoryId = postCommand.getCategoryId();
        Long writerId = postCommand.getWriterId();

        Category category = getCategory(categoryId);
        Member writer = getMember(account);

        Post post = Post.create(category, postCommand.getTitle(), postCommand.getContent(), writer);
        return postRepository.save(post).getId();
    }

    /**
     * 1. 작성자 또는 관리자는 글을 수정할 수 있음
     * 2. 제목 또는 내용 수정
     */
    public Long update(String account, PostCommand postCommand) {
        Member writer = getMember(account);

        Long postId = postCommand.getPostId();
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostEntityNotFoundException(postId));

        if (Objects.equals(writer, post.getWriter()) ||
            writer.getGrade() == MemberGrade.ROLE_ADMIN) {
            post.setTitle(postCommand.getTitle());
            post.setContent(postCommand.getContent());
            return post.getId();
        } else {
            throw new UnauthorizedOperationException();
        }
    }

    /**
     * 작성자 또는 관리자는 글을 삭제할 수 있음
     */
    public Long delete(String account, Long id) {
        Member writer = getMember(account);
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostEntityNotFoundException(id));

        if (Objects.equals(writer, post.getWriter()) ||
                writer.getGrade() == MemberGrade.ROLE_ADMIN) {
            postRepository.delete(post);
            return id;
        } else {
            throw new UnauthorizedOperationException();
        }
    }

    public PostDto convertToDto(Post post) {
        return modelMapper.map(post, PostDto.class);
    }

    private Category getCategory(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryEntityNotFoundException(id));
    }

    private Member getMember(String account) {
        return memberRepository.findByAccount(account)
                .orElseThrow(() -> new MemberEntityNotFoundException(account));
    }

}
