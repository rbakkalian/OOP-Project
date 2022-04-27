import javax.swing.*;

public abstract class MotionPictures {
    private String name;
    private String[] cast;
    private String genre;
    private String ageCategory;

    public MotionPictures(String name, String[] cast, String genre, String ageCategory) {
        this.name = name;
        this.cast = cast;
        this.genre = genre;
        this.ageCategory = ageCategory;
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
    }


    public String getGenre() {
        return this.genre;
    }

    public void setGenre(String genre) {
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

    public abstract String returnStringInfo();

}
