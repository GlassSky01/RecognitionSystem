package ciallo.glasssky.model;

public class Result {
    public int code;
    public String info;
    public Object object;
    public Result(int code , String info , Object object){
        this.code = code;
        this.info = info;
        this.object = object;
    }
    public static Result success(){
        return new Result(1 , "success" , null);
    }
    public static Result success(Object object){
        return new Result(1 , "success" , object);
    }
    public static Result failure(){
        return new Result(0 , "failure" , null);
    }
}
