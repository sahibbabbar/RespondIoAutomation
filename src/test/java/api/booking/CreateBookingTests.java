package api.booking;

import api.CommonApiTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import platform.api.builders.BookingBuilder;
import platform.api.builders.BookingDatesBuilder;
import platform.api.constants.booking.Endpoints;
import platform.api.dtos.booking.payload.BookingDates;
import platform.api.dtos.booking.payload.BookingDto;
import platform.api.dtos.booking.response.Booking;

import java.time.LocalDate;
import java.util.Random;

import static io.restassured.RestAssured.given;

public class CreateBookingTests extends CommonApiTest {

    @Test(description = "Test to verify the creation of booking to check the endpoints able to create booking with valid data.")
    public void createBookingTest() throws JsonProcessingException {
        BookingDates bookingDates = BookingDatesBuilder.withCheckIn(LocalDate.now().toString())
                .withCheckOut(LocalDate.now().plusDays(1).toString())
                .build();

        BookingDto bookingDto = BookingBuilder.withFirstName(RandomStringUtils.randomAlphabetic(1, 10))
                .withLastName(RandomStringUtils.randomAlphabetic(1, 10))
                .withTotalPrice(Integer.parseInt(RandomStringUtils.randomNumeric(1, 10)))
                .withDepositPaid(new Random().nextBoolean())
                .withBookingDates(bookingDates)
                .withAdditionalNeed(RandomStringUtils.randomAlphanumeric(10))
                .build();

        var response = given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(bookingDto)
                .when().post(Endpoints.BOOKING_ENDPOINT);

        var booking = mapper.readValue(response.asString(), Booking.class);

        verify.assertEquals(response.getStatusCode(), 200, "Verify that the valid status code is returned.");
        verify.assertTrue(booking != null, "Verify that the response is not null");
        verify.assertTrue(booking.getBookingid() != null, "Verify that the booking id is not null");

        verify.assertEquals(booking.getBooking().getFirstName(), bookingDto.getFirstName(), "Verify the firstname is correct.");
        verify.assertEquals(booking.getBooking().getLastName(), bookingDto.getLastName(), "Verify the lastname is correct.");
        verify.assertEquals(booking.getBooking().getTotalPrice(), bookingDto.getTotalPrice(), "Verify the total price is correct.");
        verify.assertEquals(booking.getBooking().getDepositPaid(), bookingDto.getDepositPaid(), "Verify the deposit paid is correct.");
        verify.assertEquals(booking.getBooking().getBookingDates().getCheckIn(), bookingDto.getBookingDates().getCheckIn(), "Verify the booking check-in date is correct.");
        verify.assertEquals(booking.getBooking().getBookingDates().getCheckOut(), bookingDto.getBookingDates().getCheckOut(), "Verify the booking check-out date is correct.");
        verify.assertEquals(booking.getBooking().getAdditionalNeeds(), bookingDto.getAdditionalNeeds(), "Verify the additional needs are correct.");
    }

    @Test(description = "Test to verify the creation of booking without FirstName to check the API should response error.")
    public void createBookingWithoutFirstNameTest() {
        BookingDates bookingDates = BookingDatesBuilder.withCheckIn(LocalDate.now().toString())
                .withCheckOut(LocalDate.now().plusDays(1).toString())
                .build();

        BookingDto bookingDto = BookingBuilder.withFirstName(null)
                .withLastName(RandomStringUtils.randomAlphabetic(1, 10))
                .withTotalPrice(Integer.parseInt(RandomStringUtils.randomNumeric(1, 10)))
                .withDepositPaid(new Random().nextBoolean())
                .withBookingDates(bookingDates)
                .withAdditionalNeed(RandomStringUtils.randomAlphanumeric(10))
                .build();

        var response = given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(bookingDto)
                .when().post(Endpoints.BOOKING_ENDPOINT);

        verify.assertEquals(response.getStatusCode(), 500, "Verify that the valid errors status code is returned.");
        verify.assertEquals(response.asString(), "Internal Server Error", "Verify that the API responding valid error.");
    }

    @Test(description = "Test to verify the creation of booking with empty FirstName to check the API should response error.")
    public void createBookingWithEmptyFirstNameTest() throws JsonProcessingException {
        BookingDates bookingDates = BookingDatesBuilder.withCheckIn(LocalDate.now().toString())
                .withCheckOut(LocalDate.now().plusDays(1).toString())
                .build();

        BookingDto bookingDto = BookingBuilder.withFirstName("")
                .withLastName(RandomStringUtils.randomAlphabetic(1, 10))
                .withTotalPrice(Integer.parseInt(RandomStringUtils.randomNumeric(1, 10)))
                .withDepositPaid(new Random().nextBoolean())
                .withBookingDates(bookingDates)
                .withAdditionalNeed(RandomStringUtils.randomAlphanumeric(10))
                .build();

        var response = given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(bookingDto)
                .when().post(Endpoints.BOOKING_ENDPOINT);

        var booking = mapper.readValue(response.asString(), Booking.class);

        verify.assertEquals(response.getStatusCode(), 200, "Verify that the valid status code is returned.");
        verify.assertTrue(booking != null, "Verify that the response is not null");
        verify.assertTrue(booking.getBookingid() != null, "Verify that the booking id is not null");

        verify.assertEquals(booking.getBooking().getFirstName(), bookingDto.getFirstName(), "Verify the firstname is correct.");
        verify.assertEquals(booking.getBooking().getLastName(), bookingDto.getLastName(), "Verify the lastname is correct.");
        verify.assertEquals(booking.getBooking().getTotalPrice(), bookingDto.getTotalPrice(), "Verify the total price is correct.");
        verify.assertEquals(booking.getBooking().getDepositPaid(), bookingDto.getDepositPaid(), "Verify the deposit paid is correct.");
        verify.assertEquals(booking.getBooking().getBookingDates().getCheckIn(), bookingDto.getBookingDates().getCheckIn(), "Verify the booking check-in date is correct.");
        verify.assertEquals(booking.getBooking().getBookingDates().getCheckOut(), bookingDto.getBookingDates().getCheckOut(), "Verify the booking check-out date is correct.");
        verify.assertEquals(booking.getBooking().getAdditionalNeeds(), bookingDto.getAdditionalNeeds(), "Verify the additional needs are correct.");
    }

    @Test(description = "Test to verify the creation of booking without LastName to check the API should response error.")
    public void createBookingWithoutLastNameTest() {
        BookingDates bookingDates = BookingDatesBuilder.withCheckIn(LocalDate.now().toString())
                .withCheckOut(LocalDate.now().plusDays(1).toString())
                .build();

        BookingDto bookingDto = BookingBuilder.withFirstName(RandomStringUtils.randomAlphabetic(1, 10))
                .withLastName(null)
                .withTotalPrice(Integer.parseInt(RandomStringUtils.randomNumeric(1, 10)))
                .withDepositPaid(new Random().nextBoolean())
                .withBookingDates(bookingDates)
                .withAdditionalNeed(RandomStringUtils.randomAlphanumeric(10))
                .build();

        var response = given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(bookingDto)
                .when().post(Endpoints.BOOKING_ENDPOINT);

        verify.assertEquals(response.getStatusCode(), 500, "Verify that the valid errors status code is returned.");
        verify.assertEquals(response.asString(), "Internal Server Error", "Verify that the API responding valid error.");
    }

    @Test(description = "Test to verify the creation of booking with empty LastName to check the API should response error.")
    public void createBookingWithEmptyLastNameTest() throws JsonProcessingException {
        BookingDates bookingDates = BookingDatesBuilder.withCheckIn(LocalDate.now().toString())
                .withCheckOut(LocalDate.now().plusDays(1).toString())
                .build();

        BookingDto bookingDto = BookingBuilder.withFirstName(RandomStringUtils.randomAlphabetic(1, 10))
                .withLastName("")
                .withTotalPrice(Integer.parseInt(RandomStringUtils.randomNumeric(1, 10)))
                .withDepositPaid(new Random().nextBoolean())
                .withBookingDates(bookingDates)
                .withAdditionalNeed(RandomStringUtils.randomAlphanumeric(10))
                .build();

        var response = given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(bookingDto)
                .when().post(Endpoints.BOOKING_ENDPOINT);

        var booking = mapper.readValue(response.asString(), Booking.class);

        verify.assertEquals(response.getStatusCode(), 200, "Verify that the valid status code is returned.");
        verify.assertTrue(booking != null, "Verify that the response is not null");
        verify.assertTrue(booking.getBookingid() != null, "Verify that the booking id is not null");

        verify.assertEquals(booking.getBooking().getFirstName(), bookingDto.getFirstName(), "Verify the firstname is correct.");
        verify.assertEquals(booking.getBooking().getLastName(), bookingDto.getLastName(), "Verify the lastname is correct.");
        verify.assertEquals(booking.getBooking().getTotalPrice(), bookingDto.getTotalPrice(), "Verify the total price is correct.");
        verify.assertEquals(booking.getBooking().getDepositPaid(), bookingDto.getDepositPaid(), "Verify the deposit paid is correct.");
        verify.assertEquals(booking.getBooking().getBookingDates().getCheckIn(), bookingDto.getBookingDates().getCheckIn(), "Verify the booking check-in date is correct.");
        verify.assertEquals(booking.getBooking().getBookingDates().getCheckOut(), bookingDto.getBookingDates().getCheckOut(), "Verify the booking check-out date is correct.");
        verify.assertEquals(booking.getBooking().getAdditionalNeeds(), bookingDto.getAdditionalNeeds(), "Verify the additional needs are correct.");
    }

    @Test(description = "Test to verify the creation of booking without 'total price' to check the API should response error.")
    public void createBookingWithoutTotalPriceTest() {
        BookingDates bookingDates = BookingDatesBuilder.withCheckIn(LocalDate.now().toString())
                .withCheckOut(LocalDate.now().plusDays(1).toString())
                .build();

        BookingDto bookingDto = BookingBuilder.withFirstName(RandomStringUtils.randomAlphabetic(1, 10))
                .withLastName(RandomStringUtils.randomAlphabetic(1, 10))
                .withTotalPrice(null)
                .withDepositPaid(new Random().nextBoolean())
                .withBookingDates(bookingDates)
                .withAdditionalNeed(RandomStringUtils.randomAlphanumeric(10))
                .build();

        var response = given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(bookingDto)
                .when().post(Endpoints.BOOKING_ENDPOINT);

        verify.assertEquals(response.getStatusCode(), 500, "Verify that the valid errors status code is returned.");
        verify.assertEquals(response.asString(), "Internal Server Error", "Verify that the API responding valid error.");
    }

    @Test(description = "Test to verify the creation of booking with the amount '0' (zero) Total Price to check the API should response error.")
    public void createBookingWithZeroTotalPriceTest() throws JsonProcessingException {
        BookingDates bookingDates = BookingDatesBuilder.withCheckIn(LocalDate.now().toString())
                .withCheckOut(LocalDate.now().plusDays(1).toString())
                .build();

        BookingDto bookingDto = BookingBuilder.withFirstName(RandomStringUtils.randomAlphabetic(1, 10))
                .withLastName(RandomStringUtils.randomAlphabetic(1, 10))
                .withTotalPrice(0)
                .withDepositPaid(new Random().nextBoolean())
                .withBookingDates(bookingDates)
                .withAdditionalNeed(RandomStringUtils.randomAlphanumeric(10))
                .build();

        var response = given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(bookingDto)
                .when().post(Endpoints.BOOKING_ENDPOINT);

        var booking = mapper.readValue(response.asString(), Booking.class);

        verify.assertEquals(response.getStatusCode(), 200, "Verify that the valid status code is returned.");
        verify.assertTrue(booking != null, "Verify that the response is not null");
        verify.assertTrue(booking.getBookingid() != null, "Verify that the booking id is not null");

        verify.assertEquals(booking.getBooking().getFirstName(), bookingDto.getFirstName(), "Verify the firstname is correct.");
        verify.assertEquals(booking.getBooking().getLastName(), bookingDto.getLastName(), "Verify the lastname is correct.");
        verify.assertEquals(booking.getBooking().getTotalPrice(), bookingDto.getTotalPrice(), "Verify the total price is correct.");
        verify.assertEquals(booking.getBooking().getDepositPaid(), bookingDto.getDepositPaid(), "Verify the deposit paid is correct.");
        verify.assertEquals(booking.getBooking().getBookingDates().getCheckIn(), bookingDto.getBookingDates().getCheckIn(), "Verify the booking check-in date is correct.");
        verify.assertEquals(booking.getBooking().getBookingDates().getCheckOut(), bookingDto.getBookingDates().getCheckOut(), "Verify the booking check-out date is correct.");
        verify.assertEquals(booking.getBooking().getAdditionalNeeds(), bookingDto.getAdditionalNeeds(), "Verify the additional needs are correct.");
    }

    // Possible bug, as system is allowing negative value of the total price/amount.
    @Test(description = "Test to verify the creation of booking with the negative amount total price to check the API should response error.")
    public void createBookingWithNegativeTotalPriceTest() throws JsonProcessingException {
        BookingDates bookingDates = BookingDatesBuilder.withCheckIn(LocalDate.now().toString())
                .withCheckOut(LocalDate.now().plusDays(1).toString())
                .build();

        BookingDto bookingDto = BookingBuilder.withFirstName(RandomStringUtils.randomAlphabetic(1, 10))
                .withLastName(RandomStringUtils.randomAlphabetic(1, 10))
                .withTotalPrice(-1)
                .withDepositPaid(new Random().nextBoolean())
                .withBookingDates(bookingDates)
                .withAdditionalNeed(RandomStringUtils.randomAlphanumeric(10))
                .build();

        var response = given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(bookingDto)
                .when().post(Endpoints.BOOKING_ENDPOINT);

        var booking = mapper.readValue(response.asString(), Booking.class);

        verify.assertEquals(response.getStatusCode(), 200, "Verify that the valid status code is returned.");
        verify.assertTrue(booking != null, "Verify that the response is not null");
        verify.assertTrue(booking.getBookingid() != null, "Verify that the booking id is not null");

        verify.assertEquals(booking.getBooking().getFirstName(), bookingDto.getFirstName(), "Verify the firstname is correct.");
        verify.assertEquals(booking.getBooking().getLastName(), bookingDto.getLastName(), "Verify the lastname is correct.");
        verify.assertEquals(booking.getBooking().getTotalPrice(), bookingDto.getTotalPrice(), "Verify the total price is correct.");
        verify.assertEquals(booking.getBooking().getDepositPaid(), bookingDto.getDepositPaid(), "Verify the deposit paid is correct.");
        verify.assertEquals(booking.getBooking().getBookingDates().getCheckIn(), bookingDto.getBookingDates().getCheckIn(), "Verify the booking check-in date is correct.");
        verify.assertEquals(booking.getBooking().getBookingDates().getCheckOut(), bookingDto.getBookingDates().getCheckOut(), "Verify the booking check-out date is correct.");
        verify.assertEquals(booking.getBooking().getAdditionalNeeds(), bookingDto.getAdditionalNeeds(), "Verify the additional needs are correct.");
    }

    @Test(description = "Test to verify the creation of booking without 'deposit paid' to check the API should response error.")
    public void createBookingWithoutDepositPaidTest() {
        BookingDates bookingDates = BookingDatesBuilder.withCheckIn(LocalDate.now().toString())
                .withCheckOut(LocalDate.now().plusDays(1).toString())
                .build();

        BookingDto bookingDto = BookingBuilder.withFirstName(RandomStringUtils.randomAlphabetic(1, 10))
                .withLastName(RandomStringUtils.randomAlphabetic(1, 10))
                .withTotalPrice(Integer.parseInt(RandomStringUtils.randomNumeric(1, 10)))
                .withDepositPaid(null)
                .withBookingDates(bookingDates)
                .withAdditionalNeed(RandomStringUtils.randomAlphanumeric(10))
                .build();

        var response = given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(bookingDto)
                .when().post(Endpoints.BOOKING_ENDPOINT);

        verify.assertEquals(response.getStatusCode(), 500, "Verify that the valid errors status code is returned.");
        verify.assertEquals(response.asString(), "Internal Server Error", "Verify that the API responding valid error.");
    }

    @Test(description = "Test to verify the creation of booking without 'booking dates' to check the API should response error.")
    public void createBookingWithoutBookingDatesTest() {
        BookingDto bookingDto = BookingBuilder.withFirstName(RandomStringUtils.randomAlphabetic(1, 10))
                .withLastName(RandomStringUtils.randomAlphabetic(1, 10))
                .withTotalPrice(Integer.parseInt(RandomStringUtils.randomNumeric(1, 10)))
                .withDepositPaid(new Random().nextBoolean())
                .withBookingDates(null)
                .withAdditionalNeed(RandomStringUtils.randomAlphanumeric(10))
                .build();

        var response = given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(bookingDto)
                .when().post(Endpoints.BOOKING_ENDPOINT);

        verify.assertEquals(response.getStatusCode(), 500, "Verify that the valid errors status code is returned.");
        verify.assertEquals(response.asString(), "Internal Server Error", "Verify that the API responding valid error.");
    }

    @Test(description = "Test to verify the creation of booking without 'booking check-in date' to check the API should response error.")
    public void createBookingWithoutBookingCheckInDateTest() {
        BookingDates bookingDates = BookingDatesBuilder.withCheckIn(null)
                .withCheckOut(LocalDate.now().plusDays(1).toString())
                .build();

        BookingDto bookingDto = BookingBuilder.withFirstName(RandomStringUtils.randomAlphabetic(1, 10))
                .withLastName(RandomStringUtils.randomAlphabetic(1, 10))
                .withTotalPrice(Integer.parseInt(RandomStringUtils.randomNumeric(1, 10)))
                .withDepositPaid(new Random().nextBoolean())
                .withBookingDates(bookingDates)
                .withAdditionalNeed(RandomStringUtils.randomAlphanumeric(10))
                .build();

        var response = given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(bookingDto)
                .when().post(Endpoints.BOOKING_ENDPOINT);

        verify.assertEquals(response.getStatusCode(), 500, "Verify that the valid errors status code is returned.");
        verify.assertEquals(response.asString(), "Internal Server Error", "Verify that the API responding valid error.");
    }

    @Test(description = "Test to verify the creation of booking without 'booking check-out date' to check the API should response error.")
    public void createBookingWithoutBookingCheckOutDateTest() {
        BookingDates bookingDates = BookingDatesBuilder.withCheckIn(LocalDate.now().toString())
                .withCheckOut(null)
                .build();

        BookingDto bookingDto = BookingBuilder.withFirstName(RandomStringUtils.randomAlphabetic(1, 10))
                .withLastName(RandomStringUtils.randomAlphabetic(1, 10))
                .withTotalPrice(Integer.parseInt(RandomStringUtils.randomNumeric(1, 10)))
                .withDepositPaid(new Random().nextBoolean())
                .withBookingDates(bookingDates)
                .withAdditionalNeed(RandomStringUtils.randomAlphanumeric(10))
                .build();

        var response = given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(bookingDto)
                .when().post(Endpoints.BOOKING_ENDPOINT);

        verify.assertEquals(response.getStatusCode(), 500, "Verify that the valid errors status code is returned.");
        verify.assertEquals(response.asString(), "Internal Server Error", "Verify that the API responding valid error.");
    }

    @Test(description = "Test to verify the creation of booking without 'additional need' to check the API should not response error.")
    public void createBookingWithoutAdditionalNeedTest() throws JsonProcessingException {
        BookingDates bookingDates = BookingDatesBuilder.withCheckIn(LocalDate.now().toString())
                .withCheckOut(LocalDate.now().plusDays(1).toString())
                .build();

        BookingDto bookingDto = BookingBuilder.withFirstName(RandomStringUtils.randomAlphabetic(1, 10))
                .withLastName(RandomStringUtils.randomAlphabetic(1, 10))
                .withTotalPrice(Integer.parseInt(RandomStringUtils.randomNumeric(1, 10)))
                .withDepositPaid(new Random().nextBoolean())
                .withBookingDates(bookingDates)
                .withAdditionalNeed(null)
                .build();

        var response = given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(bookingDto)
                .when().post(Endpoints.BOOKING_ENDPOINT);

        var booking = mapper.readValue(response.asString(), Booking.class);

        verify.assertEquals(response.getStatusCode(), 200, "Verify that the valid status code is returned.");
        verify.assertTrue(booking != null, "Verify that the response is not null");
        verify.assertTrue(booking.getBookingid() != null, "Verify that the booking id is not null");

        verify.assertEquals(booking.getBooking().getFirstName(), bookingDto.getFirstName(), "Verify the firstname is correct.");
        verify.assertEquals(booking.getBooking().getLastName(), bookingDto.getLastName(), "Verify the lastname is correct.");
        verify.assertEquals(booking.getBooking().getTotalPrice(), bookingDto.getTotalPrice(), "Verify the total price is correct.");
        verify.assertEquals(booking.getBooking().getDepositPaid(), bookingDto.getDepositPaid(), "Verify the deposit paid is correct.");
        verify.assertEquals(booking.getBooking().getBookingDates().getCheckIn(), bookingDto.getBookingDates().getCheckIn(), "Verify the booking check-in date is correct.");
        verify.assertEquals(booking.getBooking().getBookingDates().getCheckOut(), bookingDto.getBookingDates().getCheckOut(), "Verify the booking check-out date is correct.");
        verify.assertTrue(booking.getBooking().getAdditionalNeeds() == null, "Verify the additional needs are correct.");
    }

    @Test(description = "Test to verify the creation of booking with empty 'additional need' to check the API should not response error.")
    public void createBookingWithEmptyAdditionalNeedTest() throws JsonProcessingException {
        BookingDates bookingDates = BookingDatesBuilder.withCheckIn(LocalDate.now().toString())
                .withCheckOut(LocalDate.now().plusDays(1).toString())
                .build();

        BookingDto bookingDto = BookingBuilder.withFirstName(RandomStringUtils.randomAlphabetic(1, 10))
                .withLastName(RandomStringUtils.randomAlphabetic(1, 10))
                .withTotalPrice(Integer.parseInt(RandomStringUtils.randomNumeric(1, 10)))
                .withDepositPaid(new Random().nextBoolean())
                .withBookingDates(bookingDates)
                .withAdditionalNeed("")
                .build();

        var response = given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(bookingDto)
                .when().post(Endpoints.BOOKING_ENDPOINT);

        var booking = mapper.readValue(response.asString(), Booking.class);

        verify.assertEquals(response.getStatusCode(), 200, "Verify that the valid status code is returned.");
        verify.assertTrue(booking != null, "Verify that the response is not null");
        verify.assertTrue(booking.getBookingid() != null, "Verify that the booking id is not null");

        verify.assertEquals(booking.getBooking().getFirstName(), bookingDto.getFirstName(), "Verify the firstname is correct.");
        verify.assertEquals(booking.getBooking().getLastName(), bookingDto.getLastName(), "Verify the lastname is correct.");
        verify.assertEquals(booking.getBooking().getTotalPrice(), bookingDto.getTotalPrice(), "Verify the total price is correct.");
        verify.assertEquals(booking.getBooking().getDepositPaid(), bookingDto.getDepositPaid(), "Verify the deposit paid is correct.");
        verify.assertEquals(booking.getBooking().getBookingDates().getCheckIn(), bookingDto.getBookingDates().getCheckIn(), "Verify the booking check-in date is correct.");
        verify.assertEquals(booking.getBooking().getBookingDates().getCheckOut(), bookingDto.getBookingDates().getCheckOut(), "Verify the booking check-out date is correct.");
        verify.assertEquals(booking.getBooking().getAdditionalNeeds(), bookingDto.getAdditionalNeeds(), "Verify the additional needs are correct.");
    }
}