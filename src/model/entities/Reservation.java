package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Reservation {
	private Integer roomNumber;
	private Date checkIn;
	private Date checkOut;
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public Reservation(Integer roomNumber, Date checkIn, Date checkOut) {
		
		this.roomNumber = roomNumber;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Date getCheckIn() {
		return checkIn;
	}

	/*De responsabilidade do m�todo updateDate
	public void setCheckIn(Date checkIn) {
		this.checkIn = checkIn;
	}*/

	public Date getCheckOut() {
		return checkOut;
	}

	/*De responsabilidade do m�todo updateDate
	 * public void setCheckOut(Date checkOut) {
		this.checkOut = checkOut;
	}*/
	
	
	public long duration() {
		long diff = checkOut.getTime() - checkIn.getTime();
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}
	
	public void updateDates (Date checkIn, Date checkOut) {
		Date now = new Date();
		if (checkIn.before(now) || checkOut.before(now)) {
			//tratamento de exce��o do java para dados inv�lidos
			throw new IllegalArgumentException("Reservation dates for update must be future dates");
		}
		
		if (!checkOut.after(checkIn)) {
			throw new IllegalArgumentException("Check-out date must be after check-in date");
		}
		
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}
	
	@Override
	public String toString() {
		return "Room "
				+ roomNumber
				+", check-in: "
				+sdf.format(checkIn)
				+", check-out: "
				+sdf.format(checkOut)
				+ ", "
				+ duration()
				+ " nights";				
	}

}
