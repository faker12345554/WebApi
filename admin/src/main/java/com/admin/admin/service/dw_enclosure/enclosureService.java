package com.admin.admin.service.dw_enclosure;

import com.admin.admin.dao.dw_enclosure.enclosureDao;
import com.admin.admin.entity.dw_enclosure.enclosure;
import com.common.common.result.ResponseResult;
import com.common.common.result.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class enclosureService {

    @Autowired
    private enclosureDao enclosureDao;
    private ResponseResult result = new ResponseResult();

    //新增
    public ResponseResult saveEnclosure(enclosure enclosure) {
        if (enclosureDao.selectEnclosureByPersonId(enclosure.getPersonId()) > 0) {
            result.setCode(ResultCode.DATA_DUPLICATION.getCode());
            result.setMessage(ResultCode.DATA_DUPLICATION.getMessage());
            return result.setData("已存在位置信息");
        }
//        for (Longitude iten:map.getPosition()){
//            enclosure enclosure=new enclosure();
//            enclosure.setPersonId(map.getPerson_id());
//            enclosure.setAreaName(map.getAreaname());
//            enclosure.setType(map.getType());
//            enclosure.setStatus(map.isStatus());
//            result.setData(enclosureService.saveEnclosure(enclosure));
//            System.out.println(result.getData());
//        }


        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(enclosureDao.saveEnclosure(enclosure));
    }

    //修改
    public ResponseResult updateEnclosure(enclosure enclosure) {
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
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(enclosureDao.updateEnclosure(enclosure));
    }

    //删除
    public ResponseResult deleteEnclosure(String personId) {
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(enclosureDao.deleteEnclosure(personId));
    }



    //查看
    public ResponseResult<enclosure> selectEnclosure(String personId) {
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
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());

        return result.setData(enclosureDao.selectEnclosure(personId));
    }
}
