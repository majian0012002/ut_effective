import org.junit.Test;

import static org.junit.Assert.*;

public class RentalTest {
    @Test
    public void rentalIsStartedIfInStore() {
        Movie movie = a.movie.build();
        Rental rental =
                a.rental.w(movie).build();
        Store store = a.store.w(movie).build();
        rental.start(store);
        assertTrue(rental.isStarted());
        assertEquals(
                0, store.getAvailability(movie));
    }
    @Test
    public void
    rentalDoesNotStartIfNotAvailable() {
        Movie movie = a.movie.build();
        Rental rental = a.rental.build();
        Store store = a.store.build();
        rental.start(store);
        assertFalse(rental.isStarted());
        assertEquals(
                0, store.getAvailability(movie));
    }
}