import React, { useEffect, useState } from 'react';
import NotificationModel from '../../Models/NotificationModel.js';
import Swal from 'sweetalert2';

const NotificationComponent = () => {
    var lastChecked = useState();
    var contador = 0;

    const checkForNotifications = async () => {
        try {
            const response = await NotificationModel.getNotifications();
        
            console.log(response);
            console.log('ultimoGuardado: ',lastChecked);
            console.log('ID actual: ', response.id);

            if((response !== null) && (response.id !== lastChecked) && (contador !== 0)){

                if(response._tipo === 'Alerta 1'){
                    Swal.fire({
                        title: '¡AYUDA!',
                        text: '¡El usuario ' + response._usuario._nombre + ' ' + response._usuario._apellido + ' tiene al atacante a menos de 10 metros!',
                        icon: 'warning',
                        confirmButtonText: 'Aceptar'
                    });
                }else if(response._tipo === 'Alerta SOS'){
                    Swal.fire({
                        title: '¡AYUDA!',
                        text: '¡El usuario ' + response._usuario._nombre + ' ' + response._usuario._apellido + ' está en peligro!',
                        icon: 'warning',
                        confirmButtonText: 'Aceptar'
                    });
                }else if(response._tipo === 'Alerta 2'){
                    Swal.fire({
                        title: '¡AYUDA!',
                        text: '¡El usuario ' + response._usuario._nombre + ' ' + response._usuario._apellido + ' tiene a su atacante entre 10 y 30 metros!',
                        icon: 'warning',
                        confirmButtonText: 'Aceptar'
                    });
                }else if(response._tipo === 'Alerta 3'){
                    Swal.fire({
                        title: '¡AYUDA!',
                        text: '¡El usuario ' + response._usuario._nombre + ' ' + response._usuario._apellido + ' tiene a su atacante entre 30 y 50 metros!',
                        icon: 'warning',
                        confirmButtonText: 'Aceptar'
                    });
                }else if(response._tipo === 'Alerta ZONA'){
                    Swal.fire({
                        title: '¡AYUDA!',
                        text: '¡El usuario ' + response._usuario._nombre + ' ' + response._usuario._apellido + ' tiene a su atacante dentro de su zona segura!',
                        icon: 'warning',
                        confirmButtonText: 'Aceptar'
                    });
                }
            }
            lastChecked = response.id;
            contador++;
            
        } catch (error) {
            console.error('Error al verificar notificaciones', error);
        }
    };

    useEffect(() => {
        const interval = setInterval(checkForNotifications, 5000); // Revisa cada 5 segundos
        console.log('hola');
        return () => clearInterval(interval);
    }, []);

    return <></>;
    
};

export default NotificationComponent;
