package streams;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ApartmentRental {

    private List<Apartment> apartments = new ArrayList<>();

    public void addApartment(Apartment apartment) {
        apartments.add(apartment);
    }


    public List<Apartment> findApartmentByLocation(String location) {
        return apartments
                .stream()
                .filter(apartment -> location.equals(apartment.getLocation()))
                .toList();
    }

    public List<Apartment> findApartmentByExtras(String...extras) {
       return apartments
               .stream()
               .filter(apartment -> apartment.getExtras().containsAll(List.of(extras)))
               .toList();
    }


    public boolean isThereApartmentWithBathroomType(BathRoomType bathRoomType) {
        return apartments
                .stream()
                .anyMatch(apartment -> bathRoomType.equals(apartment.getBathRoomType()));
    }

    public List<Integer> findApartmentsSize() {
        return apartments
                .stream()
                .map(Apartment::getSize)
                .toList();
    }
}
