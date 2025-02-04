import { View, Text } from 'react-native'
import React from 'react'
import { createNativeStackNavigator } from '@react-navigation/native-stack'
import { NavigationContainer } from '@react-navigation/native';
import Login from '../componentes/Login';
import Menu from '../componentes/Menu';
import Ubicacion from '../componentes/Ubicacion';
import Rastreo from '../Ubicaciones/Rastreo';


const Stack = createNativeStackNavigator();

const MainStack = () => {
  return (
    <NavigationContainer>
        <Stack.Navigator>

            <Stack.Screen
                name='Inicio'
                component={Login}

            />

            <Stack.Screen
                name='Menu'
                component={Menu}

            />

            <Stack.Screen
                name='Mapa'
                component={Ubicacion}

            />

            <Stack.Screen
                name='Tracking'
                component={Rastreo}

            />

        </Stack.Navigator>
    </NavigationContainer>
  )
}

export default MainStack