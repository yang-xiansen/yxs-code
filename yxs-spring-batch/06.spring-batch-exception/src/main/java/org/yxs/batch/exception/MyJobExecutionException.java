package org.yxs.batch.exception;

public class MyJobExecutionException extends  Exception{

    private static final long serialVersionUID = 7168487913507656106L;

    public MyJobExecutionException(String message) {
        super(message);
    }
}
