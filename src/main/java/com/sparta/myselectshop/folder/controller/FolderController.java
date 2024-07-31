package com.sparta.myselectshop.folder.controller;

import com.sparta.myselectshop.folder.dto.req.FolderReqDto;
import com.sparta.myselectshop.folder.dto.res.FolderResDto;
import com.sparta.myselectshop.folder.service.FolderService;
import com.sparta.myselectshop.user.service.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FolderController {

	private final FolderService folderService;

	@PostMapping("/folders")
	public void addFolders(@RequestBody FolderReqDto dto,
	                       @AuthenticationPrincipal UserDetailsImpl userDetails) {

		List<String> folderNames = dto.getFolderNames();

		folderService.addFolders(folderNames, userDetails.getUser());
	}

	@GetMapping("folders")
	public ResponseEntity getFolders(@AuthenticationPrincipal UserDetailsImpl userDetails) {
		List<FolderResDto> res = folderService.getFolders(userDetails.getUser());

		return ResponseEntity.ok(res);
	}
}
