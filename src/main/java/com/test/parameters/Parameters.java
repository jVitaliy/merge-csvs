package com.test.parameters;

import java.util.List;

public class Parameters {
    private List<String> filenames;
    private boolean paramsExist;
    private String dateFormat;
    private int top;

    public Parameters() {
        this.paramsExist = false;
        this.dateFormat = "MM/dd/yyyy";
    }

    public List<String> getFilenames() {
        return filenames;
    }

    public void setFilenames(List<String> filenames) {
        this.filenames = filenames;
        paramsExist = true;
    }

    public boolean isParamsExist() {
        return paramsExist;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }
}
