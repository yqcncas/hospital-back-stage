package com.ruoyi.common.enums;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public enum UserType {
    SYS_USER(0,"系统用户","warning"),
    DOCTORS(1,"医护用户","info"),
    LOGISTICS_USER(2,"后勤用户","error")
    ;


    private Integer value;
    private String text;
    private String listClass;

    UserType(Integer value, String text,String listClass) {
        this.value = value;
        this.text = text;
        this.listClass = listClass;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getListClass() {
        return listClass;
    }

    public void setListClass(String listClass) {
        this.listClass = listClass;
    }

    public static UserType findByValue(Integer value){
        if (null==value){
            return null;
        }
        for (UserType e : UserType.values()){
            if (value.equals(e.getValue())){
                return e;
            }
        }
        return null;
    }

    public static List<Map> getAllEnum(){
        List<Map> result=new LinkedList<>();
        for (UserType e : UserType.values()){
            Map<String,Object> map=new HashMap<>();
            map.put("dictValue",e.getValue());
            map.put("dictLabel",e.getText());
            map.put("listClass",e.getListClass());
            result.add(map);
        }
        return result;
    }

}
