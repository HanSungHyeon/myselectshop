package com.sparta.myselectshop.folder.dto.res;

import com.sparta.myselectshop.folder.entity.Folder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class FolderResDto {
    private Long id;
    private String name;

    public FolderResDto(Folder folder) {
        this.id = folder.getId();
        this.name = folder.getName();
    }
}
