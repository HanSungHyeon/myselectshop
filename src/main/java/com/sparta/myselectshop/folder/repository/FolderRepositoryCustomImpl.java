package com.sparta.myselectshop.folder.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.myselectshop.folder.dto.res.FolderResDto;
import com.sparta.myselectshop.folder.entity.QFolder;
import com.sparta.myselectshop.user.entity.User;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.sparta.myselectshop.folder.entity.QFolder.folder;

@RequiredArgsConstructor
public class FolderRepositoryCustomImpl implements FolderRepositoryCustom{
	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public List<FolderResDto> findAllByUser(User user) {
		return jpaQueryFactory
				.select(Projections.bean(FolderResDto.class,
						folder.id,
						folder.name
						)
				)
				.from(folder)
				.where(folder.user.eq(user))
				.fetch();
	}
}
