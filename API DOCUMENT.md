Currency Exchange Rest API

1. Get Currency Rate

GET /api/exchangeService/getCurrency

This endpoint gets the exchange rate between two currencies.

Request Example:
GET /api/exchangeService/getCurrency?baseCurrency=USD&targetCurrency=EUR

Response Example:
{
  "sourceCurrency": "USD",
  "targetCurrency": "EUR",
  "conversionRate": 0.8793249598148494
}

###

2. Convert Currency

POST /api/exchangeService/convertCurrency

This endpoint converts currecny from one currency to another.

Request Example:
{
  "sourceCurrency": "USD",
  "targetCurrency": "EUR",
  "amount": 100.0
}


Response Example:
{
  "transactionId": 12345,
  "originalAmount": 100.0,
  "conversionAmount": 85.0,
  "sourceCurrency": "USD",
  "targetCurrency": "EUR",
  "conversionRate": 0.85
}

###

3. Get Conversion History

POST /api/exchangeService/conversionHistory

This endpoint gets the conversion history based on a given transaction Id or date.

Request Example:
{
  "transactionId": 12345,
  "transactionDate": "2025-04-21",
  "pageNumber": 1,
  "pageSize": 10
}

Response Example:
{
  "conversions": [
    {
      "transactionId": 1,
      "sourceCurrency": "USD",
      "targetCurrency": "USD",
      "originalAmount": 100,
      "convertedAmount": 100,
      "transactionTime": "2025-04-21T19:47:01"
    }
  ]
}



###

1. Bulk Convert Currency

POST /api/bulkExchangeService/convertCurrency

This endpoint converts multiple currency values in bulk using a CSV file upload.

Description:
The uploaded CSV file should contain rows with three columns:
- fromCurrency (String)
- toCurrency (String)
- amount (Double)

Each valid row will be processed to calculate the converted value using current exchange rates. 
Invalid or malformatted rows will be skipped.

Request:
- Multipart form-data with the file parameter.

Request Example (CSV File Content):

USD,EUR,100 
EUR,GBP,50 
JPY,USD,1000

---

Error Handling

- 400 Bad Request: If required parameters invalid.
- 500 Internal Server Error: All other exceptions
