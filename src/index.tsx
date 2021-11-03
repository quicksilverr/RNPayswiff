import React, { useEffect } from 'react'
import { PayswiffPay } from './components/payswiffPay'
import { NativeModules, NativeEventEmitter } from 'react-native'


export const Root = () => {
  return <PayswiffPay/>
}

export default Root
