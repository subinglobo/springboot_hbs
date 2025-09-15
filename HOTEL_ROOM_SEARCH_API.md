# Hotel Room Search API

## Overview
RESTful API endpoint for searching hotel rooms with support for IWTX external API integration (apiId=11).

## Endpoint
```
POST /api/hotel-rooms/search
GET /api/hotel-rooms/health
```

## Request Format
```json
{
  "checkInDate": "2024-12-01",
  "checkOutDate": "2024-12-03", 
  "hotelCode": "HTL001",
  "nationality": "US",
  "agentId": "AGENT123",
  "apiId": 11,
  "rooms": [
    {
      "adults": 2,
      "children": 1,
      "adultAges": [30, 28],
      "childAges": [8]
    }
  ]
}
```

## Response Format
```json
{
  "success": true,
  "message": "Search completed successfully",
  "hotels": [
    {
      "hotelId": "HTL001",
      "hotelName": "Mock Hotel HTL001",
      "starRating": 4,
      "propertyType": "Hotel",
      "chain": null,
      "city": "Mock City",
      "timeZone": null,
      "geoLocation": null,
      "rooms": []
    }
  ]
}
```

## Key Features
- **Age Separation**: Clear distinction between adult and child ages
- **Validation**: Comprehensive input validation with detailed error messages
- **Error Handling**: Robust error handling with standardized responses
- **Mock Implementation**: Currently returns mock data for testing
- **Extensible**: Designed to support multiple APIs (currently supports apiId=11)

## Configuration
Update `application.properties`:
```properties
# IWTX API Configuration
iwtx.api.url=https://your-iwtx-api-endpoint
iwtx.api.password=your-password
iwtx.api.code=your-code
iwtx.api.token=your-token
```

## Current Status
- ✅ Mock implementation working
- ✅ Age handling fixed (separate adult/child ages)
- ✅ Validation and error handling complete
- 🔄 XML processing temporarily disabled (will be re-enabled after dependency resolution)

## Next Steps
1. Enable full XML processing with IWTX API
2. Add comprehensive unit and integration tests
3. Add API documentation with Swagger/OpenAPI
