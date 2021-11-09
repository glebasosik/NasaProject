package com.example.nasaproject.API;

import com.example.nasaproject.DI.RestApiModule;

public class PhotoDTO {
    private String identifier;
    private String caption;
    private String image;
    private String date;

    public String getIdentifier() {
        return identifier;
    }

    public String getCaption() {
        return caption;
    }


    public String getImage() {
        return image;
    }

    public String getDate() {
        return date;
    }

    public String getImageUrl() {
        StringBuilder sb = new StringBuilder();
        sb.append("https://api.nasa.gov/EPIC/archive/natural/");
        String[] dateComponents = date.split(" ")[0].split("-");
        sb
                .append(dateComponents[0]).append('/')
                .append(dateComponents[1]).append('/')
                .append(dateComponents[2]).append("/png/")
                .append(image).append(".png?api_key=").append(RestApiModule.KEY);
        return sb.toString();

    }

}
