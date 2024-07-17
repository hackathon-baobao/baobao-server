package com.baobao.baobaoserver.tree;

import com.baobao.baobaoserver.member.Member;

public record Tree(
        String treeKind,
        Boolean bird,
        Boolean squirrel,
        Boolean deer,
        Boolean bunny,
        Long height
) {
    public static Tree of(Member member){
        return new Tree(
                member.getTreeKind(),
                AnimalState.isSet(member.getBird()),
                AnimalState.isSet(member.getSquirrel()),
                AnimalState.isSet(member.getDeer()),
                AnimalState.isSet(member.getBunny()),
                member.getHeight()
        );
    }
}