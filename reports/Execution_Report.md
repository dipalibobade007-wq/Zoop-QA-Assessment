# QA Assessment – Execution Report

## Environment

| Item | Value |
|---|---|
| Application | The Internet by Herokuapp |
| Automation | Java + Selenium |
| Test runner | TestNG |
| API library | REST Assured |
| Browser | Chrome / Chromium |
| Execution mode | Headless by default |
| Java | 17+ |

## Execution command

```bash
mvn clean test
```

## Automated coverage

| ID | Scenario | Expected result | Result |
|---|---|---|---|
| TC-DC-01 | Dynamic checkbox remove/add | Checkbox disappears/reappears and correct messages are shown | Pending local execution |
| TC-DC-02 | Dynamic input enable/disable | Input state changes asynchronously and correct messages are shown | Pending local execution |
| TC-DL-01 | Dynamic Loading example 1 | `Hello World!` becomes visible after loading | Pending local execution |
| TC-DL-02 | Dynamic Loading example 2 | `Hello World!` is rendered after loading | Pending local execution |
| TC-SC-01 | API status codes 200/301/404/500 | API returns the expected HTTP status for each endpoint | Pending local execution |
| TC-SC-02 | UI status code 200 | UI displays the successful status-code response | Pending local execution |
| TC-NM-01 | Dynamic notification | Notification contains one of the documented success/failure outcomes | Pending local execution |

## Evidence after execution

- `target/surefire-reports/` – TestNG/Surefire execution results.
- `target/screenshots/` – screenshots automatically captured for failed UI tests.

Unexpected API status responses are logged in the test output before the assertion fails. Failed UI screenshots use timestamped filenames to preserve evidence across reruns.

> Note: this repository was prepared as the submission package. The execution environment used to prepare it did not have Maven installed, so no pass/fail result has been fabricated. Run `mvn clean test` locally or in CI to populate the final execution results.
