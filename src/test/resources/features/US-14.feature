Feature: RegistrarDueñoDeRestaurante
  COMO dueño de restaurante
  QUIERO registrarme en la aplicación web
  PARA administrar y gestionar mi negocio eficientemente.

  Scenario:  El dueño de restaurante crea su cuenta
    Given que el dueño de restaurante se encuentra en la pantalla de crear una cuenta
    When ingresa sus datos personales y de su  restaurante
      | UserName | FirstName | LastName | Cellphone | Email             | Password        | Ruc         |
      | cintia12 | Cintia    | Rosales  | 912334411 | cintiar@gmail.com | Rosalescintia#1 | 12745621351 |
    Then el sistema guarda todos los datos registrados por el usuario
      | Name          |  Address    | PhoneNumber   |
      | El mero Loco  | Chorrillos  | 456789123     |