package com.bocky.Service;

import com.bocky.pojo.Adress;
import com.bocky.utils.R;

public interface AdressService {
    R list(Integer userId);

    R save(Adress adress);

    R remove(Integer id);
}
