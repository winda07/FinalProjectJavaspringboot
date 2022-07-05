package com.winda.bus.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.server.ResponseStatusException;

import com.winda.bus.models.Agency;
import com.winda.bus.models.Bus;
import com.winda.bus.payload.request.BusCustomRequest;
import com.winda.bus.repository.AgencyRepository;
import com.winda.bus.repository.BusRepository;

import io.swagger.annotations.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/bus")
public class BusController {

	@Autowired
	BusRepository busRepository;

	@Autowired
	AgencyRepository agencyRepository;

	@GetMapping("/{id}")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getBusByAgencyId(@PathVariable(value = "id") Long id) {
		List<Bus> bus = busRepository.findByAgencyId(id);
		return ResponseEntity.ok(bus);
	}

	@PostMapping("/{id}")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> addBusByUserId(@PathVariable(value = "id") Long id,
			@Valid @RequestBody BusCustomRequest busCustomRequest) {
		Agency agency = agencyRepository.findByOwnerUser(id);
		Bus bus = new Bus(busCustomRequest.getCode(), busCustomRequest.getCapacity(), busCustomRequest.getMake(),
				agency);
		return ResponseEntity.ok(busRepository.save(bus));
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "delete bus", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> deleteBus(@PathVariable(value = "id") Long id) {

		try {
			busRepository.deleteById(id);
			String result = "Success Delete Bus with Id: " + id;
			return ResponseEntity.ok(result);

		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e.getCause());
		}
	}

}