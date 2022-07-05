package com.winda.bus.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.winda.bus.models.Agency;
import com.winda.bus.models.Bus;
import com.winda.bus.models.Stop;
import com.winda.bus.models.Trip;
import com.winda.bus.payload.request.TripRequest;
import com.winda.bus.payload.response.MessageResponse;
import com.winda.bus.repository.AgencyRepository;
import com.winda.bus.repository.BusRepository;
import com.winda.bus.repository.StopRepository;
import com.winda.bus.repository.TripRepository;

import io.swagger.annotations.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/trip")
public class TripController {
	@Autowired
	TripRepository tripRepository;

	@Autowired
	AgencyRepository agencyRepository;

	@Autowired
	BusRepository busRepository;

	@Autowired
	StopRepository stopRepository;

	@PostMapping("/")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> addTrip(@Valid @RequestBody TripRequest tripRequest) {
		Agency agency = agencyRepository.findById(tripRequest.getAgencyId()).get();
		Bus bus = busRepository.findById(tripRequest.getBusId()).get();
		Stop sourceStop = stopRepository.findById(tripRequest.getSourceStopId()).get();
		Stop destStop = stopRepository.findById(tripRequest.getDestStopId()).get();
		Trip trip = new Trip(tripRequest.getFare(), tripRequest.getJourneyTime(), sourceStop, destStop, bus, agency);
		return ResponseEntity.ok(tripRepository.save(trip));
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getTripByAgencyId(@PathVariable(value = "id") Long id) {
		List<Trip> trip = tripRepository.findByAgencyId(id);
		return ResponseEntity.ok(trip);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteTrip(@PathVariable(value = "id") Long id) {
		String result = "";
		try {
			tripRepository.findById(id).get();

			result = "Success Deleting Data with Id: " + id;
			tripRepository.deleteById(id);

			return ResponseEntity.ok(new MessageResponse<Trip>(true, result));
		} catch (Exception e) {
			result = "Data with Id: " + id + " Not Found";
			return ResponseEntity.ok(new MessageResponse<Trip>(false, result));
		}
	}
}


