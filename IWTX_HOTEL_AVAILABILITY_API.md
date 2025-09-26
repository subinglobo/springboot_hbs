# IWTX Hotel Room Availability API

## Overview
This API provides hotel room availability and pricing information by integrating with the IWTX external API. It allows you to check room availability, pricing, and detailed hotel information for specific dates and guest configurations.

## API Endpoints

### 1. Check Hotel Availability
**POST** `/api/iwtx/hotel/availability`

Check hotel room availability and pricing using detailed search criteria.

#### Request Body
```json
{
  "profile": {
    "password": "C0nN3cTW0rLd_2O23",
    "code": "Connect_World",
    "tokenNumber": "1d11cf19-fc8b-4d58-b590-a048bc601f81-20230516"
  },
  "searchCriteria": {
    "roomConfiguration": {
      "room": {
        "adult": {
          "age": "25"
        },
        "roomTypeCode": "16306703",
        "mealPlanCode": "7",
        "contractTokenId": "766536",
        "roomConfigurationId": "1"
      }
    },
    "startDate": "20260125",
    "endDate": "20260126",
    "hotelCode": "101-1256",
    "nationality": "AF",
    "includeRateDetails": "Y",
    "cancellationPolicy": "Y",
    "groupByRooms": "Y"
  }
}
```

#### Response
```json
{
  "profile": {
    "tokenNumber": "1d11cf19-fc8b-4d58-b590-a048bc601f81-20230516",
    "iata": null,
    "companyClientCode": null,
    "subClientId": null
  },
  "hotels": {
    "hotel": [
      {
        "sourceId": "101",
        "hotelId": "20191",
        "hotelName": "Address Montgomerie",
        "propertyType": "Hotel",
        "starRating": "5",
        "geoLocation": {
          "longitude": "55.164388",
          "latitude": "25.067463"
        },
        "chain": "Emaar Hospitality Group",
        "hotelCode": "101-1256",
        "timeZone": "Asia/Dubai",
        "city": "DXB",
        "roomTypeDetails": {
          "rooms": {
            "room": [
              {
                "roomNo": "1",
                "roomType": "Deluxe Room Courtyard View",
                "roomTypeCode": "16306703",
                "roomStatus": "OK",
                "blackOut": {
                  "status": "N",
                  "msg": ""
                },
                "currCode": "USD",
                "contractTokenId": "766536",
                "roomConfigurationId": "1",
                "ratePlanId": "766536",
                "mealPlan": "Breakfast",
                "mealPlanCode": "7",
                "numberOfMeals": "1",
                "roomNumber": "1",
                "rate": "533.84",
                "rateDetails": {
                  "rate": "533.84"
                },
                "roomStatusDetails": {
                  "status": "OK"
                },
                "messages": {
                  "message": [
                    {
                      "id": "0",
                      "messageShort": "Rates are dynamic and non-amendable.",
                      "messageFull": "Refundable rates are dynamic, non-changeable, and non-amendable.",
                      "type": "Amendment Policy",
                      "messageChargeBase": "0",
                      "valueType": "Fixed",
                      "ageFrom": "0",
                      "ageTo": "99"
                    }
                  ]
                },
                "packageYN": "N",
                "nonRefundable": "N",
                "dynamicYN": "Y",
                "recommendedRetailPrice": "660.63",
                "contractLabel": "Bed & Breakfast Flexible"
              }
            ]
          }
        },
        "startDate": "20260125",
        "endDate": "20260126"
      }
    ]
  }
}
```

### 2. Get Sample Request
**GET** `/api/iwtx/hotel/availability/sample`

Generate a sample hotel search request for testing purposes.

#### Query Parameters
- `hotelCode` (optional): Hotel code to search for (default: "101-1256")
- `startDate` (optional): Check-in date in YYYYMMDD format (default: "20260125")
- `endDate` (optional): Check-out date in YYYYMMDD format (default: "20260126")
- `nationality` (optional): Guest nationality code (default: "AF")

#### Example
```
GET /api/iwtx/hotel/availability/sample?hotelCode=101-1256&startDate=20260125&endDate=20260126&nationality=AF
```

### 3. Test Hotel Availability
**POST** `/api/iwtx/hotel/availability/test`

Test the hotel availability API using sample data for quick testing.

#### Query Parameters
- `hotelCode` (optional): Hotel code to test (default: "101-1256")
- `startDate` (optional): Check-in date in YYYYMMDD format (default: "20260125")
- `endDate` (optional): Check-out date in YYYYMMDD format (default: "20260126")
- `nationality` (optional): Guest nationality code (default: "AF")

#### Example
```
POST /api/iwtx/hotel/availability/test?hotelCode=101-1256&startDate=20260125&endDate=20260126&nationality=AF
```

## Request Parameters

### Profile
- `password`: IWTX API password
- `code`: IWTX API client code
- `tokenNumber`: IWTX API token number

### Search Criteria
- `hotelCode`: Specific hotel code to search
- `startDate`: Check-in date in YYYYMMDD format
- `endDate`: Check-out date in YYYYMMDD format
- `nationality`: Guest nationality code (2-letter country code)
- `includeRateDetails`: Include detailed rate information ("Y" or "N")
- `cancellationPolicy`: Include cancellation policy ("Y" or "N")
- `groupByRooms`: Group results by rooms ("Y" or "N")

### Room Configuration
- `adult.age`: Age of adult guest
- `roomTypeCode`: Specific room type code
- `mealPlanCode`: Meal plan code
- `contractTokenId`: Contract token identifier
- `roomConfigurationId`: Room configuration identifier

## Response Structure

### Hotel Information
- `sourceId`: Source system identifier
- `hotelId`: Internal hotel identifier
- `hotelName`: Hotel name
- `propertyType`: Type of property (Hotel, Resort, etc.)
- `starRating`: Hotel star rating
- `geoLocation`: Latitude and longitude coordinates
- `chain`: Hotel chain name
- `hotelCode`: Hotel code
- `timeZone`: Hotel timezone
- `city`: City code

### Room Details
- `roomType`: Room type description
- `roomTypeCode`: Room type code
- `roomStatus`: Availability status
- `currCode`: Currency code
- `rate`: Room rate
- `mealPlan`: Meal plan description
- `mealPlanCode`: Meal plan code
- `contractLabel`: Contract description
- `recommendedRetailPrice`: Suggested retail price

## Error Handling

### Error Response Format
```json
{
  "timestamp": "2026-01-25T10:30:00",
  "status": 400,
  "error": "IWTX Availability API Error",
  "message": "Error description",
  "errorCode": "IWTX_001",
  "path": "/api/iwtx/hotel/availability"
}
```

### Common Error Codes
- `IWTX_001`: Invalid request parameters
- `IWTX_002`: External API connection failure
- `IWTX_003`: XML parsing error
- `IWTX_004`: Authentication failure

## Configuration

### Application Properties
```properties
# IWTX API Configuration
iwtx.api.availability.url=https://api.iwtxconnect.com/hotel/availability
iwtx.api.password=C0nN3cTW0rLd_2O23
iwtx.api.code=Connect_World
iwtx.api.token=1d11cf19-fc8b-4d58-b590-a048bc601f81-20230516
```

## Usage Examples

### cURL Example
```bash
curl -X POST "http://localhost:8081/api/iwtx/hotel/availability" \
  -H "Content-Type: application/json" \
  -d '{
    "profile": {
      "password": "C0nN3cTW0rLd_2O23",
      "code": "Connect_World",
      "tokenNumber": "1d11cf19-fc8b-4d58-b590-a048bc601f81-20230516"
    },
    "searchCriteria": {
      "roomConfiguration": {
        "room": {
          "adult": {
            "age": "25"
          },
          "roomTypeCode": "16306703",
          "mealPlanCode": "7",
          "contractTokenId": "766536",
          "roomConfigurationId": "1"
        }
      },
      "startDate": "20260125",
      "endDate": "20260126",
      "hotelCode": "101-1256",
      "nationality": "AF",
      "includeRateDetails": "Y",
      "cancellationPolicy": "Y",
      "groupByRooms": "Y"
    }
  }'
```

### Test Endpoint Example
```bash
curl -X POST "http://localhost:8081/api/iwtx/hotel/availability/test?hotelCode=101-1256&startDate=20260125&endDate=20260126&nationality=AF"
```

## Notes
- All dates must be in YYYYMMDD format
- The API uses XML for external communication but provides JSON REST endpoints
- Rate information is returned in the currency specified by the hotel (typically USD)
- The API includes comprehensive error handling and logging
- Swagger documentation is available at `/swagger-ui.html` when the application is running
