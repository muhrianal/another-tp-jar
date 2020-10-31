package apap.tugaspraktikum.sipes.service;

import apap.tugaspraktikum.sipes.model.*;
import apap.tugaspraktikum.sipes.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TeknisiServiceImpl implements TeknisiService{
	
	@Autowired
	TeknisiDb teknisiDb;
	
	@Override
	public List<TeknisiModel> getAllTeknisi(){
		return teknisiDb.findAll();
	}

}
