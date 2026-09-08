# Zoop.one – QA Engineer Assessment

Java + Selenium + TestNG + REST Assured automation for **The Internet by Herokuapp**.

## What is covered

The suite intentionally focuses on the requirements in the assessment rather than automating every page on the sample site.

| Area | Coverage | Why it was selected |
|---|---|---|
| Dynamic Controls | Remove/add checkbox; enable/disable input | Asynchronous DOM changes and changing UI state |
| Dynamic Loading | Example 1 and Example 2 | Delayed responses and elements appearing after an action |
| Status Codes | UI 200 + API 200/301/404/500 | Frontend + backend response validation and unexpected/failure responses |
| Notification Message | Dynamic success/failure message | UI state/content can vary between requests |

The sample site documents Dynamic Controls as asynchronous changes to elements and Dynamic Loading as content updated dynamically without a full page reload.

## Framework design

```text
src/test/java/com/zoop/qa
├── base
│   ├── BaseTest.java
│   └── DriverFactory.java
├── config
│   └── ConfigReader.java
├── pages
│   ├── DynamicControlsPage.java
│   ├── DynamicLoadingPage.java
│   ├── NotificationMessagePage.java
│   └── StatusCodesPage.java
├── tests
│   ├── DynamicControlsTest.java
│   ├── DynamicLoadingTest.java
│   ├── NotificationMessageTest.java
│   └── StatusCodesTest.java
└── utils
    ├── ApiClient.java
    ├── ScreenshotUtils.java
    ├── TestListener.java
    └── WaitUtils.java
```

### Key decisions

- **Page Object Model:** UI locators and actions are separated from test assertions.
- **Explicit waits only:** `WebDriverWait` / Selenium `ExpectedConditions` are used for visibility, clickability, disappearance and state changes. There is no `Thread.sleep()`.
- **Dynamic locators:** the suite uses semantic text, element type and stable attributes instead of depending exclusively on IDs.
- **Selenium Manager:** no WebDriver executable is hardcoded or committed to the repository.
- **API validation:** REST Assured calls `/status_codes/{code}` directly, applies a configurable API timeout, and disables redirect following so a `301` can be validated as a real `301` response.
- **Independent API tests:** API checks do not create a browser session; UI checks retain their WebDriver setup.
- **Failure diagnostics:** failed UI tests capture uniquely named screenshots in `target/screenshots`; an unexpected API status logs the full response before failing the assertion.
- **Configurable data:** URL, browser, headless mode, UI timeout and API timeout are externalized to `config.properties` and can be overridden with Maven system properties.
- **DataProvider:** HTTP status cases are data-driven rather than duplicated across test methods.

## Requirements

- Java 17+
- Maven 3.9+
- Google Chrome / Chromium
- Internet access

The project pins Selenium 4.35.0, TestNG 7.11.0 and REST Assured 5.5.6.

## Run from command line

From the project root:

```bash
mvn clean test
```

Headless is enabled by default. To run with a visible browser:

```bash
mvn clean test -Dheadless=false
```

To override the browser URL or timeout:

```bash
mvn clean test -DbaseUrl=https://the-internet.herokuapp.com -DtimeoutSeconds=20
```

## Run in Eclipse

1. **File → Import → Maven → Existing Maven Projects**.
2. Select this project folder containing `pom.xml`.
3. Right-click the project → **Maven → Update Project**.
4. Right-click `testng.xml` → **Run As → TestNG Suite**.

Do **not** use **New Maven Project → Add Archetype** for this repository. It is a normal Maven test project, not a Maven archetype.

## Reports

After execution:

- Surefire/TestNG XML and text reports: `target/surefire-reports/`
- Failure screenshots: `target/screenshots/`
- Assessment execution-report template: `reports/Execution_Report.md`

The Maven Surefire reports are the source of truth for the final pass/fail result. The report template is included so the evaluator has a clear mapping between requirements and tests.

## Test cases

### TC-DC-01 – Remove/add checkbox

1. Open Dynamic Controls.
2. Verify checkbox is present.
3. Click **Remove**.
4. Wait until checkbox disappears.
5. Verify `It's gone!`.
6. Click **Add**.
7. Wait until checkbox is visible again.
8. Verify `It's back!`.

### TC-DC-02 – Enable/disable input

1. Open Dynamic Controls.
2. Verify text input is disabled.
3. Click **Enable**.
4. Wait for the input's enabled state.
5. Verify `It's enabled!`.
6. Click **Disable**.
7. Wait for the input's disabled state.
8. Verify `It's disabled!`.

### TC-DL-01 – Dynamic Loading Example 1

Start the request and wait for the initially hidden `Hello World!` element to become visible.

### TC-DL-02 – Dynamic Loading Example 2

Start the request and wait for `Hello World!` to be rendered into the DOM.

### TC-SC-01 – Backend status-code validation

Validate `200`, `301`, `404` and `500` using REST Assured. Redirect following is disabled so the API assertion validates the actual response code. The `404` and `500` cases are intentional negative-response checks, not test failures; an unexpected response is logged and fails the test.

### TC-SC-02 – UI status-code validation

Open Status Codes, select `200`, and verify the resulting page identifies the 200 response.

### TC-NM-01 – Dynamic notification

Trigger the notification and accept either documented outcome: `Action successful` or `Action unsuccessful`.

## CI

A GitHub Actions workflow is included under `.github/workflows/qa.yml`. It installs Java, runs the Maven suite and uploads `target/surefire-reports` and `target/screenshots` as artifacts.

After publishing the repository, confirm that the workflow completes successfully and include the workflow-run link with the submission. It is the execution evidence for this project.

## Failure handling

- UI state changes are synchronized with explicit waits instead of fixed delays.
- Assertions fail with scenario-specific messages when the observed UI or HTTP status differs from the expected result.
- Failed UI tests produce a timestamped screenshot without masking the original failure.
- API requests have a configurable timeout and log the response when the returned status is unexpected.

## Submission checklist

- [x] Java + Selenium implementation
- [x] Dynamic element handling
- [x] UI synchronization without fixed waits
- [x] Backend/API validation where applicable
- [x] Failure/unexpected-response assertions
- [x] Reusable Page Objects and utilities
- [x] TestNG suite
- [x] README with approach and execution instructions
- [x] Execution-report template
- [x] CI workflow
- [x] Push repository to Git