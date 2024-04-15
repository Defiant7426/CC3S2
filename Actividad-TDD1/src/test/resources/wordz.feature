Feature: Wordz

  Scenario: Un usuario ingresa una letra correcta
    Given que la palabra clave es GRANT
    When trato de adivinar la palabra con ARISE
    Then el estado de la letra R es correcto

  Scenario: Un usuario ingresa una letra incorrecta
    Given que la palabra clave es GRANT
    When trato de adivinar la palabra con ARISE
    Then el estado de la letra I es incorrecto

  Scenario: Un usuario ingresa una letra parcialmente correcta
    Given que la palabra clave es GRANT
    When trato de adivinar la palabra con ARISE
    Then el estado de la letra A es parcialmente correcta

    Scenario: sfds
      Given que la palabra clave es GRANT
      When trato de adivinar la palabra con GRANT
      Then la palabra fue adivinada