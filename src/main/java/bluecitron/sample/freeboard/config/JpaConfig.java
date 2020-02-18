package bluecitron.sample.freeboard.config;

import bluecitron.sample.freeboard.domain.Post;
import bluecitron.sample.freeboard.model.dto.PostDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JpaConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        TypeMap<Post, PostDto> typeMap = modelMapper.createTypeMap(Post.class, PostDto.class);

        typeMap.addMappings(mapper -> {
            mapper.map(Post::getLikeCount, PostDto::setPostLikes);
            mapper.map(Post::getDislikeCount, PostDto::setPostDislikes);
        });

        return modelMapper;
    }

}
