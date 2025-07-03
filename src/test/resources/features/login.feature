Feature: validate login functionality

@manual
@manual-result:passed
@manual-last-tested:sprint-15
@manual-test-evidence:assets/invoice_download_screenshot.png
@manual-test-evidence:https://some.external/link.png
Scenario: validate that user is able to login with valid credentials
Given user is opening "chrome" browser
And navigating to "https://www.facebook.com"
And type "test" in "username" field



Scenario: validate that user is able not to login with valid credentials
Given user is opening "chrome" browser
And navigating to "https://www.facebook.com"
And type "second" in "username" field