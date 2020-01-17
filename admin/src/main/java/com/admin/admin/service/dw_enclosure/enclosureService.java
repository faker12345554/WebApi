package com.admin.admin.service.dw_enclosure;

import com.admin.admin.dao.dw_enclosure.enclosureDao;
import com.admin.admin.entity.dw_enclosure.enclosure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class enclosureService {

    @Autowired
    private enclosureDao enclosureDao;


    //新增
    public int saveEnclosure(enclosure enclosure) {
        enclosureDao.deleteEnclosure(enclosure.getPersonId());
//        for (Longitude iten:map.getPosition()){
//            enclosure enclosure=new enclosure();
//            enclosure.setPersonId(map.getPerson_id());
//            enclosure.setAreaName(map.getAreaname());
//            enclosure.setType(map.getType());
//            enclosure.setStatus(map.isStatus());
//            result.setData(enclosureService.saveEnclosure(enclosure));
//            System.out.println(result.getData());
//        }
//        if (enclosure.getId() != 0) {
//            return enclosureDao.updateEnclosure(enclosure);
//        }


        return enclosureDao.saveEnclosure(enclosure);
    }

    public int selectEnclosureByPersonId(String PersonId) {
        return enclosureDao.selectEnclosureByPersonId(PersonId);
    }

    //修改
    public int updateEnclosure(enclosure enclosure) {
        //        for (Longitude iten:map.getPosition()){
//            enclosure enclosure=new enclosure();
//            enclosure.setPersonId(map.getPerson_id());
//            enclosure.setAreaName(map.getAreaname());
//            enclosure.setType(map.getType());
//            enclosure.setStatus(map.isStatus());
//            enclosure.setLongitude(iten.getLongitude());
//            enclosure.setLatitude(iten.getLatitude());
//            result.setData(enclosureService.saveEnclosure(enclosure));
//            System.out.println(result.getData());
//        }
        return enclosureDao.updateEnclosure(enclosure);
    }

    //删除
    public int deleteEnclosure(String personId) {

        return enclosureDao.deleteEnclosure(personId);
    }


    //查看
    public List<enclosure> selectEnclosure(String personId) {
        //  List<enclosure> listEncLouSure=enclosureService.selectEnclosure(personId);
//        RangeMap rangeMap=new RangeMap();
//        List<Longitude> listLongitudes=new ArrayList<Longitude>();

//        for (enclosure item:listEncLouSure){
//            rangeMap.setPerson_id(item.getPersonId());
//            rangeMap.setType(item.getType());
//            rangeMap.setAreaname(item.getAreaName());
//            rangeMap.setStatus(item.isStatus());
//            Longitude longitude=new Longitude();
//            longitude.setLatitude(item.getLatitude());
//            longitude.setLongitude(item.getLongitude());
//            listLongitudes.add(longitude);
//        }
//        rangeMap.setPosition(listLongitudes);


        return enclosureDao.selectEnclosure(personId);
    }
}
