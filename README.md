# Weather Forecast API with AWS Lambda (Java)

This project is a serverless REST API developed with Java 21, deployed on AWS Lambda and triggered via API Gateway. It receives a city name as input and returns real-time weather information from OpenWeatherMap.

## Public Demo (lambda Endpoint)

You can test the API live using the public lambda endpoint:

https://yvdlwxaymiddkan6ct4a4tmyq40otaof.lambda-url.us-east-2.on.aws/
Make sure to send a POST request with JSON content.

### Example Request
**POST** /weather  
**Content-Type**: application/json

```json
{
  "city": "natal"
}
```

Response (200 OK)
```json
{
  "coord": {
    "lon": -35.2094,
    "lat": -5.795
  },
  "weather": [
    {
      "id": 803,
      "main": "Clouds",
      "description": "nublado",
      "icon": "04d"
    }
  ],
  "base": "stations",
  "main": {
    "temp": 27.12,
    "feels_like": 29.42,
    "temp_min": 26.36,
    "temp_max": 27.12,
    "pressure": 1015,
    "humidity": 74,
    "sea_level": 1015,
    "grnd_level": 1013
  },
  "visibility": 10000,
  "wind": {
    "speed": 8.75,
    "deg": 150
  },
  "clouds": {
    "all": 75
  },
  "dt": 1752005033,
  "sys": {
    "type": 1,
    "id": 8417,
    "country": "BR",
    "sunrise": 1751963504,
    "sunset": 1752005984
  },
  "timezone": -10800,
  "id": 3394023,
  "name": "Natal",
  "cod": 200
}
```
#### The response contains current weather data from the OpenWeatherMap API.

## Technologies used

- Java 21+
- Spring Boot
- AWS Lambda
- OpenWeatherMap API
- Maven
- OkHttp (HTTP client)
- Jackson (JSON processing)

---

### Features
* Real-time weather forecast by city
* Integration with OpenWeatherMap API
* Deployable as AWS Lambda function
* Lightweight and fast (no Spring framework)