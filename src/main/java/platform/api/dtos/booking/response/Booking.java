package platform.api.dtos.booking.response;

import platform.api.dtos.booking.payload.BookingDto;

public class Booking {
    private Integer bookingid;
    private BookingDto booking;

    public Integer getBookingid() {
        return bookingid;
    }

    public void setBookingid(Integer bookingid) {
        this.bookingid = bookingid;
    }

    public BookingDto getBooking() {
        return booking;
    }

    public void setBooking(BookingDto booking) {
        this.booking = booking;
    }
}