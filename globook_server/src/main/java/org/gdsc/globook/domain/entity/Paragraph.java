package org.gdsc.globook.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.gdsc.globook.domain.type.ELanguage;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
@Table(name = "paragraphs")
public class Paragraph {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "`index`", nullable = false)
    private Long index;

    @Column(name = "audio_url", nullable = false)
    private String audioUrl;

    // ------ Foreign Key ------ //
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_book_id", referencedColumnName = "id")
    private UserBook userBook;  // 도서 id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_id", referencedColumnName = "id")
    private File file;  // 도서 id

    @Builder(access = AccessLevel.PRIVATE)
    public Paragraph(String content, Long index, String audioUrl, UserBook userBook, File file) {
        this.content = content;
        this.index = index;
        this.audioUrl = audioUrl;
        this.userBook = userBook;
        this.file = file;
    }

    public static Paragraph createBookParagraph(String content, Long index, String audioUrl, UserBook userBook) {
        return Paragraph.builder()
                .content(content)
                .index(index)
                .audioUrl(audioUrl)
                .userBook(userBook)
                .file(null)
                .build();
    }

    public static Paragraph createFileParagraph(String content, Long index, String audioUrl, File file) {
        return Paragraph.builder()
                .content(content)
                .index(index)
                .audioUrl(audioUrl)
                .userBook(null)
                .file(file)
                .build();
    }

    public void updateAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }
}
