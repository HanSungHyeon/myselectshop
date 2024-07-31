package com.sparta.myselectshop.folder.dto.req;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class FolderReqDto {
	List<String> folderNames;
}
