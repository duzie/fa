package com.f.fa.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.f.fa.pojo.Ssq;

import java.io.IOException;

public interface SsqService extends IService<Ssq> {

    void init() throws IOException;

    void init500() throws IOException;
}
