# Natural Disasters Analysis Web App 🌍

This is a Spring Boot-based web application that processes and analyzes historical data on natural disasters (e.g., floods, earthquakes, etc.) per country and per year.

It provides statistical summaries and regression analysis based on selected country, disaster type, and optional time range. The frontend is built using Thymeleaf templates.

---

### Features

- 📄 Load data from a `.tsv` file into a database automatically on application startup
- 🌐 Web interface for querying disaster data by country and type
- 📊 Descriptive statistics: min, max, mean, median, standard deviation, kurtosis, etc.
- 📈 Linear regression analysis to identify trends over the years
- 🛠️ Spring Boot + Thymeleaf + JPA + Apache Commons Math3

---

### Project Structure

| Component                     | Package                 | Description                                      |
|------------------------------|-------------------------|--------------------------------------------------|
| `NaturalDisaster`            | `com.app.model`         | Entity representing a natural disaster entry     |
| `NaturalDisasterRepository`  | `com.app.repository`    | Spring Data JPA repository                       |
| `NaturalDisasterService`     | `com.app.service`       | Handles filtering and computes results           |
| `ISingleMeasureRequest`      | `com.app.domain`        | Interface for a single analysis request          |
| `MeasurementVectorImpl`      | `com.app.domain.impl`   | Stores country + type + year-value pairs         |
| `NaturalDisasterController`  | `com.app.controller`    | Handles web requests and renders results         |
| `NaturalDisasterDataLoader`  | `com.app.service`       | Loads data from TSV on startup                   |

---

### Usage

1. Place your data file at:  
   `src/main/resources/disasters.tsv`  
   Format: TSV with header including columns for year values (e.g. `1980`, `1981`, ..., `2022`).

2. Run the Spring Boot application.

3. Open your browser and go to:  
   [http://localhost:8080/disasters](http://localhost:8080/disasters)

4. Fill out the form to select a country and disaster type (and optionally a time range).

---

### Status

-- Under development ⚙️

---

### Requirements

- Java 17+
- Maven
- Spring Boot 3.x
- Apache Commons Math3

---

