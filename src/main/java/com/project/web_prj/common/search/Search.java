package com.project.web_prj.common.search;

import com.project.web_prj.common.paging.Page;
import lombok.*;

@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class Search extends Page {

//    private Page page; // 상속으로 바뀜
    private String type;    // 검색 조건
    private String keyword; // 검색 키워드
}
