package com.baobao.baobaoserver.util;

import java.util.Random;

public class RandomTreeSelector {

    public static String getRandomTree() {
        String[] trees = {"참나무", "소나무", "자작나무", "바오밥나무"};
        Random random = new Random();
        int randomIndex = random.nextInt(trees.length);
        return trees[randomIndex];
    }

    public static void main(String[] args) {
        System.out.println("랜덤으로 선택된 나무: " + getRandomTree());
    }
}