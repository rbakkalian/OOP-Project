import enums.Genres;

import javax.swing.*;

public abstract class MotionPictures {
    private String name;
    private String[] cast;
    private Genres genre;
    private String ageCategory;
    private String image;

    public MotionPictures(String name, String[] cast, Genres genre, String ageCategory, String image) {
        this.name = name;
        this.cast = cast;
        this.genre = genre;
        this.ageCategory = ageCategory;
        this.image = image;
    }

    public MotionPictures(String name) {
        this.name = name;
    }

    public MotionPictures() {
    }

    public MotionPictures(MotionPictures motionPictures) {    //copy constructor
        this.name = motionPictures.name;
        this.cast = motionPictures.cast;
        this.genre = motionPictures.genre;
        this.ageCategory = motionPictures.ageCategory;
        this.image = motionPictures.image;
    }


    public Genres getGenre() {
        return this.genre;
    }

    public void setGenre(Genres genre) {
        this.genre = genre;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String getAgeCategory() {
        return ageCategory;
    }

    public void setAgeCategory(String ageCategory) {
        this.ageCategory = ageCategory;
    }

    public String[] getCast() {
        String[] result = new String[this.cast.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = cast[i];
        }
        return result;
    }

    public void setCast(String[] cast) {
        String[] copy = new String[this.cast.length];
        for (int i = 0; i < copy.length; i++) {
            copy[i] = cast[i];
        }
        this.cast = copy;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public abstract String returnStringInfo();

}
