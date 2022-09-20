package platform.api.builders;

import platform.api.dtos.booking.payload.BookingDates;
import platform.api.dtos.booking.payload.BookingDto;

public class BookingBuilder {
    private final BookingDto booking;

    public BookingBuilder() {
        booking = new BookingDto();
    }

    public static BookingBuilder withFirstName(String firstName) {
        BookingBuilder builder = new BookingBuilder();
        builder.booking.setFirstName(firstName);
        return builder;
    }

    public BookingBuilder withLastName(String lastName) {
        booking.setLastName(lastName);
        return this;
    }

    public BookingBuilder withTotalPrice(Integer totalPrice) {
        booking.setTotalPrice(totalPrice);
        return this;
    }

    public BookingBuilder withDepositPaid(Boolean depositPaid) {
        booking.setDepositPaid(depositPaid);
        return this;
    }

    public BookingBuilder withBookingDates(BookingDates bookingDates) {
        booking.setBookingDates(bookingDates);
        return this;
    }

    public BookingBuilder withAdditionalNeed(String additionalNeeds) {
        booking.setAdditionalNeeds(additionalNeeds);
        return this;
    }

    public BookingDto build() {
        return this.booking;
    }
}