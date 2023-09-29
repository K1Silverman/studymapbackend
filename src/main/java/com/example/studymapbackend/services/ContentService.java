package com.example.studymapbackend.services;

import com.example.studymapbackend.dtos.*;
import com.example.studymapbackend.entities.Chapter;
import com.example.studymapbackend.entities.Folder;
import com.example.studymapbackend.entities.Post;
import com.example.studymapbackend.entities.Theme;
import com.example.studymapbackend.repositories.*;
import com.example.studymapbackend.repositories.mappers.*;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ContentService {

    @Resource
    private FolderRepository folderRepository;

    @Resource
    private FolderMapper folderMapper;

    @Resource
    private PostsRepository postsRepository;

    @Resource
    private PostsMapper postsMapper;

    @Resource
    private ThemeRepository themeRepository;

    @Resource
    private ThemeMapper themeMapper;

    @Resource
    private AttachmentRepository attachmentRepository;

    @Resource
    private AttachmentMapper attachmentMapper;

    @Resource
    private ChapterRepository chapterRepository;

    @Resource
    private ChapterMapper chapterMapper;

    @Autowired
    public ContentService(FolderRepository folderRepository, FolderMapper folderMapper, PostsRepository postsRepository, PostsMapper postsMapper, ThemeRepository themeRepository, ThemeMapper themeMapper, AttachmentRepository attachmentRepository, AttachmentMapper attachmentMapper, ChapterRepository chapterRepository, ChapterMapper chapterMapper) {
        this.folderRepository = folderRepository;
        this.folderMapper = folderMapper;
        this.postsRepository = postsRepository;
        this.postsMapper = postsMapper;
        this.themeRepository = themeRepository;
        this.themeMapper = themeMapper;
        this.attachmentRepository = attachmentRepository;
        this.attachmentMapper = attachmentMapper;
        this.chapterRepository = chapterRepository;
        this.chapterMapper = chapterMapper;
    }

    public List<FolderDto> getAllUserFolders(Integer userId) {
        List<Folder> userFolders = folderRepository.getAllUserFolders(userId);
        return folderMapper.toDtos(userFolders);
    }

    public List<FolderDto> addFolder(FolderDto newFolderDto) {
        Folder newFolder = folderMapper.toEntity(newFolderDto);
        newFolder.setPosition(getLastPositionOfFolder(newFolderDto.getUserId()));
        newFolder.setStatus("Active");
        folderRepository.save(newFolder);
        return folderMapper.toDtos(folderRepository.getAllUserFolders(newFolderDto.getUserId()));
    }

    private Integer getLastPositionOfFolder(Integer userId) {
        List<Folder> userFolders = folderRepository.getAllUserFolders(userId);
        Integer lastPosition = 0;
        for (Folder folder : userFolders) {
            if (folder.getPosition() > lastPosition) {
                lastPosition = folder.getPosition();
            }
        }
        return ++lastPosition;
    }

    private Integer getLastPositionOfChapter(Integer folderId) {
        List<Chapter> folderChapters = chapterRepository.getAllChaptersInFolder(folderId);
        Integer lastPosition = 0;
        for (Chapter chapter : folderChapters) {
            if (chapter.getPosition() > lastPosition) {
                lastPosition = chapter.getPosition();
            }
        }
        return ++lastPosition;
    }

    private Integer getLastPositionOfPost(Integer chapterId) {
        List<Post> userFolders = postsRepository.getAllChapterActivePosts(chapterId);
        Integer lastPosition = 0;
        for (Post post : userFolders) {
            if (post.getPosition() > lastPosition) {
                lastPosition = post.getPosition();
            }
        }
        return ++lastPosition;
    }

    public void createDefaultFolder(Integer userId) {
        Folder newFolder = new Folder();
        newFolder.setFolderName("Unfoldered subjects");
        newFolder.setPosition(0);
        newFolder.setUserId(userId);
        newFolder.setStatus("Default");
        folderRepository.save(newFolder);
    }

    public List<ChapterDto> getAllChaptersInFolder(Integer folderId) {
        return getAllChaptersWithContent(folderId);
    }

    private List<ChapterDto> getAllChaptersWithContent(Integer folderId) {
        List<Chapter> chaptersInFolder = chapterRepository.getAllChaptersInFolder(folderId);
        List<ChapterDto> chaptersInFolderDto = chapterMapper.toDtos(chaptersInFolder);
        for (ChapterDto chapter : chaptersInFolderDto) {
            List<Post> chapterPosts = postsRepository.getAllChapterActivePosts(chapter.getId());
            List<PostDto> chapterPostsDtos = postsMapper.toDtos(chapterPosts);
            chapter.setPosts(chapterPostsDtos);
        }
        return chaptersInFolderDto;
    }

    public List<ChapterDto> saveChapter(ChapterDto chapterDto) {
        if (chapterDto.getName() == null || chapterDto.getName().trim().isEmpty()) {
            List<Chapter> chaptersInSameFolder = chapterRepository.getAllChaptersInFolder(chapterDto.getFolderId());
            int unnamedChaptersCount = 0;
            for (Chapter chapter : chaptersInSameFolder) {
                if (chapter.getName().startsWith("Unnamed Chapter")) {
                    unnamedChaptersCount++;
                }
            }
            unnamedChaptersCount++;
            chapterDto.setName(String.format("Unnamed Chapter (%d)", unnamedChaptersCount));
        }
        if (chapterDto.getTheme() == null) {
            chapterDto.setTheme(themeMapper.toDto(themeRepository.getDefaultTheme()));
        }
        if (chapterDto.getPosition() == null || chapterDto.getPosition() < 1) {
            chapterDto.setPosition(getLastPositionOfChapter(chapterDto.getFolderId()));

        }
        Chapter newChapter = chapterRepository.save(chapterMapper.toEntity(chapterDto));
        return getAllChaptersInFolder(newChapter.getFolderId());
    }

    public PostDto savePost(PostDto postDto, List<AttachmentDto> attachments) {

//        LocalDateTime postTimestamp = LocalDateTime.parse(postDto.getTimestamp());

        Post newPost = postsMapper.toEntity(postDto);
        if (newPost.getPosition() == null || newPost.getPosition() < 1) {
            newPost.setPosition(getLastPositionOfPost(newPost.getChapterId()));
        }
//        newPost.setTimestamp(postTimestamp);

        // if (attachments == null || attachments.isEmpty()) {
        //
        // }
        // List<Attachment> newAttachments = attachmentMapper.toEntities(attachments);
        // TODO: Saving attachments functionality

        return postsMapper.toDto(postsRepository.save(newPost));
    }

    public Integer saveTheme(ThemeDto themeDto) {
        Theme newTheme = themeRepository.save(themeMapper.toEntity(themeDto));
        return newTheme.getId();
    }

    public Boolean validateFolderOwner(Integer userId, Integer folderId) {
        Optional<Folder> folder = folderRepository.validateFolderOwner(folderId, userId);
        return folder.isPresent();
    }
}
