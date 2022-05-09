import javax.swing.*;

public abstract class MotionPictures {
    private String name;
    private String[] cast;
    private Genre genre;
    private String image;

    public MotionPictures(String name, String[] cast, Genre genre, String image) {
        this.name = name;
        this.cast = cast;
        this.genre = genre;
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
        this.image = motionPictures.image;
    }


    public Genre getGenre() {
        return this.genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
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

    public enum Genre{
        ACTION, COMEDY, DRAMA, THRILLER, ROMANCE, MYSTERY
    }

}
