package com.pghome.service.impl;

import com.pghome.dto.AdvertDto;
import com.pghome.mapper.ad.AdvertMapper;
import com.pghome.pojo.Advert;
import com.pghome.service.AdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

/**
 * @Auther: tianws
 * @Date: 2018/11/29 10:23
 * @Description:
 */
@Service
public class AdvertServiceImpl implements AdvertService {

    @Autowired
    private AdvertMapper advertMapper;


    @Override
    public boolean addAdvert(AdvertDto dto) {
        Advert advert = new Advert();
        advert.setTitle(dto.getTitle());
        advert.setLink(dto.getTitle());
        advert.setWeight(dto.getWeight());
        File file = new File("/");
        try {
            dto.getImgFileName().transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return false;
    }
}
