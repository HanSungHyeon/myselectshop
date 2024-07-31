package com.sparta.myselectshop.folder.service;

import com.sparta.myselectshop.folder.dto.res.FolderResDto;
import com.sparta.myselectshop.folder.entity.Folder;
import com.sparta.myselectshop.folder.repository.FolderRepository;
import com.sparta.myselectshop.user.entity.User;
import com.sparta.myselectshop.user.repository.UserRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FolderService {
	private final FolderRepository folderRepository;
	public void addFolders(List<String> folderNames, User user) {

		// 입력으로 들어온 폴더 이름을 기준으로, 회원이 이미 생성한 폴더들을 조회합니다.
		List<Folder> existFolderList = folderRepository.findAllByUserAndNameIn(user, folderNames);

		List<Folder> folderList = new ArrayList<>();

		for (String folderName : folderNames) {
			// 이미 생성한 폴더가 아닌 경우만 폴더 생성
			if (!isExistFolderName(folderName, existFolderList)) {
				Folder folder = new Folder(folderName, user);
				folderList.add(folder);
			} else {
				throw new IllegalArgumentException("폴더명이 중복되었습니다.");
			}
		}

		folderRepository.saveAll(folderList);
	}

	private Boolean isExistFolderName(String folderName, List<Folder> existFolderList) {
		// 기존 폴더 리스트에서 folder name 이 있는지?
		for (Folder existFolder : existFolderList) {
			if(folderName.equals(existFolder.getName())) {
				return true;
			}
		}
		return false;
	}
	public List<FolderResDto> getFolders(User user) {
		return folderRepository.findAllByUser(user);
	}

	public Folder findFolder(Long id) {
		Optional<Folder> findFolder = folderRepository.findById(id);

		return findFolder
				.orElseThrow(() -> new NullPointerException("존재하지 않습니다."));
	}
}
