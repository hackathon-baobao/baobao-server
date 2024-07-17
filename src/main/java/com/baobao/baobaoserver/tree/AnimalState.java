package com.baobao.baobaoserver.tree;

public enum AnimalState {
    NONE, HAVE, SET;
    public static boolean isSet(AnimalState state) {
        return state == AnimalState.SET;
    }
}
