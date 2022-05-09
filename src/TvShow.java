import javax.swing.*;
import java.util.Arrays;

public class TvShow extends MotionPictures{
    private String years;
    private int numberOfSeasons;

    public TvShow(String name, String[] cast, String years, Genre genres, int numberOfSeasons, String image) {
        super(name, cast, genres, image);
        this.years = years;
        this.numberOfSeasons = numberOfSeasons;
    }

    public TvShow (TvShow other) {
        super();
        this.years = other.years;
        this.numberOfSeasons = other.numberOfSeasons;
    }

    public TvShow(){}

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }

    public int getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public void setNumberOfSeasons(int numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
    }


    @Override
    public String returnStringInfo() {
        return "Name: " + getName() + "\n" +
                "Number of Seasons: " + getNumberOfSeasons() + "\n" +
                "Years Aired: " + years + "\n" +
                "Genre: " + getGenre() + "\n" +
                "Cast: " + "\n" + Arrays.toString(getCast()) + "\n";
    }
}
