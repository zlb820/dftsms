package org.common.test.daoTest;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.common.FDBK.dao.*;
import org.common.FDBK.domain.Areacounty_addr;
import org.common.FDBK.domain.City_addr;
import org.common.FDBK.domain.Province_addr;
import org.common.test.util.AbstranctUnit;
import org.common.util.ChangToJsonUtil;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;
@Ignore
public class TestThreeLevel extends AbstranctUnit{
	@Resource
	ProvinceDao<Province_addr> provinceDao;
	@Resource
	CityDao<City_addr> cityDao;
	@Resource
	AreacountyDao<Areacounty_addr> areacountDao;
	
	@Test
	public void testProvince(){
		List<Province_addr> result=provinceDao.getAllProvice();
		assertEquals("获取省份数量不正确", 34,result.size());
		Iterator<Province_addr> it=result.iterator();
		while(it.hasNext()){
			System.out.println(ChangToJsonUtil.toJson(it.next()));
		}
	}
	
	@Test
	public void testCity(){
		List<City_addr> result=cityDao.getCitysByProvinceCode("130000");
		assertNotNull("获取数量为空",result);
		Iterator<City_addr> it=result.iterator();
		while(it.hasNext()){
			System.out.println(ChangToJsonUtil.toJson(it.next()));
		}
	}
	
	@Test
	public void testAreacounty(){
		List<Areacounty_addr> result=areacountDao.getAllAreacountyByCityCode("130400");
		System.out.println(ChangToJsonUtil.toJson(result));
	}
	
	@Test
	public void testAll(){
		List<Province_addr> resultPro=provinceDao.getAllProvice();
		Iterator<Province_addr> it_pro=resultPro.iterator();
		int count=0;
		while (it_pro.hasNext()) {
			Province_addr pro=it_pro.next();
			System.out.println("######"+pro.getP_name()+"######");
			List<City_addr> resultCity=cityDao.getCitysByProvinceCode(pro.getP_code());
			Iterator<City_addr> it_city=resultCity.iterator();
			while(it_city.hasNext()){
				count++;
				City_addr city=it_city.next();
				System.out.println("------------------"+city.getC_name()+"-------------------------");
				List<Areacounty_addr> resultArea=areacountDao.getAllAreacountyByCityCode(city.getC_code());
				System.out.println(ChangToJsonUtil.toJson(resultArea));
			}
		}
		System.out.println("总数量为："+count);
	}

}
