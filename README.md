# PersonalInsuranceApp

## Table of contents
* [Setup](#setup)

	
## Setup
To run this project, do the below:

1) Download github folder and turn on docker Desktop

2) After unzipping above folder open it, and run the below command in the current (main directory) directory
```
$ docker compose up
```

3) Send cUrl command to this address: http://localhost:8081/bank-account/create-business  to create business account for the Insurance company
```
{
"accountNumber" : "aa83cab5-55bc-42d1-b075-6196271cdeb2"
}
```

4) Send cUrl command to this address: http://localhost:8081/bank-account/create   to create an account with an overdraft limit. Account # will be returned, save number.

```
{
"overdraftLimit" : 250
}
```



5) Fill out applciation form, in order to receive our policy. Dummy command from David Thomas (random name) made below, Bank Account Account # is from step 4. Destination address  is http://localhost:8083/claims/policy

```
{
"firstName" : "David",
"lastName" : "Thomas",
"bankAccountId" : "124-number-here",
"coverageType" : "2",
"coverageAmount" : 2000,
"creditScore" : 234
}

```

6) Make attempt to file a claim, POST request to address : http://localhost:8083/claims/file . Send the below body as an example. Response will be given if successful
```
{
"firstName" : "David",
"lastName" : "Thomas",
"damagesClaimed" : 255	,
"incidentType" :	"2"	,
"policyId" : "91a75769-8204-490e-9515-b89626938aa6"

}

```
