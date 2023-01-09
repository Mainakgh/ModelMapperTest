package com.infy.controller;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.infy.entity.Destination;
import com.infy.request.Source;

@RestController
public class Controller {
	private Source source;
	private Destination ds;
	@Autowired
	private ModelMapper mapper;
	
	@PostMapping("/save&check")
	public String insert(@RequestBody Source source) {
		 TypeMap<Source, Destination> typeMapper = this.mapper.createTypeMap(Source.class, Destination.class);
		 typeMapper.addMapping(Source::getSourceField,Destination::setSourcedata);
		 typeMapper.addMappings(
			      mapper -> mapper.map(src -> src.getMobile().getName(), Destination::setFav)
				    );
		 
		 ds= this.mapper.map(source, Destination.class);
		return "Data Inserted!!";
	}
	@GetMapping("/save&check")
	public ResponseEntity<Destination> check(){
		return new ResponseEntity<Destination>(ds,HttpStatus.OK);
	}

}
