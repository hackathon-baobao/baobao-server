package com.baobao.baobaoserver.client;

public record ProfileRecord(
    String profile_nickname,
    String profile_thumbnail_image,
    boolean allowed_msg,
    long id,
    String uuid,
    boolean favorite
) {}