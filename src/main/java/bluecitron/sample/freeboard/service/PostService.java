package bluecitron.sample.freeboard.service;

import bluecitron.sample.freeboard.domain.Category;
import bluecitron.sample.freeboard.domain.MemberGrade;
import bluecitron.sample.freeboard.domain.Post;
import bluecitron.sample.freeboard.dto.PostDto;
import bluecitron.sample.freeboard.dto.SearchDto;
import bluecitron.sample.freeboard.repository.CategoryRepository;
import bluecitron.sample.freeboard.repository.PostRepository;
import bluecitron.sample.freeboard.service.exception.InvalidRequestException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static bluecitron.sample.freeboard.domain.MemberGrade.ROLE_NORMAL;

@Transactional
@RequiredArgsConstructor
@Service
public class PostService {

    private final ModelMapper modelMapper;
    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;

    /**
     * 1. 포스트 조회
     * 2. 포스트 등록
     * 3. 포스트 수정
     * 4. 포스트 삭제
     */

    public List<PostDto> getPosts(SearchDto searchDto) {
        Optional<Category> category = categoryRepository.findById(searchDto.getCategoryId());
        if (category.isPresent()) {
            PageRequest pageRequest = PageRequest.of(searchDto.getPage(), searchDto.getPerPage(), Sort.Direction.DESC);
            List<Post> byCategory = postRepository.findByCategory(category.get(), pageRequest);
            List<PostDto> posts = byCategory.stream()
                    .map(post -> convertToDto(post))
                    .collect(Collectors.toList());
            return posts;
        } else {
            throw new InvalidRequestException("유효하지 않은 카테고리 아이디 입니다. [" + searchDto.getCategoryId() + "]");
        }
    }

    public Long insert(PostDto postDto) {
        return 1L;
    }

    public Long update() {
        return 1L;
    }

    public Long delete() {
        return 1L;
    }

    public PostDto convertToDto(Post post) {
        return modelMapper.map(post, PostDto.class);
    }

}
