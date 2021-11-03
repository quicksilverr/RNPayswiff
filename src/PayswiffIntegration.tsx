/**
* This exposes the native CalendarModule module as a JS module. This has a
* function 'createCalendarEvent' which takes the following parameters:
*
* 1. String name: A string representing the name of the event
* 2. String location: A string representing the location of the event
*/
import { NativeModules, NativeEventEmitter } from 'react-native';
const { RNPayswiff } = NativeModules

interface Event {
    eventName: string
    response: string
}

const eventEmitter = new NativeEventEmitter(NativeModules.RNPayswiff);
const getTransactionStatus = (callBack: (event: Event) => void) => {
    eventEmitter.addListener('RNPayswiffResponseEvent',callBack)
}

export const RNPayswiffMethods = {
    getTransactionStatus: getTransactionStatus
}

interface PaySwiffIntegrationInterface {
    startTransaction(
        description: string,
        amount: string,
        customerMobileNumber: string, 
        paymentOrderNo: string, 
        merchandId: number,
        billingContactName: string,
        responseUrl: string,
        billingCity: string,
        billingState: string,
        billingPostalCode: string,
        billingCountry: string,
        billingEmail: string,
        secretKey: string
        ): void;
        getTransactionDetails( 
        paymentOrderNo: string, 
        merchandId:number,
        secretKey:string
        ): void;
        getTransactionStatus( 
        paymentOrderNo: string, 
        merchandId:number,
        secretKey:string
        ): void;
}
export default RNPayswiff as PaySwiffIntegrationInterface;