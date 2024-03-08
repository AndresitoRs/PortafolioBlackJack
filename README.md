# BlackJack con JavaFX

- - - - - - - - - - - - - - - - - - - - - - - - - - - - -
- - - - - - - - - - - - - - - - - - - - - - - - - - - - -
- - - - - - - - - - - - - - - - - - - - - - - - - - - - -
- - - - - - - - - - - - - - - - - - - - - - - - - - - - -

- Descripción
  
Un breve BlackJack desarrollado en JavaFX, se jugará solamente con una baraja, y no habrá comodines.

Tiene  distintos métodos dentro de su código, los cuales hacen que la lógica se divida en tres partes las cuales se verán en el apartado de uso

- Funcionamiento y uso:

Para dar comienzo a su funcionamiento debemos ejecutar el ejecutable JAR o la clase "BlackJackAPP"

- 1.1 : Iniciar juego:
  
  El usuario inicia su partida clicando en el botón jugar partiendo con 5 créditos iniciales para apostar, este recibe dos cartas y puede decidir si seguir jugando
  para sacar más, o plantarse con las que ya tiene, también se muestran dos cartas de la CPU, pero una estará dada la vuelta para no saber su puntuación, la decisión
  del usuario determinará las siguientes acciones:

        Plantarse: El jugador se planta con sus cartas y su puntuación se mantendra, de ahí su turno finalizará y comenzará el turno de la máquina, el cual redactaré a continuación en el siguiente paso.

        Pedir carta: EL jugador pide una carta arriesgandose a que su puntuación pase de 21, si pasa, pierde automáticamente, si no, puede pedir carta de nuevo,
                     volviendo a comenzar este método hasta que decida plantarse o su puntuación sobrepase 21.
  
- 1.2 : Turno de la máquina

   La máquina inicia su turno y sacará otra carta siempre que su puntuación sea menor que la del jugador, cómo al mismo jugador, le pueden ocurrir el caso de pasarse de 21, 
   lo cual terminaría en una derrota instanánea por pasarse, también se desvelará su carta inicial y se hará la suma de las puntuaciones de las correspondientes 
   de usuario y máquina para determinar el último paso.

- 1.3 : Fin de la partida

   La partida finalizará con los posibles casos:

        Derrotas inmediatas: El jugador o la máquina pierden automáticamente ya que su puntuación supera 21, se ganará o perderá un crédito si la consola se pasa, y se perderá uno si el usuario pierde.

        Victoria con Blackjack: Si el jugador consigue 21 puntos, gana automáticamente a no ser que la consola iguale su puntuación, en este caso, ganará 2 créditos para seguir apostando.

        Victoria: Si el jugador obtiene una puntuación mayor a la CPU, ganará un crédito.

        Empate técnico: Si el jugador y la CPU obtienen la misma puntuación, no ganará ni perderá créditos, si no que volverá a reiniciar un juego para apostar de nuevo.

        Derrota: El jugador perderá si la CPU consigue 21 puntos o una puntuación mayor que la suya al decidir plantarse, si esto ocurre, el jugador perderá un crédito para seguir apostando.

- 1.4: 0 créditos restantes

   Si el jugador se queda con 0 créditos, será redirigido al menú principal para reflexionar, ya que ha perdido su don de las apuestas, si decide volver a jugar,
   partirá de nuevo con 5 créditos






                                                                                                                                                  Andrés Rivada Sánchez, 08/03/2024, Portafolio UD6 Interfaces
      
 
