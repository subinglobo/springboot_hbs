package com.choosenfly.hotelbookingsystem.masters.service.bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.choosenfly.hotelbookingsystem.masters.dto.MasterBankDTO;
import com.choosenfly.hotelbookingsystem.masters.dto.MasterPlaceDTO;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterBank;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterPlace;
import com.choosenfly.hotelbookingsystem.masters.repository.MasterBankRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class BankService implements BankServiceInterface {
	
	
	private final MasterBankRepository masterBankRepository;
	
	@Autowired
	public BankService(MasterBankRepository masterBankRepository) {
		this.masterBankRepository = masterBankRepository;
		
	}

	@Override
	@Transactional
	public Long saveMasterBank( MasterBankDTO bankDTO) {
		// TODO Auto-generated method stub
		
		MasterBank entity = new MasterBank();
		entity.setName(bankDTO.getName());
		entity.setIsDeleted(false);
		MasterBank save = masterBankRepository.save(entity);
		Long bankId = save.getBankId();
		if(bankId != 0) {
			return bankId;
		}
		return null;
	}

	@Override
	@Transactional
	public MasterBankDTO getBankDetailsById(Long id) {
		// TODO Auto-generated method stub
		
		MasterBank bank = 
				masterBankRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Bank not found Exception:"+ id));
		
		if(bank.getBankId() != null) {
			
			MasterBankDTO masterBankDTO = new MasterBankDTO();
			masterBankDTO.setBankId(bank.getBankId());
			masterBankDTO.setName(bank.getName());
			masterBankDTO.setIsDeleted(bank.getIsDeleted());
			return masterBankDTO;
		}
		
		return null;
	}

	@Override
	@Transactional
	public MasterBankDTO editBank(Long id , MasterBankDTO bankDTO) {
		// TODO Auto-generated method stub
		
		MasterBank bank = 
				masterBankRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Bank not found:"+id));
		
		bank.setName(bankDTO.getName());

		MasterBank save = masterBankRepository.save(bank);
		 
		 MasterBankDTO masterBankDTO = new MasterBankDTO();
		 masterBankDTO.setBankId(save.getBankId());
		 masterBankDTO.setName(save.getName());
		 masterBankDTO.setIsDeleted(save.getIsDeleted());
		 return masterBankDTO;
	}

	@Transactional
	public ResponseEntity<String>  deleteBank(Long id) {
		// TODO Auto-generated method stub
		
		MasterBank existbank = 
				masterBankRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Bank Not Found:" + id));
		
		masterBankRepository.delete(existbank);
		
		return ResponseEntity.ok("Bank with id " + id + " deleted successfully");
	}

	@Override
	public Page<MasterBankDTO> getAllBanks(Pageable pageable, String searchTerm) {
		// TODO Auto-generated method stub
		Page<MasterBank> bankPage;

	    if (StringUtils.hasText(searchTerm)) {
	    	
	    	bankPage = masterBankRepository.findByNameStartingWithIgnoreCase(searchTerm, pageable);
	    } else {
	    	bankPage = masterBankRepository.findAll(pageable);
	    }

	    return bankPage.map(bank -> {
	    	MasterBankDTO dto = new MasterBankDTO();
	        dto.setBankId(bank.getBankId());
	        dto.setName(bank.getName());
	        dto.setIsDeleted(bank.getIsDeleted());
	        return dto;
	    });
	}

	

	

}
