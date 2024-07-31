package com.sparta.myselectshop.folder.repository;

import com.sparta.myselectshop.folder.dto.res.FolderResDto;
import com.sparta.myselectshop.folder.entity.Folder;
import com.sparta.myselectshop.user.entity.User;

import java.util.List;

public interface FolderRepositoryCustom {
	List<FolderResDto> findAllByUser(User user);
}
