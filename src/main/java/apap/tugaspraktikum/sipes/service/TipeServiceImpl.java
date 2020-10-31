package apap.tugaspraktikum.sipes.service;

import apap.tugaspraktikum.sipes.model.*;
import apap.tugaspraktikum.sipes.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TipeServiceImpl implements TipeService{
	@Autowired
	TipeDb tipeDb;
	
	@Override
	public List<TipeModel> getAllTipe(){
		return tipeDb.findAll();
		
	}
}
