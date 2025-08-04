package com.choosenfly.springboot.Choosenfly.hotelBookingSystemTest.service.masters.PackageSettings;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
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
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.choosenfly.hotelbookingsystem.configuration.FileStorageProperties;
import com.choosenfly.hotelbookingsystem.dto.masters.MasterItenaryDetailsDTO;
import com.choosenfly.hotelbookingsystem.entities.master.MasterItenaryDetails;
import com.choosenfly.hotelbookingsystem.exceptions.EntityNotFoundException;
import com.choosenfly.hotelbookingsystem.repository.master.MasterItenaryDetailsRepository;
import com.choosenfly.hotelbookingsystem.service.masters.itenaryDetails.ItenaryDetailsService;

@ExtendWith(MockitoExtension.class)
class ItenaryDetailsServiceTest {

    @Mock
    private MasterItenaryDetailsRepository itenaryDetailsRepository;

    @Mock
    private FileStorageProperties fileStorageProperties;

    private ItenaryDetailsService itenaryDetailsService;
    private String testUploadDir;
    private MasterItenaryDetails testEntity;
    private MasterItenaryDetailsDTO testDTO;

    @BeforeEach
    void setUp() throws IOException {
        // Create a temporary directory for testing
        testUploadDir = Files.createTempDirectory("test-uploads").toString() + File.separator;
        
        // Configure the mock before service initialization
        when(fileStorageProperties.getDirectory()).thenReturn(testUploadDir);
        
        // Manually create the service instance with mocked dependencies
        itenaryDetailsService = new ItenaryDetailsService(itenaryDetailsRepository, fileStorageProperties);
        
        // Initialize test data
        testEntity = new MasterItenaryDetails();
        testEntity.setItineraryId(1L);
        testEntity.setItineraryCode("CODE1");
        testEntity.setItineraryDesc("Description");
        testEntity.setItineraryHeading("Heading");
        testEntity.setItineraryImg(testUploadDir + "test.jpg");

        testDTO = new MasterItenaryDetailsDTO();
        testDTO.setItineraryId(1L);
        testDTO.setItineraryCode("CODE1");
        testDTO.setItineraryDesc("Description");
        testDTO.setItineraryHeading("Heading");
        testDTO.setItineraryImg(new MockMultipartFile(
            "test.jpg", 
            "test.jpg", 
            "image/jpeg", 
            "test image".getBytes()
        ));
    }

    @AfterEach
    void tearDown() throws IOException {
        // Clean up the test directory
        Files.walk(Path.of(testUploadDir))
            .sorted((a, b) -> -a.compareTo(b)) // reverse order
            .map(Path::toFile)
            .forEach(File::delete);
    }

    @Test
    void constructor_CreatesUploadDirectoryWhenNotExists() {
        File uploadDir = new File(testUploadDir);
        assertTrue(uploadDir.exists());
        assertTrue(uploadDir.isDirectory());
    }

    @Test
    void saveItenaryDetails_WithImage_Success() throws IOException {
        // Arrange
        MasterItenaryDetails savedEntity = new MasterItenaryDetails();
        savedEntity.setItineraryId(1L); // Make sure ID is set
        
        // Configure the mock to return our entity with ID
        when(itenaryDetailsRepository.save(any(MasterItenaryDetails.class)))
            .thenAnswer(invocation -> {
                MasterItenaryDetails entity = invocation.getArgument(0);
                // You can copy properties if needed
                entity.setItineraryId(savedEntity.getItineraryId());
                return entity;
            });

        // Act
        Long result = itenaryDetailsService.saveItenaryDetails(testDTO);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result); // Verify the exact ID
        
        // Verify file was saved
        File[] files = new File(testUploadDir).listFiles();
        assertNotNull(files);
        assertTrue(files.length > 0);
        
        // Verify repository was called
        verify(itenaryDetailsRepository).save(any(MasterItenaryDetails.class));
    }

    @Test
    void saveItenaryDetails_WithoutImage_Success() {
        // Arrange
        testDTO.setItineraryImg(null);
        
        MasterItenaryDetails savedEntity = new MasterItenaryDetails();
        savedEntity.setItineraryId(1L);
        System.out.println("Test - Created entity with ID: " + savedEntity.getItineraryId());
        
        when(itenaryDetailsRepository.save(any(MasterItenaryDetails.class)))
            .thenAnswer(invocation -> {
                MasterItenaryDetails entity = invocation.getArgument(0);
                System.out.println("Mock - Saving entity: " + entity);
                entity.setItineraryId(savedEntity.getItineraryId());
                System.out.println("Mock - Saved entity ID: " + entity.getItineraryId());
                return entity;
            });

        // Act
        Long result = itenaryDetailsService.saveItenaryDetails(testDTO);
        System.out.println("Test - Result: " + result);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result);
        verify(itenaryDetailsRepository).save(any(MasterItenaryDetails.class));
    }

    @Test
    void getItenaryDetailsById_Found() {
        // Arrange
        when(itenaryDetailsRepository.findById(1L)).thenReturn(Optional.of(testEntity));

        // Act
        MasterItenaryDetailsDTO result = itenaryDetailsService.getItenaryDetailsById(1L);

        // Assert
        assertNotNull(result);
        assertEquals(testEntity.getItineraryId(), result.getItineraryId());
        assertEquals(testEntity.getItineraryCode(), result.getItineraryCode());
        assertEquals(testEntity.getItineraryImg(), result.getImagePath());
    }

    @Test
    void getItenaryDetailsById_NotFound() {
        // Arrange
        when(itenaryDetailsRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> {
            itenaryDetailsService.getItenaryDetailsById(1L);
        });
    }

    @Test
    void editItenaryDetails_UpdateWithoutNewImage_Success() {
        // Arrange
        when(itenaryDetailsRepository.findById(1L)).thenReturn(Optional.of(testEntity));
        when(itenaryDetailsRepository.save(any(MasterItenaryDetails.class))).thenReturn(testEntity);

        testDTO.setItineraryImg(null);
        testDTO.setItineraryHeading("Updated Heading");

        // Act
        MasterItenaryDetailsDTO result = itenaryDetailsService.editItenaryDetails(1L, testDTO);

        // Assert
        assertNotNull(result);
        assertEquals("Updated Heading", result.getItineraryHeading());
        assertEquals(testEntity.getItineraryImg(), result.getImagePath());
    }

    @Test
    void editItenaryDetails_UpdateWithNewImage_Success() throws IOException {
        // Arrange
        when(itenaryDetailsRepository.findById(1L)).thenReturn(Optional.of(testEntity));
        when(itenaryDetailsRepository.save(any(MasterItenaryDetails.class))).thenReturn(testEntity);

        MultipartFile newImage = new MockMultipartFile("new.jpg", "new.jpg", "image/jpeg", "new image".getBytes());
        testDTO.setItineraryImg(newImage);

        // Act
        MasterItenaryDetailsDTO result = itenaryDetailsService.editItenaryDetails(1L, testDTO);

        // Assert
        assertNotNull(result);
        
        // Verify new file was saved
        File savedFile = new File(testUploadDir).listFiles()[0];
        assertTrue(savedFile.exists());
        savedFile.delete(); // Clean up
    }

    @Test
    void deleteItenaryDetails_Success() {
        // Arrange
        when(itenaryDetailsRepository.findById(1L)).thenReturn(Optional.of(testEntity));
        doNothing().when(itenaryDetailsRepository).delete(testEntity);

        // Create a dummy image file
        File imageFile = new File(testEntity.getItineraryImg());
        try {
            imageFile.createNewFile();
        } catch (IOException e) {
            fail("Failed to create test image file");
        }

        // Act
        ResponseEntity<String> response = itenaryDetailsService.deleteItenaryDetails(1L);

        // Assert
        assertEquals(200, response.getStatusCode().value());
        assertFalse(imageFile.exists()); // Verify image was deleted
        verify(itenaryDetailsRepository).delete(testEntity);
    }

    @Test
    void deleteItenaryDetails_NotFound() {
        // Arrange
        when(itenaryDetailsRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> {
            itenaryDetailsService.deleteItenaryDetails(1L);
        });
    }

    @Test
    void getAllItenaryDetails_WithSearch() {
        // Arrange
        Page<MasterItenaryDetails> page = new PageImpl<>(List.of(testEntity));
        
        // Use matchers for all parameters
        when(itenaryDetailsRepository.findByitineraryHeadingContainingIgnoreCase(
            eq("test"), // Use eq() matcher for String
            any(Pageable.class) // Use any() matcher for Pageable
        )).thenReturn(page);

        // Act
        Page<MasterItenaryDetailsDTO> result = itenaryDetailsService.getAllItenaryDetails(
            Pageable.unpaged(), 
            "test"
        );

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
    }

    @Test
    void getAllItenaryDetails_WithoutSearch() {
        // Arrange
        Page<MasterItenaryDetails> page = new PageImpl<>(List.of(testEntity));
        when(itenaryDetailsRepository.findAll(any(Pageable.class))).thenReturn(page);

        // Act
        Page<MasterItenaryDetailsDTO> result = itenaryDetailsService.getAllItenaryDetails(Pageable.unpaged(), null);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
    }

    @Test
    void saveItenaryDetails_FileUploadFailure() throws IOException {
        // Arrange
        MultipartFile mockFile = mock(MultipartFile.class);
        when(mockFile.isEmpty()).thenReturn(false);
        when(mockFile.getOriginalFilename()).thenReturn("test.jpg");
        doThrow(new IOException("Failed to transfer file")).when(mockFile).transferTo(any(File.class));

        testDTO.setItineraryImg(mockFile);
        
        // Configure mock to capture and return the saved entity
        when(itenaryDetailsRepository.save(any(MasterItenaryDetails.class)))
            .thenAnswer(invocation -> {
                MasterItenaryDetails entity = invocation.getArgument(0);
                // Verify the entity state before saving
                assertNull(entity.getItineraryImg(), "Image path should be null when file transfer fails");
                // Simulate DB assigning an ID
                entity.setItineraryId(1L);
                return entity;
            });

        // Act
        Long result = itenaryDetailsService.saveItenaryDetails(testDTO);

        // Assert
        assertNotNull(result, "Service should return non-null ID even when file upload fails");
        assertEquals(1L, result, "Expected ID 1 to be returned");
        
        // Verify file transfer was attempted
        verify(mockFile).transferTo(any(File.class));
        
        // Verify repository was called
        verify(itenaryDetailsRepository).save(any(MasterItenaryDetails.class));
    }
}