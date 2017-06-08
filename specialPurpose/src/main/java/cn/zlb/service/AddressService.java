package cn.zlb.service;

import cn.zlb.dto.AddressDto;
import cn.zlb.entity.TAddress;

public interface AddressService {
public void save(TAddress address);
public void modifyAddress(TAddress address);
public TAddress findAddress(String stoId);
}
