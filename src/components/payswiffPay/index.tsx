import React, { useEffect } from 'react'
import { View, Text, NativeModules, TouchableOpacity, Image,NativeEventEmitter } from 'react-native'
import { Images } from '../../assets'
import PaySwiffIntegration, { RNPayswiffMethods } from '../../PayswiffIntegration'

export const PayswiffPay = () => {
    const onSuccess = (eventName,response) => {
        console.log('SUCCESS',eventName,response)
    }
    useEffect(() => {
            RNPayswiffMethods.getTransactionStatus((event) => onSuccess(event.eventName,event.response))
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
