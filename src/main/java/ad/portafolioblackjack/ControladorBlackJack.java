package ad.portafolioblackjack;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


public class ControladorBlackJack implements Initializable {

    int creditos = 5;
    @FXML
    VBox fondo;
    @FXML
    Label puntuacionMaquina;
    @FXML
    Label puntuacionJugador;
    List<Carta> baraja;
    List<Carta> ordenador;
    List<Carta> jugador;
    @FXML
    Label cred;
    @FXML
    AnchorPane ordenadorLugar;
    @FXML
    AnchorPane jugadorLugar;
    @FXML
    VBox pararBox;
    @FXML
    VBox pedirBox;
    @FXML
    Button btnPlay = new Button();
    @FXML
    VBox opciones;
    @FXML
    Button btnRanking = new Button();
    @FXML
    Button btnParar;
    @FXML
    Button btnPedir;
    @FXML
    Button btnsalir = new Button();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mostrarOpciones();
    }

    public void iniciarPartida() {
        mostrarOpciones();

        opciones.getChildren().remove(1);

        btnPlay.setVisible(false);
        btnRanking.setVisible(false);

        this.baraja = new ArrayList<>();
        this.ordenador = new ArrayList<>();
        this.jugador = new ArrayList<>();
        this.crearBaraja();

        jugadorLugar.setVisible(true);
        ordenadorLugar.setVisible(true);
        pararBox.setVisible(true);
        pedirBox.setVisible(true);
        cred.setVisible(true);
        cred.setText("Creditos restantes actuales: " + creditos);
        puntuacionJugador.setVisible(true);
        puntuacionMaquina.setVisible(true);

        cred.setVisible(true);

        cartaJugador();
        cartaOrdenador();
        cartaJugador();
        cartaOrdenador();
    }
    private void limpiarTablero() {

        //Se limpia todo el tablero

        ordenadorLugar.getChildren().clear();
        jugadorLugar.getChildren().clear();
        opciones.getChildren().clear();

        btnPedir.setDisable(false);
        btnParar.setDisable(false);
    }


    //Deshace todos los cambios de la partida para poder empezar una nueva
    public void mostrarOpciones() {


        //Limpia el tablero

        limpiarTablero();

        //Se crean los botones de las opciones y se muestran

        Image imagePlay = new Image(Objects.requireNonNull(getClass().getResourceAsStream("iconos/play.png")));
        ImageView iconoPlay = new ImageView();
        Image imageRanking = new Image(Objects.requireNonNull(getClass().getResourceAsStream("iconos/ranking.png")));
        ImageView iconoRanking = new ImageView();

        iconoPlay.setImage(imagePlay);
        iconoPlay.setPreserveRatio(true);
        iconoPlay.setFitHeight(40);

        iconoRanking.setImage(imageRanking);
        iconoRanking.setPreserveRatio(true);
        iconoRanking.setFitHeight(40);

        opciones.getChildren().addAll(btnPlay, btnRanking);
        btnPlay.setGraphic(iconoPlay);
        btnPlay.setText("Jugar");
        btnPlay.setFont(Font.font("Stencil", 30));
        btnPlay.setContentDisplay(ContentDisplay.RIGHT);
        btnPlay.setOnMouseClicked(event -> iniciarPartida());

        opciones.setSpacing(40);
        btnRanking.setGraphic(iconoRanking);
        btnRanking.setText("Ranking");
        btnRanking.setFont(Font.font("Stencil", 30));
        btnRanking.setContentDisplay(ContentDisplay.RIGHT);
        btnRanking.setVisible(true);

        //Se oculta toda la información y botones de juego

        jugadorLugar.setVisible(false);
        ordenadorLugar.setVisible(false);
        pararBox.setVisible(false);
        pedirBox.setVisible(false);
        puntuacionJugador.setVisible(false);
        cred.setVisible(false);
        puntuacionMaquina.setVisible(false);

        //Se reinician puntuaciones

        puntuacionJugador.setText("Tu puntuación: 0");
        puntuacionMaquina.setText("Puntuación maquina: 0");

    }



    //Método para continuar cuando termina el turno del jugador
    public void terminarTurno() {


        Alert alertaganar = new Alert(Alert.AlertType.CONFIRMATION);
        Alert alertaperder = new Alert(Alert.AlertType.ERROR);
        Alert alertaempate = new Alert(Alert.AlertType.INFORMATION);

        alertaganar.setContentText("VICTORIA DEL JUGADOR, felicidades!!");
        alertaganar.setTitle("Felicidades!!");
        alertaganar.setHeaderText("Fue una magnífica victoria por su parte");
        alertaperder.setContentText("VICTORIA DE LA MÁQUINA, siempre habrá un siguiente intento...");
        alertaperder.setTitle("Lo sentimos...");
        alertaperder.setHeaderText("Fue una derrota, es lo que hay");
        alertaempate.setContentText("EMPATE!!! Es hora de otra partida");
        alertaempate.setTitle("Casi!!");
        alertaempate.setHeaderText("Fue un empate, vuelve a intentarlo con más ganas");


        if(puntuarJugador() == 21) {

            alertaganar.showAndWait();

            //Si el jugador gana con 21 se le suman dos creditos para seguir jugando
            cred.setText("Creditos restantes actuales: "+ (creditos + 2));
            creditos=creditos+2;

        } else if (puntuarJugador() > 21 ){

            alertaperder.showAndWait();
            cred.setText("Creditos restantes actuales: "+ (creditos + -1));
            creditos=creditos-1;

        } else {
            //Se voltea la primera carta de la máquina para comparar puntuaciones

            mostrarCartaOrdenador();
            puntuacionMaquina.setText("Puntuación maquina: " + puntuarOrdenador());

            //Comprobamos si la puntuación del jugador supera los 21 puntos

            if(puntuarOrdenador() > 21) {

                alertaganar.showAndWait();
                cred.setText("Creditos restantes actuales: "+ (creditos + 1));
                creditos=creditos+1;


            } else if (puntuarOrdenador() == puntuarJugador()) {

                //Falta comprobación de que el ordenador tenga puntuación y sea > 17

                alertaempate.showAndWait();
                cred.setText("Creditos restantes actuales: "+ (creditos));

            } else {

                //Turno de la máquina

                //Si la máquina tiene menos de 17 puntos y menos puntos que el jugador pedirá una carta

                while (puntuarOrdenador() < 17 && puntuarOrdenador() < puntuarJugador()) {
                    cartaOrdenador();
                }

                //La máquina ya tiene por lo menos 17 puntos y supera la puntuación del jugador

                puntuacionMaquina.setText("Puntuación maquina: " + puntuarOrdenador());

                //Comprobamos si la máquina tiene una puntuación superior a 21

                if (puntuarOrdenador() > 21) {
                    //Es superior a 21 (Victoria)
                    alertaganar.showAndWait();
                    cred.setText("Creditos restantes actuales: "+ (creditos + 1));
                    creditos=creditos+1;

                } else if (puntuarOrdenador() == puntuarJugador()) {

                    alertaempate.showAndWait();

                } else if (puntuarOrdenador() > puntuarJugador()){

                    alertaperder.showAndWait();
                    cred.setText("Creditos restantes actuales: "+ (creditos - 1));
                    creditos=creditos-1;
                } else {
                    alertaganar.showAndWait();
                    cred.setText("Creditos restantes actuales: "+ (creditos + 1));
                    creditos=creditos+1;
                }
            }
        }
        terminarPartida();

        if(creditos <= 0) {
            Alert alerta0cred = new Alert(Alert.AlertType.INFORMATION);
            alerta0cred.setTitle("Te quedaste sin créditos pendejo");
            alerta0cred.setContentText("0 créditos restantes, se acabó el juego");
            alerta0cred.setTitle("Banca rota");
            alerta0cred.showAndWait();
            mostrarOpciones();
            creditos = 5;
        }
    }

    //Método que bloquea cualquier acción excepto volver a jugar tras terminar la partida
    private void terminarPartida() {


        //Se muestra los botones que permiten reiniciar la partida o salir

        Image imageRestart = new Image(Objects.requireNonNull(getClass().getResourceAsStream("iconos/restart.png")));
        ImageView iconoRestart = new ImageView();

        iconoRestart.setImage(imageRestart);
        iconoRestart.setPreserveRatio(true);
        iconoRestart.setFitHeight(40);

        opciones.getChildren().add(btnsalir);
        btnsalir.setGraphic(iconoRestart);
        btnsalir.setText("Salir al menú");
        btnsalir.setFont(Font.font("Stencil", 30));
        btnsalir.setContentDisplay(ContentDisplay.RIGHT);
        btnsalir.setOnMouseClicked(event -> mostrarOpciones());

        btnPlay.setText("Jugar de nuevo");
        btnPlay.setVisible(true);

        //Se borran todos los mazos y la baraja

        ordenador.clear();
        jugador.clear();

        //Se deshabilitan los botones de juego

        btnPedir.setDisable(true);
        btnParar.setDisable(true);
    }


    //Método para voltear la primera carta de lá máquina
    private void mostrarCartaOrdenador() {
        ImageView im = (ImageView) ordenadorLugar.getChildren().get(0);
        Carta c = ordenador.get(0);
        Image img = new Image(Objects.requireNonNull(getClass().getResourceAsStream("cartas/" + c.getImagen() + ".png")));
        im.setImage(img);
    }

    //Método para crear una baraja
    public void crearBaraja() {
        char[] palos = {'C', 'T', 'P', 'D'};
        String[] nombres = {"AS", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        int valor;

        for (char palo : palos) {
            for (int i = 0; i < 13; i++) {
                valor = (i >= 10) ? 10 : i + 1;
                Carta carta = new Carta(palo, nombres[i], valor);
                this.baraja.add(carta);
            }
        }
    }

    //Genera una carta aleatoria para ser entregada
    public Carta sacarCarta() {
        Carta carta = null;
        Random aleatorio = new Random(System.currentTimeMillis());
        boolean control = true;
        while (control) {
            carta = this.baraja.get(aleatorio.nextInt(52));
            if (!carta.isRepartida()) {
                carta.setRepartida(true);
                control = false;
            }
        }

        return carta;
    }

    public void cartaJugador() {
        this.jugador.add(this.sacarCarta());
        Carta ultimaCarta = this.jugador.get(this.jugador.size() - 1);
        String cartaSacada = ultimaCarta.getImagen();

        Image img = new Image(Objects.requireNonNull(getClass().getResourceAsStream("cartas/" + cartaSacada + ".png")));
        ImageView image = new ImageView();

        image.setImage(img);
        image.setPreserveRatio(true);
        image.setFitHeight(200);
        image.setLayoutX(60 * (this.jugador.size() - 1));
        image.setEffect(new DropShadow(20, Color.BLACK));
        jugadorLugar.getChildren().add(image);

        //Si el jugador recibe un as pasa esto...

        if (ultimaCarta.getValor() == 1) {

            //Comprobamos que la puntuacion se pasa de 21 al sumar 11

            if (puntuarJugador() + 10 <= 21) {

                //Si la puntuacion no se pasa de 21 al sumar 11, se cambia el valor a 11

                ultimaCarta.setValor(11);
            }
            //Si la puntuacion se pasa de 21 al sumar 11 no hacemos nada :)
        }
        puntuarJugador();

        //Si la puntuación del jugador supera los 20 puntos, termina el turno automáticamente
        if (puntuarJugador() >= 21) {
            terminarTurno();
        }

    }

    //Método para repartir una carta al ordenador
    public void cartaOrdenador() {
        this.ordenador.add(this.sacarCarta());
        Carta ultimaCarta = this.ordenador.get(this.ordenador.size() - 1);
        String cartaSacada;

        //Pone la primera carta de la máquina boca abajo
        if (this.ordenador.size() == 1) {
            cartaSacada = "Trasera";
        } else {
            cartaSacada = ultimaCarta.getImagen();
        }

        Image img = new Image(Objects.requireNonNull(getClass().getResourceAsStream("cartas/" + cartaSacada + ".png")));
        ImageView image = new ImageView();

        image.setImage(img);
        image.setPreserveRatio(true);
        image.setFitHeight(200);
        image.setLayoutX(60 * (this.ordenador.size() - 1));
        image.setEffect(new DropShadow(20, Color.BLACK));

        ordenadorLugar.getChildren().add(image);

        //Si el ordenador recibe un as pasa esto...

        if (ultimaCarta.getValor() == 1) {

            //Comprobamos que la puntuacion se pasa de 21 al sumar 11

            if (puntuarJugador() + 10 <= 21) {

                //Si la puntuacion no se pasa de 21 al sumar 11, se cambia el valor a 11

                ultimaCarta.setValor(11);

            }

            //Si la puntuacion se pasa de 21 al sumar 11 no hacemos nada
        }
    }
    public int puntuarOrdenador() {
        AtomicInteger suma = new AtomicInteger();
        this.ordenador.forEach(carta -> {
            suma.addAndGet(carta.getValor());
        });
        return suma.intValue();
    }

    public int puntuarJugador() {
        AtomicInteger suma = new AtomicInteger();
        this.jugador.forEach(carta -> {
            suma.addAndGet(carta.getValor());
        });
        puntuacionJugador.setText("Tu puntuación: " + suma);
        return suma.intValue();
    }

    //Getters & Setters
    public List<Carta> getBaraja() {
        return baraja;
    }

    public void setBaraja(List<Carta> baraja) {
        this.baraja = baraja;
    }

    public List<Carta> getOrdenador() {
        return ordenador;
    }

    public void setOrdenador(List<Carta> ordenador) {
        this.ordenador = ordenador;
    }

    public List<Carta> getJugador() {
        return jugador;
    }

    public void setJugador(List<Carta> jugador) {
        this.jugador = jugador;
    }
}