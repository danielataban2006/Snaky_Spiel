import java.awt.Point;
import java.util.Scanner;


public class Snake{
    public static void main(String[] args) {
        System.out.println("WELCOME---TO---ESCAPE---SNAKY");
        System.out.println();
        System.out.println("to move one step in your wished direction please enter: ");
        System.out.println("                                                        f for forward");
        System.out.println("                                                        b for backward");
        System.out.println("                                                        l for left");
        System.out.println("                                                        r for right");

        Point spielerPosition=new Point(4, 19);
        Point schlangePosition=new Point(29, 4);
        Point turPosition=new Point(0, 5);
        Point goldPosition=new Point(5,5);
        boolean goingon=true;
        boolean goldGesammelt=false;

        //Spielbrett:
        while (goingon){                                                 
            // zuerst zeichnen wir das Feld
            for (int y=0; y<20; y++){
                for (int x=0; x<40; x++){
                    if(x==(spielerPosition.getX()) && y==(spielerPosition.getY())){
                        System.out.print("P");
                    }else if(x==schlangePosition.getX() && y==schlangePosition.getY()){
                        System.out.print("S");
                    }else if(x==turPosition.getX() && y==turPosition.getY()&& !turPosition.equals(spielerPosition)){
                        System.out.print("T");
                    }else if(x==goldPosition.getX() && y==goldPosition.getY() && !goldGesammelt){
                        System.out.print("G");
                    }else{
                        System.out.print(".");
                    }
                }

            System.out.println("");
            }
            // bewegen und prüfen
            bewegeSpieler(spielerPosition);         // dann bewegen wir den Spieler
            bewegeSchlange(schlangePosition, spielerPosition);                   // dann bewegt sich die Schlange in Richtung des Spielers
            goingon=schlangefrisstSpielernicht(spielerPosition, schlangePosition);    // dann prüfen wir ob die Schlange nach dem Zug des Spielers diesen gegessen hat. 
            goldGesammelt=proofGoldsack(goldGesammelt, goldPosition, spielerPosition);
            if(turPosition.equals(spielerPosition) && goldGesammelt){      // dann prüfen wir ob der Spieler gewonnen hat, indem er das Gold gesammelt und schon an der Position der Tür steht
                goingon=false;
                System.out.println("---YOU---WIN---");
                break;
            }
        }
        System.out.println("");
        System.out.println("---TRY---AGAIN---?---");
        System.out.println("");
    }

    // Methode bewegeSpieler, die je nach Eingabe per Terminal den Spieler um ein Feld bewegt, gleichzeitig wird geprüft ob die Eingabe und ergebende Position gültig ist
    private static void bewegeSpieler(Point SpielerPosition) {
        // Bewege den Spieler
        Scanner scan=new Scanner(System.in);
        String eingabe=scan.next();
        switch (eingabe) {
            case "r" -> {
                if(SpielerPosition.getX()+1>39){
                    System.out.println("you can't escape the snake by going out of the field");
                }else{
                    SpielerPosition.x++;
                }
            }
            case "l" -> {
                if(SpielerPosition.getX()-1<0){
                    System.out.println("you can't escape the snake by going out of the field");
                }else{   
                    SpielerPosition.x--;
                }
            }
            case "b" -> {
                if(SpielerPosition.getY()+1>19){
                    System.out.println("you can't escape the snake by going out of the field");
                }else{
                    SpielerPosition.y++;
                }   
            }
            case "f" -> {
                if(SpielerPosition.getY()-1<0){
                    System.out.println("you can't escape the snake by going out of the field");
                }else{
                    SpielerPosition.y--;
                }   
            }
            default -> System.out.println("You can only enter r, l, b and f");
        }
    }

    private static void bewegeSchlange(Point schlangePosition, Point spielerPosition) {
        if(Math.pow(schlangePosition.getX()-spielerPosition.getX(),2)>Math.pow(schlangePosition.getY()-spielerPosition.getY(),2)){
            if(schlangePosition.getX()<spielerPosition.getX()){
                schlangePosition.x++;
            }else{
                schlangePosition.x--;
            }
        }else{
            if(schlangePosition.getY()<spielerPosition.getY()){
                schlangePosition.y++;
            }else{
                schlangePosition.y--;
            }
        }
    }

    private static boolean schlangefrisstSpielernicht(Point spielerPosition, Point schlangePosition) {
        if(schlangePosition.getX()==spielerPosition.getX() && schlangePosition.getY()==spielerPosition.getY()){
            System.out.println("the snake ate you");
            System.out.println("---GAME---OVER---");
            return false;
        }
        return true;
    }

    private static boolean proofGoldsack(boolean goldGesammelt, Point goldPosition, Point spielerPosition) {
        if(goldGesammelt){
            return true;
        }else{
            return goldPosition.equals(spielerPosition);
        }
    }
}