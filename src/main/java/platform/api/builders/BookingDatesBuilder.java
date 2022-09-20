package platform.api.builders;

import platform.api.dtos.booking.payload.BookingDates;

public class BookingDatesBuilder {
    private final BookingDates bookingDates;

    public BookingDatesBuilder() {
        bookingDates = new BookingDates();
    }

    public static BookingDatesBuilder withCheckIn(String checkIn) {
        BookingDatesBuilder builder = new BookingDatesBuilder();
        builder.bookingDates.setCheckIn(checkIn);
        return builder;
    }

    public BookingDatesBuilder withCheckOut(String checkOut) {
        bookingDates.setCheckOut(checkOut);
        return this;
    }

    public BookingDates build() {
        return this.bookingDates;
    }
}