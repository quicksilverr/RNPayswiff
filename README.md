# RNPayswiff

## Build Instructions - 
Please Run
```bash
yarn install
```

```bash
yarn android
```

## Native Modules -
The file PayswiffIntegration.tsx in src folder has all the exported native modules.
<br>The methods that are exported are:
- StartTransaction
```javascript 
startTransaction(description: string,amount: string,customerMobileNumber: string, paymentOrderNo: string, merchandId: number,billingContactName: string,responseUrl: string,billingCity: string,billingState: string,billingPostalCode: string,billingCountry: string,billingEmail: string,secretKey: string)
```
This will initiate the transaction and open the payswiff transaction window screen, from where we can pay off the amount.

- GetTransactionDetails
```javascript 
getTransactionDetails(paymentOrderNo: string, merchandId:number,secretKey:string)
```
We can get the transaction details, of a particular orderNumber that we had used for paying off a transaction. It return all the order details.
eg- customer Details, billing details etc.

- GetTransactionStatus
```javascript 
getTransactionStatus( paymentOrderNo: string,merchandId:number,secretKey:string)
```
We can get the status of a transaction by passing the orderNumber for a particular order that we had used for paying off a transaction. It returns only the
status, eg - Success,Failure or Error.

- RNPayswiffMethods
These are collection of methods that are used between android and react native.
  - getRNPayswiffResponse((event) => console.log(event))<br>
   This method listens to the events that are triggered when a particular native module function is complete.<br>
   **Please use it in an useEffect, as it's a listener method.**<br>
   The event has three things - 
     - eventName: The ending status of that event
        - RNPayswiff::OnSuccess, RNPayswiff::OnFail & RNPayswiff::onError
     - eventType: The type of event triggered (Refer Native modules)
        - payTransaction, transactionDetails & transactionStatus
     - response: The response payload.
     
     
### Example - 
```javascript
import React, { useEffect } from 'react'
import { View, Text, NativeModules, TouchableOpacity, Image,NativeEventEmitter } from 'react-native'
import { Images } from '../../assets'
import PaySwiffIntegration, { RNPayswiffMethods } from '../../PayswiffIntegration'

export const PayswiffPay = () => {
    const onSuccess = (eventName,eventType,response) => {
        console.log('SUCCESS',eventName,eventType,response)
    }
    useEffect(() => {
            RNPayswiffMethods.getRNPayswiffResponse((event) => onSuccess(event.eventName,event.eventType,event.response))
    })

    return (
        <View style={{backgroundColor: '#CAEFD1',flex: 1,justifyContent: 'center',alignItems: 'center'}}>
            <View style={{marginHorizontal: 20, marginBottom: 40}}><Image source={Images.ZONO_LOGO} /></View>
            
            <TouchableOpacity style={{borderColor: 'black', borderRadius: 6, borderStyle: 'solid', borderWidth: 1}} onPress={() => {
                PaySwiffIntegration.startTransaction('heel','11.00',"9533951423","1000123",1000001 , "Payswiff PG Integration Kit","https://netpaytest.payswiff.com/sale/redirect","Hyderabad","Telangana", "500032", "IND", "jyothikiran.nallamothu@gmail.com" ,'C67371678FB035CC7D7F0F9A04AFE2D2')
            }}>
                <Text style={{fontSize: 15, padding: 7}}>Pay your bill</Text>
            </TouchableOpacity>
            <TouchableOpacity style={{borderColor: 'black', borderRadius: 6, borderStyle: 'solid', borderWidth: 1, marginTop: 10}} onPress={() => {
                PaySwiffIntegration.getTransactionStatus("1000123",1000001 , 'C67371678FB035CC7D7F0F9A04AFE2D2')
            }}>
                <Text style={{fontSize: 15, padding: 7}}>Get Transaction Status</Text>
            </TouchableOpacity>
            <TouchableOpacity style={{borderColor: 'black', borderRadius: 6, borderStyle: 'solid', borderWidth: 1, marginTop: 10}} onPress={() => {
                PaySwiffIntegration.getTransactionDetails("1000123",1000001 , 'C67371678FB035CC7D7F0F9A04AFE2D2')
            }}>
                <Text style={{fontSize: 15, padding: 7}}>Get Transaction Details</Text>
            </TouchableOpacity>
        </View>
    )
}

```

