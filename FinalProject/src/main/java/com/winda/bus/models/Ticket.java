package com.winda.bus.models;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;

@Entity
@Table(name = "ticket")
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private int seatNumber;

	private Boolean cancellable;

	private String journeyDate;
	
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId = true)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "trip_schedule_id")
	private TripSchedule tripSchedule;

	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId = true)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User passenger;

	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public int getSeatNumber() {
		return seatNumber;
	}


	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}


	public Boolean getCancellable() {
		return cancellable;
	}


	public void setCancellable(Boolean cancellable) {
		this.cancellable = cancellable;
	}


	public String getJourneyDate() {
		return journeyDate;
	}


	public void setJourneyDate(String journeyDate) {
		this.journeyDate = journeyDate;
	}


	public TripSchedule getTripSchedule() {
		return tripSchedule;
	}


	public void setTripSchedule(TripSchedule tripSchedule) {
		this.tripSchedule = tripSchedule;
	}


	public User getPassenger() {
		return passenger;
	}


	public void setPassenger(User passenger) {
		this.passenger = passenger;
	}

	public Ticket() {
		super();
		// TODO Auto-generated constructor stub
	}


//	public Ticket(Long id, int seatNumber, Boolean cancellable, String journeyDate, TripSchedule tripSchedule,
//			User passenger) {
//		
//		this.seatNumber = seatNumber;
//		this.cancellable = cancellable;
//		this.journeyDate = journeyDate;
//		this.tripSchedule = tripSchedule;
//		this.passenger = passenger;
//	}


	public Ticket(int seatNumber, Boolean cancellable, String journeyDate, User user, TripSchedule tripSchedule) {
		// TODO Auto-generated constructor stub
		this.seatNumber = seatNumber;
		this.cancellable = cancellable;
		this.journeyDate = journeyDate;
		this.tripSchedule = tripSchedule;
		this.passenger = passenger;
	}
	

}