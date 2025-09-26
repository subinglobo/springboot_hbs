package com.choosenfly.hotelbookingsystem.util;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.choosenfly.hotelbookingsystem.inventory.dto.HotelDTO;
import com.choosenfly.hotelbookingsystem.inventory.dto.HotelRoomCategoryDTO;
import com.choosenfly.hotelbookingsystem.inventory.dto.HotelRoomTypeDTO;
import com.choosenfly.hotelbookingsystem.inventory.entities.Hotel;
import com.choosenfly.hotelbookingsystem.inventory.entities.HotelRoomCategory;
import com.choosenfly.hotelbookingsystem.inventory.entities.HotelRoomType;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterRoomCategory;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterRoomType;
import com.choosenfly.hotelbookingsystem.masters.repository.MasterRoomCategoryRepository;
import com.choosenfly.hotelbookingsystem.masters.repository.MasterRoomTypeRepository;

public class HotelMapperTest {

    @Mock
    private MasterRoomCategoryRepository roomCategoryRepository;

    @Mock
    private MasterRoomTypeRepository roomTypeRepository;

    @InjectMocks
    private HotelMapper hotelMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testHierarchicalRoomCategoryAndTypeMapping() {
        // Arrange
        HotelDTO hotelDTO = new HotelDTO();
        hotelDTO.setHotelName("Test Hotel");

        // Create room categories
        HotelRoomCategoryDTO category1 = new HotelRoomCategoryDTO();
        category1.setRoomCategoryId(1L);
        category1.setName("Junior Suite");
        category1.setNoOfRooms("10");

        HotelRoomCategoryDTO category2 = new HotelRoomCategoryDTO();
        category2.setRoomCategoryId(2L);
        category2.setName("Classic Room Balcony");
        category2.setNoOfRooms("20");

        hotelDTO.setRoomCategories(Arrays.asList(category1, category2));

        // Create room types that reference room categories by index
        HotelRoomTypeDTO type1 = new HotelRoomTypeDTO();
        type1.setHotelRoomCategoryId(0L); // References first category (Junior Suite)
        type1.setRoomTypeId(101L);

        HotelRoomTypeDTO type2 = new HotelRoomTypeDTO();
        type2.setHotelRoomCategoryId(0L); // References first category (Junior Suite)
        type2.setRoomTypeId(102L);

        HotelRoomTypeDTO type3 = new HotelRoomTypeDTO();
        type3.setHotelRoomCategoryId(1L); // References second category (Classic Room Balcony)
        type3.setRoomTypeId(103L);

        hotelDTO.setRoomTypes(Arrays.asList(type1, type2, type3));

        // Mock repository responses
        MasterRoomCategory masterCategory1 = new MasterRoomCategory();
        masterCategory1.setRoomCategoryId(1L);
        when(roomCategoryRepository.findById(1L)).thenReturn(Optional.of(masterCategory1));

        MasterRoomCategory masterCategory2 = new MasterRoomCategory();
        masterCategory2.setRoomCategoryId(2L);
        when(roomCategoryRepository.findById(2L)).thenReturn(Optional.of(masterCategory2));

        MasterRoomType masterType1 = new MasterRoomType();
        masterType1.setRoomtypeId(101L);
        when(roomTypeRepository.findById(101L)).thenReturn(Optional.of(masterType1));

        MasterRoomType masterType2 = new MasterRoomType();
        masterType2.setRoomtypeId(102L);
        when(roomTypeRepository.findById(102L)).thenReturn(Optional.of(masterType2));

        MasterRoomType masterType3 = new MasterRoomType();
        masterType3.setRoomtypeId(103L);
        when(roomTypeRepository.findById(103L)).thenReturn(Optional.of(masterType3));

        // Act
        Hotel hotel = hotelMapper.mapToEntity(hotelDTO);

        // Assert
        assertNotNull(hotel);
        assertEquals("Test Hotel", hotel.getHotelName());

        // Verify room categories
        assertNotNull(hotel.getHotelRoomCategories());
        assertEquals(2, hotel.getHotelRoomCategories().size());

        HotelRoomCategory savedCategory1 = hotel.getHotelRoomCategories().get(0);
        assertEquals("Junior Suite", savedCategory1.getName());
        assertEquals("10", savedCategory1.getNoOfRooms());
        assertEquals(hotel, savedCategory1.getHotel());

        HotelRoomCategory savedCategory2 = hotel.getHotelRoomCategories().get(1);
        assertEquals("Classic Room Balcony", savedCategory2.getName());
        assertEquals("20", savedCategory2.getNoOfRooms());
        assertEquals(hotel, savedCategory2.getHotel());

        // Verify room types
        assertNotNull(hotel.getHotelRoomTypes());
        assertEquals(3, hotel.getHotelRoomTypes().size());

        // Verify room types are correctly linked to categories by index
        HotelRoomType savedType1 = hotel.getHotelRoomTypes().get(0);
        assertEquals(0L, savedType1.getHotelRoomcategoryId()); // Should reference first category
        assertEquals(hotel, savedType1.getHotel());

        HotelRoomType savedType2 = hotel.getHotelRoomTypes().get(1);
        assertEquals(0L, savedType2.getHotelRoomcategoryId()); // Should reference first category
        assertEquals(hotel, savedType2.getHotel());

        HotelRoomType savedType3 = hotel.getHotelRoomTypes().get(2);
        assertEquals(1L, savedType3.getHotelRoomcategoryId()); // Should reference second category
        assertEquals(hotel, savedType3.getHotel());

        // Verify repository calls
        verify(roomCategoryRepository, times(2)).findById(anyLong());
        verify(roomTypeRepository, times(3)).findById(anyLong());
    }

    @Test
    void testEntityToDTOMapping() {
        // Arrange
        Hotel hotel = new Hotel();
        hotel.setHotelId(1L);
        hotel.setHotelName("Test Hotel");

        // Create room categories
        HotelRoomCategory category1 = new HotelRoomCategory();
        category1.setHotel_room_category_id(10L);
        category1.setHotel(hotel);
        category1.setName("Junior Suite");
        category1.setNoOfRooms("10");

        MasterRoomCategory masterCategory1 = new MasterRoomCategory();
        masterCategory1.setRoomCategoryId(1L);
        category1.setRoomCategory(masterCategory1);

        hotel.setHotelRoomCategories(Arrays.asList(category1));

        // Create room types
        HotelRoomType type1 = new HotelRoomType();
        type1.setHotelRoomTypeId(20L);
        type1.setHotel(hotel);
        type1.setHotelRoomcategoryId(0L);

        MasterRoomType masterType1 = new MasterRoomType();
        masterType1.setRoomtypeId(101L);
        type1.setRoomType(masterType1);

        hotel.setHotelRoomTypes(Arrays.asList(type1));

        // Act
        HotelDTO dto = hotelMapper.mapToDTO(hotel);

        // Assert
        assertNotNull(dto);
        assertEquals(1L, dto.getId());
        assertEquals("Test Hotel", dto.getHotelName());

        // Verify room categories mapping
        assertNotNull(dto.getRoomCategories());
        assertEquals(1, dto.getRoomCategories().size());

        HotelRoomCategoryDTO categoryDTO = dto.getRoomCategories().get(0);
        assertEquals(10L, categoryDTO.getHotelRoomCategoryId());
        assertEquals(1L, categoryDTO.getHotelId());
        assertEquals(1L, categoryDTO.getRoomCategoryId());
        assertEquals("Junior Suite", categoryDTO.getName());
        assertEquals("10", categoryDTO.getNoOfRooms());

        // Verify room types mapping
        assertNotNull(dto.getRoomTypes());
        assertEquals(1, dto.getRoomTypes().size());

        HotelRoomTypeDTO typeDTO = dto.getRoomTypes().get(0);
        assertEquals(1L, typeDTO.getHotelId());
        assertEquals(0L, typeDTO.getHotelRoomCategoryId());
        assertEquals(101L, typeDTO.getRoomTypeId());
    }
}
