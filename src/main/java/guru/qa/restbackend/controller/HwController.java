package guru.qa.restbackend.controller;

import guru.qa.restbackend.domain.hw.Authors;
import guru.qa.restbackend.exception.InvalidUsernameException;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class HwController {
    public Map<String, Authors> books = Map.of(
            "Пушкин", Authors.builder().authorName("Пушкин")
                    .book("Капитанская Дочка").build(),
            "Достаевский", Authors.builder().authorName("Достаевский")
                    .book("Преступление и наказание").build()
    );

    @GetMapping("book/get")
    @ApiOperation("Получение книг по автору")
    public List<Authors> getAllBooks(@RequestParam Authors authorName) {
        if (authorName.getAuthorName().equals("Пушкин")) {
            return books.entrySet()
                    .stream()
                    .map(Map.Entry::getValue)
                    .collect(Collectors.toList());
        }
        if (authorName.getAuthorName().equals("Достаевский")) {
            return books.entrySet()
                    .stream()
                    .map(Map.Entry::getValue)
                    .collect(Collectors.toList());
        }
        else {
            throw new InvalidUsernameException();
        }
    }
}