package com.choosenfly.springboot.Choosenfly.hotelBookingSystemTest.service.masters.PackageSettings;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.choosenfly.hotelbookingsystem.dto.masters.MasterDayActivitiesDTO;
import com.choosenfly.hotelbookingsystem.entities.master.MasterCountry;
import com.choosenfly.hotelbookingsystem.entities.master.MasterDayActivities;
import com.choosenfly.hotelbookingsystem.entities.master.MasterState;
import com.choosenfly.hotelbookingsystem.exceptions.EntityNotFoundException;
import com.choosenfly.hotelbookingsystem.exceptions.InvalidFeildException;
import com.choosenfly.hotelbookingsystem.exceptions.MissingRequestBodyException;
import com.choosenfly.hotelbookingsystem.exceptions.StateCountryMismatchException;
import com.choosenfly.hotelbookingsystem.repository.master.MasterCountryRepository;
import com.choosenfly.hotelbookingsystem.repository.master.MasterDayActivitiesRepository;
import com.choosenfly.hotelbookingsystem.repository.master.MasterStateRepository;
import com.choosenfly.hotelbookingsystem.service.masters.dayActivities.DayActivitiesService;

@ExtendWith(MockitoExtension.class)
public class DayActivitiesServiceTest {
	
    @Mock
    private MasterDayActivitiesRepository activitiesRepository;

    @Mock
    private MasterCountryRepository masterCountryRepository;

    @Mock
    private MasterStateRepository masterStateRepository;

    @InjectMocks
    private DayActivitiesService dayActivitiesService;

    private MasterDayActivitiesDTO validDto;
    private MasterDayActivities existingActivity;
    private MasterCountry validCountry;
    private MasterState validState;
	
    @BeforeEach
    void setUp() {
        validDto = new MasterDayActivitiesDTO();
        validDto.setCountryId(1L);
        validDto.setStateId(1L);
        validDto.setActivityName("Test Activity");
        validDto.setActivityCode("TEST001");
        validDto.setDescription("Test Description");

        validCountry = new MasterCountry();
        validCountry.setId(1L);
        validCountry.setName("Test Country");

        validState = new MasterState();
        validState.setId(1L);
        validState.setName("Test State");
        validState.setCountry(validCountry);

        existingActivity = new MasterDayActivities();
        existingActivity.setDayActivityId(1L);
        existingActivity.setActivityName("Existing Activity");
        existingActivity.setActivityCode("EXIST001");
        existingActivity.setDescription("Existing Description");
        existingActivity.setCountry(validCountry);
        existingActivity.setState(validState);
    }

    @Test
    void saveDayActivities_Success() {
        when(masterCountryRepository.findById(1L)).thenReturn(Optional.of(validCountry));
        when(masterStateRepository.findById(1L)).thenReturn(Optional.of(validState));
        when(activitiesRepository.save(any(MasterDayActivities.class))).thenAnswer(invocation -> {
            MasterDayActivities saved = invocation.getArgument(0);
            saved.setDayActivityId(1L);
            return saved;
        });

        Long result = dayActivitiesService.saveDayActivities(validDto);

        assertNotNull(result);
        assertEquals(1L, result);
        verify(activitiesRepository).save(any(MasterDayActivities.class));
    }

    @Test
    void saveDayActivities_CountryNotFound() {
        when(masterCountryRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            dayActivitiesService.saveDayActivities(validDto);
        });
    }

    @Test
    void saveDayActivities_StateNotFound() {
        when(masterCountryRepository.findById(1L)).thenReturn(Optional.of(validCountry));
        when(masterStateRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            dayActivitiesService.saveDayActivities(validDto);
        });
    }

    @Test
    void saveDayActivities_StateCountryMismatch() {
        MasterCountry anotherCountry = new MasterCountry();
        anotherCountry.setId(2L);
        validState.setCountry(anotherCountry);

        when(masterCountryRepository.findById(1L)).thenReturn(Optional.of(validCountry));
        when(masterStateRepository.findById(1L)).thenReturn(Optional.of(validState));

        assertThrows(StateCountryMismatchException.class, () -> {
            dayActivitiesService.saveDayActivities(validDto);
        });
    }

    @Test
    void getDayActivitiesById_Success() {
        when(activitiesRepository.findById(1L)).thenReturn(Optional.of(existingActivity));

        MasterDayActivitiesDTO result = dayActivitiesService.getDayActivitiesById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getDayActivityId());
        assertEquals("Existing Activity", result.getActivityName());
        assertEquals(1L, result.getCountryId());
        assertEquals(1L, result.getStateId());
    }

    @Test
    void getDayActivitiesById_NotFound() {
        when(activitiesRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            dayActivitiesService.getDayActivitiesById(1L);
        });
    }

    @Test
    void getDayActivitiesById_InvalidId() {
        assertThrows(InvalidFeildException.class, () -> {
            dayActivitiesService.getDayActivitiesById(0L);
        });

        assertThrows(InvalidFeildException.class, () -> {
            dayActivitiesService.getDayActivitiesById(-1L);
        });

        assertThrows(InvalidFeildException.class, () -> {
            dayActivitiesService.getDayActivitiesById(null);
        });
    }

    @Test
    void editDayActivities_Success() {
        when(activitiesRepository.findById(1L)).thenReturn(Optional.of(existingActivity));
        when(masterCountryRepository.findById(1L)).thenReturn(Optional.of(validCountry));
        when(masterStateRepository.findById(1L)).thenReturn(Optional.of(validState));
        when(activitiesRepository.save(any(MasterDayActivities.class))).thenReturn(existingActivity);

        MasterDayActivitiesDTO result = dayActivitiesService.editDayActivities(1L, validDto);

        assertNotNull(result);
        assertEquals(1L, result.getDayActivityId());
        assertEquals("Test Activity", result.getActivityName());
        assertEquals("TEST001", result.getActivityCode());
    }

    @Test
    void editDayActivities_InvalidId() {
        assertThrows(InvalidFeildException.class, () -> {
            dayActivitiesService.editDayActivities(0L, validDto);
        });
    }

    @Test
    void editDayActivities_NullRequestBody() {
        assertThrows(MissingRequestBodyException.class, () -> {
            dayActivitiesService.editDayActivities(1L, null);
        });
    }

    @Test
    void editDayActivities_ActivityNotFound() {
        when(activitiesRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            dayActivitiesService.editDayActivities(1L, validDto);
        });
    }

    @Test
    void deleteDayActivities_Success() {
        when(activitiesRepository.findById(1L)).thenReturn(Optional.of(existingActivity));
        doNothing().when(activitiesRepository).delete(existingActivity);

        ResponseEntity<String> response = dayActivitiesService.deleteDayActivities(1L);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Day activities with id 1 deleted successfully", response.getBody());
        verify(activitiesRepository).delete(existingActivity);
    }

    @Test
    void deleteDayActivities_NotFound() {
        when(activitiesRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            dayActivitiesService.deleteDayActivities(1L);
        });
    }

    @Test
    void getAllDayActivities_WithSearch() {
        Pageable pageable = Pageable.unpaged();
        when(activitiesRepository.findByActivityNameContainingIgnoreCase("test", pageable))
            .thenReturn(new PageImpl<>(Collections.singletonList(existingActivity)));

        Page<MasterDayActivitiesDTO> result = dayActivitiesService.getAllDayActivities(pageable, "test");

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals("Existing Activity", result.getContent().get(0).getActivityName());
    }

    @Test
    void getAllDayActivities_WithoutSearch() {
        Pageable pageable = Pageable.unpaged();
        when(activitiesRepository.findAll(pageable))
            .thenReturn(new PageImpl<>(Collections.singletonList(existingActivity)));

        Page<MasterDayActivitiesDTO> result = dayActivitiesService.getAllDayActivities(pageable, null);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
    }
}
