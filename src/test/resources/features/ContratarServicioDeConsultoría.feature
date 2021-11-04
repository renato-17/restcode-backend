Feature: Contratar servicio de consultoría
  COMO dueño de un restaurante
  QUIERO contratar un servicio de consultoría
  PARA evaluar y escoger la solución más efectiva al problema que estoy atravesando,
  además de mejorar la gestión de mi negocio de una forma eficiente

  Scenario: El dueño del restaurante ingresa a la vista "Asesorías y consultorías"
    Given el dueño del restaurante se encuentra en la vista principal después de haber iniciado sesión
      | Email           | Password     |
      | carla@gmail.com | Carla#1      |
    When selecciona el botón Asesorías y consultorías
    Then el sistema lo redirecciona a la vista “Asesorías y consultorías".

  Scenario: El dueño del restaurante programa una cita
    Given el dueño del restaurante quiere programar una cita con un consultor
    When elige un consultor y solicita una cita ingresando los datos que se piden
      | CurrentDateTime  | ScheduleDateTime  | Topic  | MeetLink                                   |
      | 03/17/2021T18:30 | 03/18/2021T18:30 | Topic1 | https://meet.google.com/fyh-vnxx-tah?pli=1 |
    Then el sistema programa la cita de manera exitosa.