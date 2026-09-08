# Test Data

The assessment scenarios do not require user-specific credentials. Stable application configuration is kept in `src/test/resources/config.properties`.

HTTP status scenarios are maintained in a TestNG `@DataProvider` so the same test logic is reused for 200, 301, 404 and 500 responses.
