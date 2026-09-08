# Assessment Requirement Coverage

| Assessment requirement | Implementation |
|---|---|
| Java | Java 17 source/target in `pom.xml` |
| Selenium | Selenium Java 4.35.0 |
| Dynamic elements | Dynamic Controls + Dynamic Loading page objects/tests |
| UI synchronization | Reusable `WaitUtils` using explicit waits |
| No fixed waits | No `Thread.sleep()` in the framework |
| Avoid only IDs | Text, CSS element-type selectors and stable attributes are used; IDs are used only where appropriate |
| Backend/API validation | REST Assured validates status-code endpoints |
| Delayed responses | Dynamic Loading tests wait for actual DOM state |
| Failures/unexpected API responses | Data-driven 200/301/404/500 API assertions; 301 redirects are not followed, API timeouts are configurable, and unexpected responses are logged before the assertion fails |
| Reusable locators | Page Object Model |
| Assertions | TestNG assertions |
| Framework design | BaseTest, DriverFactory, ConfigReader, Page Objects, utilities, listener |
| Execution evidence | Surefire reports + failure screenshots |
