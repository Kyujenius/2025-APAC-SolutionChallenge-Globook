package org.gdsc.globook.application.dto;

import java.util.Optional;
import lombok.AccessLevel;
import lombok.Builder;
import org.gdsc.globook.domain.entity.Book;
import org.gdsc.globook.domain.entity.UserBook;
import org.gdsc.globook.domain.type.EUserBookStatus;

@Builder(access = AccessLevel.PRIVATE)
public record BookThumbnailResponseDto(
        Long id,
        String title,
        String author,
        String imageUrl,
        String category,
        Long index,
        Long userBookId
) {
    public static BookThumbnailResponseDto fromEntity(Book book, Optional<UserBook> userBook) {
        return BookThumbnailResponseDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .imageUrl(book.getImageUrl())
                .category(String.valueOf(book.getCategory()))
                .index(userBook.map(UserBook::getIndex).orElse(null))
                .userBookId(userBook.map(UserBook::getId).orElse(0L))
                .build();
    }
}
