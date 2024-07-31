package com.sparta.myselectshop.folder.repository;

import com.sparta.myselectshop.folder.entity.Folder;
import com.sparta.myselectshop.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FolderRepository extends JpaRepository<Folder, Long>, FolderRepositoryCustom {
    List<Folder> findAllByUserAndNameIn(User user, List<String> folderNames);


}
