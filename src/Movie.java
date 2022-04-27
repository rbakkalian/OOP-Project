import java.util.Arrays;

public class Movie extends MotionPictures {
    private int year;
    private int length;
    private String director;

    public Movie(String name, String[] cast, int year, String genres, String ageCategory) {
        super(name, cast, genres, ageCategory);
        this.year = year;
    }

    public Movie(Movie other) {
        super();
        this.year = other.year;
        this.director = other.director;
        this.length = other.length;
    }

    public Movie(String name){
        super(name);
    }

    public Movie() {
    }

    public String toString() {
        return this.getName() + " (" + this.year + ")";
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public int getLength() {
        return length;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public boolean equals(Object o) {
        if (o == null || o.getClass() != Movie.class) return false;
        Movie other = (Movie) o;
        return this.getName().equals(((Movie) o).getName()) &&
                this.year == other.year;
    }


    @Override
    public String returnStringInfo() {
        return "Name: " + getName() + "\n" +
                "Release Date: " + year + "\n" +
                "Genre: " + getGenre() + "\n" +
                "Age Category: " + getAgeCategory() + "\n" +
                "Cast: " + "\n" + Arrays.toString(getCast()) + "\n\n";
    }
}
