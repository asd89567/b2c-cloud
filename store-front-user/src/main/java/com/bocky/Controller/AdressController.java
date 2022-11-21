package com.bocky.Controller;

import com.bocky.Service.AdressService;
import com.bocky.parm.AddressListParm;
import com.bocky.parm.AdressRemoveParm;
import com.bocky.pojo.Adress;
import com.bocky.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user/address")
public class AdressController {

    @Autowired
    private AdressService adressService;
    @PostMapping("list")
    public R list(@RequestBody @Validated AddressListParm addressListParm, BindingResult result){
        if (result.hasErrors()){
            return R.fail("參數異常");
        }
        return adressService.list(addressListParm.getUserId());
    }
    @PostMapping("save")
    public R save(@RequestBody @Validated Adress adress,BindingResult result){
        if (result.hasErrors()){
            return R.fail("參數異常");
        }
        return adressService.save(adress);
    }
    @PostMapping("remove")
    public R remove(@RequestBody @Validated AdressRemoveParm adressRemoveParm, BindingResult result){
        if (result.hasErrors()){
            return R.fail("參數異常");
        }
        return adressService.remove(adressRemoveParm.getId());
    }
}
