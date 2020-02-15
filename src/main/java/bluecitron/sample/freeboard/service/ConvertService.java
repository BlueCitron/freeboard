package bluecitron.sample.freeboard.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ConvertService {

    private final ModelMapper modelMapper;

}
