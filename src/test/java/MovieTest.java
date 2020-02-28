import org.junit.Test;

public class MovieTest {
    @Test(expected = IllegalArgumentException.class)
    public void invalidTitle() {
        a.movie.w(Type.UNKNOWN).build();
    }
}