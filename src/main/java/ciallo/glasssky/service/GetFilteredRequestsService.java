package ciallo.glasssky.service;

import ciallo.glasssky.dao.GetFilteredRequestsDao;
import ciallo.glasssky.model.Result;

public class GetFilteredRequestsService {
    public static Result get(String name, String grade, String academy, String username, String dates) {
        name = name.strip();
        username = username.strip();
        dates = dates.strip();
        name = "%" + name + "%";
        if(grade == "全部")
            grade = "";
        else
            grade = " and i.grade = " + grade;
        if(academy == "全部")
            academy = "";
        else
            academy = " and academy = " + academy;
        username = "%" + username + "%";
        dates = "%" + dates + "%";

        String sql = "select requestId , name , i.grade , academy , username , dates , total from users u natural join UsersInformation i join creditrequestmain c on u.id = c.id  \n" +
                "where name like ? " + grade + academy + " and username like ? and dates like ?;";
        return GetFilteredRequestsDao.get(sql , name , username , dates);

    }
}
