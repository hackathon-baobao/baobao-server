package com.baobao.baobaoserver.client;

import java.util.List;

public record ElementsWrapper(
    List<ProfileRecord> elements,
    int total_count,
    String after_url,
    int favorite_count
) {}
