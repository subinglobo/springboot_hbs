package com.choosenfly.hotelbookingsystem.util;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.choosenfly.hotelbookingsystem.dto.HotelBankDetailsDTO;
import com.choosenfly.hotelbookingsystem.dto.HotelContactDetailsDTO;
import com.choosenfly.hotelbookingsystem.dto.HotelDTO;
import com.choosenfly.hotelbookingsystem.dto.HotelRoomDTO;
import com.choosenfly.hotelbookingsystem.dto.HotelTermsAndConditionsDTO;
import com.choosenfly.hotelbookingsystem.dto.HotelWeekDaysDTO;
import com.choosenfly.hotelbookingsystem.entities.hotel.Hotel;
import com.choosenfly.hotelbookingsystem.entities.hotel.HotelBankDetails;
import com.choosenfly.hotelbookingsystem.entities.hotel.HotelContactDetails;
import com.choosenfly.hotelbookingsystem.entities.hotel.HotelRoom;
import com.choosenfly.hotelbookingsystem.entities.hotel.HotelTermsAndConditions;
import com.choosenfly.hotelbookingsystem.entities.hotel.HotelWeekDays;
import com.choosenfly.hotelbookingsystem.entities.hotel.linked.LinkedHotelAmenity;
import com.choosenfly.hotelbookingsystem.entities.hotel.linked.LinkedHotelRoomAmenity;
import com.choosenfly.hotelbookingsystem.entities.master.MasterHotelAmenities;
import com.choosenfly.hotelbookingsystem.repository.HotelBankDetailsRepository;
import com.choosenfly.hotelbookingsystem.repository.HotelContactDetailsRepository;
import com.choosenfly.hotelbookingsystem.repository.HotelRepository;
import com.choosenfly.hotelbookingsystem.repository.HotelRoomRepository;
import com.choosenfly.hotelbookingsystem.repository.HotelTermsAndConditionsRepository;
import com.choosenfly.hotelbookingsystem.repository.HotelWeekDaysRepository;
import com.choosenfly.hotelbookingsystem.repository.LinkedHotelRoomAmenityRepository;
import com.choosenfly.hotelbookingsystem.repository.MasterBankRepository;
import com.choosenfly.hotelbookingsystem.repository.MasterContactTypeRepository;
import com.choosenfly.hotelbookingsystem.repository.MasterCountryRepository;
import com.choosenfly.hotelbookingsystem.repository.MasterCurrencyRepository;
import com.choosenfly.hotelbookingsystem.repository.MasterHotelAmenitiesRepository;
import com.choosenfly.hotelbookingsystem.repository.MasterHotelCategoryRepository;
import com.choosenfly.hotelbookingsystem.repository.MasterHotelTypeRepository;
import com.choosenfly.hotelbookingsystem.repository.MasterMarkupTypeRepository;
import com.choosenfly.hotelbookingsystem.repository.MasterPlaceRepository;
import com.choosenfly.hotelbookingsystem.repository.MasterRegionRepository;
import com.choosenfly.hotelbookingsystem.repository.MasterRoomCategoryRepository;
import com.choosenfly.hotelbookingsystem.repository.MasterRoomTypeRepository;
import com.choosenfly.hotelbookingsystem.repository.MasterStateRepository;

@Component
public class HotelMapper {

	
	private final MasterCurrencyRepository currencyRepository;
	private final MasterHotelCategoryRepository categoryRepository;
	private final MasterHotelTypeRepository typeRepository;
	private final MasterMarkupTypeRepository markupTypeRepository;
	private final MasterRegionRepository regionRepository;
	private final MasterCountryRepository countryRepository;
	private final MasterStateRepository stateRepository;
	private final MasterPlaceRepository placeRepository;
	private final MasterHotelAmenitiesRepository amenitiesRepository;
	private final MasterContactTypeRepository contactTypeRepository;
	private final MasterBankRepository bankRepository;
	private final MasterRoomCategoryRepository roomCategoryRepository;
	private final MasterRoomTypeRepository roomTypeRepository;
	private final LinkedHotelRoomAmenityRepository roomAmenityRepository;
	
	
		@Autowired
		public HotelMapper(HotelRepository hotelRepository, MasterCurrencyRepository currencyRepository,
				MasterHotelCategoryRepository categoryRepository, MasterHotelTypeRepository typeRepository,
				MasterMarkupTypeRepository markupTypeRepository, MasterRegionRepository regionRepository,
				MasterCountryRepository countryRepository, MasterStateRepository stateRepository,
				MasterPlaceRepository placeRepository, MasterHotelAmenitiesRepository amenitiesRepository,
				MasterContactTypeRepository contactTypeRepository, MasterBankRepository bankRepository,
				MasterRoomCategoryRepository roomCategoryRepository, MasterRoomTypeRepository roomTypeRepository,
				LinkedHotelRoomAmenityRepository roomAmenityRepository,
				HotelContactDetailsRepository contactDetailsRepository, HotelBankDetailsRepository bankDetailsRepository,
				HotelWeekDaysRepository weekDaysRepository, HotelRoomRepository roomRepository,
				HotelTermsAndConditionsRepository termsAndConditionsRepository) {
		
			this.currencyRepository = currencyRepository;
			this.categoryRepository = categoryRepository;
			this.typeRepository = typeRepository;
			this.markupTypeRepository = markupTypeRepository;
			this.regionRepository = regionRepository;
			this.countryRepository = countryRepository;
			this.stateRepository = stateRepository;
			this.placeRepository = placeRepository;
			this.amenitiesRepository = amenitiesRepository;
			this.contactTypeRepository = contactTypeRepository;
			this.bankRepository = bankRepository;
			this.roomCategoryRepository = roomCategoryRepository;
			this.roomTypeRepository = roomTypeRepository;
			this.roomAmenityRepository = roomAmenityRepository;

		}

	
	public Hotel mapToEntity(HotelDTO dto) {
		Hotel hotel = new Hotel();
		hotel.setHotelName(dto.getHotelName());
		hotel.setImage360(dto.getImage360());
		hotel.setHotelDescription(dto.getHotelDescription());
		hotel.setChildComAgeMin(dto.getChildComAgeMin());
		hotel.setChildComAgeMax(dto.getChildComAgeMax());
		hotel.setChildChargeableAgeMin(dto.getChildChargeableAgeMin());
		hotel.setChildChargeableAgeMax(dto.getChildChargeableAgeMax());
		hotel.setAddress(dto.getAddress());
		hotel.setZipcode(dto.getZipcode());
		hotel.setLatitude(dto.getLatitude());
		hotel.setLongitude(dto.getLongitude());
		hotel.setIsDeleted(dto.getIsDeleted() != null ? dto.getIsDeleted() : false);

		// Map referenced entities
		if (dto.getHotelCurrencyId() != null) {
			hotel.setHotelCurrency(currencyRepository.findById(dto.getHotelCurrencyId())
					.orElseThrow(() -> new RuntimeException("Currency not found")));
		}
		if (dto.getHotelCategoryId() != null) {
			hotel.setHotelCategory(categoryRepository.findById(dto.getHotelCategoryId())
					.orElseThrow(() -> new RuntimeException("Category not found")));
		}
		if (dto.getHotelTypeId() != null) {
			hotel.setHotelType(typeRepository.findById(dto.getHotelTypeId())
					.orElseThrow(() -> new RuntimeException("Type not found")));
		}
		if (dto.getRegionId() != null) {
			hotel.setRegion(regionRepository.findById(dto.getRegionId())
					.orElseThrow(() -> new RuntimeException("Region not found")));
		}
		if (dto.getMarkupTypeId() != null) {
			hotel.setMarkupType(markupTypeRepository.findById(dto.getMarkupTypeId())
					.orElseThrow(() -> new RuntimeException("Markup type not found")));
		}

		if (dto.getCountryId() != null) {
			hotel.setCountry(countryRepository.findById(dto.getCountryId())
					.orElseThrow(() -> new RuntimeException("Country not found")));
		}
		if (dto.getStateId() != null) {
			hotel.setState(stateRepository.findById(dto.getStateId())
					.orElseThrow(() -> new RuntimeException("State not found")));
		}
		if (dto.getPlaceId() != null) {
			hotel.setPlace(placeRepository.findById(dto.getPlaceId())
					.orElseThrow(() -> new RuntimeException("Place not found")));
		}
		if (dto.getAmenityIds() != null) {
			List<MasterHotelAmenities> amenities = amenitiesRepository.findAllById(dto.getAmenityIds());

			List<LinkedHotelAmenity> linkedAmenities = amenities.stream().map(amenity -> {

				LinkedHotelAmenity linkedAmenity = new LinkedHotelAmenity();
				linkedAmenity.setHotel(hotel);
				linkedAmenity.setAmenity(amenity);

				return linkedAmenity;
			}).collect(Collectors.toList());

			hotel.setHotelAmenities(linkedAmenities);

		}

		// Map nested collections
		if (dto.getContactDetails() != null) {
			List<HotelContactDetails> contactDetails = dto.getContactDetails().stream().map(contactDTO -> {
				HotelContactDetails contact = new HotelContactDetails();
				contact.setHotel(hotel);
				if (contactDTO.getContactTypeId() != null) {
					contact.setContactType(contactTypeRepository.findById(contactDTO.getContactTypeId())
							.orElseThrow(() -> new RuntimeException("Contact type not found")));
				}
				contact.setContactPerson(contactDTO.getContactPerson());
				contact.setPersonalEmail(contactDTO.getPersonalEmail());
				contact.setTeleNumber(contactDTO.getTeleNumber());
				contact.setMobileNumber(contactDTO.getMobileNumber());
				return contact;
			}).collect(Collectors.toList());
			hotel.setContactDetails(contactDetails);
		}

		if (dto.getBankDetails() != null) {
			List<HotelBankDetails> bankDetails = dto.getBankDetails().stream().map(bankDTO -> {
				HotelBankDetails bank = new HotelBankDetails();
				bank.setHotel(hotel);
				if (bankDTO.getBankId() != null) {
					bank.setBank(bankRepository.findById(bankDTO.getBankId())
							.orElseThrow(() -> new RuntimeException("Bank not found")));
				}
				bank.setAccountNo(bankDTO.getAccountNo());
				bank.setIban(bankDTO.getIban());
				bank.setSwiftCode(bankDTO.getSwiftCode());
				bank.setBankAddress(bankDTO.getBankAddress());
				bank.setTelephone(bankDTO.getTelephone());
				bank.setFaxNumber(bankDTO.getFaxNumber());
				bank.setContactPerson(bankDTO.getContactPerson());
				return bank;
			}).collect(Collectors.toList());
			hotel.setBankDetails(bankDetails);
		}

		if (dto.getWeekDays() != null) {
			HotelWeekDays weekDays = new HotelWeekDays();
			weekDays.setHotel(hotel);
			weekDays.setWdSunday(dto.getWeekDays().getWdSunday() != null ? dto.getWeekDays().getWdSunday() : false);
			weekDays.setWdMonday(dto.getWeekDays().getWdMonday() != null ? dto.getWeekDays().getWdMonday() : false);
			weekDays.setWdTuesday(dto.getWeekDays().getWdTuesday() != null ? dto.getWeekDays().getWdTuesday() : false);
			weekDays.setWdWednesday(
					dto.getWeekDays().getWdWednesday() != null ? dto.getWeekDays().getWdWednesday() : false);
			weekDays.setWdThursday(
					dto.getWeekDays().getWdThursday() != null ? dto.getWeekDays().getWdThursday() : false);
			weekDays.setWdFriday(dto.getWeekDays().getWdFriday() != null ? dto.getWeekDays().getWdFriday() : false);
			weekDays.setWdSaturday(
					dto.getWeekDays().getWdSaturday() != null ? dto.getWeekDays().getWdSaturday() : false);
			weekDays.setWedSunday(dto.getWeekDays().getWedSunday() != null ? dto.getWeekDays().getWedSunday() : false);
			weekDays.setWedMonday(dto.getWeekDays().getWedMonday() != null ? dto.getWeekDays().getWedMonday() : false);
			weekDays.setWedTuesday(
					dto.getWeekDays().getWedTuesday() != null ? dto.getWeekDays().getWedTuesday() : false);
			weekDays.setWedWednesday(
					dto.getWeekDays().getWedWednesday() != null ? dto.getWeekDays().getWedWednesday() : false);
			weekDays.setWedThursday(
					dto.getWeekDays().getWedThursday() != null ? dto.getWeekDays().getWedThursday() : false);
			weekDays.setWedFriday(dto.getWeekDays().getWedFriday() != null ? dto.getWeekDays().getWedFriday() : false);
			weekDays.setWedSaturday(
					dto.getWeekDays().getWedSaturday() != null ? dto.getWeekDays().getWedSaturday() : false);
			hotel.setWeekDays(weekDays);
		}

		if (dto.getRooms() != null) {
			List<HotelRoom> rooms = dto.getRooms().stream().map(roomDTO -> {
				HotelRoom room = new HotelRoom();
				room.setHotel(hotel);
				if (roomDTO.getRoomCategoryId() != null) {
					room.setRoomCategory(roomCategoryRepository.findById(roomDTO.getRoomCategoryId())
							.orElseThrow(() -> new RuntimeException("Room category not found")));
				}
				room.setRoomName(roomDTO.getRoomName());
				if (roomDTO.getRoomTypeId() != null) {
					room.setRoomType(roomTypeRepository.findById(roomDTO.getRoomTypeId())
							.orElseThrow(() -> new RuntimeException("Room type not found")));
				}
				room.setIsDeleted(roomDTO.getIsDeleted() != null ? roomDTO.getIsDeleted() : false);
				if (roomDTO.getAmenityIds() != null) {
					List<LinkedHotelRoomAmenity> amenities = roomAmenityRepository.findAllById(roomDTO.getAmenityIds())
							.stream().peek(amenity -> amenity.setHotelRoom(room)).collect(Collectors.toList());
					room.setAmenities(amenities);
				}
				return room;
			}).collect(Collectors.toList());
			hotel.setRooms(rooms);
		}

		if (dto.getTermsAndConditions() != null) {
			List<HotelTermsAndConditions> termsAndConditions = dto.getTermsAndConditions().stream().map(tcDTO -> {
				HotelTermsAndConditions tc = new HotelTermsAndConditions();
				tc.setHotel(hotel);
				tc.setDescription(tcDTO.getDescription());
				return tc;
			}).collect(Collectors.toList());
			hotel.setTermsAndConditions(termsAndConditions);
		}

		return hotel;
	}

	public HotelDTO mapToDTO(Hotel hotel) {

		HotelDTO dto = new HotelDTO();
		dto.setId(hotel.getHotelId());
		dto.setHotelName(hotel.getHotelName());
		dto.setHotelCurrencyId(hotel.getHotelCurrency() != null ? hotel.getHotelCurrency().getCurrencyId() : null);
		dto.setHotelCategoryId(hotel.getHotelCategory() != null ? hotel.getHotelCategory().getHotelCategoryId() : null);
		dto.setHotelTypeId(hotel.getHotelType() != null ? hotel.getHotelType().getHotelTypeId() : null);
		dto.setMarkupTypeId(hotel.getMarkupType() != null ? hotel.getMarkupType().getId() : null);
		dto.setImage360(hotel.getImage360());
		dto.setHotelDescription(hotel.getHotelDescription());
		dto.setChildComAgeMin(hotel.getChildComAgeMin());
		dto.setChildComAgeMax(hotel.getChildComAgeMax());
		dto.setChildChargeableAgeMin(hotel.getChildChargeableAgeMin());
		dto.setChildChargeableAgeMax(hotel.getChildChargeableAgeMax());
		dto.setRegionId(hotel.getRegion() != null ? hotel.getRegion().getId() : null);
		dto.setCountryId(hotel.getCountry() != null ? hotel.getCountry().getId() : null);
		dto.setStateId(hotel.getState() != null ? hotel.getState().getId() : null);
		dto.setPlaceId(hotel.getPlace() != null ? hotel.getPlace().getId() : null);
		dto.setAddress(hotel.getAddress());
		dto.setZipcode(hotel.getZipcode());
		dto.setLatitude(hotel.getLatitude());
		dto.setLongitude(hotel.getLongitude());
		dto.setIsDeleted(hotel.getIsDeleted());
		dto.setAmenityIds(
				hotel.getHotelAmenities() != null
						? hotel.getHotelAmenities().stream().map(a -> a.getAmenity()).map(a -> a.getAmenitiesId())
								.collect(Collectors.toList())
						: null);

		// Map nested collections
		if (hotel.getContactDetails() != null) {
			dto.setContactDetails(hotel.getContactDetails().stream().map(contact -> {
				HotelContactDetailsDTO contactDTO = new HotelContactDetailsDTO();
				contactDTO.setId(contact.getId());
				contactDTO.setHotelId(contact.getHotel().getHotelId());
				contactDTO.setContactTypeId(
						contact.getContactType() != null ? contact.getContactType().getContacttypeId() : null);
				contactDTO.setContactPerson(contact.getContactPerson());
				contactDTO.setPersonalEmail(contact.getPersonalEmail());
				contactDTO.setTeleNumber(contact.getTeleNumber());
				contactDTO.setMobileNumber(contact.getMobileNumber());
				return contactDTO;
			}).collect(Collectors.toList()));
		}

		if (hotel.getBankDetails() != null) {
			dto.setBankDetails(hotel.getBankDetails().stream().map(bank -> {
				HotelBankDetailsDTO bankDTO = new HotelBankDetailsDTO();
				bankDTO.setId(bank.getId());
				bankDTO.setHotelId(bank.getHotel().getHotelId());
				bankDTO.setBankId(bank.getBank() != null ? bank.getBank().getBankId() : null);
				bankDTO.setAccountNo(bank.getAccountNo());
				bankDTO.setIban(bank.getIban());
				bankDTO.setSwiftCode(bank.getSwiftCode());
				bankDTO.setBankAddress(bank.getBankAddress());
				bankDTO.setTelephone(bank.getTelephone());
				bankDTO.setFaxNumber(bank.getFaxNumber());
				bankDTO.setContactPerson(bank.getContactPerson());
				return bankDTO;
			}).collect(Collectors.toList()));
		}

		if (hotel.getWeekDays() != null) {
			HotelWeekDaysDTO weekDaysDTO = new HotelWeekDaysDTO();
			weekDaysDTO.setId(hotel.getWeekDays().getId());
			weekDaysDTO.setHotelId(hotel.getWeekDays().getHotel().getHotelId());
			weekDaysDTO.setWdSunday(hotel.getWeekDays().getWdSunday());
			weekDaysDTO.setWdMonday(hotel.getWeekDays().getWdMonday());
			weekDaysDTO.setWdTuesday(hotel.getWeekDays().getWdTuesday());
			weekDaysDTO.setWdWednesday(hotel.getWeekDays().getWdWednesday());
			weekDaysDTO.setWdThursday(hotel.getWeekDays().getWdThursday());
			weekDaysDTO.setWdFriday(hotel.getWeekDays().getWdFriday());
			weekDaysDTO.setWdSaturday(hotel.getWeekDays().getWdSaturday());
			weekDaysDTO.setWedSunday(hotel.getWeekDays().getWedSunday());
			weekDaysDTO.setWedMonday(hotel.getWeekDays().getWedMonday());
			weekDaysDTO.setWedTuesday(hotel.getWeekDays().getWedTuesday());
			weekDaysDTO.setWedWednesday(hotel.getWeekDays().getWedWednesday());
			weekDaysDTO.setWedThursday(hotel.getWeekDays().getWedThursday());
			weekDaysDTO.setWedFriday(hotel.getWeekDays().getWedFriday());
			weekDaysDTO.setWedSaturday(hotel.getWeekDays().getWedSaturday());
			dto.setWeekDays(weekDaysDTO);
		}

		if (hotel.getRooms() != null) {
			dto.setRooms(hotel.getRooms().stream().map(room -> {
				HotelRoomDTO roomDTO = new HotelRoomDTO();
				roomDTO.setId(room.getId());
				roomDTO.setHotelId(room.getHotel().getHotelId());
				roomDTO.setRoomCategoryId(
						room.getRoomCategory() != null ? room.getRoomCategory().getRoomCategoryId() : null);
				roomDTO.setRoomName(room.getRoomName());
				roomDTO.setAmenityIds(room.getAmenities() != null
						? room.getAmenities().stream().map(LinkedHotelRoomAmenity::getId).collect(Collectors.toList())
						: null);
				roomDTO.setRoomTypeId(room.getRoomType() != null ? room.getRoomType().getRoomtypeId() : null);
				roomDTO.setIsDeleted(room.getIsDeleted());
				return roomDTO;
			}).collect(Collectors.toList()));
		}

		if (hotel.getTermsAndConditions() != null) {
			dto.setTermsAndConditions(hotel.getTermsAndConditions().stream().map(tc -> {
				HotelTermsAndConditionsDTO tcDTO = new HotelTermsAndConditionsDTO();
				tcDTO.setId(tc.getId());
				tcDTO.setHotelId(tc.getHotel().getHotelId());
				tcDTO.setDescription(tc.getDescription());
				return tcDTO;
			}).collect(Collectors.toList()));
		}

		return dto;
	}
	
}
