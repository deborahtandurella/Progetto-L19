import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ClientSportello {

    static int numeroSportello;
    static String tipo;
    static String stato;
    static String servizioOfferto;
    static String numeroClienteInServizio;

    public static void settaAttributi(String datiDaServer){
        StringTokenizer st = new StringTokenizer(datiDaServer);

        stato =  st.nextToken();
        servizioOfferto =  st.nextToken();
        numeroClienteInServizio = st.nextToken();

    }

    public static void main(String[] args) {

        String host = "192.168.1.4";

        numeroSportello=1;
        tipo="VARIABILE";

        try {
            Registry registry = LocateRegistry.getRegistry(host);
            TerminaleSportello stubSportello = (TerminaleSportello) registry.lookup("sportello");

            stubSportello.creaSportello(numeroSportello,tipo);

            System.out.println(numeroSportello);
        }catch (Exception e){
            e.printStackTrace();
        }

        Scanner tastieraSportello = new Scanner(System.in);

        while(true) {
            String comando= tastieraSportello.nextLine();
            String datiDaServer="blank";
            try {

                if(comando.equals("LIBERO")) {
                    Registry registry = LocateRegistry.getRegistry(host);
                    TerminaleSportello stubSportello = (TerminaleSportello) registry.lookup("sportello");

                    datiDaServer = stubSportello.changeStato(numeroSportello, StatoSportello.LIBERO);

                    settaAttributi(datiDaServer);

                }



                System.out.println(stato+"\n" +
                        servizioOfferto+"\n" +
                        numeroClienteInServizio);

            }catch (Exception e){
                e.printStackTrace();
            }


        }


    }


}