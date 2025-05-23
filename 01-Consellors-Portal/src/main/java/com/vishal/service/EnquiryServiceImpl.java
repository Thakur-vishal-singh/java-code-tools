package com.vishal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.vishal.dto.DashboardResponseDTO;
import com.vishal.dto.EnqFilterDTO;
import com.vishal.dto.EnquiryDTO;
import com.vishal.entitiy.CounsellorEntity;
import com.vishal.entitiy.EnquiryEntity;
import com.vishal.repo.CounsellorRepo;
import com.vishal.repo.EnquiryRepo;

@Service
public class EnquiryServiceImpl implements EnquiryService {

	
	@Autowired
	private EnquiryRepo enqRepo;
	
	@Autowired
	private CounsellorRepo counsellorRepo;
	
	
	
	
	@Override
	public DashboardResponseDTO getDashboardInfo(Integer counsellorId) {
      List<EnquiryEntity> enqList = enqRepo.findByCounsellorCounsellorId(counsellorId);
		
      DashboardResponseDTO dto = new DashboardResponseDTO(); 
      dto.setTotalEnqCnt(enqList.size());
      
      /*with the for loop and if condition 
      int openCnt=0;
      int enrolledCnt=0;
      int lostCnt=0;
      
      
      for(EnquiryEntity entity : enqList) {
    	  if(entity.getEnqStatus().equals("OPEN")) {
    		  openCnt++;
    	  }else if(entity.getEnqStatus().equals("ENOROLED")) {
    		  openCnt++;
    	  }else if(entity.getEnqStatus().equals("Lost")) {
    		  openCnt++;
    	  }
      }
      */
      
      
      // this process is called method chaining 
      int openCnt = enqList.stream()// here we use stream to manipulate the data 
    		               .filter(enq -> enq.getEnqStatus().equals("Open")) // here we see that the enquire are equal to the open 
    		               .collect(Collectors.toList()) //here we are saving that into the one list 
    		               .size(); // here we getting the size of the list
      dto.setOpenEnqCnt(openCnt);
      
     int enrolledCount = enqList.stream()
                                .filter(enq -> enq.getEnqStatus().equals("Enrolled"))
                                .collect(Collectors.toList())
                                .size();
     dto.setEnrolledEnqCnt(enrolledCount); 
     
     int  enqLost = enqList.stream().filter(enq -> enq.getEnqStatus().equals("Lost "))
                          .collect(Collectors.toList())
                          .size();
     dto.setLostEnqCnt(enqLost);
     
      
      return dto;
	}
	
	
	

	@Override
	public boolean addEnquiry(EnquiryDTO enqDTO, Integer counsellorId) {
		
		
		EnquiryEntity entity = new EnquiryEntity();
		BeanUtils.copyProperties(enqDTO, entity);
		
		// setting FK(Counsellor_id) to enquiry obj
		Optional<CounsellorEntity> byId = counsellorRepo.findById(counsellorId);
		if(byId.isPresent()) {
			CounsellorEntity counsellor = byId.get();
			entity.setCounsellor(counsellor);
		}
		
		EnquiryEntity save = enqRepo.save(entity);
		
		return save.getEnqId() != null;
	}
	
	

	@Override
	public List<EnquiryDTO> getEnquiries(Integer counsellorId) {
       
		List<EnquiryDTO> enqsDtoList = new ArrayList<>();
		
		// here we are getting the list of entity so we want store in the list of DTO
		List<EnquiryEntity> enqList =
		enqRepo.findByCounsellorCounsellorId(counsellorId);// here we have List of Entity so we have to do the loop iteration   
		for(EnquiryEntity entity : enqList) {
			EnquiryDTO dto = new EnquiryDTO();// we getting the DTO object to do next step store the entity 
			BeanUtils.copyProperties(entity, dto); // here we are store the data that is given by the entity and store in the DTO 
		    enqsDtoList.add(dto);
		}
		
		return enqsDtoList;
	}

	@Override
	public List<EnquiryDTO> getEnquiries(EnqFilterDTO filterDTO, Integer counsellorId) {

		EnquiryEntity entity = new EnquiryEntity();
		
		if(filterDTO.getClassMode()!=null &&  !filterDTO.getClassMode().equals("")) {
			entity.setClassMode(filterDTO.getClassMode());
		}
		
		if(filterDTO.getCourse() != null && !filterDTO.getCourse().equals("")) {
			entity.setCourse(filterDTO.getCourse());
		}
		
		if(filterDTO.getEnqStatus() != null && !filterDTO.getEnqStatus().equals("")) {
			entity.setEnqStatus(filterDTO.getEnqStatus());
		}
		
		CounsellorEntity counsellor = new CounsellorEntity();
		counsellor.setCounsellorId(counsellorId);
		entity.setCounsellor(counsellor);
		
		Example<EnquiryEntity> of = Example.of(entity);
       	List<EnquiryEntity> enqList =  enqRepo.findAll(of);
		
       	List<EnquiryDTO> enqsDtoList = new ArrayList<>();
		for(EnquiryEntity enq  : enqList) {
			EnquiryDTO dto = new EnquiryDTO();
			BeanUtils.copyProperties(enq, dto);
			enqsDtoList.add(dto);
			
		}
       	
		return enqsDtoList;
	}

	@Override
	public EnquiryDTO getEnquiryById(Integer enqId) {// it's method for getting the data by counselor id 

	    Optional<EnquiryEntity> byId = enqRepo.findById(enqId);
	    if(byId.isPresent()) {
	    	EnquiryEntity enquiryEntity = byId.get();
	    	EnquiryDTO dto = new EnquiryDTO();
	    	BeanUtils.copyProperties(enquiryEntity, dto);
	    	return dto;
	    }
		return null;
	}

}
