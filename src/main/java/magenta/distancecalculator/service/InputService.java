package magenta.distancecalculator.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface InputService {
    void input(MultipartFile document) throws IOException;
}