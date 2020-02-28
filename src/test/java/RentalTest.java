import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RentalTest {
   /* @Test
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
*/
    @Test
    public void rentalIsStartedIfInStore() {
        Movie movie = a.movie.build();
        Rental rental =
                a.rental.w(movie).build();
        Store store = mock(Store.class);
        when(store.getAvailability(movie))
                .thenReturn(1);
        rental.start(store);
        assertTrue(rental.isStarted());
        verify(store).remove(movie);
    }

    @Test
    public void rentalDoesNotStartIfNotAvailable() {
        Rental rental = a.rental.build();
        Store store = mock(Store.class);
        rental.start(store);
        assertFalse(rental.isStarted());
        verify(
                store, never()).remove(
                any(Movie.class));
    }
}