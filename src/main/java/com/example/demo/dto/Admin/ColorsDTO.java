package com.example.demo.dto.Admin;

public class ColorsDTO {

    private String color_code;
    private String color_name;

    public ColorsDTO() {
    }

    public ColorsDTO(String color_code, String color_name) {

        this.color_code = color_code;
        this.color_name = color_name;
    }

    public String getColor_code() {
        return color_code;
    }

    public String getColor_name() {
        return color_name;
    }

    public void setColor_code(String color_code) {
        this.color_code = color_code;
    }

    public void setColor_name(String color_name) {
        this.color_name = color_name;
    }
}
