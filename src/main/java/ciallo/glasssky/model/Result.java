package ciallo.glasssky.model;

public class Result {
    public int code;
    public String info;
    public Object content;
    public Result(int code , String info , Object content){
        this.code = code;
        this.info = info;
        this.content = content;
    }
    public static Result success(){
        return new Result(1 , "success" , null);
    }
    public static Result success(Object content){
        return new Result(1 , "success" , content);
    }
    public static Result failure(String info){
        return new Result(0 , info , null);
    }
    public static Result failure(){
        return new Result(0 , "failure" , null);
    }

}
