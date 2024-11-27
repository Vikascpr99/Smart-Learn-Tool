package com.smartlearntool.SmartLearnTool.exceptions;

public class ResourseNotFoundException extends RuntimeException
{
    public ResourseNotFoundException(){
        super("Resourse Not Found !");
    }

    public ResourseNotFoundException(String courseNotFound) {
        super(courseNotFound);
    }
}
