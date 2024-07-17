package com.baobao.baobaoserver.tree;

import com.baobao.baobaoserver.common.Response;
import com.baobao.baobaoserver.common.ResponseData;
import com.baobao.baobaoserver.member.Member;
import com.baobao.baobaoserver.member.MemberRepository;
import com.baobao.baobaoserver.member.MemberSessionHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TreeService {
    private final MemberRepository memberRepository;
    private final MemberSessionHolder sessionHolder;

    @Transactional(rollbackFor = Exception.class)
    public Response grow(Long point){
        Member curMember = sessionHolder.current();
        if(curMember.getPoint()<point) throw new LessPointException();
        curMember.addHeight(point);
        memberRepository.save(curMember);
        return Response.ok("성장 성공");
    }

    @Transactional(rollbackFor = Exception.class)
    public Response changeTree(String tree){
        Member curMember = sessionHolder.current();
        if(curMember.getPoint()<1000L) throw new LessPointException();
        curMember.changeTree(tree);
        memberRepository.save(curMember);
        return Response.ok("성장 성공");
    }

    public ResponseData<Tree> read(){
        Member curMember = sessionHolder.current();
        return ResponseData.ok("내 나무 조회 성공",Tree.of(curMember));
    }

    @Transactional(rollbackFor = Exception.class)
    public Response addAnimal(String animal){
        Member curMember = sessionHolder.current();
        if(curMember.getPoint()<500L) throw new LessPointException();
        curMember.minusPoint(500L);
        switch (animal) {
            case "bird" -> {
                if (curMember.getBird().equals(AnimalState.HAVE)) throw new HaveNotAnimalException();
                curMember.setBird(AnimalState.SET);
            }
            case "squirrel" -> {
                if (curMember.getSquirrel().equals(AnimalState.HAVE)) throw new HaveNotAnimalException();
                curMember.setSquirrel(AnimalState.SET);
            }
            case "bunny" -> {
                if (curMember.getBunny().equals(AnimalState.HAVE)) throw new HaveNotAnimalException();
                curMember.setBunny(AnimalState.SET);
            }
            case "deer" -> {
                if (curMember.getBunny().equals(AnimalState.HAVE)) throw new HaveNotAnimalException();
                curMember.setDeer(AnimalState.SET);
            }
        }
        memberRepository.save(curMember);
        return Response.ok("동물 추가 성공");
    }

    @Transactional(rollbackFor = Exception.class)
    public Response setAnimal(String animal){
        Member curMember = sessionHolder.current();
        switch (animal) {
            case "bird" -> {
                if (curMember.getBird().equals(AnimalState.NONE)) throw new HaveNotAnimalException();
                curMember.setBird(AnimalState.SET);
            }
            case "squirrel" -> {
                if (curMember.getSquirrel().equals(AnimalState.NONE)) throw new HaveNotAnimalException();
                curMember.setSquirrel(AnimalState.SET);
            }
            case "bunny" -> {
                if (curMember.getBunny().equals(AnimalState.NONE)) throw new HaveNotAnimalException();
                curMember.setBunny(AnimalState.SET);
            }
            case "deer" -> {
                if (curMember.getBunny().equals(AnimalState.NONE)) throw new HaveNotAnimalException();
                curMember.setDeer(AnimalState.SET);
            }
        }
        memberRepository.save(curMember);
        return Response.ok("동물 추가 성공");
    }

    @Transactional(rollbackFor = Exception.class)
    public Response disableAnimal(String animal){
        Member curMember = sessionHolder.current();
        switch (animal) {
            case "bird" -> {
                if (curMember.getBird().equals(AnimalState.NONE)) throw new HaveNotAnimalException();
                curMember.setBird(AnimalState.HAVE);
            }
            case "squirrel" -> {
                if (curMember.getSquirrel().equals(AnimalState.NONE)) throw new HaveNotAnimalException();
                curMember.setSquirrel(AnimalState.HAVE);
            }
            case "bunny" -> {
                if (curMember.getBunny().equals(AnimalState.NONE)) throw new HaveNotAnimalException();
                curMember.setBunny(AnimalState.HAVE);
            }
            case "deer" -> {
                if (curMember.getBunny().equals(AnimalState.NONE)) throw new HaveNotAnimalException();
                curMember.setDeer(AnimalState.HAVE);
            }
        }
        memberRepository.save(curMember);
        return ResponseData.ok("동물 비활성화 성공");
    }
}
