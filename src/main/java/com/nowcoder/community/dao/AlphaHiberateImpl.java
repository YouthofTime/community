package com.nowcoder.community.dao;

import org.springframework.stereotype.Repository;

@Repository
public class AlphaHiberateImpl implements AlphaDao {
    @Override
    public String select() {
        return "Hiberate";
    }
}
