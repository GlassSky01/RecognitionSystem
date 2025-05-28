package ciallo.glasssky.service;

import ciallo.glasssky.dao.GetPersonalInfoDao;
import ciallo.glasssky.model.Result;

import java.util.ArrayList;
import java.util.List;

public class GetPersonalInfoService {

    public static Result get(List<String> fieldsName , ArrayList<Class<?>> types) {
        ArrayList<String> newName = new ArrayList<>();
        ArrayList<Class<?>> newType = new ArrayList<>();
        for(int i = 1 ; i < fieldsName.size() ; i ++){
            newName.add(fieldsName.get(i));
            newType.add(types.get(i));
        }
        Result result = GetPersonalInfoDao.get(newName , newType);
        if(result.content != null){
            Object[] objects = (Object[]) result.content;
            for(int i = 0 ;  i< objects.length ; i ++)
                if(objects[i] == null || objects[i].equals(0))
                    objects[i] = "";
        }
        return result;
    }
}
