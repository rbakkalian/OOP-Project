import javax.swing.*;
import java.util.Arrays;

public class TvShow extends MotionPictures{
    private int[] years;
    private int numberOfSeasons;

    public TvShow(int[] years, int numberOfSeasons, String name, String[] cast, String genres, String ageCategory, String image) {
        super(name, cast, genres, ageCategory, image);
        this.years = years;
        this.numberOfSeasons = numberOfSeasons;
    }

    public TvShow (TvShow other) {
        super();
        this.years = other.years;
        this.numberOfSeasons = other.numberOfSeasons;
    }

    public TvShow(){}

    public int[] getYears() {
        return years;
    }

    public void setYears(int[] years) {
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
                "Years Aired: " + Arrays.toString(years) + "\n" +
                "Genre: " + getGenre() + "\n" +
                "Age Category: " + getAgeCategory() + "\n" +
                "Cast: " + "\n" + Arrays.toString(getCast()) + "\n";
    }
}
