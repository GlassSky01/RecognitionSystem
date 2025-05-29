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
            grade = "and grade = " + grade;
        if(academy == "全部")
            academy = "";
        else
            academy = "and academy = " + academy;
        username = "%" + username + "%";
        dates = "%" + dates + "%";

        String sql = "select requestId , name , grade , academy , username , dates from creditrequestmain natural join UsersInformation natural join users where  \n" +
                "name like ? " + grade + academy + "and id like ? and dates like ?;";

        return GetFilteredRequestsDao.get(sql , name , username , dates);

    }
}
