package sociable;

import org.junit.Test;
import com.ep.*;
import builder.a;

import java.util.HashMap;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
public class RentalTest {
    @Test
    public void
    isStartedIfInStoreStateBased() {
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
    doesNotStartIfNotAvailableStateBased() {
        Movie movie = a.movie.build();
        Rental rental = a.rental.build();
        Store store = a.store.build();
        rental.start(store);
        assertFalse(rental.isStarted());
        assertEquals(
                0, store.getAvailability(movie));
    }


    @Test
    public void rentalIsStartedIfInStore() {
        Rental rental = a.rental.build();
        Store store = mock(Store.class);
        when(store
                .getAvailability(any(Movie.class)))
                .thenReturn(1);
        rental.start(store);
        assertTrue(rental.isStarted());
    }

    @Test
    public void
    storeAvailabilityIsModifiedOnRental() {
        Movie movie = a.movie.build();
        Rental rental =
                a.rental.w(movie).build();
        Store store =
                a.store.w(movie, movie).build();
        rental.start(store);
        a.rental.build().start(store);
        assertEquals(
                1, store.getAvailability(movie));
    }

    @Test
    public void
    notStartedIfUnavailableInteractionBased() {
        Rental rental = a.rental.build();
        Store store = mock(Store.class);
        rental.start(store);
        assertFalse(rental.isStarted());
        verify(
                store, never()).remove(
                any(Movie.class));
    }

    @Test
    public void verifyStoreInteractions() {
        Movie movie = mock(Movie.class);
        Rental rental =
                a.rental.w(movie).build();
        Store store = mock(Store.class);
        rental.start(store);
        verify(store).getAvailability(movie);
        verifyNoMoreInteractions(store);
    }
}
