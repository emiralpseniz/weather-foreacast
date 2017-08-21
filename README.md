# weather-foreacast
A web application providing five days weather forecast for cities using OpenWeatherMap API

Although OpenWeatherMap provides significant detail, this application simplifies it and provides
only daily lowest and highest temperatures. Also it calculates the hightest and lowest two
temperatures for that period.

## Structure
The application consists of two separate parts.  

### Backend application
Backend is developed in Java using Spring boot. It acts as a proxy for 
the OpenWeatherMap API.

The API of this proxy application is tiny and provides only one interface:

    `/forecast/city/{city}[?unit={temperature_unit}]`

{city}: Required city parameter.
{temperature_unit}: Optional temperature unit. Can be "metric" for Celsius or
"imperial" for Fahrenheit. If not provided temperatures will be listed in Kelvin
units.

#### How to run
Application uses maven for dependency management and can be started using it.

    `mvn spring-boot:run` to quickly compile and run the application.

Please note that application uses default `8080` port that can be changed 
in the properties file using `server.port` setting.

### Frontend application
Frontend application is a SPA that provides graphical interface to the users.
In it's only page it has just one field that user can manipulate to get
the forecasts for.

The application will have the capability to get location of the user automatically if it is allowed.
Currently the api is still implemented. Google's Maps API will be used to guess user's city.

Users can access this page from `localhost:8080`.

### Further Improvements
The application can be easily containerized (Dockerized). This way the application
can be run as many instances together. A further improvement on this can be to
centralize configuration management (via Spring's config-server, HashiCorp's consul, etc.)

Application does not have caching capability, using simple cache's can improve the response times.

OpenWeatherMap API supports geolocation requests. In order to simplify getting user's city
automatically, the logic can be changed to getting geolocation and passing it to the
external API to get the forecasts.

### Missing Parts
Google SSO (honestly I didn't had time for this and didn't understand how to use it in the app)
Backend app has tests included but there is no frontend testing and automated testing.