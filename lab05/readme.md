# Camel Email Application

Camel spring Boot Application that exposes a generic Rest Service to send emails. Two camel components have been used: Camel Mail and Camel FreeMarker. The service is generic enough to add additional languages, templates, and variables without redeployment.


# Clone the Project

# Customise Application Properties
No changes required to run the example. The default application properties can be changed here `src/main/resources/application.yml`.

# FreeMarker Templates
Cache of template files is currently set to the default value 5 seconds. The image below represents the folder structure where the templates are hosted.

# Build the App
`mvn install`

# Run the App
`mvn spring-boot:run`

# Test the App
Open a new terminal tab in the project home directory.
## Download and Unzip fake SMTP Server
`wget http://nilhcem.github.com/FakeSMTP/downloads/fakeSMTP-latest.zip && unzip fakeSMTP-latest.zip`
## Start a fake SMTP Server
`sudo java -jar fakeSMTP*.jar`

Once the UI starts click  "Start Server"

## Invoke Rest service with the curl client:
Open a new terminal tab in the project home directory.

`curl -X POST --data @sample_request.json http://localhost:9091/citi/sendEmail`

Here is the Sample Request being used:

```json
{
    "templateID" : "001-kyc-check",
    "from" : "CCB Onboarding Case Manager <no-reply@citi.com>",
    "to" : "BSU KYC EMEA <bsu-kyc-emea@citi.com>",
    "language" : "GB",
    "templateVars" :
    {
        "clientName" : "Red Hat",
        "gfcid": "1234567890",
        "url" : "https://secureUI/Approve/Reject"
    }

}
```

Try changing the language on the request to `PT` and check the differences:
