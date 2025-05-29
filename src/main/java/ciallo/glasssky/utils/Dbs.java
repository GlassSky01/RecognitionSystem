package ciallo.glasssky.utils;

import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Dbs {
    private static String url = "jdbc:mysql://localhost:3306/";
    private static String user;
    private static String password;
    private static Connection conn;
    private static Statement state;

    public static boolean login(String user, String password) {
        try {

            conn = DriverManager.getConnection(url, user, password);
            state = conn.createStatement();
        } catch (SQLException e) {
            return false;
        }
        Dbs.user = user;
        Dbs.password = password;
        DbOperators.conn = conn;
        init();
        return true;
    }

    public static void init() {
        try {
            DbOperators.execute("create database if not exists GlassSkyRecognitionSystem;");
            DbOperators.execute("use GlassSkyRecognitionSystem;");

            //用户表
            DbOperators.execute("create table if not exists Users(\n" +
                    "    id int auto_increment primary key,\n" +
                    "    username varchar(20),\n" +
                    "    password varchar(20),\n" +
                    "    role int\n" +
                    ")ENGINE=InnoDB , auto_increment= 100000;");


            //用户信息表
            DbOperators.execute("create table if not exists UsersInformation(\n" +
                    "    id int ,\n" +
                    "    name varchar(20),\n" +
                    "    phoneNumber varchar(20),\n" +
                    "    email varchar(20),\n" +
                    "    professional varchar(20),\n" +
                    "    class int ,\n" +
                    "    tutorId int,\n" +
                    "    foreign key (id) references users(id ) on delete cascade,\n" +
                    "    foreign key (tutorId) references users(id ) on delete cascade\n" +
                    ")ENGINE=InnoDB;");
            try {
                //学生测试账号
                DbOperators.execute("insert into users(id , username , password , role) value(1 , 'stu' , '123456' , 0);");
                //教师测试账号
                DbOperators.execute("insert into users(id , username , password , role) value(2 , 'tea' , '123456' , 1);");
                //默认管理员
                DbOperators.execute("insert into users(id , username , password , role) value(3 , 'root' , '123456' , 2);");
                DbOperators.execute("insert into usersinformation(id , tutorId , name) value(1 , 2 , 'student');");
                DbOperators.execute("insert into usersinformation(id , name ) value(2 , 'teacher');");
                DbOperators.execute("insert into usersinformation(id , name ) value(3 , 'root');");
            } catch (Exception e) {
                System.out.println("已插入初始角色");
            }

            //主申请信息
            DbOperators.execute("create table if not exists CreditRequestMain(\n" +
                    "    requestId int primary key auto_increment,\n" +
                    "    tutorId int ,\n" +
                    "    file longblob,\n" +
                    "    dates date,\n" +
                    "    total double,\n" +
                    "    id int ,\n" +
                    "    getTotal double,\n" +
                    "    grade varchar(20),\n" +
                    "    auditDate date,\n" +
                    "    auditState int default 0,\n" +
                    "    auditAdvice varchar(1024),\n" +
                    "    foreign key (id) references users(id) on delete cascade,\n" +
                    "    foreign key (tutorId) references users(id) on delete cascade\n" +
                    ")ENGINE=InnoDB;");

            //申请细节
            DbOperators.execute("create table if not exists CreditRequestDetails(\n" +
                    "    detailsId int ,\n" +
                    "    types varchar(20) ,\n" +
                    "    project varchar(20) ,\n" +
                    "    content varchar(20) ,\n" +
                    "    score double,\n" +
                    "    requestId int ,\n" +
                    "    getScore double,\n" +
                    "    foreign key (requestId) references CreditRequestMain(requestId) on delete cascade\n" +
                    ")ENGINE=InnoDB;");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
