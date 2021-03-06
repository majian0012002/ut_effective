package builder;

import com.ep.Customer;
import com.ep.*;

import java.util.HashMap;
import java.util.Map;

public class a {
    public static CustomerBuilder customer =
            new CustomerBuilder();
    public static RentalBuilder rental =
            new RentalBuilder();
    public static MovieBuilder movie =
            new MovieBuilder();
    public static StoreBuilder store =
            new StoreBuilder();
    public static RegularPriceBuilder regularPrice =
            new RegularPriceBuilder();

    public static MoneyBuilder money = new MoneyBuilder();

    public static class MoneyBuilder {
        final double val;
        MoneyBuilder() {
            this(1.0);
        }
        MoneyBuilder(double val) {
            this.val = val;
        }
        public MoneyBuilder w(double val) {
            return new MoneyBuilder(val);
        }
        public Money build() {
            return new Money(val);
        }
    }


    public static class CustomerBuilder {
        Rental[] rentals;
        String name;

        CustomerBuilder() {
            this("Jim", new Rental[0]);
        }

        CustomerBuilder(
                String name, Rental[] rentals) {
            this.name = name;
            this.rentals = rentals;
        }

        public CustomerBuilder w(
                RentalBuilder... builders) {
            Rental[] rentals =
                    new Rental[builders.length];
            for (int i = 0; i < builders.length; i++) {
                rentals[i] = builders[i].build();
            }
            return
                    new CustomerBuilder(name, rentals);
        }

        public CustomerBuilder w(Rental... rentals) {
            return new CustomerBuilder("Jim", rentals);
        }

        public CustomerBuilder w(String name) {
            return
                    new CustomerBuilder(name, rentals);
        }

        public Customer build() {
            Customer result = new Customer(name);
            for (Rental rental : rentals) {
                result.addRental(rental);
            }
            return result;
        }
    }

    public static class RentalBuilder {
        final Movie movie;
        final int days;

        RentalBuilder() {
            this(new MovieBuilder().build(), 3);
        }

        RentalBuilder(Movie movie, int days) {
            this.movie = movie;
            this.days = days;
        }

        public RentalBuilder w(
                MovieBuilder movie) {
            return
                    new RentalBuilder(
                            movie.build(), days);
        }

        public Rental build() {
            return new Rental(movie, days);
        }

        public RentalBuilder w(Movie movie) {
            return new RentalBuilder(movie, 3);
        }
    }

    public static class MovieBuilder {
        final String name;
        final Type type;

        MovieBuilder() {
            this("Godfather 4",
                    Type.NEW_RELEASE);
        }

        MovieBuilder(
                String name, Type type) {
            this.name = name;
            this.type = type;
        }

        public MovieBuilder w(Type type) {
            return new MovieBuilder(name, type);
        }

        public MovieBuilder w(String name) {
            return new MovieBuilder(name, type);
        }

        public Movie build() {
            return new Movie(name, type);
        }
    }


    public static class StoreBuilder {

        private Map<Movie, Integer> movieMap;

        StoreBuilder(Map<Movie, Integer> movieMap) {
            this.movieMap = movieMap;
        }

        StoreBuilder() {
            this.movieMap = new HashMap<>();
        }

        public StoreBuilder w(Movie... movies) {
            Map<Movie, Integer> movieMap = new HashMap<>();
            for (Movie movie : movies) {
                if (movieMap.containsKey(movie))
                    movieMap.put(movie, movieMap.get(movie) + 1);
                else
                    movieMap.put(movie, 1);
            }
            return new StoreBuilder(movieMap);
        }

        public Store build() {
            return new Store(movieMap);
        }
    }

    public static class RegularPriceBuilder {

        public RegularPrice build() {
            return new RegularPrice();
        }
    }


}